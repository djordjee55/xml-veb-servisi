package com.tim123.vaccinationportal.service.impl;

import com.tim123.vaccinationportal.model.saglasnost.Saglasnost;
import com.tim123.vaccinationportal.repository.CRUDRepository;
import com.tim123.vaccinationportal.service.SaglasnostService;
import org.springframework.stereotype.Service;

@Service
public class SaglasnostServiceImpl extends CRUDServiceImpl<Saglasnost> implements SaglasnostService {

    @Override
    protected CRUDRepository<Saglasnost> getRepository() {
        return null;
    }
}
