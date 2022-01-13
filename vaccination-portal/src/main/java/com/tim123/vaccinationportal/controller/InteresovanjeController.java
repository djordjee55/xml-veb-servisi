package com.tim123.vaccinationportal.controller;

import com.tim123.vaccinationportal.model.interesovanje.Interesovanje;
import com.tim123.vaccinationportal.service.InteresovanjeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interesovanje")
@RequiredArgsConstructor
public class InteresovanjeController {

    private final InteresovanjeService interesovanjeService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> dodajInteresovanje(@RequestBody Interesovanje interesovanje) {
        interesovanjeService.dodajInteresovanje(interesovanje);
        return ResponseEntity.ok("Interesovanje uspesno dodato");
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Interesovanje> dobaviInteresovanje(@PathVariable String id) {
        var interesovanje = interesovanjeService.dobaviInteresovanje(id);
        return ResponseEntity.ok(interesovanje);
    }

}
