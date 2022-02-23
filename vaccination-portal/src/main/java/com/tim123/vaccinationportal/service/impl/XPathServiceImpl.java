package com.tim123.vaccinationportal.service.impl;

import com.tim123.vaccinationportal.repository.XPathRepository;
import com.tim123.vaccinationportal.service.XPathService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.ResourceSet;

@RequiredArgsConstructor
@Service
public class XPathServiceImpl implements XPathService {

    private final XPathRepository xPathRepository;

    @Override
    public ResourceSet executeXPath(String collectionId, String expression, String targetNamespace) throws Exception {
        return xPathRepository.executeXPath(collectionId, expression, targetNamespace);
    }
}
