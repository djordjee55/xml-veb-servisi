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
import com.tim123.vaccinationportal.service.RDFService;
import com.tim123.vaccinationportal.util.HTMLTransformer;
import com.tim123.vaccinationportal.util.PDFTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

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
            if(termin == null)
                interesovanjePrimljenoEmail(i, email);
            else
                interesovanjePrimljenoEmail(i, email, termin);

            rdfService.extractMetadata(interesovanje, Interesovanje.class, interesovanjePath);
            return i;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lose interesovanje");
        }
    }

    private String dobaviInteresovanje2() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Interesovanje\n" +
                "         xmlns=\"http://www.xws.org/interesovanje\"\n" +
                "         xmlns:tip=\"http://www.xws.org/tipovi\"\n" +
                "         xmlns:vc=\"http://www.w3.org/2007/XMLSchema-versioning\"\n" +
                "         xmlns:xs=\"http://www.w3.org/2001/XMLSchema#\"\n" +
                "         xmlns:pred=\"http://www.xws.org/vacc/#\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://www.xws.org/interesovanje file:../xsd/interesovanje.xsd\"\n" +
                "         id=\"99350e11-8a92-4c53-a55d-bc4545b02e90\"\n" +
                "         about=\"http://www.xws.org/interesovanje#99350e11-8a92-4c53-a55d-bc4545b02e90\">\n" +
                "    <Datum property=\"pred:datumIzdavanja\" datatype=\"xs:date\">2006-05-04</Datum>\n" +
                "    <Drzavljanstvo>\n" +
                "        <Drzavljanin_Republike_Srbije property=\"pred:drzavljanin\" datatype=\"xs:string\">Drzavljanin_Republike_Srbije</Drzavljanin_Republike_Srbije>\n" +
                "    </Drzavljanstvo>\n" +
                "    <Zeljena_opstina_vakcinacije>Zeljena_opstina_vakcinacije0</Zeljena_opstina_vakcinacije>\n" +
                "    <Zeljena_vakcina>\n" +
                "        <Sputnik_V property=\"pred:zeljenaVakcina\" datatype=\"xs:string\">Sputnik_V</Sputnik_V>\n" +
                "        <Moderna property=\"pred:zeljenaVakcina\" datatype=\"xs:string\">Moderna</Moderna>\n" +
                "    </Zeljena_vakcina>\n" +
                "    <Dobrovoljni_davalac_krvi property=\"pred:davalacKrvi\" datatype=\"xs:boolean\">false</Dobrovoljni_davalac_krvi>\n" +
                "    <Primalac>\n" +
                "        <tip:Ime>Ime0</tip:Ime>\n" +
                "        <tip:Prezime>Prezime0</tip:Prezime>\n" +
                "        <tip:Datum_rodjenja>2006-05-04</tip:Datum_rodjenja>\n" +
                "        <tip:Kontakt>\n" +
                "                <tip:E_mail>djordjenjegic@email.com</tip:E_mail>\n" +
                "                <tip:Fiksni_telefon>023456789</tip:Fiksni_telefon>\n" +
                "                <tip:Mobilni_telefon>060123456789</tip:Mobilni_telefon>\n" +
                "        </tip:Kontakt>\n" +
                "        <tip:JMBG rel=\"pred:identifikatorKorisnika\" href=\"http://www.xws.org/korisnici#921909aa-8ff1-47f4-b761-4cbac06929ad\">0000000000000</tip:JMBG>\n" +
                "    </Primalac>\n" +
                "</Interesovanje>\n";
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
        //Interesovanje interesovanje = dobaviInteresovanje(id);
        //return htmlTransformer.generateHTML(interesovanje.toString(), Interesovanje.class);
        return htmlTransformer.generateHTML(dobaviInteresovanje2(), Interesovanje.class);
    }

    @Override
    public ByteArrayInputStream generisiPDF(String id) {
        //Interesovanje interesovanje = dobaviInteresovanje(id);
        //return pdfTransformer.generatePDF(interesovanje.toString(), Interesovanje.class);
        return pdfTransformer.generatePDF(dobaviInteresovanje2(), Interesovanje.class);
    }

    @Override
    public int prebrojInteresovanjaZaPeriod(String startDate, String endDate) throws ParseException {

        List<Interesovanje> interesovanja =  interesovanjeRepository.findAll();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar intervalStart = Calendar.getInstance();
        intervalStart.setTime(formatter.parse(startDate));
        Calendar intervalEnd = Calendar.getInstance();
        intervalStart.setTime(formatter.parse(endDate));

        int numberOfDocumentsInPeriod = 0;
        
        for (Interesovanje interesovanje : interesovanja) {

            Calendar documentDate = interesovanje.getDatum().getValue().toGregorianCalendar();
            if (documentDate.compareTo(intervalStart) > 0 && documentDate.compareTo(intervalEnd) < 0) {
                numberOfDocumentsInPeriod++;
            }

        }
        return numberOfDocumentsInPeriod;
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
        tZainteresovanoLice.setKontakt(interesovanje.getPrimalac().getKontakt());
        interesovanje.setPrimalac(tZainteresovanoLice);
    }

    private void interesovanjePrimljenoEmail(Interesovanje interesovanje, String email) {
//        emailService.sendEmail("", email, "Interesovanje primljeno", "Ovo se mora srediti");
    }

    private void interesovanjePrimljenoEmail(Interesovanje interesovanje, String email, Termin termin) {
//        emailService.sendEmail("", email, "Interesovanje primljeno sa terminom", "Ovo se mora srediti");
    }
}
