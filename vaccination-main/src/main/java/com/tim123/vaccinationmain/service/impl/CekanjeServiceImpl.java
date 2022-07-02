package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.model.cekanje.Cekanje;
import com.tim123.vaccinationmain.model.vakcina.Vakcina;
import com.tim123.vaccinationmain.model.vakcina.ZeljeneVakcine;
import com.tim123.vaccinationmain.repository.CRUDRepository;
import com.tim123.vaccinationmain.repository.CekanjeRepository;
import com.tim123.vaccinationmain.repository.VakcinaRepository;
import com.tim123.vaccinationmain.repository.ZdravstvenaUstanovaRepository;
import com.tim123.vaccinationmain.service.CekanjeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CekanjeServiceImpl extends CRUDServiceImpl<Cekanje> implements CekanjeService {

    private final CekanjeRepository cekanjeRepository;
    private final ZdravstvenaUstanovaRepository zdravstvenaUstanovaRepository;
    private final VakcinaRepository vakcinaRepository;
    private final TerminService terminService;

    @Override
    protected CRUDRepository<Cekanje> getRepository() {
        return cekanjeRepository;
    }

    @Override
    public void pokusajZakazati(String ustanova) {
        String opstina = zdravstvenaUstanovaRepository.getOpstinaForUstanova(ustanova);
        List<Cekanje> cekanja = cekanjeRepository.getCekanjeZaOpstinu(opstina).stream().filter(c -> !c.isObradjen()).collect(Collectors.toList());

        for (Cekanje cekanje : cekanja) {
            for (var vakcina : cekanje.getZeljeneVakcine()) {
                if (vakcinaRepository.checkQuantityForVaccine(vakcina.getValue(), ustanova) > 0) {
                    try {
                        terminService.napraviTermin(zdravstvenaUstanovaRepository.findById(ustanova), cekanje.getPacijent(), vakcina.getValue());
                        cekanje.setObradjen(true);
                        cekanjeRepository.save(cekanje);

                        Vakcina vakcina1 = vakcinaRepository.getVakcinaZaUstanovu(vakcina.getValue(), ustanova);
                        vakcina1.setKolicina(vakcina1.getKolicina() - 1);
                        vakcinaRepository.save(vakcina1);
                    } catch (Exception e) {
                        System.out.println("Problem prilikom pravvljenja termina!");
                    }
                }
            }
        }
    }

    @Override
    public void staviNaCekanje(ZeljeneVakcine zeljeneVakcine) {
        Cekanje cekanje = new Cekanje(zeljeneVakcine.getZeljeneVakcine(), zeljeneVakcine.getOpstina(),
                zeljeneVakcine.getPacijent(), UUID.randomUUID().toString(), false);

        try {
            this.save(cekanje);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
