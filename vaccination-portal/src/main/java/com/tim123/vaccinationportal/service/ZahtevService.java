package com.tim123.vaccinationportal.service;

import com.tim123.vaccinationportal.model.zahtev.Zahtev;

public interface ZahtevService extends CRUDService<Zahtev> {

    Zahtev dodajZahtev(Zahtev zahtev);
    Zahtev dobaviZahtev(String id);
}
