package com.tim123.vaccinationportal.repository;

import com.tim123.vaccinationportal.model.potvrda.Potvrda;
import com.tim123.vaccinationportal.service.MarshallUnmarshallService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import static com.tim123.vaccinationportal.util.Constants.saglasnostBase;
import static com.tim123.vaccinationportal.util.Constants.saglasnostCollection;

@Repository
@RequiredArgsConstructor
public class PotvrdaRepository implements CRUDRepository<Potvrda> {

    private final RepositoryUtil repositoryUtil;
    private final MarshallUnmarshallService<Potvrda> marshallUnmarshallService;

    @Override
    public Potvrda save(Potvrda entity) throws Exception {
        String documentId = entity.getSifra();
        if (documentId == null || documentId.isBlank()) {
            UUID uuid = UUID.randomUUID();
            documentId = uuid.toString();
            entity.setSifra(documentId);
            entity.setAbout(String.format("%s#%s", saglasnostBase, documentId));
        }
        repositoryUtil.save(saglasnostCollection, documentId, marshallUnmarshallService.marshall(entity, Potvrda.class));
        return entity;
    }

    @Override
    public Potvrda findById(String id) throws Exception {
        return null;
    }
}
