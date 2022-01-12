package com.tim123.vaccinationmain.service;

import org.xmldb.api.modules.XMLResource;

public interface CRUDService {
    void save(String documentId, String xmlData) throws Exception;
    XMLResource findByDocumentId(String documentId) throws Exception;
}
