package com.tim123.vaccinationportal.service;

import com.tim123.vaccinationportal.model.potvrda.Potvrda;
import com.tim123.vaccinationportal.model.potvrda.TDoza;
import com.tim123.vaccinationportal.model.saglasnost.TVakcina;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface PotvrdaService extends CRUDService<Potvrda> {

    ByteArrayInputStream generisiHTML(String id);

    ByteArrayInputStream generisiPDF(String id);

    Potvrda generisiPotvrdu(String saglasnostId);

    TDoza makeDozaFromTVakcina(TVakcina tvakc, int redniBroj);

    List<Potvrda> dobaviZaKorisnika(String email);
}
