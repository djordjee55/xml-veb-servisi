package com.tim123.vaccinationmain.repository;

import com.tim123.vaccinationmain.model.cekanje.Cekanje;
import com.tim123.vaccinationmain.model.zdravstvenaUstanova.ZdravstvenaUstanova;
import com.tim123.vaccinationmain.service.ConverterService;
import com.tim123.vaccinationmain.service.MarshallUnmarshallService;
import com.tim123.vaccinationmain.service.XPathService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.tim123.vaccinationmain.util.Constants.cekanjeCollection;

@Repository
@RequiredArgsConstructor
public class CekanjeRepository implements CRUDRepository<Cekanje>{
    private final RepositoryUtil repositoryUtil;
    private final MarshallUnmarshallService<Cekanje> marshallUnmarshallService;
    private final XPathService xPathService;
    private final ConverterService<Cekanje> converterService;

    @Override
    public Cekanje save(Cekanje entity) throws Exception {
        String documentId = entity.getId();
        repositoryUtil.save(cekanjeCollection, documentId, marshallUnmarshallService.marshall(entity, Cekanje.class));
        return entity;
    }

    @Override
    public Cekanje findById(String id) throws Exception {
        var result = repositoryUtil.findByDocumentId(cekanjeCollection, id);
        return marshallUnmarshallService.unmarshall(result, Cekanje.class);
    }
}
