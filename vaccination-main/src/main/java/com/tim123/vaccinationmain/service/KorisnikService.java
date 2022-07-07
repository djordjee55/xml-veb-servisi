package com.tim123.vaccinationmain.service;

import com.tim123.vaccinationmain.model.Korisnik;

public interface KorisnikService extends CRUDService<Korisnik> {
    Korisnik findByEmail(String email);
}
