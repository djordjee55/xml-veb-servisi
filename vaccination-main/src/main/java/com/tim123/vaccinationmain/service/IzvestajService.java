package com.tim123.vaccinationmain.service;

import com.tim123.vaccinationmain.model.izvestaj.Izvestaj;

import javax.xml.bind.JAXBException;

public interface IzvestajService extends CRUDService<Izvestaj> {

    String getIzvestajForPeriod(String startDate, String endDate) throws Exception;
}
