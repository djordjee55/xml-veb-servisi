package com.tim123.vaccinationportal.service;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface RDFService {
    void extractMetadata(String xmlDocument, String graphPath) throws IOException, TransformerException;
    void extractMetadata(Object object, Class<?> objectClass, String graphPath) throws IOException, TransformerException, JAXBException;
    void dropSchema() throws IOException;
}
