package com.tim123.vaccinationportal.service.impl;

import com.tim123.vaccinationportal.model.saglasnost.Saglasnost;
import com.tim123.vaccinationportal.repository.CRUDRepository;
import com.tim123.vaccinationportal.repository.SaglasnostRepository;
import com.tim123.vaccinationportal.service.RDFService;
import com.tim123.vaccinationportal.service.SaglasnostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaglasnostServiceImpl extends CRUDServiceImpl<Saglasnost> implements SaglasnostService {

    private final SaglasnostRepository saglasnostRepository;
    private final RDFService rdfService;

    @Override
    protected CRUDRepository<Saglasnost> getRepository() {
        return saglasnostRepository;
    }

    @Override
    public Saglasnost dodajSaglasnost(Saglasnost saglasnost) {
        //da li postoji termin, i da nije izvrsena vec vakcinacija u okviru njega
        return null;
    }

    @Override
    public Saglasnost dobaviSaglasnost(String id) {
        return null;
    }
}
