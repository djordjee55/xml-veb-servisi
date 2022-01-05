package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.service.IzvestajService;
import org.springframework.stereotype.Service;

@Service
public class IzvestajServiceImpl extends CRUDServiceImpl implements IzvestajService {

    @Override
    protected String getCollectionId() {
        return "/db/vakcinacija/izvestaji";
    }

}
