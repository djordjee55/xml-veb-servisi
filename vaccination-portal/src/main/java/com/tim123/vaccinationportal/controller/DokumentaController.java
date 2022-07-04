package com.tim123.vaccinationportal.controller;

import com.tim123.vaccinationportal.model.dto.dokumenta.ListaDokumenata;
import com.tim123.vaccinationportal.service.impl.DokumentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/dokumenta")
@RequiredArgsConstructor
public class DokumentaController {

    private final DokumentaService dokumentaService;

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAnyAuthority('GRADJANIN')")
    public ListaDokumenata mojaDokumenta(Authentication authentication) {
        return dokumentaService.mojaDokumenta(authentication.getName());
    }

}
