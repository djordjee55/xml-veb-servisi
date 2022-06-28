package com.tim123.vaccinationportal.service.impl;

import com.tim123.vaccinationportal.exception.BadCredentialsException;
import com.tim123.vaccinationportal.exception.ExistDbException;
import com.tim123.vaccinationportal.exception.UserAlreadyExistException;
import com.tim123.vaccinationportal.model.Korisnik;
import com.tim123.vaccinationportal.model.dto.Token;
import com.tim123.vaccinationportal.security.JWTUserDetails;
import com.tim123.vaccinationportal.service.AuthService;
import com.tim123.vaccinationportal.service.KorisnikService;
import com.tim123.vaccinationportal.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final KorisnikService korisnikService;
    private final PasswordEncoder passwordEncoder;
    private final TokenUtils tokenUtils;
    private final AuthenticationManager authenticationManager;

    @Override
    public void registracija(Korisnik korisnik) {
        if (korisnikService.findByEmail(korisnik.getEmail()) != null) {
            throw new UserAlreadyExistException(String.format("Korisnik sa emailom: %s je vec registrovan!",
                    korisnik.getEmail()));
        }
        korisnik.setLozinka(passwordEncoder.encode(korisnik.getLozinka()));

        try {
            korisnikService.save(korisnik);
        } catch (Exception e) {
            throw new ExistDbException("Greska sa bazom, pokusajte kasnije!");
        }
    }

    @Override
    public Token login(String email, String lozinka) {
        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    email, lozinka));
        } catch (Exception e) {
            throw new BadCredentialsException("Losi kredencijali!");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Map<String, Object> claims = new HashMap<>();
        var u = korisnikService.findByEmail(email);
        claims.put("role", u.getUloga());

        var user = (JWTUserDetails) authentication.getPrincipal();

        String jwt = tokenUtils.generateToken(user.getUsername(), claims);

        return new Token(jwt);
    }
}
