package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.model.Korisnik;
import com.tim123.vaccinationmain.repository.CRUDRepository;
import com.tim123.vaccinationmain.repository.KorisnikRepository;
import com.tim123.vaccinationmain.service.KorisnikService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class KorisnikServiceImpl extends CRUDServiceImpl<Korisnik> implements KorisnikService {

    private final KorisnikRepository korisnikRepository;
    private final RestTemplate restTemplate;

    @Override
    protected CRUDRepository<Korisnik> getRepository() {
        return this.korisnikRepository;
    }

    @Override
    public Korisnik findByEmail(String email) {
        return korisnikRepository.findByEmail(email);
    }

    @Override
    public Korisnik dobaviKorisika(String jmbg, String pasos) {
        String url = String.format("http://localhost:8082/api/korisnik/get-id?jmbg=%s&pasos=%s", jmbg, pasos);
        var kres = restTemplate.getForEntity(url, Korisnik.class);
        return kres.getBody();
    }
}
