package com.tim123.vaccinationportal.service.impl;

import com.tim123.vaccinationportal.model.zahtev.Zahtev;
import com.tim123.vaccinationportal.repository.CRUDRepository;
import com.tim123.vaccinationportal.repository.ZahtevRepository;
import com.tim123.vaccinationportal.service.RDFService;
import com.tim123.vaccinationportal.service.ZahtevService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ZahtevServiceImpl extends CRUDServiceImpl<Zahtev> implements ZahtevService {

    private final ZahtevRepository zahtevRepository;
    private final RDFService rdfService;

    @Override
    protected CRUDRepository<Zahtev> getRepository() {
        return zahtevRepository;
    }

    @Override
    public Zahtev dodajZahtev(Zahtev zahtev) {
        //provera da li je primio dve doze
        return null;
    }

    @Override
    public Zahtev dobaviZahtev(String id) {
        return null;
    }
}
