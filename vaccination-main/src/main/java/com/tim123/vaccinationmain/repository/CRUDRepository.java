package com.tim123.vaccinationmain.repository;

import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface CRUDRepository<T> {

    T save(T entity) throws Exception;
    T findById(String id) throws IOException, JAXBException, ClassNotFoundException, InstantiationException, XMLDBException, IllegalAccessException;

}
