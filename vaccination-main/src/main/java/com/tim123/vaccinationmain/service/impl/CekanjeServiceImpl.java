package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.model.cekanje.Cekanje;
import com.tim123.vaccinationmain.repository.CRUDRepository;
import com.tim123.vaccinationmain.repository.CekanjeRepository;
import com.tim123.vaccinationmain.service.CekanjeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CekanjeServiceImpl extends CRUDServiceImpl<Cekanje> implements CekanjeService {

    private final CekanjeRepository cekanjeRepository;

    @Override
    protected CRUDRepository<Cekanje> getRepository() {
        return cekanjeRepository;
    }
}
