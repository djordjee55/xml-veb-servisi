package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.service.PotvrdaService;
import org.springframework.stereotype.Service;

@Service
public class PotvrdaServiceImpl extends CRUDServiceImpl implements PotvrdaService {

    @Override
    protected String getCollectionId() {
        return "/db/vakcinacija/potvrde";
    }

}
