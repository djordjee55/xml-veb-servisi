package com.tim123.vaccinationportal.controller;

import com.tim123.vaccinationportal.model.Korisnik;
import com.tim123.vaccinationportal.model.dto.LoginDto;
import com.tim123.vaccinationportal.model.dto.Token;
import com.tim123.vaccinationportal.security.JWTUserDetails;
import com.tim123.vaccinationportal.service.KorisnikService;
import com.tim123.vaccinationportal.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final KorisnikService korisnikService;
    private final TokenUtils tokenUtils;
    private final AuthenticationManager authenticationManager;


    @PostMapping(value = "", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> registracija(@RequestBody Korisnik korisnik) {
        korisnikService.registrujKorisnika(korisnik);
        return ResponseEntity.ok("Korisnik uspesno registrovan!");
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginDto login) {
        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    login.getEmail(), login.getLozinka()));
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Map<String, Object> claims = new HashMap<>();
        var u = korisnikService.findByEmail(login.getEmail());
        claims.put("role",u.getUloga());

        var user = (JWTUserDetails) authentication.getPrincipal();

        String jwt = tokenUtils.generateToken(user.getUsername(), claims);
        return ResponseEntity.ok(new Token(jwt));
    }

}
