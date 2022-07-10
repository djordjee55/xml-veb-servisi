package com.tim123.vaccinationmain.repository;

import com.tim123.vaccinationmain.model.sertifikat.Sertifikat;
import com.tim123.vaccinationmain.service.ConverterService;
import com.tim123.vaccinationmain.service.MarshallUnmarshallService;
import com.tim123.vaccinationmain.service.XPathService;
import com.tim123.vaccinationmain.util.QRUtil;
import com.tim123.vaccinationmain.util.RepositoryUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
            entity.setQrKod(QRUtil.getQRImage("http://localhost:8082/api/sertifikat/html/"+uuid));

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

    public List<Sertifikat> getZaKorisnika(String jmbg, String pasos) {
        List<Sertifikat> resultSet = new ArrayList<>();
        try {
            ResourceSet result = xPathService.executeXPath(sertifikatCollection, "//*[local-name()='Sertifikat']", "");
            resultSet = converterService.convert(result, Sertifikat.class);
            resultSet = resultSet.stream().filter(res -> res.getBrojSertifikata() != null).collect(Collectors.toList());
            resultSet = resultSet.stream().filter(res -> res.getPrimalac().getJMBG().getValue().equals(jmbg)
                    || res.getPrimalac().getBrojPasosa().getValue().equals(pasos)).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
