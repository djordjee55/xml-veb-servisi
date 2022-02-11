package com.tim123.vaccinationportal.controller;

import com.tim123.vaccinationportal.model.zahtev.Zahtev;
import com.tim123.vaccinationportal.service.ZahtevService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/zahtev")
public class ZahtevController {

    private final ZahtevService zahtevService;

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> dodajZahtev(@RequestBody Zahtev zahtev) {
        zahtevService.dodajZahtev(zahtev);
        return ResponseEntity.ok("Zahtev uspesno dodat");
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Zahtev> dobaviZahtev(@PathVariable String id) {
        return ResponseEntity.ok(zahtevService.dobaviZahtev(id));
    }
}
