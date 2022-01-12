package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.model.sertifikat.Sertifikat;
import com.tim123.vaccinationmain.repository.CRUDRepository;
import com.tim123.vaccinationmain.service.SertifikatService;
import org.springframework.stereotype.Service;

@Service
public class SertifikatServiceImpl extends CRUDServiceImpl<Sertifikat> implements SertifikatService {

    @Override
    protected CRUDRepository<Sertifikat> getRepository() {
        return null;
    }
}
