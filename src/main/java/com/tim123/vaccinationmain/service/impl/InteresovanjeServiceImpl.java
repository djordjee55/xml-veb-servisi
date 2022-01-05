package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.service.InteresovanjeService;
import org.springframework.stereotype.Service;

@Service
public class InteresovanjeServiceImpl extends CRUDServiceImpl implements InteresovanjeService {

    @Override
    protected String getCollectionId() {
        return "/db/vakcinacija/interesovanja";
    }

}
