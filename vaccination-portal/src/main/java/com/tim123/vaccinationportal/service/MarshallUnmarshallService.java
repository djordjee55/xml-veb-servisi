package com.tim123.vaccinationportal.service;

import javax.xml.bind.JAXBException;

public interface MarshallUnmarshallService<T>{

    T unmarshall(String xmlString, Class<T> objectClass) throws Exception;

    String marshall(T object, Class<T> objectClass) throws JAXBException;

}
