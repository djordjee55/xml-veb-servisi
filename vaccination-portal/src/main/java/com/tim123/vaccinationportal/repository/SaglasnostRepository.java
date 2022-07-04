package com.tim123.vaccinationportal.repository;

import com.tim123.vaccinationportal.model.saglasnost.Saglasnost;
import com.tim123.vaccinationportal.service.ConverterService;
import com.tim123.vaccinationportal.service.XPathService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.tim123.vaccinationportal.util.Constants.saglasnostCollection;

@Repository
@RequiredArgsConstructor
public class SaglasnostRepository implements CRUDRepository<Saglasnost> {
    private final XPathService xPathService;
    private final ConverterService<Saglasnost> converterService;

    @Override
    public Saglasnost save(Saglasnost entity) throws Exception {
        return null;
    }

    @Override
    public Saglasnost findById(String id) throws Exception {
        return null;
    }

    public List<Saglasnost> getForUserEmail(String email) {
        List<Saglasnost> resultSet = new ArrayList<>();
        try {
            ResourceSet result = xPathService.executeXPath(saglasnostCollection, "//*", "");
            resultSet = converterService.convert(result, Saglasnost.class);
            resultSet = resultSet.stream().filter(res -> res.getPacijent().getKontakt().getEMail().equals(email)).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
