package com.tim123.vaccinationmain.repository;

import com.tim123.vaccinationmain.model.zdravstvenaUstanova.ZdravstvenaUstanova;
import com.tim123.vaccinationmain.service.ConverterService;
import com.tim123.vaccinationmain.service.MarshallUnmarshallService;
import com.tim123.vaccinationmain.service.XPathService;
import com.tim123.vaccinationmain.util.RepositoryUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;

import java.util.ArrayList;
import java.util.List;

import static com.tim123.vaccinationmain.util.Constants.ustanoveCollection;

@Repository
@RequiredArgsConstructor
public class ZdravstvenaUstanovaRepository implements CRUDRepository<ZdravstvenaUstanova> {
    private final RepositoryUtil repositoryUtil;
    private final MarshallUnmarshallService<ZdravstvenaUstanova> marshallUnmarshallService;
    private final XPathService xPathService;
    private final ConverterService<ZdravstvenaUstanova> converterService;

    @Override
    public ZdravstvenaUstanova save(ZdravstvenaUstanova entity) throws Exception {
        String documentId = entity.getId();
        repositoryUtil.save(ustanoveCollection, documentId, marshallUnmarshallService.marshall(entity, ZdravstvenaUstanova.class));
        return entity;
    }

    @Override
    public ZdravstvenaUstanova findById(String id) throws Exception {
        var result = repositoryUtil.findByDocumentId(ustanoveCollection, id);
        return marshallUnmarshallService.unmarshall(result, ZdravstvenaUstanova.class);
    }

    public List<ZdravstvenaUstanova> findAllForOpstina(String opstina) {
        List<ZdravstvenaUstanova> resultSet = new ArrayList<>();
        try {
            ResourceSet result = xPathService.executeXPath(ustanoveCollection,
                    String.format("//ZdravstvenaUstanova[@opstina='%s']", opstina), "");

            resultSet = converterService.convert(result, ZdravstvenaUstanova.class);

            if (resultSet.isEmpty())
                return null;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public String getOpstinaForUstanova(String ustanova) {
        List<ZdravstvenaUstanova> resultSet = new ArrayList<>();
        try {
            ResourceSet result = xPathService.executeXPath(ustanoveCollection,
                    String.format("//ZdravstvenaUstanova[@id='%s']", ustanova), "");

            resultSet = converterService.convert(result, ZdravstvenaUstanova.class);

            if (resultSet.isEmpty())
                return null;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet.get(0).getOpstina();
    }

    public List<ZdravstvenaUstanova> getByPacijentEmail(String email) {
        List<ZdravstvenaUstanova> resultSet = new ArrayList<>();
        try {
            ResourceSet result = xPathService.executeXPath(ustanoveCollection,
                    String.format("//ZdravstvenaUstanova[Termini[@pacijent='%s']]", email), "");

            resultSet = converterService.convert(result, ZdravstvenaUstanova.class);

            if (resultSet.isEmpty())
                return null;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;

    }

    public ZdravstvenaUstanova findByNaziv(String ustanova) {
        List<ZdravstvenaUstanova> resultSet = new ArrayList<>();
        try {
            ResourceSet result = xPathService.executeXPath(ustanoveCollection,
                    String.format("//ZdravstvenaUstanova[@naziv='%s']", ustanova), "");

            resultSet = converterService.convert(result, ZdravstvenaUstanova.class);

            if (resultSet.isEmpty())
                return null;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet.get(0);
    }
}
