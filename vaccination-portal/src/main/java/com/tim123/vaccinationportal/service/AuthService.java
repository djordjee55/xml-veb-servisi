package com.tim123.vaccinationportal.service;

import com.tim123.vaccinationportal.model.Korisnik;
import com.tim123.vaccinationportal.model.dto.Token;

public interface AuthService {
    void registracija(Korisnik korisnik);
    Token login(String email, String lozinka);
}
