package com.tim123.vaccinationmain.repository;

import com.tim123.vaccinationmain.model.sertifikat.Sertifikat;
import com.tim123.vaccinationmain.service.ConverterService;
import com.tim123.vaccinationmain.service.MarshallUnmarshallService;
import com.tim123.vaccinationmain.service.XPathService;
import com.tim123.vaccinationmain.util.RepositoryUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;

import java.util.List;
import java.util.UUID;

import static com.tim123.vaccinationmain.util.Constants.sertifikatBase;
import static com.tim123.vaccinationmain.util.Constants.sertifikatCollection;

@Repository
@RequiredArgsConstructor
public class SertifikatRepository implements CRUDRepository<Sertifikat> {

    private final XPathService xPathService;
    private final ConverterService<Sertifikat> converterService;
    private final RepositoryUtil repositoryUtil;
    private final MarshallUnmarshallService<Sertifikat> marshallUnmarshallService;

    @Override
    public Sertifikat save(Sertifikat entity) throws Exception {
        String documentId = entity.getBrojSertifikata();
        if (documentId == null || documentId.isBlank()) {
            UUID uuid = UUID.randomUUID();
            documentId = uuid.toString();
            entity.setBrojSertifikata(documentId);
            entity.setAbout(String.format("%s#%s", sertifikatBase, documentId));
        }
        repositoryUtil.save(sertifikatCollection, documentId, marshallUnmarshallService.marshall(entity, Sertifikat.class));
        return entity;
    }

    @Override
    public Sertifikat findById(String id) throws Exception {
        var sertifikat = repositoryUtil.findByDocumentId(sertifikatCollection, id);
        return marshallUnmarshallService.unmarshall(sertifikat, Sertifikat.class);
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
