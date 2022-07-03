package com.tim123.vaccinationmain.service;

import com.tim123.vaccinationmain.model.sertifikat.Sertifikat;

import java.text.ParseException;

public interface SertifikatService extends CRUDService<Sertifikat> {

    int prebrojSertifikateZaPeriod(String startDate, String endDate) throws ParseException;
}
