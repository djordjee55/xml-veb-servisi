package com.tim123.vaccinationmain.service;

import javax.xml.bind.JAXBException;

public interface MarshallUnmarshallService <T>{

    T unmarshall(String documentId) throws Exception;

    String marshall(T object) throws JAXBException;

    T save(T object) throws Exception;
}
