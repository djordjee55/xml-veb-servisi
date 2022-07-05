package com.tim123.vaccinationportal.service;

import com.tim123.vaccinationportal.model.potvrda.Potvrda;

import java.io.ByteArrayInputStream;

public interface PotvrdaService extends CRUDService<Potvrda> {

    ByteArrayInputStream generisiHTML(String id);

    ByteArrayInputStream generisiPDF(String id);

    Potvrda generisiPotvrdu(String saglasnostId);
}
