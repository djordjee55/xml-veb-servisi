package com.tim123.vaccinationportal.service.impl;

import com.tim123.vaccinationportal.model.Korisnik;
import com.tim123.vaccinationportal.service.ConverterService;
import com.tim123.vaccinationportal.service.MarshallUnmarshallService;
import lombok.RequiredArgsConstructor;
import org.exist.xmldb.EXistResource;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConverterServiceImpl<T> implements ConverterService<T> {

    private final MarshallUnmarshallService<T> marshallUnmarshallService;
    @Override
    public List<T> convert(ResourceSet resourceSet, Class<T> classToConvert) {
        List<T> retVal = new ArrayList<>();
        try {
            ResourceIterator i = resourceSet.getIterator();
            Resource res = null;
            while(i.hasMoreResources()) {
                res = i.nextResource();
                retVal.add(marshallUnmarshallService.unmarshall((String) res.getContent(), classToConvert));

                ((EXistResource)res).freeResources();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retVal;
    }
}
