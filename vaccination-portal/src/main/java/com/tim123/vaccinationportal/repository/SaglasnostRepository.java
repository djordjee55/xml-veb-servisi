package com.tim123.vaccinationportal.repository;

import com.tim123.vaccinationportal.model.saglasnost.Saglasnost;
import com.tim123.vaccinationportal.service.ConverterService;
import com.tim123.vaccinationportal.service.MarshallUnmarshallService;
import com.tim123.vaccinationportal.service.XPathService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.tim123.vaccinationportal.util.Constants.saglasnostCollection;

import java.util.UUID;

import static com.tim123.vaccinationportal.util.Constants.*;

@Repository
@RequiredArgsConstructor
public class SaglasnostRepository implements CRUDRepository<Saglasnost> {
    private final XPathService xPathService;
    private final ConverterService<Saglasnost> converterService;
    private final RepositoryUtil repositoryUtil;
    private final MarshallUnmarshallService<Saglasnost> marshallUnmarshallService;

    @Override
    public Saglasnost save(Saglasnost entity) throws Exception {
        String documentId = entity.getId();
        if (documentId == null || documentId.isBlank()) {
            UUID uuid = UUID.randomUUID();
            documentId = uuid.toString();
            entity.setId(documentId);
            entity.setAbout(String.format("%s#%s", saglasnostBase, documentId));
        }
        repositoryUtil.save(saglasnostCollection, documentId, marshallUnmarshallService.marshall(entity, Saglasnost.class));
        return entity;
    }

    @Override
    public Saglasnost findById(String id) throws Exception {
        var result = repositoryUtil.findByDocumentId(saglasnostCollection, id);
        return marshallUnmarshallService.unmarshall(result, Saglasnost.class);
    }

    public List<Saglasnost> getForUserEmail(String email) {
        List<Saglasnost> resultSet = new ArrayList<>();
        try {
            ResourceSet result = xPathService.executeXPath(saglasnostCollection, "//*[local-name()='Saglasnost']", "");
            resultSet = converterService.convert(result, Saglasnost.class);
            resultSet = resultSet.stream().filter(res -> res.getId() != null).collect(Collectors.toList());
            resultSet = resultSet.stream().filter(res -> res.getPacijent().getKontakt().getEMail().equals(email)).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

}
