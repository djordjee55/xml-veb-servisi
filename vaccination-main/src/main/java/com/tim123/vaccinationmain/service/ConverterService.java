package com.tim123.vaccinationmain.service;

import org.xmldb.api.base.ResourceSet;

import java.util.List;

public interface ConverterService<T> {
    List<T> convert(ResourceSet resourceSet, Class<T> classToConvert);
}

