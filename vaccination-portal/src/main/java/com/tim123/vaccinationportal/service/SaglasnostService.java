package com.tim123.vaccinationportal.service;

import com.tim123.vaccinationportal.model.dto.DopuniEvidencijuDto;
import com.tim123.vaccinationportal.model.dto.vakcine.GetVakcinaStringDto;
import com.tim123.vaccinationportal.model.potvrda.TVakcinacija;
import com.tim123.vaccinationportal.model.saglasnost.Saglasnost;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface SaglasnostService extends CRUDService<Saglasnost> {

    Saglasnost dodajSaglasnost(Saglasnost saglasnost, String email);

    Saglasnost dobaviSaglasnost(String id);

    ByteArrayInputStream generisiHTML(String id);

    ByteArrayInputStream generisiPDF(String id);

    List<Saglasnost> dobaviZaKorisnika(String email);

    List<Saglasnost> dobaviZaKorisnika(String jmbg, String passport);

    void dopuniEvidenciju(String id, DopuniEvidencijuDto dopuniEvidencijuDto, String name) throws Exception;

    GetVakcinaStringDto vakcinaByUsername(String username);

    GetVakcinaStringDto ustanovaByUsername(String userEmail);
}
