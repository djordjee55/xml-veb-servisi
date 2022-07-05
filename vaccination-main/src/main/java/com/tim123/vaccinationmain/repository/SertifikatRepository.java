package com.tim123.vaccinationmain.repository;

import com.tim123.vaccinationmain.model.sertifikat.Sertifikat;
import com.tim123.vaccinationmain.service.ConverterService;
import com.tim123.vaccinationmain.service.XPathService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SertifikatRepository implements CRUDRepository<Sertifikat> {

    private final XPathService xPathService;
    private final ConverterService<Sertifikat> converterService;

    public static final String sertifikatCollection = "db/vakcinisanje/sertifikat";

    @Override
    public Sertifikat save(Sertifikat entity) throws Exception {
        // TODO implement
        return null;
    }

    @Override
    public Sertifikat findById(String id) throws Exception {
        return null;
    }

    public List<Sertifikat> findAll() {

        try {
            ResourceSet result = xPathService.executeXPath(sertifikatCollection, "//*[local-name()='Sertifikat']", "");
            return converterService.convert(result, Sertifikat.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }
}
