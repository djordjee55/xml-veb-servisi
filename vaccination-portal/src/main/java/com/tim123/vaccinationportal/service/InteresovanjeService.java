package com.tim123.vaccinationportal.service;

import com.tim123.vaccinationportal.model.interesovanje.Interesovanje;

import java.io.ByteArrayInputStream;
import java.text.ParseException;

public interface InteresovanjeService extends CRUDService<Interesovanje> {

    Interesovanje dodajInteresovanje(Interesovanje interesovanje, String email);
    Interesovanje dobaviInteresovanje(String id);
    ByteArrayInputStream generisiHTML(String id);
    ByteArrayInputStream generisiPDF(String id);

    int prebrojInteresovanjaZaPeriod(String startDate, String endDate) throws ParseException;

    Boolean dozvoljenoDodavanje(String name);
    Interesovanje dobaviZaKorisnika(String email);

    String searchByString(String searchedString);
}
