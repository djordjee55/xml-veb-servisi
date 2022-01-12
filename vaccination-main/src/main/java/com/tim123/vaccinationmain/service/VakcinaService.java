package com.tim123.vaccinationmain.service;

import com.tim123.vaccinationmain.model.vakcina.TipVakcine;
import com.tim123.vaccinationmain.model.vakcina.Vakcina;

public interface VakcinaService extends CRUDService<Vakcina> {
    void azurirajKolicinu(TipVakcine tipVakcine, int kolicina);
    Vakcina dobaviVakcinu(String id);
}

