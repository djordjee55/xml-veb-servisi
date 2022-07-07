package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.exception.NotFoundException;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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

    @Override
    public List<String> ustanovaByUsername(String userEmail) throws NotFoundException {
        List<ZdravstvenaUstanova> ustanove = zdravstvenaUstanovaRepository.getByPacijentEmail(userEmail);

        if (ustanove.isEmpty())
            throw new NotFoundException("Korisnik nema zakazani termin...");

        List<Termin> sviTermini = new ArrayList<>();
        List<Termin> finalSviTermini = sviTermini;
        ustanove.forEach(u -> finalSviTermini.addAll(u.getTermini()));

        sviTermini = sviTermini.stream().filter(t -> t.getPacijent().equals(userEmail)).collect(Collectors.toList());
        sviTermini.sort((t1, t2) -> t2.getDatumVreme().compare(t1.getDatumVreme()));
        Termin termin = sviTermini.get(0);

        ZdravstvenaUstanova ustanova = ustanove.stream().filter(u -> u.getTermini()
                .contains(termin))
                .findFirst()
                .orElseThrow(
                    () -> new NotFoundException("Korisnik nema zakazan termin"));

        return List.of(ustanova.getNaziv());

    }

    @Override
    public List<String> vakcinaByUsername(String userEmail) {
        List<ZdravstvenaUstanova> ustanove = zdravstvenaUstanovaRepository.getByPacijentEmail(userEmail);
        ZdravstvenaUstanova ustanova;

        if (ustanove.isEmpty())
            throw new NotFoundException("Korisnik nema zakazani termin...");

        List<Termin> sviTermini = new ArrayList<>();
        List<Termin> finalSviTermini = sviTermini;
        ustanove.forEach(u -> finalSviTermini.addAll(u.getTermini()));

        sviTermini = sviTermini.stream().filter(t -> t.getPacijent().equals(userEmail)).collect(Collectors.toList());
        sviTermini.sort((t1, t2) -> t2.getDatumVreme().compare(t1.getDatumVreme()));
        Termin termin = sviTermini.get(0);


        switch (termin.getVakcina()) {
            case "PHIZER_BIONTECH":
                return List.of("Pfizer-BioNTech/Pfizer Inc.");
            case "SPUTNIK_V":
                return List.of("Sputnik V/NRCEM GAMALEYA");
            case "SINOPHARM":
                return List.of("Sinopharm/China National Pharmaceutical Group (CNPGC)");
            case "ASTRA_ZENECA":
                return List.of("AstraZeneca/AstraZeneca L.T.D.");
            case "MODERNA":
                return List.of("Sinopharm/Moderna, Inc");
            default:
                throw new NotFoundException("U terminu nije pronadjena odgovarajuca vakcina");

        }
    }
}
