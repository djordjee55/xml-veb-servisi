package com.tim123.vaccinationportal.repository;

import com.tim123.vaccinationportal.model.interesovanje.Interesovanje;
import com.tim123.vaccinationportal.service.ConverterService;
import com.tim123.vaccinationportal.service.MarshallUnmarshallService;
import com.tim123.vaccinationportal.service.XPathService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.tim123.vaccinationportal.util.Constants.interesovanjeBase;
import static com.tim123.vaccinationportal.util.Constants.interesovanjeCollection;

@Repository
@RequiredArgsConstructor
public class InteresovanjeRepository implements CRUDRepository<Interesovanje> {

    private final RepositoryUtil repositoryUtil;
    private final XPathService xPathService;
    private final MarshallUnmarshallService<Interesovanje> marshallUnmarshallService;
    private final ConverterService<Interesovanje> converterService;

    @Override
    public Interesovanje save(Interesovanje entity) throws Exception {
        UUID uuid = UUID.randomUUID();
        String documentId = uuid.toString();
        entity.setId(documentId);
        entity.setAbout(String.format("%s#%s", interesovanjeBase, documentId));
        repositoryUtil.save(interesovanjeCollection, documentId, marshallUnmarshallService.marshall(entity, Interesovanje.class));
        return entity;
    }

    @Override
    public Interesovanje findById(String id) throws Exception {
        var result = repositoryUtil.findByDocumentId(interesovanjeCollection, id);
        return marshallUnmarshallService.unmarshall(result, Interesovanje.class);
    }

    public Interesovanje findForUser(String jmbg) {
        List<Interesovanje> resultSet = new ArrayList<>();
        try {
            ResourceSet result = xPathService.executeXPath(interesovanjeCollection, String.format("//*[local-name()='Interesovanje']"), "");
            resultSet = converterService.convert(result, Interesovanje.class);
            resultSet = resultSet.stream().filter(r -> r.getPrimalac().getJMBG().getValue().equals(jmbg)).collect(Collectors.toList());
            if (resultSet.isEmpty())
                return null;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet.get(0);
    }


    public List<Interesovanje> findAll() {
        List<Interesovanje> resultSet = new ArrayList<>();
        try {
            ResourceSet result = xPathService.executeXPath(interesovanjeCollection, "//*[local-name()='Interesovanje']", "");
            resultSet = converterService.convert(result, Interesovanje.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public Interesovanje findForUserEmail(String email) {
        List<Interesovanje> resultSet = new ArrayList<>();
        try {
            ResourceSet result = xPathService.executeXPath(interesovanjeCollection, "//*", "");
            resultSet = converterService.convert(result, Interesovanje.class);

            resultSet = resultSet.stream().filter(res -> res.getPrimalac().getKontakt().getEMail().equals(email)).collect(Collectors.toList());
            if (resultSet.isEmpty()) return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet.get(0);
    }
}
