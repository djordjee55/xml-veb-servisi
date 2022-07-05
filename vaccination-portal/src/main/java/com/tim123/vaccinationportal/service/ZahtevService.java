package com.tim123.vaccinationportal.service;

import com.tim123.vaccinationportal.model.zahtev.Zahtev;

import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.util.List;

public interface ZahtevService extends CRUDService<Zahtev> {

    Zahtev dodajZahtev(Zahtev zahtev, String email);
    Zahtev dobaviZahtev(String id);

    int prebrojZahteveZaPeriod(String startDate, String endDate) throws ParseException;

    ByteArrayInputStream generisiHTML(String toString);

    ByteArrayInputStream generisiPDF(String toString);

    List<Zahtev> dobaviZaKorisnika(String email);
}
