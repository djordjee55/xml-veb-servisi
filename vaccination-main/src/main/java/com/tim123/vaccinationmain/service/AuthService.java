package com.tim123.vaccinationmain.service;

import com.tim123.vaccinationmain.dto.Token;

public interface AuthService {
    Token login(String email, String lozinka);
}
