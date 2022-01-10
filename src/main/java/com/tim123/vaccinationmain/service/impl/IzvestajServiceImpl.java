package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.model.izvestaj.Izvestaj;
import com.tim123.vaccinationmain.service.IzvestajService;
import com.tim123.vaccinationmain.service.MarshallUnmarshallService;
import com.tim123.vaccinationmain.util.MarshallUnmarshallFactory;
import org.springframework.stereotype.Service;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringWriter;

@Service
public class IzvestajServiceImpl extends CRUDServiceImpl implements IzvestajService, MarshallUnmarshallService<Izvestaj> {

    @Override
    protected String getCollectionId() {
        return "/db/vakcinacija/izvestaji";
    }

    @Override
    public Izvestaj unmarshall(String documentId) throws Exception {
        Unmarshaller unmarshaller = MarshallUnmarshallFactory.getUnmarshaller(Izvestaj.class);
        XMLResource resource = findByDocumentId(documentId);
        return (Izvestaj) unmarshaller.unmarshal(resource.getContentAsDOM());
    }

    @Override
    public String marshall(Izvestaj object) throws JAXBException {
        Marshaller marshaller = MarshallUnmarshallFactory.getMarshaller(Izvestaj.class);
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(object, stringWriter);
        return stringWriter.toString();
    }

    @Override
    public Izvestaj save(Izvestaj izvestaj) throws Exception {
/*        save(izvestaj.getId(), marshall(izvestaj));
        return unmarshall(izvestaj.getId());*/
        return null;

    }
}
