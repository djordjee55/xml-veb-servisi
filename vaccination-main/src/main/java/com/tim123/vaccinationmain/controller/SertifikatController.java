package com.tim123.vaccinationmain.controller;

import com.tim123.vaccinationmain.model.sertifikat.Sertifikat;
import com.tim123.vaccinationmain.model.tipovi.TVakcinisanoLice;
import com.tim123.vaccinationmain.service.SertifikatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/sertifikat")
@RequiredArgsConstructor
public class SertifikatController {

    private final SertifikatService sertifikatService;

    @PostMapping(value = "/")
    public ResponseEntity<Sertifikat> generisiSertifikat(@RequestBody TVakcinisanoLice lice) {
        var s = sertifikatService.generisiSertifikat(lice);
        return ResponseEntity.ok(s);
    }
}
