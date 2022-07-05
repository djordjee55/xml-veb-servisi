package com.tim123.vaccinationmain.controller;

import com.tim123.vaccinationmain.model.sertifikat.Sertifikat;
import com.tim123.vaccinationmain.model.tipovi.TVakcinisanoLice;
import com.tim123.vaccinationmain.service.SertifikatService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/sertifikat")
@RequiredArgsConstructor
public class SertifikatController {

    private final SertifikatService sertifikatService;

    @PostMapping(value = "/", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Sertifikat> generisiSertifikat(@RequestBody TVakcinisanoLice lice) {
        var s = sertifikatService.generisiSertifikat(lice);
        return ResponseEntity.ok(s);
    }

    @GetMapping(value = "/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generisiPDF(@PathVariable UUID id) {
        ByteArrayInputStream stream = sertifikatService.generisiPDF(id.toString());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=details.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(stream));
    }
}
