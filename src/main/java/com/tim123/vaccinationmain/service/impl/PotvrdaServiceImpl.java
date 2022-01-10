package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.model.potvrda.Potvrda;
import com.tim123.vaccinationmain.service.MarshallUnmarshallService;
import com.tim123.vaccinationmain.service.PotvrdaService;
import com.tim123.vaccinationmain.util.MarshallUnmarshallFactory;
import org.springframework.stereotype.Service;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringWriter;

@Service
public class PotvrdaServiceImpl extends CRUDServiceImpl implements PotvrdaService, MarshallUnmarshallService<Potvrda> {

    @Override
    protected String getCollectionId() {
        return "/db/vakcinacija/potvrde";
    }


    @Override
    public Potvrda unmarshall(String documentId) throws Exception {
        Unmarshaller unmarshaller = MarshallUnmarshallFactory.getUnmarshaller(Potvrda.class);
        XMLResource resource = findByDocumentId(documentId);
        return (Potvrda) unmarshaller.unmarshal(resource.getContentAsDOM());
    }

    @Override
    public String marshall(Potvrda object) throws JAXBException {
        Marshaller marshaller = MarshallUnmarshallFactory.getMarshaller(Potvrda.class);
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(object, stringWriter);
        return stringWriter.toString();
    }

    @Override
    public Potvrda save(Potvrda potvrda) throws Exception {
/*        save(potvrda.getId(), marshall(potvrda));
        return unmarshall(potvrda.getId());*/
        return null;

    }
}
