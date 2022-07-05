package com.tim123.vaccinationportal.service.impl;

import com.tim123.vaccinationportal.model.Korisnik;
import com.tim123.vaccinationportal.model.interesovanje.Interesovanje;
import com.tim123.vaccinationportal.model.saglasnost.Saglasnost;
import com.tim123.vaccinationportal.model.tipovi.TCBrojPasosa;
import com.tim123.vaccinationportal.model.tipovi.TCJMBG;
import com.tim123.vaccinationportal.model.tipovi.TVakcinisanoLice;
import com.tim123.vaccinationportal.model.zahtev.Zahtev;
import com.tim123.vaccinationportal.repository.CRUDRepository;
import com.tim123.vaccinationportal.repository.ZahtevRepository;
import com.tim123.vaccinationportal.service.KorisnikService;
import com.tim123.vaccinationportal.service.MarshallUnmarshallService;
import com.tim123.vaccinationportal.service.RDFService;
import com.tim123.vaccinationportal.service.ZahtevService;
import com.tim123.vaccinationportal.util.HTMLTransformer;
import com.tim123.vaccinationportal.util.PDFTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.bind.JAXBException;
import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import static com.tim123.vaccinationportal.util.Constants.zahtevPath;

@Service
@RequiredArgsConstructor
public class ZahtevServiceImpl extends CRUDServiceImpl<Zahtev> implements ZahtevService {

    private final ZahtevRepository zahtevRepository;
    private final RDFService rdfService;
    private final KorisnikService korisnikService;
    private final PDFTransformer pdfTransformer;
    private final HTMLTransformer htmlTransformer;
    private final MarshallUnmarshallService<Zahtev> marshallUnmarshallService;

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
    public int prebrojZahteveZaPeriod(String startDate, String endDate) throws ParseException {

        List<Zahtev> zahtevi =  zahtevRepository.findAll();

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
}
