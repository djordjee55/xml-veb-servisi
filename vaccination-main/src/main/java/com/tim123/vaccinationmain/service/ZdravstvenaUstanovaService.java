package com.tim123.vaccinationmain.service;

import com.tim123.vaccinationmain.model.termin.Termin;
import com.tim123.vaccinationmain.model.vakcina.ZeljeneVakcine;
import com.tim123.vaccinationmain.model.zdravstvenaUstanova.ZdravstvenaUstanova;

public interface ZdravstvenaUstanovaService extends CRUDService<ZdravstvenaUstanova> {
    Termin dodeliTermin(ZeljeneVakcine zeljeneVakcine);
}
