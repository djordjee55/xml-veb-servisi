package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.service.SertifikatService;
import org.springframework.stereotype.Service;

@Service
public class SertifikatServiceImpl extends CRUDServiceImpl implements SertifikatService {

    @Override
    protected String getCollectionId() {
        return "/db/vakcinacija/sertifikati";
    }

}
