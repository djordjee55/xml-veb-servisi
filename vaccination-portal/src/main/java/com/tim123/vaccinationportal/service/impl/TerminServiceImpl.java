package com.tim123.vaccinationportal.service.impl;

import com.tim123.vaccinationportal.model.Termin;
import com.tim123.vaccinationportal.repository.CRUDRepository;
import com.tim123.vaccinationportal.repository.TerminRepository;
import com.tim123.vaccinationportal.service.RDFService;
import com.tim123.vaccinationportal.service.TerminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TerminServiceImpl extends CRUDServiceImpl<Termin> implements TerminService {

    private final TerminRepository terminRepository;
    private final RDFService rdfService;

    @Override
    protected CRUDRepository<Termin> getRepository() {
        return terminRepository;
    }

    @Override
    public Termin kreirajNoviTermin(String domZdravlja, LocalDate datum) {
        return null;
    }
}
