package com.tim123.vaccinationmain.controller;

import com.tim123.vaccinationmain.dto.LoginDto;
import com.tim123.vaccinationmain.dto.Token;
import com.tim123.vaccinationmain.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public Token login(@RequestBody LoginDto login) {
        return authService.login(login.getEmail(), login.getLozinka());
    }

}