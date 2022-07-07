package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.model.Korisnik;
import com.tim123.vaccinationmain.security.JWTUserDetails;
import com.tim123.vaccinationmain.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final KorisnikService korisnikService;

    @Autowired
    public CustomUserDetailsService(KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Korisnik user = korisnikService.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return new JWTUserDetails(user);
        }
    }

}