package com.tim123.vaccinationportal.controller;

import com.tim123.vaccinationportal.model.dto.OdbijZahtevDto;
import com.tim123.vaccinationportal.model.dto.dokumenta.ListaDokumenata;
import com.tim123.vaccinationportal.model.zahtev.Zahtev;
import com.tim123.vaccinationportal.service.ZahtevService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/zahtev")
public class ZahtevController {

    private final ZahtevService zahtevService;

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAnyAuthority('GRADJANIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Zahtev dodajZahtev(@RequestBody Zahtev zahtev, Authentication authentication) {
        return zahtevService.dodajZahtev(zahtev, authentication.getName());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Zahtev> dobaviZahtev(@PathVariable String id) {
        return ResponseEntity.ok(zahtevService.dobaviZahtev(id));
    }

    @GetMapping(value = "/count/{startDate}/{endDate}")
    public int prebrojZahteveZaPeriod(@PathVariable String startDate, @PathVariable String endDate) throws ParseException {
        return zahtevService.prebrojZahteveZaPeriod(startDate, endDate);
    }

    @GetMapping(value = "/html/{id}")
    public ResponseEntity<InputStreamResource> generisiHTML(@PathVariable UUID id) throws Exception {
        ByteArrayInputStream stream = zahtevService.generisiHTML(id.toString());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=details.html");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.TEXT_HTML)
                .body(new InputStreamResource(stream));
    }

    @GetMapping(value = "/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generisiPDF(@PathVariable UUID id) throws Exception {
        ByteArrayInputStream stream = zahtevService.generisiPDF(id.toString());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=details.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(stream));
    }

    @PostMapping(value = "/reject/{requestId}/{razlog}")
    public Boolean rejectRequest(@PathVariable String requestId, @PathVariable String razlog) {
        zahtevService.odbijZahtev(requestId, razlog);
        return Boolean.TRUE;
    }

    @GetMapping(value = "/accept/{requestId}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Boolean> acceptRequest(@PathVariable String requestId) {
        zahtevService.prihvatiZahtev(requestId);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @GetMapping(value = "/search-by-string")
    public String searchByString(@RequestParam String searchedString) {
        return zahtevService.searchByString(searchedString);
    }

    @GetMapping(value = "/novi", produces = MediaType.APPLICATION_XML_VALUE)
    public ListaDokumenata noviZahtevi() {
        return zahtevService.getNoviZahtevi();
    }
}
