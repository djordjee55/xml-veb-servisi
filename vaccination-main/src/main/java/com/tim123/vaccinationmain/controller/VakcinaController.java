package com.tim123.vaccinationmain.controller;

import com.tim123.vaccinationmain.dto.AzurirajVakcinuDTO;
import com.tim123.vaccinationmain.model.vakcina.Vakcina;
import com.tim123.vaccinationmain.service.CekanjeService;
import com.tim123.vaccinationmain.service.VakcinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vakcina")
@RequiredArgsConstructor
public class VakcinaController {

    private final VakcinaService vakcinaService;
    private final CekanjeService cekanjeService;


    @PostMapping(path = "/azuriraj", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> azurirajVakcinu(@RequestBody AzurirajVakcinuDTO azurirajVakcinuDTO) {
        vakcinaService.azurirajKolicinu(azurirajVakcinuDTO.getVakcina(), azurirajVakcinuDTO.getKolicina(), azurirajVakcinuDTO.getUstanova(), azurirajVakcinuDTO.getId());
        cekanjeService.pokusajZakazati(azurirajVakcinuDTO.getUstanova());
        return ResponseEntity.ok("Uspesno azurirano");
    }

    @GetMapping(path = "/kolicina/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Vakcina> dobaviVakcinu(@PathVariable String id) {
        return ResponseEntity.ok(vakcinaService.dobaviVakcinu(id));
    }
}

