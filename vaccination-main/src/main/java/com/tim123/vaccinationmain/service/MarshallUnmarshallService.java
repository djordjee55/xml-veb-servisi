package com.tim123.vaccinationmain.service;

import javax.xml.bind.JAXBException;

public interface MarshallUnmarshallService <T>{

    T unmarshall(String xmlString, Class<T> objectClass) throws Exception;

    String marshall(T object, Class<T> objectClass) throws JAXBException;

}
