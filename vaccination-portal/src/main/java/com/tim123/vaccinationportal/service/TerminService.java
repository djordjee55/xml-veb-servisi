package com.tim123.vaccinationportal.service;

import com.tim123.vaccinationportal.model.Termin;

import java.time.LocalDate;

public interface TerminService {

    Termin kreirajNoviTermin(String domZdravlja, LocalDate datum);
}
