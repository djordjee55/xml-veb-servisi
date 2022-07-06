package com.tim123.vaccinationportal.service.impl;

import com.tim123.vaccinationportal.model.Korisnik;
import com.tim123.vaccinationportal.model.sertifikat.Sertifikat;
import com.tim123.vaccinationportal.model.tipovi.TCBrojPasosa;
import com.tim123.vaccinationportal.model.tipovi.TCJMBG;
import com.tim123.vaccinationportal.model.tipovi.TVakcinisanoLice;
import com.tim123.vaccinationportal.model.zahtev.Zahtev;
import com.tim123.vaccinationportal.repository.CRUDRepository;
import com.tim123.vaccinationportal.repository.ZahtevRepository;
import com.tim123.vaccinationportal.service.*;
import com.tim123.vaccinationportal.util.HTMLTransformer;
import com.tim123.vaccinationportal.util.PDFTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.bind.JAXBException;
import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import static com.tim123.vaccinationportal.util.Constants.zahtevPath;

@Service
@RequiredArgsConstructor
public class ZahtevServiceImpl extends CRUDServiceImpl<Zahtev> implements ZahtevService {

    private final ZahtevRepository zahtevRepository;
    private final KorisnikService korisnikService;
    private final EmailService emailService;
    private final SertifikatService sertifikatService;
    private final RDFService rdfService;
    private final PDFTransformer pdfTransformer;
    private final HTMLTransformer htmlTransformer;
    private final MarshallUnmarshallService<Zahtev> marshallUnmarshallService;
    private final RestTemplate restTemplate;

    @Override
    protected CRUDRepository<Zahtev> getRepository() {
        return zahtevRepository;
    }

    @Override
    public Zahtev dodajZahtev(Zahtev zahtev, String email) {
        //provera da li je primio dve doze
        //TODO

        Korisnik korisnik = korisnikService.findByEmail(email);
        dodajInfoOKorisniku(zahtev, korisnik);

        try {
            var i = this.save(zahtev);
            rdfService.extractMetadata(zahtev, Zahtev.class, zahtevPath);
            return i;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Los zahtev");
        }
    }

    private void dodajInfoOKorisniku(Zahtev zahtev, Korisnik korisnik) {
        TCBrojPasosa pasos = new TCBrojPasosa();
        pasos.setValue(korisnik.getPasos());
        TCJMBG jmbg = new TCJMBG();
        jmbg.setValue(korisnik.getJmbg());
        TVakcinisanoLice podnosilac = new TVakcinisanoLice();


        podnosilac.setBrojPasosa(pasos);
        podnosilac.setJMBG(jmbg);
        podnosilac.setPol(korisnik.getPol());
        podnosilac.setIme(korisnik.getIme());
        podnosilac.setPrezime(korisnik.getPrezime());
        podnosilac.setDatumRodjenja(korisnik.getDatumRodjenja());

        zahtev.setPodnosilac(podnosilac);
    }

    @Override
    public Zahtev dobaviZahtev(String id) {

        var zahtev = this.findById(id);
        if (zahtev.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Zahtev nije pronadjen");
        }
        return zahtev.get();
    }

    @Override
    public int prebrojZahteveZaPeriod(String startDate, String endDate) {

        List<Zahtev> zahtevi = zahtevRepository.findAll();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate intervalStart = LocalDate.parse(startDate, formatter);
        LocalDate intervalEnd = LocalDate.parse(endDate, formatter);

        int numberOfDocumentsInPeriod = 0;

        for (Zahtev zahtev : zahtevi) {

            LocalDate documentDate = zahtev.getDatum().getValue().toGregorianCalendar().toZonedDateTime().toLocalDate();

            if (documentDate.compareTo(intervalStart) > 0 && documentDate.compareTo(intervalEnd) < 0) {
                numberOfDocumentsInPeriod++;
            }

        }
        return numberOfDocumentsInPeriod;
    }

