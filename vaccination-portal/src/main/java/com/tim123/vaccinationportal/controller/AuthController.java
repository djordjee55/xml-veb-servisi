package com.tim123.vaccinationportal.controller;

import com.tim123.vaccinationportal.model.Korisnik;
import com.tim123.vaccinationportal.model.dto.LoginDto;
import com.tim123.vaccinationportal.model.dto.Token;
import com.tim123.vaccinationportal.model.enumi.Uloga;
import com.tim123.vaccinationportal.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void registracija(@RequestBody Korisnik korisnik) {
        korisnik.setUloga(Uloga.GRADJANIN);
        authService.registracija(korisnik);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public Token login(@RequestBody LoginDto login) {
        return authService.login(login.getEmail(), login.getLozinka());
    }

}
