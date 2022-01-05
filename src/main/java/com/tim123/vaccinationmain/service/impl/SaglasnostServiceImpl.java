package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.service.SaglasnostService;
import org.springframework.stereotype.Service;

@Service
public class SaglasnostServiceImpl extends CRUDServiceImpl implements SaglasnostService {

    @Override
    protected String getCollectionId() {
        return "/db/vakcinacija/saglasnosti";
    }

}
