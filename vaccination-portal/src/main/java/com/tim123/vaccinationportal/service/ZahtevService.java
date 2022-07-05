package com.tim123.vaccinationportal.service;

import com.tim123.vaccinationportal.model.Korisnik;
import com.tim123.vaccinationportal.model.dto.OdbijZahtevDto;
import com.tim123.vaccinationportal.model.zahtev.Zahtev;

import java.io.ByteArrayInputStream;
import java.text.ParseException;

public interface ZahtevService extends CRUDService<Zahtev> {

    Zahtev dodajZahtev(Zahtev zahtev);
    Zahtev dobaviZahtev(String id);
    int prebrojZahteveZaPeriod(String startDate, String endDate) throws ParseException;
    void odbijZahtev(String requestId, String reason);
    void prihvatiZahtev(String requestId);
    Korisnik dobaviKorisnikaZahteva(Zahtev zahtev);
    ByteArrayInputStream generisiPdf(Zahtev zahtev);
    ByteArrayInputStream generisiHTML(Zahtev zahtev);
}
