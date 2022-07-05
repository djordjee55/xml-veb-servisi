package com.tim123.vaccinationportal.service;

import com.tim123.vaccinationportal.model.dto.OdbijZahtevDto;
import com.tim123.vaccinationportal.model.zahtev.Zahtev;

import java.text.ParseException;

public interface ZahtevService extends CRUDService<Zahtev> {

    Zahtev dodajZahtev(Zahtev zahtev);
    Zahtev dobaviZahtev(String id);
    int prebrojZahteveZaPeriod(String startDate, String endDate) throws ParseException;
    void odbijZahtev(String requestId, String reason);
}
