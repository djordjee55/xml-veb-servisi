package com.tim123.vaccinationportal.repository;

import com.tim123.vaccinationportal.model.interesovanje.Interesovanje;
import com.tim123.vaccinationportal.service.MarshallUnmarshallService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import static com.tim123.vaccinationportal.util.Constants.interesovanjeBase;
import static com.tim123.vaccinationportal.util.Constants.interesovanjeCollection;

@Repository
@RequiredArgsConstructor
public class InteresovanjeRepository implements CRUDRepository<Interesovanje> {

    private final RepositoryUtil repositoryUtil;
    private final MarshallUnmarshallService<Interesovanje> marshallUnmarshallService;

    @Override
    public Interesovanje save(Interesovanje entity) throws Exception {
        UUID uuid = UUID.randomUUID();
        // Naziv dokumenta
        String documentId = uuid.toString();
        // Koji je ujedno i njegov ID;
        entity.setId(documentId);
        // Za RDF
        entity.setAbout(String.format("%s#%s", interesovanjeBase, documentId));
        repositoryUtil.save(interesovanjeCollection, documentId, marshallUnmarshallService.marshall(entity, Interesovanje.class));
        return entity;
    }

    @Override
    public Interesovanje findById(String id) throws Exception {
        var result = repositoryUtil.findByDocumentId(interesovanjeCollection, id);
        return marshallUnmarshallService.unmarshall(result, Interesovanje.class);
    }
}
