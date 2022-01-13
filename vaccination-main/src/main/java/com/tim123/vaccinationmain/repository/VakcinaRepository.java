package com.tim123.vaccinationmain.repository;

import com.tim123.vaccinationmain.model.vakcina.Vakcina;
import com.tim123.vaccinationmain.util.MarshallUnmarshallFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.StringReader;
import static com.tim123.vaccinationmain.util.Constants.vakcinaCollection;

@Repository
@RequiredArgsConstructor
public class VakcinaRepository implements CRUDRepository<Vakcina> {

    private final RepositoryUtil repositoryUtil;

    @Override
    public Vakcina save(Vakcina entity) throws Exception {
        String documentId = entity.getNaziv().name().toLowerCase();
        repositoryUtil.save(vakcinaCollection, documentId, repositoryUtil.marshall(entity, Vakcina.class));
        return entity;
    }

    @Override
    public Vakcina findById(String id) throws IOException, JAXBException, ClassNotFoundException, InstantiationException, XMLDBException, IllegalAccessException {
        var result = repositoryUtil.findByDocumentId(vakcinaCollection, id);
        var unmarshaller = MarshallUnmarshallFactory.getUnmarshaller(Vakcina.class);
        var stringReader = new StringReader(result);
        return (Vakcina) unmarshaller.unmarshal(stringReader);
    }
}
