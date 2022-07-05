package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.model.termin.Termin;
import com.tim123.vaccinationmain.model.termin.TerminUstanova;
import com.tim123.vaccinationmain.model.vakcina.ZeljenaVakcina;
import com.tim123.vaccinationmain.model.vakcina.ZeljeneVakcine;
import com.tim123.vaccinationmain.model.zdravstvenaUstanova.ZdravstvenaUstanova;
import com.tim123.vaccinationmain.repository.CRUDRepository;
import com.tim123.vaccinationmain.repository.ZdravstvenaUstanovaRepository;
import com.tim123.vaccinationmain.service.CekanjeService;
import com.tim123.vaccinationmain.service.VakcinaService;
import com.tim123.vaccinationmain.service.ZdravstvenaUstanovaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ZdravstvenaUstanovaServiceImpl extends CRUDServiceImpl<ZdravstvenaUstanova> implements ZdravstvenaUstanovaService {

    private final ZdravstvenaUstanovaRepository zdravstvenaUstanovaRepository;
    private final VakcinaService vakcinaService;
    private final CekanjeService cekanjeService;
    private final TerminService terminService;

    @Override
    protected CRUDRepository<ZdravstvenaUstanova> getRepository() {
        return zdravstvenaUstanovaRepository;
    }

    @Override
    public TerminUstanova dodeliTermin(ZeljeneVakcine zeljeneVakcine) {
        List<ZdravstvenaUstanova> zdravstveneUstanove = zdravstvenaUstanovaRepository.findAllForOpstina(zeljeneVakcine.getOpstina());
        for (ZdravstvenaUstanova zU : zdravstveneUstanove) {
            for (ZeljenaVakcina zV : zeljeneVakcine.getZeljeneVakcine()) {
                if (vakcinaService.dostupnaVakcina(zV.getValue(), zU.getId())) {
                    Termin termin = null;
                    try {
                        termin = terminService.napraviTermin(zU, zeljeneVakcine.getPacijent(), zV.getValue());
                    } catch (DatatypeConfigurationException e) {
                        e.printStackTrace();
                    }
                    vakcinaService.smanjiKolicinu(zV.getValue(), zU.getId());
                    TerminUstanova tU = new TerminUstanova();
                    tU.setUstanova(zU.getNaziv() + " u " + zU.getOpstina());
                    tU.setPacijent(termin.getPacijent());
                    tU.setVakcina(termin.getVakcina());
                    tU.setDatumVreme(termin.getDatumVreme());
                    return tU;
                }
            }
        }
        cekanjeService.staviNaCekanje(zeljeneVakcine);
        return null;
    }
}
