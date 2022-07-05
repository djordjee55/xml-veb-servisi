package com.tim123.vaccinationportal.controller;

import com.tim123.vaccinationportal.model.dto.OdbijZahtevDto;
import com.tim123.vaccinationportal.model.zahtev.Zahtev;
import com.tim123.vaccinationportal.service.ZahtevService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

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

    @GetMapping(value = "/count/{startDate}/{endDate}")
    public int prebrojZahteveZaPeriod(@PathVariable String startDate, @PathVariable String endDate) throws ParseException {
        return zahtevService.prebrojZahteveZaPeriod(startDate, endDate);
    }

    @PostMapping(value = "/reject/{requestId}", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Boolean> rejectRequest(@PathVariable String requestId, @RequestBody OdbijZahtevDto odbijZahtevDto) {
        zahtevService.odbijZahtev(requestId, odbijZahtevDto.getRazlog());
        return ResponseEntity.ok(true);
    }

    @PostMapping(value = "/accept/{requestId}")
        public ResponseEntity<Boolean> acceptRequest(@PathVariable String requestId) {
        zahtevService.prihvatiZahtev(requestId);
        return ResponseEntity.ok(true);
    }
}
