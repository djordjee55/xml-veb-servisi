package com.tim123.vaccinationmain.repository;

import com.tim123.vaccinationmain.model.vakcina.Vakcina;
import com.tim123.vaccinationmain.service.MarshallUnmarshallService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.tim123.vaccinationmain.util.Constants.vakcinaCollection;

@Repository
@RequiredArgsConstructor
public class VakcinaRepository implements CRUDRepository<Vakcina> {

    private final RepositoryUtil repositoryUtil;
    private final MarshallUnmarshallService<Vakcina> marshallUnmarshallService;

    @Override
    public Vakcina save(Vakcina entity) throws Exception {
        String documentId = entity.getNaziv().name().toLowerCase();
        repositoryUtil.save(vakcinaCollection, documentId, marshallUnmarshallService.marshall(entity, Vakcina.class));
        return entity;
    }

    @Override
    public Vakcina findById(String id) throws Exception {
        var result = repositoryUtil.findByDocumentId(vakcinaCollection, id);
        return marshallUnmarshallService.unmarshall(result, Vakcina.class);
    }
}
