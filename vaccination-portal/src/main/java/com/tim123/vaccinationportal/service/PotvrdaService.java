package com.tim123.vaccinationportal.service;

import com.tim123.vaccinationportal.model.potvrda.Potvrda;
import com.tim123.vaccinationportal.model.potvrda.TDoza;
import com.tim123.vaccinationportal.model.saglasnost.TVakcina;

import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.util.List;

public interface PotvrdaService extends CRUDService<Potvrda> {

    ByteArrayInputStream generisiHTML(String id);

    ByteArrayInputStream generisiPDF(String id);

    Potvrda generisiPotvrdu(String saglasnostId);

    TDoza makeDozaFromTVakcina(TVakcina tvakc, int redniBroj);

    List<Potvrda> dobaviZaKorisnika(String email);

    String izadaj(String saglasnostId);

    Integer countDosesByNo(String startDate, String endDate, int numberOfDose) throws ParseException;

    Integer countDosesByManufacturer(String startDate, String endDate, String manufacturer) throws ParseException;

    String searchByString(String searchedString);

    Potvrda dobaviPotvrdu(String id);
}
