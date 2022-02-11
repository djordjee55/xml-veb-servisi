package com.tim123.vaccinationportal.repository;

import com.tim123.vaccinationportal.model.saglasnost.Saglasnost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SaglasnostRepository implements CRUDRepository<Saglasnost> {

    @Override
    public Saglasnost save(Saglasnost entity) throws Exception {
        return null;
    }

    @Override
    public Saglasnost findById(String id) throws Exception {
        return null;
    }
}
