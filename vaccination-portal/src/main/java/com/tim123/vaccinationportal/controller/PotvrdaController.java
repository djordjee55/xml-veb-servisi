package com.tim123.vaccinationportal.controller;


import com.tim123.vaccinationportal.service.PotvrdaService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/potvrda")
public class PotvrdaController {

    private final PotvrdaService potvrdaService;

    @GetMapping(value = "/html/{id}")
    @PreAuthorize("hasAnyAuthority('ZDRAVSTVENI_RADNIK')")
    public ResponseEntity<?> generisiHTML(@PathVariable UUID id) throws Exception {
        ByteArrayInputStream stream = potvrdaService.generisiHTML(id.toString());
        if(stream == null)
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=details.html");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.TEXT_HTML)
                .body(new InputStreamResource(stream));
    }

    @GetMapping(value = "/pdf/{id}")
    @PreAuthorize("hasAnyAuthority('ZDRAVSTVENI_RADNIK')")
    public ResponseEntity<InputStreamResource> generisiPDF(@PathVariable UUID id) throws Exception {
        ByteArrayInputStream stream = potvrdaService.generisiPDF(id.toString());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=details.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(stream));
    }

    @GetMapping(value = "/izdaj/{id}")
    @PreAuthorize("hasAnyAuthority('ZDRAVSTVENI_RADNIK')")
    public ResponseEntity<?> izdajPotvrdu(@PathVariable UUID id) throws Exception {
        String potvrdaId = potvrdaService.izadaj(id.toString());
        if(potvrdaId == null)
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_XML)
                .body(potvrdaId);
    }
}
