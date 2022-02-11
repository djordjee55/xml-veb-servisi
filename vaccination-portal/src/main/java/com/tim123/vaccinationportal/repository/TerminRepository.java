package com.tim123.vaccinationportal.repository;

import com.tim123.vaccinationportal.model.Termin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TerminRepository implements CRUDRepository<Termin> {

    @Override
    public Termin save(Termin entity) throws Exception {
        return null;
    }

    @Override
    public Termin findById(String id) throws Exception {
        return null;
    }
}
