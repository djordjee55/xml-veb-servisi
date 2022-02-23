package com.tim123.vaccinationportal.service.impl;

import com.tim123.vaccinationportal.model.Korisnik;
import com.tim123.vaccinationportal.repository.CRUDRepository;
import com.tim123.vaccinationportal.repository.KorisnikRepository;
import com.tim123.vaccinationportal.service.KorisnikService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KorisnikServiceImpl extends CRUDServiceImpl<Korisnik> implements KorisnikService {

    private final KorisnikRepository korisnikRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Korisnik registrujKorisnika(Korisnik korisnik) {

        Korisnik k = korisnikRepository.findByEmail(korisnik.getEmail());
        if(k != null)
            return null;

        String psw = passwordEncoder.encode(korisnik.getLozinka());
        korisnik.setLozinka(psw);
        try {
            return korisnikRepository.save(korisnik);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Korisnik findByEmail(String email) {
        return korisnikRepository.findByEmail(email);
    }

    @Override
    protected CRUDRepository<Korisnik> getRepository() {
        return this.korisnikRepository;
    }
}
