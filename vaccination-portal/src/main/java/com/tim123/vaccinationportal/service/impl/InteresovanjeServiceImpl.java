package com.tim123.vaccinationportal.service.impl;

import com.tim123.vaccinationportal.clients.TerminClient;
import com.tim123.vaccinationportal.exception.InterestAlreadyExists;
import com.tim123.vaccinationportal.model.Korisnik;
import com.tim123.vaccinationportal.model.interesovanje.Interesovanje;
import com.tim123.vaccinationportal.model.termin.Termin;
import com.tim123.vaccinationportal.model.tipovi.TCJMBG;
import com.tim123.vaccinationportal.model.tipovi.TZainteresovanoLice;
import com.tim123.vaccinationportal.repository.CRUDRepository;
import com.tim123.vaccinationportal.repository.InteresovanjeRepository;
import com.tim123.vaccinationportal.service.InteresovanjeService;
import com.tim123.vaccinationportal.service.KorisnikService;
import com.tim123.vaccinationportal.service.MarshallUnmarshallService;
import com.tim123.vaccinationportal.service.RDFService;
import com.tim123.vaccinationportal.util.HTMLTransformer;
import com.tim123.vaccinationportal.util.PDFTransformer;
import com.tim123.vaccinationportal.util.SearchUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.bind.JAXBException;
import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.tim123.vaccinationportal.util.Constants.interesovanjePath;

@Service
@RequiredArgsConstructor
public class InteresovanjeServiceImpl extends CRUDServiceImpl<Interesovanje> implements InteresovanjeService {

    private final InteresovanjeRepository interesovanjeRepository;
    private final RDFService rdfService;
    private final KorisnikService korisnikService;
    private final PDFTransformer pdfTransformer;
    private final HTMLTransformer htmlTransformer;
    private final EmailService emailService;
    private final TerminClient terminClient;
    private final MarshallUnmarshallService<Interesovanje> marshallUnmarshallService;
    private final SearchUtil searchUtil;

    @Override
    protected CRUDRepository<Interesovanje> getRepository() {
        return interesovanjeRepository;
    }

    @Override
    public Interesovanje dodajInteresovanje(Interesovanje interesovanje, String email) {
        postaviInfoOKorisniku(interesovanje, email);
        if (interesovanjeRepository.findForUser(korisnikService.findByEmail(email).getJmbg()) != null)
            throw new InterestAlreadyExists(String.format("Korisnik %s je vec podneo interesovanje!", email));

        try {
            var i = this.save(interesovanje);
            Termin termin = terminClient.dodeliTermin(i, email);
            if (termin == null)
                interesovanjePrimljenoEmail(i, email);
            else
                interesovanjePrimljenoEmail(i, email, termin);

            rdfService.extractMetadata(interesovanje, Interesovanje.class, interesovanjePath);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lose interesovanje");
        }
    }

    @Override
    public Interesovanje dobaviInteresovanje(String id) {
        var interesovanje = this.findById(id);
        if (interesovanje.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Interesovanje nije pronadjeno");
        }
        return interesovanje.get();
    }

