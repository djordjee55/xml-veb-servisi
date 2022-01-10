package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.model.saglasnost.Saglasnost;
import com.tim123.vaccinationmain.service.MarshallUnmarshallService;
import com.tim123.vaccinationmain.service.SaglasnostService;
import com.tim123.vaccinationmain.util.MarshallUnmarshallFactory;
import org.springframework.stereotype.Service;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringWriter;

@Service
public class SaglasnostServiceImpl extends CRUDServiceImpl implements SaglasnostService, MarshallUnmarshallService<Saglasnost> {

    @Override
    protected String getCollectionId() {
        return "/db/vakcinacija/saglasnosti";
    }

    @Override
    public Saglasnost unmarshall(String documentId) throws Exception {
        Unmarshaller unmarshaller = MarshallUnmarshallFactory.getUnmarshaller(Saglasnost.class);
        XMLResource resource = findByDocumentId(documentId);
        return (Saglasnost) unmarshaller.unmarshal(resource.getContentAsDOM());
    }

    @Override
    public String marshall(Saglasnost object) throws JAXBException {
        Marshaller marshaller = MarshallUnmarshallFactory.getMarshaller(Saglasnost.class);
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(object, stringWriter);
        return stringWriter.toString();
    }

    @Override
    public Saglasnost save(Saglasnost object) throws Exception {
        /*        save(interesovanje.getId(), marshall(interesovanje));
        return unmarshall(interesovanje.getId());*/
        return null;
    }
}
