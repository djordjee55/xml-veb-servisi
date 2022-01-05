package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.service.ZahtevService;
import org.springframework.stereotype.Service;

@Service
public class ZahtevServiceImpl extends CRUDServiceImpl implements ZahtevService {

    @Override
    protected String getCollectionId() {
        return "/db/vakcinacija/zahtevi";
    }

}
