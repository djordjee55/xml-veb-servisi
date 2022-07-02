package com.tim123.vaccinationportal.service;

import com.tim123.vaccinationportal.model.saglasnost.Saglasnost;

import java.io.ByteArrayInputStream;

public interface SaglasnostService extends CRUDService<Saglasnost> {

    Saglasnost dodajSaglasnost(Saglasnost saglasnost);
    Saglasnost dobaviSaglasnost(String id);
    ByteArrayInputStream generisiHTML(String id);
    ByteArrayInputStream generisiPDF(String id);
}
