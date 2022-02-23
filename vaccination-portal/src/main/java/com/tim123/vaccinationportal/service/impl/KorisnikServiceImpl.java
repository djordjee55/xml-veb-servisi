package com.tim123.vaccinationportal.service.impl;

import com.tim123.vaccinationportal.model.Korisnik;
import com.tim123.vaccinationportal.repository.CRUDRepository;
import com.tim123.vaccinationportal.repository.KorisnikRepository;
import com.tim123.vaccinationportal.service.KorisnikService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KorisnikServiceImpl extends CRUDServiceImpl<Korisnik> implements KorisnikService {

    private final KorisnikRepository korisnikRepository;

    @Override
    public Korisnik registrujKorisnika(Korisnik korisnik) {
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
