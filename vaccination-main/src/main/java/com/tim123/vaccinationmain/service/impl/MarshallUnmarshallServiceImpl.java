package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.service.MarshallUnmarshallService;
import com.tim123.vaccinationmain.util.MarshallUnmarshallFactory;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.StringReader;
import java.io.StringWriter;

@Service
public class MarshallUnmarshallServiceImpl<T> implements MarshallUnmarshallService<T> {

    @Override
    public T unmarshall(String xmlString, Class<T> objectClass) throws Exception {
        var unmarshaller = MarshallUnmarshallFactory.getUnmarshaller(objectClass);
        var stringReader = new StringReader(xmlString);
        var unmarshalledObject = unmarshaller.unmarshal(stringReader);
        try {
            return objectClass.cast(unmarshalledObject);
        } catch(ClassCastException e) {
            return null;
        }
    }

    @Override
    public String marshall(T object, Class<T> objectClass) throws JAXBException {
        var marshaller = MarshallUnmarshallFactory.getMarshaller(objectClass);
        var stringWriter = new StringWriter();
        marshaller.marshal(object, stringWriter);
        return stringWriter.toString();
    }
}