    @Override
    public ByteArrayInputStream generisiHTML(String id) {
        Zahtev zahtev = dobaviZahtev(id);
        try {
            return htmlTransformer.generateHTML(marshallUnmarshallService.marshall(zahtev, Zahtev.class), Zahtev.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ByteArrayInputStream generisiPDF(String id) {
        Zahtev zahtev = dobaviZahtev(id);
        try {
            return pdfTransformer.generatePDF(marshallUnmarshallService.marshall(zahtev, Zahtev.class), Zahtev.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Zahtev> dobaviZaKorisnika(String email) {
        return zahtevRepository.getForUserEmail(email);
    }
    public void odbijZahtev(String requestId, String reason) {
        Zahtev zahtev = this.findById(requestId).orElse(null);
        if (zahtev == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Zahtev nije pronadjen");
        }
        if (zahtev.isObradjen()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Zahtev je vec obradjen");
        }
        var k = dobaviKorisnikaZahteva(zahtev);
        if (k == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Korisnik nije pronadjen");
        }
        var email = k.getEmail();

        zahtev.setObradjen(true);
        try {
            zahtevRepository.save(zahtev);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nije moguce sacuvati zahtev");
        }

        // Posalji mejl da je zahtev odbijen
        odbijZahtevEmail(email, reason);
    }

    @Override
    public void prihvatiZahtev(String requestId) {
        Zahtev zahtev = this.findById(requestId).orElse(null);
        if (zahtev == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Zahtev nije pronadjen");
        }
        if (zahtev.isObradjen()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Zahtev je vec obradjen");
        }
        var k = dobaviKorisnikaZahteva(zahtev);
        if (k == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Korisnik nije pronadjen");
        }
        var email = k.getEmail();

        var pdf = generisiPdf(zahtev);
        var html = generisiHTML(zahtev);

        if (pdf == null || html == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greska prilikom generisanja dokumenata");
        }

        Sertifikat s = kreirajSertifikat(zahtev);
        if (s != null) {
            zahtev.setObradjen(true);
            var sPdf = sertifikatService.generisiPDF(s);
            var sHtml = sertifikatService.generisiHTML(s);
            try {
                posaljiSertifikatMail(email, sPdf, sHtml, pdf, html);
                zahtevRepository.save(zahtev);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greska prilikom cuvanja zahteva");
            }
        }
    }

    @Override
    public Korisnik dobaviKorisnikaZahteva(Zahtev zahtev) {
        if (zahtev == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Zahtev nije pronadjen");
        }

        var jmbg = zahtev.getPodnosilac().getJMBG().getValue();
        var pasos = zahtev.getPodnosilac().getBrojPasosa().getValue();

        return korisnikService.findByDocumentId(jmbg, pasos);
    }

    @Override
    public ByteArrayInputStream generisiPdf(Zahtev zahtev) {
        try {
            return pdfTransformer.generatePDF(marshallUnmarshallService.marshall(zahtev, Zahtev.class), Zahtev.class);
        } catch (JAXBException e) {
            return null;
        }
    }

    @Override
    public ByteArrayInputStream generisiHTML(Zahtev zahtev) {
        try {
            return htmlTransformer.generateHTML(marshallUnmarshallService.marshall(zahtev, Zahtev.class), Zahtev.class);
        } catch (JAXBException e) {
            return null;
        }
    }

    private void odbijZahtevEmail(String email, String reason) {
        var msg = String.format("Poštovani,\n\n" +
                "Vaš zahtev za izdavanje digitalnog zelenog sertifikata je odbijen.\n\n" +
                "Razlog:\n" +
                "%s\n\n" +
                "Srdacan pozdrav,\n" +
                "Sistem za imunizaciju", reason);
        emailService.sendEmail("", email, "Odbijen zahtev", msg);
    }

    private void posaljiSertifikatMail(String email, ByteArrayInputStream sPdf, ByteArrayInputStream sHtml,
                                       ByteArrayInputStream zPdf, ByteArrayInputStream zHtml) {
        var msg =
                "Poštovani,\n\n" +
                "Vaš zahtev za izdavanje digitalnog zelenog sertifikata je prihvaćen.\n\n" +
                "Srdacan pozdrav,\n" +
                "Sistem za imunizaciju";
        var files = new ByteArrayInputStream[] {sPdf, sHtml, zPdf, zHtml};
        var names = new String[] {"sertifikat.pdf", "sertifikat.html", "zahtev.pdf", "zahtev.html"};
        try {
            emailService.sendEmailWithAttachment("", email, "Prihvacen zahtev", msg, files, names);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greska prilikom slanja mejla");
        }
    }

    private Sertifikat kreirajSertifikat(Zahtev zahtev) {
        ResponseEntity<Sertifikat> sertifikat = restTemplate.postForEntity(
                "http://localhost:8081/api/sertifikat/",
                zahtev.getPodnosilac(),
                Sertifikat.class);
        return Objects.requireNonNull(sertifikat.getBody());
    }
}
