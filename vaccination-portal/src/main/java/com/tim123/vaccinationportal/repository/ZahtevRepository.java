package com.tim123.vaccinationportal.repository;

import com.tim123.vaccinationportal.model.zahtev.Zahtev;
import com.tim123.vaccinationportal.service.ConverterService;
import com.tim123.vaccinationportal.service.XPathService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;

import java.util.ArrayList;
import java.util.List;

import static com.tim123.vaccinationportal.util.Constants.zahtevCollection;

@Repository
@RequiredArgsConstructor
public class ZahtevRepository implements CRUDRepository<Zahtev> {

    private final XPathService xPathService;
    private final ConverterService<Zahtev> converterService;

    @Override
    public Zahtev save(Zahtev entity) throws Exception {
        return null;
    }

    @Override
    public Zahtev findById(String id) throws Exception {
        return null;
    }

    public List<Zahtev> findAll() {
        List<Zahtev> resultSet = new ArrayList<>();
        try {
            ResourceSet result = xPathService.executeXPath(zahtevCollection, "//*[local-name()='Zahtev']", "");
            resultSet = converterService.convert(result, Zahtev.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
