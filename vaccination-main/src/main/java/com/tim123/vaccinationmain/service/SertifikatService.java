package com.tim123.vaccinationmain.service;

import com.tim123.vaccinationmain.model.sertifikat.Sertifikat;
import com.tim123.vaccinationmain.model.tipovi.TVakcinisanoLice;
import org.xws.zahtev.Zahtev;

import java.io.ByteArrayInputStream;
import java.text.ParseException;

public interface SertifikatService extends CRUDService<Sertifikat> {

    int prebrojSertifikateZaPeriod(String startDate, String endDate) throws ParseException;

    Sertifikat generisiSertifikat(TVakcinisanoLice podnosilac);

    ByteArrayInputStream generisiPDF(String id);
}
