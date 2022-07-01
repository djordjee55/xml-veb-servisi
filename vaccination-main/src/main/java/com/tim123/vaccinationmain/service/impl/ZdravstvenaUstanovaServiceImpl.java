package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.model.cekanje.Cekanje;
import com.tim123.vaccinationmain.model.termin.Termin;
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
import javax.xml.datatype.DatatypeFactory;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ZdravstvenaUstanovaServiceImpl extends CRUDServiceImpl<ZdravstvenaUstanova> implements ZdravstvenaUstanovaService {

    private final ZdravstvenaUstanovaRepository zdravstvenaUstanovaRepository;
    private final VakcinaService vakcinaService;
    private final CekanjeService cekanjeService;

    @Override
    protected CRUDRepository<ZdravstvenaUstanova> getRepository() {
        return zdravstvenaUstanovaRepository;
    }

    @Override
    public Termin dodeliTermin(ZeljeneVakcine zeljeneVakcine) {
        List<ZdravstvenaUstanova> zdravstveneUstanove = zdravstvenaUstanovaRepository.findAllForOpstina(zeljeneVakcine.getOpstina());
        for (ZdravstvenaUstanova zU : zdravstveneUstanove) {
            for (ZeljenaVakcina zV : zeljeneVakcine.getZeljeneVakcine()) {
                if (vakcinaService.dostupnaVakcina(zV.getValue(), zU.getId())) {
                    Termin termin = null;
                    try {
                        termin = this.napraviTermin(zU, zeljeneVakcine.getPacijent(), zV.getValue());
                    } catch (DatatypeConfigurationException e) {
                        e.printStackTrace();
                    }
                    vakcinaService.smanjiKolicinu(zV.getValue(), zU.getId());
                    return termin;
                }
            }
        }
        this.staviNaCekanje(zeljeneVakcine);
        return null;
    }

    private Termin napraviTermin(ZdravstvenaUstanova zdravstvenaUstanova, String pacijent, String vakcina) throws DatatypeConfigurationException {
        LocalDateTime novi;
        if (zdravstvenaUstanova.getTermini().size() == 0) {
            novi = LocalDateTime.now().plusDays(1);
            novi = novi.withHour(LocalTime.parse(zdravstvenaUstanova.getPocetakRadnogVremena()).getHour());
            novi = novi.withMinute(LocalTime.parse(zdravstvenaUstanova.getPocetakRadnogVremena()).getMinute());
        } else {
            Termin poslednji = zdravstvenaUstanova.getTermini().get(zdravstvenaUstanova.getTermini().size() - 1);
            novi = poslednji.getDatumVreme().toGregorianCalendar().toZonedDateTime().toLocalDateTime().plusMinutes(15);
            if (novi.toLocalTime().isAfter(LocalTime.parse(zdravstvenaUstanova.getKrajRadnogVremena()))) {
                novi = poslednji.getDatumVreme().toGregorianCalendar().toZonedDateTime().toLocalDateTime().plusDays(1);
                novi = novi.withHour(LocalTime.parse(zdravstvenaUstanova.getPocetakRadnogVremena()).getHour());
                novi = novi.withMinute(LocalTime.parse(zdravstvenaUstanova.getPocetakRadnogVremena()).getMinute());
            }
        }


        Termin noviTermin = new Termin(DatatypeFactory.newInstance().newXMLGregorianCalendar(novi.toString()), pacijent, vakcina);
        zdravstvenaUstanova.getTermini().add(noviTermin);
        try {
            zdravstvenaUstanovaRepository.save(zdravstvenaUstanova);
            return noviTermin;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void staviNaCekanje(ZeljeneVakcine zeljeneVakcine) {
        Cekanje cekanje = new Cekanje(zeljeneVakcine.getZeljeneVakcine(), zeljeneVakcine.getOpstina(),
                zeljeneVakcine.getPacijent(), UUID.randomUUID().toString());

        try {
            cekanjeService.save(cekanje);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
