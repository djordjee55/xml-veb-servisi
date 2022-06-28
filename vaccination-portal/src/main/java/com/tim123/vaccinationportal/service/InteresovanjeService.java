package com.tim123.vaccinationportal.service;

import com.tim123.vaccinationportal.model.interesovanje.Interesovanje;

import java.io.ByteArrayInputStream;

public interface InteresovanjeService extends CRUDService<Interesovanje> {

    Interesovanje dodajInteresovanje(Interesovanje interesovanje, String email);
    Interesovanje dobaviInteresovanje(String id);
    ByteArrayInputStream generisiHTML(String id);
    ByteArrayInputStream generisiPDF(String id);
}
