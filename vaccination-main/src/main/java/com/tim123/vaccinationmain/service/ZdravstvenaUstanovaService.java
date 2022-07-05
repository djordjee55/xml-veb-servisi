package com.tim123.vaccinationmain.service;

import com.tim123.vaccinationmain.model.termin.Termin;
import com.tim123.vaccinationmain.model.termin.TerminUstanova;
import com.tim123.vaccinationmain.model.vakcina.ZeljeneVakcine;
import com.tim123.vaccinationmain.model.zdravstvenaUstanova.ZdravstvenaUstanova;

import java.util.List;

public interface ZdravstvenaUstanovaService extends CRUDService<ZdravstvenaUstanova> {
    TerminUstanova dodeliTermin(ZeljeneVakcine zeljeneVakcine);

    List<String> ustanovaByUsername(String userEmail);

    List<String> vakcinaByUsername(String userEmail);
}
