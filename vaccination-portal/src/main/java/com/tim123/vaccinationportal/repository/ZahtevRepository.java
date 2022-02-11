package com.tim123.vaccinationportal.repository;

import com.tim123.vaccinationportal.model.zahtev.Zahtev;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ZahtevRepository implements CRUDRepository<Zahtev> {

    @Override
    public Zahtev save(Zahtev entity) throws Exception {
        return null;
    }

    @Override
    public Zahtev findById(String id) throws Exception {
        return null;
    }
}
