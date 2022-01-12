package com.tim123.vaccinationportal.service.impl;

import com.tim123.vaccinationportal.model.zahtev.Zahtev;
import com.tim123.vaccinationportal.repository.CRUDRepository;
import com.tim123.vaccinationportal.service.ZahtevService;
import org.springframework.stereotype.Service;

@Service
public class ZahtevServiceImpl extends CRUDServiceImpl<Zahtev> implements ZahtevService {

    @Override
    protected CRUDRepository<Zahtev> getRepository() {
        return null;
    }
}
