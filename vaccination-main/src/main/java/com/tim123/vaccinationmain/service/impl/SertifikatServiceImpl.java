package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.model.sertifikat.Sertifikat;
import com.tim123.vaccinationmain.service.MarshallUnmarshallService;
import com.tim123.vaccinationmain.service.SertifikatService;
import com.tim123.vaccinationmain.util.MarshallUnmarshallFactory;
import org.springframework.stereotype.Service;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringWriter;

@Service
public class SertifikatServiceImpl extends CRUDServiceImpl implements SertifikatService, MarshallUnmarshallService<Sertifikat> {

    @Override
    protected String getCollectionId() {
        return "/db/vakcinacija/sertifikati";
    }

    @Override
    public Sertifikat unmarshall(String documentId) throws Exception {
        Unmarshaller unmarshaller = MarshallUnmarshallFactory.getUnmarshaller(Sertifikat.class);
        XMLResource resource = findByDocumentId(documentId);
        return (Sertifikat) unmarshaller.unmarshal(resource.getContentAsDOM());
    }

    @Override
    public String marshall(Sertifikat object) throws JAXBException {
        Marshaller marshaller = MarshallUnmarshallFactory.getMarshaller(Sertifikat.class);
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(object, stringWriter);
        return stringWriter.toString();
    }

    @Override
    public Sertifikat save(Sertifikat sertifikat) throws Exception {
                /*        save(sertifikat.getId(), marshall(sertifikat));
        return unmarshall(sertifikat.getId());*/
        return null;
    }
}
