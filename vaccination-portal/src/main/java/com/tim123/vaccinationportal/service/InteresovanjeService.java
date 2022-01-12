package com.tim123.vaccinationportal.service;

import com.tim123.vaccinationportal.model.interesovanje.Interesovanje;

public interface InteresovanjeService extends CRUDService<Interesovanje> {
    Interesovanje dodajInteresovanje(Interesovanje interesovanje);
    Interesovanje dobaviInteresovanje(String id);
}
