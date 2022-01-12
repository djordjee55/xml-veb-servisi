package com.tim123.vaccinationportal.service.impl;

import com.tim123.vaccinationportal.model.interesovanje.Interesovanje;
import com.tim123.vaccinationportal.repository.CRUDRepository;
import com.tim123.vaccinationportal.service.InteresovanjeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InteresovanjeServiceImpl extends CRUDServiceImpl<Interesovanje> implements InteresovanjeService {

    @Override
    protected CRUDRepository<Interesovanje> getRepository() {
        return null;
    }

}
