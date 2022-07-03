package com.tim123.vaccinationmain.repository;

import com.tim123.vaccinationmain.model.izvestaj.Izvestaj;
import com.tim123.vaccinationmain.service.ConverterService;
import com.tim123.vaccinationmain.service.MarshallUnmarshallService;
import com.tim123.vaccinationmain.service.XPathService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class IzvestajRepository implements CRUDRepository<Izvestaj> {

    private final RepositoryUtil repositoryUtil;
    private final XPathService xPathService;
    private final MarshallUnmarshallService<Izvestaj> marshallUnmarshallService;
    private final ConverterService<Izvestaj> converterService;

    public static final String izvestajBase = "http://www.xws.org/izvestaj";
    public static final String izvestajPath = "/fuseki/izvestaj";
    public static final String izvestajCollection = "db/vakcinisanje/izvestaj";

    @Override
    public Izvestaj save(Izvestaj entity) throws Exception {

        String documentId = UUID.randomUUID().toString();
        entity.setId(documentId);
        repositoryUtil.save(izvestajCollection, documentId, marshallUnmarshallService.marshall(entity, Izvestaj.class));

        return entity;
    }

    @Override
    public Izvestaj findById(String id) throws Exception {
        var result = repositoryUtil.findByDocumentId(izvestajCollection, id);
        return marshallUnmarshallService.unmarshall(result, Izvestaj.class);
    }

    public Izvestaj findByPeriod(String startDate, String endDate) {

        List<Izvestaj> resultSet = new ArrayList<>();
        try {
            ResourceSet result = xPathService.executeXPath(izvestajCollection, String.format("//Izvestaj[Period[Od='%s' and Do='%s']]", startDate, endDate), "");
            resultSet = converterService.convert(result, Izvestaj.class);

            if (resultSet.isEmpty())
                return null;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet.get(0);
    }
}
