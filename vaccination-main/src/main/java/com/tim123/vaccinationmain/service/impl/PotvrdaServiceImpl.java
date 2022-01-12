package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.model.potvrda.Potvrda;
import com.tim123.vaccinationmain.repository.CRUDRepository;
import com.tim123.vaccinationmain.service.PotvrdaService;
import org.springframework.stereotype.Service;

@Service
public class PotvrdaServiceImpl extends CRUDServiceImpl<Potvrda> implements PotvrdaService {

    @Override
    protected CRUDRepository<Potvrda> getRepository() {
        return null;
    }
}
