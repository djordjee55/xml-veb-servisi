package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.model.zahtev.Zahtev;
import com.tim123.vaccinationmain.service.MarshallUnmarshallService;
import com.tim123.vaccinationmain.service.ZahtevService;
import com.tim123.vaccinationmain.util.MarshallUnmarshallFactory;
import org.springframework.stereotype.Service;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringWriter;

@Service
public class ZahtevServiceImpl extends CRUDServiceImpl implements ZahtevService, MarshallUnmarshallService<Zahtev> {

    @Override
    protected String getCollectionId() {
        return "/db/vakcinacija/zahtevi";
    }

    @Override
    public Zahtev unmarshall(String documentId) throws Exception {
        Unmarshaller unmarshaller = MarshallUnmarshallFactory.getUnmarshaller(Zahtev.class);
        XMLResource resource = findByDocumentId(documentId);
        return (Zahtev) unmarshaller.unmarshal(resource.getContentAsDOM());
    }

    @Override
    public String marshall(Zahtev object) throws JAXBException {
        Marshaller marshaller = MarshallUnmarshallFactory.getMarshaller(Zahtev.class);
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(object, stringWriter);
        return stringWriter.toString();
    }

    @Override
    public Zahtev save(Zahtev zahtev) throws Exception {
                        /*        save(zahtev.getId(), marshall(zahtev));
        return unmarshall(zahtev.getId());*/
        return null;
    }
}