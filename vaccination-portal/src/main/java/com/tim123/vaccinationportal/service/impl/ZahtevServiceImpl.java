package com.tim123.vaccinationportal.service.impl;

import com.tim123.vaccinationportal.model.interesovanje.Interesovanje;
import com.tim123.vaccinationportal.model.zahtev.Zahtev;
import com.tim123.vaccinationportal.repository.CRUDRepository;
import com.tim123.vaccinationportal.repository.ZahtevRepository;
import com.tim123.vaccinationportal.service.KorisnikService;
import com.tim123.vaccinationportal.service.RDFService;
import com.tim123.vaccinationportal.service.ZahtevService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static com.tim123.vaccinationportal.util.Constants.zahtevPath;

@Service
@RequiredArgsConstructor
public class ZahtevServiceImpl extends CRUDServiceImpl<Zahtev> implements ZahtevService {

    private final ZahtevRepository zahtevRepository;
    private final KorisnikService korisnikService;
    private final EmailService emailService;
    private final RDFService rdfService;

    @Override
    protected CRUDRepository<Zahtev> getRepository() {
        return zahtevRepository;
    }

    @Override
    public Zahtev dodajZahtev(Zahtev zahtev) {
        //provera da li je primio dve doze
        try {
            var i = this.save(zahtev);
            rdfService.extractMetadata(zahtev, Zahtev.class, zahtevPath);
            return i;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Los zahtev");
        }
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
    public void odbijZahtev(String requestId, String reason) {
        Zahtev zahtev = this.findById(requestId).orElse(null);
        if (zahtev == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Zahtev nije pronadjen");
        }

        var jmbg = zahtev.getPodnosilac().getJMBG().getValue();
        var pasos = zahtev.getPodnosilac().getBrojPasosa().getValue();

        var k = korisnikService.findByDocumentId(jmbg, pasos);

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

    private void odbijZahtevEmail(String email, String reason) {
        var msg = String.format("Poštovani,\n\n" +
                "Vaš zahtev za izdavanje digitalnog zelenog sertifikata je odbijen.\n\n" +
                "Razlog:\n" +
                "%s\n\n" +
                "Pozdrav\n", reason);
        emailService.sendEmail("", email, "Odbijen zahtev", msg);
    }
}
