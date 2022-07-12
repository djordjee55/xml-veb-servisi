package com.tim123.vaccinationportal.controller;

import com.tim123.vaccinationportal.model.Korisnik;
import com.tim123.vaccinationportal.model.potvrda.TVakcinacija;
import com.tim123.vaccinationportal.service.KorisnikService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/korisnik")
@RequiredArgsConstructor
public class KorisnikController {

    private final KorisnikService korisnikService;

    @GetMapping(value = "/vakcine")
    public TVakcinacija dobaviVakcine(@RequestParam(required = false) String jmbg,
                                                      @RequestParam(required = false) String pasos) {
        var v = korisnikService.dobaviVakcine(jmbg, pasos);
        return v;
    }

    @GetMapping(value = "/get-id")
    public ResponseEntity<Korisnik> dobaviId(@RequestParam(required = false) String jmbg,
                                             @RequestParam(required = false) String pasos) {
        var k = korisnikService.findByDocumentId(jmbg, pasos);
        if (k == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nema korisnika");
        }
        return ResponseEntity.ok(k);
    }
}
