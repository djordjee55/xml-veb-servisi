package com.tim123.vaccinationportal.repository;

import com.tim123.vaccinationportal.model.potvrda.Potvrda;
import org.springframework.stereotype.Repository;

@Repository
public class PotvrdaRepository implements CRUDRepository<Potvrda> {
    @Override
    public Potvrda save(Potvrda entity) throws Exception {
        //TODO proveri da li uopste treba da se cuva?
//        String documentId = entity.getId();
//        if (documentId == null || documentId.isBlank()) {
//            UUID uuid = UUID.randomUUID();
//            documentId = uuid.toString();
//            entity.setId(documentId);
//            entity.setAbout(String.format("%s#%s", saglasnostBase, documentId));
//        }
//        repositoryUtil.save(saglasnostCollection, documentId, marshallUnmarshallService.marshall(entity, Saglasnost.class));
//        return entity;
        return null;
    }

    @Override
    public Potvrda findById(String id) throws Exception {
        return null;
    }
}
