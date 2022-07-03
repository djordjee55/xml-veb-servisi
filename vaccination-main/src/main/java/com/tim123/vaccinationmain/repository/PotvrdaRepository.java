package com.tim123.vaccinationmain.repository;

import com.tim123.vaccinationmain.model.potvrda.Potvrda;
import com.tim123.vaccinationmain.service.ConverterService;
import com.tim123.vaccinationmain.service.XPathService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PotvrdaRepository implements CRUDRepository<Potvrda> {

    private final XPathService xPathService;
    private final ConverterService<Potvrda> converterService;

    public static final String potvrdaCollection = "db/vakcinisanje/potvrda";

    @Override
    public Potvrda save(Potvrda entity) throws Exception {
        return null;
    }

    @Override
    public Potvrda findById(String id) throws Exception {
        return null;
    }

    public List<Potvrda> findAll() {

        try {
            ResourceSet result = xPathService.executeXPath(potvrdaCollection, "//*", "");
            return converterService.convert(result, Potvrda.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return List.of();
    }
}
