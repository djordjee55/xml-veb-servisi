package com.tim123.vaccinationportal.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class MarshallUnmarshallFactory {

    public static Marshaller getMarshaller(Class<?> marshallerType) throws JAXBException
    {
        JAXBContext jaxbContext = JAXBContext.newInstance(marshallerType);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        return marshaller;
    }

    public static Unmarshaller getUnmarshaller(Class<?> unmarshallerType) throws JAXBException
    {
        JAXBContext jaxbContext = JAXBContext.newInstance(unmarshallerType);
        return jaxbContext.createUnmarshaller();
    }
}