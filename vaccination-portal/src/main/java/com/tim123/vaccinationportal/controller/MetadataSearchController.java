package com.tim123.vaccinationportal.controller;

import com.tim123.vaccinationportal.model.dto.dokumenta.ListaDokumenata;
import com.tim123.vaccinationportal.service.MetadataSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("api/metadata-search")
@RequiredArgsConstructor
public class MetadataSearchController {

    private final MetadataSearchService metadataSearchService;

    @GetMapping(value = "/interesovanje", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ListaDokumenata> searchInteresovanje(@RequestParam String filter) {
        try {
            filter = URLDecoder.decode(filter, StandardCharsets.UTF_8);
        } catch (Exception ignore) {}
        return ResponseEntity.ok(metadataSearchService.searchInteresovanje(filter));
    }
}
