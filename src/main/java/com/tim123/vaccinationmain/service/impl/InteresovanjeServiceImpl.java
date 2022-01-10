package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.model.interesovanje.Interesovanje;
import com.tim123.vaccinationmain.service.InteresovanjeService;
import com.tim123.vaccinationmain.service.MarshallUnmarshallService;
import com.tim123.vaccinationmain.util.MarshallUnmarshallFactory;
import org.apache.commons.io.output.XmlStreamWriter;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.OutputStreamWriter;
import java.io.StringWriter;

@Service
public class InteresovanjeServiceImpl extends CRUDServiceImpl implements InteresovanjeService, MarshallUnmarshallService<Interesovanje> {

    @Override
    protected String getCollectionId() {
        return "/db/vakcinacija/interesovanja";
    }

    @Override
    public Interesovanje unmarshall(String documentId) throws Exception {
        Unmarshaller unmarshaller = MarshallUnmarshallFactory.getUnmarshaller(Interesovanje.class);
        XMLResource resource = findByDocumentId(documentId);
        return (Interesovanje) unmarshaller.unmarshal(resource.getContentAsDOM());
    }

    @Override
    public String marshall(Interesovanje object) throws JAXBException {
        Marshaller marshaller = MarshallUnmarshallFactory.getMarshaller(Interesovanje.class);
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(object, stringWriter);
        return stringWriter.toString();
    }

    @Override
    public Interesovanje save(Interesovanje interesovanje) throws Exception {
/*        save(interesovanje.getId(), marshall(interesovanje));
        return unmarshall(interesovanje.getId());*/
        return null;

    }
}
