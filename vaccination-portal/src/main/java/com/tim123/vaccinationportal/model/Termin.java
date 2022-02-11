package com.tim123.vaccinationportal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Time;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Termin {

    private String domZdravlja;
    private Time vreme;
    private LocalDate datum;
    private Boolean izvrsenaVakcinacija;
}
