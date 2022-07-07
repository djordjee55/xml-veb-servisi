package com.tim123.vaccinationmain.service;

import com.tim123.vaccinationmain.model.vakcina.TipVakcine;
import com.tim123.vaccinationmain.model.vakcina.Vakcina;

import java.util.List;

public interface VakcinaService extends CRUDService<Vakcina> {
    void azurirajKolicinu(TipVakcine tipVakcine, int kolicina, String opstina, String id);
    Vakcina dobaviVakcinu(String id);

    Boolean dostupnaVakcina(String vakcina, String ustanova);

    void smanjiKolicinu(String value, String id);

    List<Vakcina> getVakcineZaUstanovu(String id);
}

