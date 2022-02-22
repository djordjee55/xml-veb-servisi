package com.tim123.vaccinationportal.service.impl;

import com.tim123.vaccinationportal.model.saglasnost.Saglasnost;
import com.tim123.vaccinationportal.repository.CRUDRepository;
import com.tim123.vaccinationportal.repository.SaglasnostRepository;
import com.tim123.vaccinationportal.service.RDFService;
import com.tim123.vaccinationportal.service.SaglasnostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static com.tim123.vaccinationportal.util.Constants.saglasnostPath;

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
        try {
            var i = this.save(saglasnost);
            rdfService.extractMetadata(saglasnost, Saglasnost.class, saglasnostPath);
            return i;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Losa saglasnost");
        }
    }

    @Override
    public Saglasnost dobaviSaglasnost(String id) {

        var saglasnost = this.findById(id);
        if (saglasnost.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Saglasnost nije pronadjena");
        }
        return saglasnost.get();
    }
}
