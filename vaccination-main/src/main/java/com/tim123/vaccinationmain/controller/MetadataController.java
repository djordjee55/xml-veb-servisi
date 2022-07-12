package com.tim123.vaccinationmain.controller;

import com.tim123.vaccinationmain.dto.dokumenta.Metapodatak;
import com.tim123.vaccinationmain.dto.dokumenta.TipDokumenta;
import com.tim123.vaccinationmain.grddl.MetadataExtractor;
import com.tim123.vaccinationmain.service.MetadataExtractorService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/metadata")
@RequiredArgsConstructor
public class MetadataController {

    private final MetadataExtractorService metadataExtractorService;

    @GetMapping()
    public ResponseEntity<Resource> exportMetadata(@RequestParam TipDokumenta tip,
                                                   @RequestParam Metapodatak vrsta,
                                                   @RequestParam String id) {

        var stream = metadataExtractorService.extractMetadata(tip, vrsta, id);

        HttpHeaders headers = new HttpHeaders();
        var name = id + "-meta.xml";
        if (vrsta == Metapodatak.JSON) {
            name = id + "-meta.json";
        }
        headers.add("Content-Disposition", "inline; filename=" + name);
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(stream));

    }
}
