package com.tim123.vaccinationmain.repository;

import com.tim123.vaccinationmain.model.vakcina.Vakcina;
import com.tim123.vaccinationmain.service.ConverterService;
import com.tim123.vaccinationmain.service.MarshallUnmarshallService;
import com.tim123.vaccinationmain.service.XPathService;
import com.tim123.vaccinationmain.util.RepositoryUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;

import java.util.ArrayList;
import java.util.List;

import static com.tim123.vaccinationmain.util.Constants.vakcinaCollection;

@Repository
@RequiredArgsConstructor
public class VakcinaRepository implements CRUDRepository<Vakcina> {

    private final RepositoryUtil repositoryUtil;
    private final MarshallUnmarshallService<Vakcina> marshallUnmarshallService;
    private final XPathService xPathService;
    private final ConverterService<Vakcina> converterService;

    @Override
    public Vakcina save(Vakcina entity) throws Exception {
        String documentId = entity.getId();
        repositoryUtil.save(vakcinaCollection, documentId, marshallUnmarshallService.marshall(entity, Vakcina.class));
        return entity;
    }

    @Override
    public Vakcina findById(String id) throws Exception {
        var result = repositoryUtil.findByDocumentId(vakcinaCollection, id);
        return marshallUnmarshallService.unmarshall(result, Vakcina.class);
    }

    public int checkQuantityForVaccine(String vakcina, String ustanova) {
        List<Vakcina> resultSet = new ArrayList<>();

        ResourceSet result;
        try {
            result = xPathService.executeXPath(vakcinaCollection,
                    String.format("//Vakcina[@ustanova='%s' and naziv='%s']", ustanova, vakcina), "");
            resultSet = converterService.convert(result, Vakcina.class);

            if (resultSet.isEmpty())
                return 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultSet.get(0).getKolicina();
    }

    public Vakcina getVakcinaZaUstanovu(String vakcina, String ustanova) {
        List<Vakcina> resultSet = new ArrayList<>();

        ResourceSet result;
        try {
            result = xPathService.executeXPath(vakcinaCollection,
                    String.format("//Vakcina[@ustanova='%s' and naziv='%s']", ustanova, vakcina), "");
            resultSet = converterService.convert(result, Vakcina.class);

            if (resultSet.isEmpty())
                return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet.get(0);
    }

    public List<Vakcina> getVakcineZaUstanovu(String id) {
        List<Vakcina> resultSet = new ArrayList<>();

        ResourceSet result;
        try {
            result = xPathService.executeXPath(vakcinaCollection,
                    String.format("//Vakcina[@ustanova='%s']", id), "");
            resultSet = converterService.convert(result, Vakcina.class);

            if (resultSet.isEmpty())
                return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
