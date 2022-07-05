package com.tim123.vaccinationportal.service;

import com.tim123.vaccinationportal.model.Korisnik;

public interface KorisnikService extends CRUDService<Korisnik> {
    Korisnik findByEmail(String email);
    Korisnik findByJMBG(String jmbg);
    Korisnik findByPassport(String passport);
    Korisnik findByDocumentId(String jmbg, String passport);
}
