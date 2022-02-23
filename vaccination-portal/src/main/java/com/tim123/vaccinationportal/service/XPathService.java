package com.tim123.vaccinationportal.service;

import org.xmldb.api.base.ResourceSet;


public interface XPathService {
    ResourceSet executeXPath(String collectionId, String expression, String targetNamespace) throws Exception;
}
