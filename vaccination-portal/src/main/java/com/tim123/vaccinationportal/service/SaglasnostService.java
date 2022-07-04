package com.tim123.vaccinationportal.service;

import com.tim123.vaccinationportal.model.dto.DopuniEvidencijuDto;
import com.tim123.vaccinationportal.model.saglasnost.Saglasnost;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface SaglasnostService extends CRUDService<Saglasnost> {

    Saglasnost dodajSaglasnost(Saglasnost saglasnost, String email);
    Saglasnost dobaviSaglasnost(String id);
    ByteArrayInputStream generisiHTML(String id);
    ByteArrayInputStream generisiPDF(String id);

    List<Saglasnost> dobaviZaKorisnika(String email);
    void dopuniEvidenciju(String id, DopuniEvidencijuDto dopuniEvidencijuDto) throws Exception;

    List<String> vakcinaByUsername(String username);

    List<String> ustanovaByUsername(String userEmail);
}
