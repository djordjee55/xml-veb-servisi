package com.tim123.vaccinationportal.repository;

import com.tim123.vaccinationportal.model.zahtev.Zahtev;
import com.tim123.vaccinationportal.service.ConverterService;
import com.tim123.vaccinationportal.service.MarshallUnmarshallService;
import com.tim123.vaccinationportal.service.XPathService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.tim123.vaccinationportal.util.Constants.*;

@Repository
@RequiredArgsConstructor
public class ZahtevRepository implements CRUDRepository<Zahtev> {

    private final RepositoryUtil repositoryUtil;
    private final XPathService xPathService;
    private final ConverterService<Zahtev> converterService;
    private final MarshallUnmarshallService<Zahtev> marshallUnmarshallService;

    @Override
    public Zahtev save(Zahtev entity) throws Exception {
        String documentId = entity.getId();
        if (documentId == null || documentId.isBlank()) {
            UUID uuid = UUID.randomUUID();
            documentId = uuid.toString();
            entity.setId(documentId);
            entity.setAbout(String.format("%s#%s", zahtevBase, documentId));
        }
        repositoryUtil.save(zahtevCollection, documentId, marshallUnmarshallService.marshall(entity, Zahtev.class));
        return entity;
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
