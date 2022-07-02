package com.tim123.vaccinationmain.service;

import com.tim123.vaccinationmain.model.cekanje.Cekanje;
import com.tim123.vaccinationmain.model.vakcina.ZeljeneVakcine;

public interface CekanjeService extends CRUDService<Cekanje>{
    void pokusajZakazati(String ustanova);
    void staviNaCekanje(ZeljeneVakcine zeljeneVakcine);
}
