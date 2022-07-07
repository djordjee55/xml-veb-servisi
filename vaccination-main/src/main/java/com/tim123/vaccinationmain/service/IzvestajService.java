package com.tim123.vaccinationmain.service;

import com.tim123.vaccinationmain.model.izvestaj.Izvestaj;

import javax.xml.bind.JAXBException;
import java.io.ByteArrayInputStream;

public interface IzvestajService extends CRUDService<Izvestaj> {

    ByteArrayInputStream generisiHTML(String id) throws JAXBException;
    ByteArrayInputStream generisiPDF(String id) throws JAXBException;

    String getIzvestajForPeriod(String startDate, String endDate) throws Exception;
}
