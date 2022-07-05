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
    protected CRUDRepository<Korisnik> getRepository() {
        return this.korisnikRepository;
    }

    @Override
    public Korisnik findByEmail(String email) {
        return korisnikRepository.findByEmail(email);
    }

    @Override
    public Korisnik findByDocumentId(String jmbg, String passport) {
        Korisnik k = null;
        if (jmbg != null && !jmbg.isBlank()) {
            k = this.findByJMBG(jmbg);
        } else if (passport != null && !passport.isBlank()) {
            k = this.findByPassport(passport);
        }
        return k;
    }

    @Override
    public Korisnik findByJMBG(String jmbg) {
        return korisnikRepository.findByJMBG(jmbg);
    }

    @Override
    public Korisnik findByPassport(String passport) {
        return korisnikRepository.findByPassport(passport);
    }
}
