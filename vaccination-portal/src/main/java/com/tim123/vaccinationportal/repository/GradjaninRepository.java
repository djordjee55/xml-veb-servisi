package com.tim123.vaccinationportal.repository;

import com.tim123.vaccinationportal.model.Gradjanin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GradjaninRepository implements CRUDRepository<Gradjanin> {

    @Override
    public Gradjanin save(Gradjanin entity) throws Exception {
        return null;
    }

    @Override
    public Gradjanin findById(String id) throws Exception {
        return null;
    }
}
