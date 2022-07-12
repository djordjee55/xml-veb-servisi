package com.tim123.vaccinationmain.controller;

import com.tim123.vaccinationmain.dto.dokumenta.ListaDokumenata;
import com.tim123.vaccinationmain.dto.dokumenta.TipDokumenta;
import com.tim123.vaccinationmain.service.MetadataSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/metadata-search")
@RequiredArgsConstructor
public class MetadataSearchController {

    private final MetadataSearchService metadataSearchService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ListaDokumenata> search(@RequestParam TipDokumenta type, @RequestParam String query) {
        var result = metadataSearchService.search(type, query);
        return ResponseEntity.ok(result);
    }
}
