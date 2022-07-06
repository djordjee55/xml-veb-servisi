package com.tim123.vaccinationmain.service;

import com.tim123.vaccinationmain.model.potvrda.Potvrda;
import com.tim123.vaccinationmain.model.tipovi.TVakcina;

import java.text.ParseException;

public interface PotvrdaService extends CRUDService<Potvrda> {

    int countDozeByNo(int numberOfDose, String startDate, String endDate) throws ParseException;

    int countDozeByManufacturer(TVakcina manufacturer, String startDate, String endDate) throws ParseException;

    String searchByString(String searchedString);
}
