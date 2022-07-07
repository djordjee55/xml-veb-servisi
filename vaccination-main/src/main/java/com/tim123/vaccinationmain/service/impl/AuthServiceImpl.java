package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.dto.Token;
import com.tim123.vaccinationmain.model.Korisnik;
import com.tim123.vaccinationmain.security.JWTUserDetails;
import com.tim123.vaccinationmain.service.AuthService;
import com.tim123.vaccinationmain.service.KorisnikService;
import com.tim123.vaccinationmain.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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

