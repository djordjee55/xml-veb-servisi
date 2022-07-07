package com.tim123.vaccinationportal.service;

import com.tim123.vaccinationportal.model.Korisnik;
import com.tim123.vaccinationportal.model.potvrda.TVakcinacija;

public interface KorisnikService extends CRUDService<Korisnik> {
    Korisnik findByEmail(String email);
    Korisnik findByJMBG(String jmbg);
    Korisnik findByPassport(String passport);
    Korisnik findByDocumentId(String jmbg, String passport);
    TVakcinacija dobaviVakcine(String jmbg, String pasos);
}
