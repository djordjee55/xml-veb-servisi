package com.tim123.vaccinationportal.service;

import com.tim123.vaccinationportal.model.Korisnik;

public interface KorisnikService extends CRUDService<Korisnik> {
    Korisnik findByEmail(String email);
}
