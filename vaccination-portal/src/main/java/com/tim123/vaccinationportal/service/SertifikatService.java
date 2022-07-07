package com.tim123.vaccinationportal.service;

import com.tim123.vaccinationportal.model.sertifikat.Sertifikat;

import java.io.ByteArrayInputStream;

public interface SertifikatService {

    ByteArrayInputStream generisiHTML(String id);

    ByteArrayInputStream generisiPDF(String id);

    Sertifikat getSertifikatById(String id);

    String dobaviSertifikat(String id);

    ByteArrayInputStream generisiHTML(Sertifikat s);

    ByteArrayInputStream generisiPDF(Sertifikat s);
}
