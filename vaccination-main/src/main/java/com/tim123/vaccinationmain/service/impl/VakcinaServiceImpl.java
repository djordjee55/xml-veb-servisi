package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.model.vakcina.TipVakcine;
import com.tim123.vaccinationmain.model.vakcina.Vakcina;
import com.tim123.vaccinationmain.repository.CRUDRepository;
import com.tim123.vaccinationmain.repository.VakcinaRepository;
import com.tim123.vaccinationmain.service.CekanjeService;
import com.tim123.vaccinationmain.service.VakcinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VakcinaServiceImpl extends CRUDServiceImpl<Vakcina> implements VakcinaService {

    private final VakcinaRepository vakcinaRepository;

    @Override
    protected CRUDRepository<Vakcina> getRepository() {
        return vakcinaRepository;
    }

    @Override
    public void azurirajKolicinu(TipVakcine tipVakcine, int kolicina, String ustanova, String id) {
        if(id == null) id = UUID.randomUUID().toString();
        var vakcina = new Vakcina(tipVakcine, kolicina, ustanova, id);
        try {
            this.save(vakcina);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nije moguce sacuvati vakcinu!");
        }
    }

    @Override
    public Vakcina dobaviVakcinu(String id) {
        var vak = this.findById(id);
        if (vak.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vakcina nije pronadjena");
        }
        return vak.get();
    }

    @Override
    public Boolean dostupnaVakcina(String vakcina, String ustanova) {
        return vakcinaRepository.checkQuantityForVaccine(vakcina, ustanova) > 0;
    }

    @Override
    public void smanjiKolicinu(String value, String id) {
        Vakcina vakcina = vakcinaRepository.getVakcinaZaUstanovu(value, id);
        vakcina.setKolicina(vakcina.getKolicina() - 1);
        try {
            save(vakcina);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}