    @Override
    public ByteArrayInputStream generisiHTML(String id) {
        Interesovanje interesovanje = dobaviInteresovanje(id);
        try {
            return htmlTransformer.generateHTML(marshallUnmarshallService.marshall(interesovanje, Interesovanje.class), Interesovanje.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ByteArrayInputStream generisiPDF(String id) {
        Interesovanje interesovanje = dobaviInteresovanje(id);
        try {
            return pdfTransformer.generatePDF(marshallUnmarshallService.marshall(interesovanje, Interesovanje.class), Interesovanje.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int prebrojInteresovanjaZaPeriod(String startDate, String endDate) throws ParseException {

        List<Interesovanje> interesovanja = interesovanjeRepository.findAll();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate intervalStart = LocalDate.parse(startDate, formatter);
        LocalDate intervalEnd = LocalDate.parse(endDate, formatter);

        int numberOfDocumentsInPeriod = 0;

        for (Interesovanje interesovanje : interesovanja) {

            LocalDate documentDate = interesovanje.getDatum().getValue().toGregorianCalendar().toZonedDateTime().toLocalDate();

            if (documentDate.compareTo(intervalStart) > 0 && documentDate.compareTo(intervalEnd) < 0) {
                numberOfDocumentsInPeriod++;
            }

        }
        return numberOfDocumentsInPeriod;
    }

    @Override
    public Boolean dozvoljenoDodavanje(String name) {
        return interesovanjeRepository.findForUser(korisnikService.findByEmail(name).getJmbg()) == null;
    }

    @Override
    public Interesovanje dobaviZaKorisnika(String email) {
        return interesovanjeRepository.findForUserEmail(email);
    }

    @Override
    public String searchByString(String searchedString) {

        List<Interesovanje> interesovanja = interesovanjeRepository.findAll();

        interesovanja = interesovanja.stream().filter(interesovanje -> interesovanje.getId() != null).collect(Collectors.toList());

        List<String> interesovanjaConverted = interesovanja.stream().map(interesovanje -> {
            try {
                return marshallUnmarshallService.marshall(interesovanje, Interesovanje.class);
            } catch (JAXBException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList());

        return searchUtil.parseSearchResult(interesovanjaConverted, "interesovanje", searchedString);
    }

    private void postaviInfoOKorisniku(Interesovanje interesovanje, String email) {
        Korisnik korisnik = korisnikService.findByEmail(email);
        TCJMBG tcjmbg = new TCJMBG();
        tcjmbg.setValue(korisnik.getJmbg());
        tcjmbg.setRel("pred:identifikatorKorisnika");
        tcjmbg.setHref(String.format("http://www.xws.org/korisnici#%s", korisnik.getId()));

        TZainteresovanoLice tZainteresovanoLice = new TZainteresovanoLice();
        tZainteresovanoLice.setJMBG(tcjmbg);
        tZainteresovanoLice.setIme(korisnik.getIme());
        tZainteresovanoLice.setPrezime(korisnik.getPrezime());
        tZainteresovanoLice.setDatumRodjenja(korisnik.getDatumRodjenja());
        interesovanje.getPrimalac().getKontakt().setEMail(email);
        tZainteresovanoLice.setKontakt(interesovanje.getPrimalac().getKontakt());
        interesovanje.setPrimalac(tZainteresovanoLice);
        interesovanje.setDrzavljanstvo(postaviDrzavljanstvo(korisnik));
    }

    private Interesovanje.Drzavljanstvo postaviDrzavljanstvo(Korisnik korisnik) {
        Interesovanje.Drzavljanstvo drzavljanstvo = new Interesovanje.Drzavljanstvo();
        switch (korisnik.getDrzavljanstvo()) {
            case DRZAVLJANIN_REPUBLIKE_SRBIJE: {
                drzavljanstvo.setDrzavljaninRepublikeSrbije(new Interesovanje.Drzavljanstvo.DrzavljaninRepublikeSrbije());
                break;
            }
            case STRANI_DRZAVLJANIN_BEZ_BORAVKA_U_RS: {
                drzavljanstvo.setStraniDrzavljaninBezBoravkaURS(new Interesovanje.Drzavljanstvo.StraniDrzavljaninBezBoravkaURS());
                break;
            }
            case STRANI_DRZAVLJANIN_SA_BORAVKOM_U_RS: {
                drzavljanstvo.setStraniDrzavljaninSaBoravkomURS(new Interesovanje.Drzavljanstvo.StraniDrzavljaninSaBoravkomURS());
                break;
            }
        }

        return drzavljanstvo;
    }

    private void interesovanjePrimljenoEmail(Interesovanje interesovanje, String email) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Postovani,\nUspesno smo primili Vase interesovanje za vakcinaciju.\n");
        stringBuilder.append("\nDobicete obavestenje cim pronadjemo slobodan termin za Vas.\n\n");
        stringBuilder.append("Vase informacije koje ste uneli:\n\n");
        stringBuilder.append("\tZeljena opstina vakcinacije: ").append(interesovanje.getZeljenaOpstinaVakcinacije());
        stringBuilder.append("\n\tOdabrane vakcine:\n");
        for (var o : interesovanje.getZeljenaVakcina().getPfizerBioNTechOrSputnikVOrSinopharm()) {
            stringBuilder.append("\n\t\t").append(o.toString());
        }
        stringBuilder.append("\nDobrovoljni davalac krvi: ").append(interesovanje.getDobrovoljniDavalacKrvi().isValue() ? "Da" : "Ne");
        stringBuilder.append("\n\n\nSrdacan pozdrav,\nSistem za imunizaciju");

        emailService.sendEmail("", email, "Interesovanje primljeno", stringBuilder.toString());
    }

    private void interesovanjePrimljenoEmail(Interesovanje interesovanje, String email, Termin termin) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Postovani,\nUspesno smo primili Vase interesovanje za vakcinaciju.\n");
        stringBuilder.append("\nVas termin je: ").append(termin.getDatumVreme()).append(" u zdravstvenoj ustanovi: ").append(termin.getUstanova()).append("\n\n");
        stringBuilder.append("Vase informacije koje ste uneli:\n\n");
        stringBuilder.append("\tZeljena opstina vakcinacije: ").append(interesovanje.getZeljenaOpstinaVakcinacije());
        stringBuilder.append("\n\tOdabrane vakcine:");
        for (var o : interesovanje.getZeljenaVakcina().getPfizerBioNTechOrSputnikVOrSinopharm()) {
            String vakcina = Arrays.stream(Arrays.stream(o.toString().split("\\$")).collect(Collectors.toList()).get(2).split("@")).collect(Collectors.toList()).get(0);
            stringBuilder.append("\n\t\t").append(vakcina);
        }
        stringBuilder.append("\n\tDobrovoljni davalac krvi: ").append(interesovanje.getDobrovoljniDavalacKrvi().isValue() ? "Da" : "Ne");
        stringBuilder.append("\n\n\nSrdacan pozdrav,\nSistem za imunizaciju");
        emailService.sendEmail("", email, "Interesovanje primljeno", stringBuilder.toString());
    }
}
