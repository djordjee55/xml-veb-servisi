package com.tim123.vaccinationmain.controller;

import com.tim123.vaccinationmain.dto.AzurirajVakcinuDTO;
import com.tim123.vaccinationmain.model.vakcina.Vakcina;
import com.tim123.vaccinationmain.service.VakcinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vaccine")
@RequiredArgsConstructor
public class VakcinaController {

    private final VakcinaService vakcinaService;

    @PostMapping(path = "/update", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> updateVaccineQuantity(@RequestBody AzurirajVakcinuDTO azurirajVakcinuDTO) {
        vakcinaService.azurirajKolicinu(azurirajVakcinuDTO.getVakcina(), azurirajVakcinuDTO.getKolicina());
        return ResponseEntity.ok("Uspesno azurirano");
    }

    @GetMapping(path = "/get-quantity/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Vakcina> getVaccineQuantity(@PathVariable String id) {
        return ResponseEntity.ok(vakcinaService.dobaviVakcinu(id));
    }
}

