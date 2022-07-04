package com.tim123.vaccinationportal.controller;

import com.tim123.vaccinationportal.model.interesovanje.Interesovanje;
import com.tim123.vaccinationportal.service.InteresovanjeService;
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
@RequestMapping("/api/interesovanje")
@RequiredArgsConstructor
public class InteresovanjeController {

    private final InteresovanjeService interesovanjeService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('GRADJANIN')")
    public Interesovanje dodajInteresovanje(@RequestBody Interesovanje interesovanje, Authentication authentication) {
        return interesovanjeService.dodajInteresovanje(interesovanje, authentication.getName());
    }

    @GetMapping(value = "/dozvoljeno")
    @PreAuthorize("hasAnyAuthority('GRADJANIN')")
    public Boolean dozvoljenoDodavanjeInteresovanja(Authentication authentication) {
        return interesovanjeService.dozvoljenoDodavanje(authentication.getName());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public Interesovanje dobaviInteresovanje(@PathVariable String id) {
        return interesovanjeService.dobaviInteresovanje(id);
    }

    @GetMapping(value = "/html/{id}")
    public ResponseEntity<InputStreamResource> generisiHTML(@PathVariable UUID id) throws Exception {
        ByteArrayInputStream stream = interesovanjeService.generisiHTML(id.toString());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=details.html");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.TEXT_HTML)
                .body(new InputStreamResource(stream));
    }

    @GetMapping(value = "/pdf/{id}")
    public ResponseEntity<InputStreamResource> generisiPDF(@PathVariable UUID id) throws Exception {
        ByteArrayInputStream stream = interesovanjeService.generisiPDF(id.toString());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=details.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(stream));
    }

    @GetMapping(value = "/count/{startDate}/{endDate}")
    public int prebrojInteresovanjaZaPeriod(@PathVariable String startDate, @PathVariable String endDate) throws ParseException {
        return interesovanjeService.prebrojInteresovanjaZaPeriod(startDate, endDate);
    }
}
