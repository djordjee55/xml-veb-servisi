package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.model.izvestaj.Izvestaj;
import com.tim123.vaccinationmain.repository.CRUDRepository;
import com.tim123.vaccinationmain.service.IzvestajService;
import org.springframework.stereotype.Service;

@Service
public class IzvestajServiceImpl extends CRUDServiceImpl<Izvestaj> implements IzvestajService {

    @Override
    protected CRUDRepository<Izvestaj> getRepository() {
        return null;
    }
}
