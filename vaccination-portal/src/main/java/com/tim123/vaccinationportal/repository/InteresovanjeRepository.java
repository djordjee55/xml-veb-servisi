package com.tim123.vaccinationportal.repository;

import com.tim123.vaccinationportal.model.interesovanje.Interesovanje;
import com.tim123.vaccinationportal.util.MarshallUnmarshallFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.StringReader;
import java.util.UUID;

import static com.tim123.vaccinationportal.util.Constants.interesovanjeBase;
import static com.tim123.vaccinationportal.util.Constants.interesovanjeCollection;

@Repository
@RequiredArgsConstructor
public class InteresovanjeRepository implements CRUDRepository<Interesovanje> {

    private final RepositoryUtil repositoryUtil;

    @Override
    public Interesovanje save(Interesovanje entity) throws Exception {
        UUID uuid = UUID.randomUUID();
        // Naziv dokumenta
        String documentId = uuid.toString();
        // Koji je ujedno i njegov ID;
        entity.setId(documentId);
        // Za RDF
        entity.setAbout(String.format("%s#%s", interesovanjeBase, documentId));
        repositoryUtil.save(interesovanjeCollection, documentId, repositoryUtil.convertToXmlString(entity, Interesovanje.class));
        return entity;
    }

    @Override
    public Interesovanje findById(String id) throws IOException, JAXBException, ClassNotFoundException, InstantiationException, XMLDBException, IllegalAccessException {
        var result = repositoryUtil.findByDocumentId(interesovanjeCollection, id);
        var unmarshaller = MarshallUnmarshallFactory.getUnmarshaller(Interesovanje.class);
        var stringReader = new StringReader(result);
        return (Interesovanje) unmarshaller.unmarshal(stringReader);
    }
}
