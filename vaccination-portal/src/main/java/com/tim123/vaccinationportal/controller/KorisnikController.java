package com.tim123.vaccinationportal.controller;

import com.tim123.vaccinationportal.model.potvrda.TVakcinacija;
import com.tim123.vaccinationportal.service.KorisnikService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/korisnik")
@RequiredArgsConstructor
public class KorisnikController {

    private final KorisnikService korisnikService;

    @GetMapping(value = "/vakcine")
    public ResponseEntity<TVakcinacija> dobaviVakcine(@RequestParam(required = false) String jmbg,
                                                      @RequestParam(required = false) String pasos) {
        var v = korisnikService.dobaviVakcine(jmbg, pasos);
        return ResponseEntity.ok(v);
    }
}
