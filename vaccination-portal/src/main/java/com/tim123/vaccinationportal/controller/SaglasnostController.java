package com.tim123.vaccinationportal.controller;

import com.tim123.vaccinationportal.model.dto.DopuniEvidencijuDto;
import com.tim123.vaccinationportal.model.saglasnost.Saglasnost;
import com.tim123.vaccinationportal.service.SaglasnostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/saglasnost")
public class SaglasnostController {

    private final SaglasnostService saglasnostService;

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> dodajSaglasnost(@RequestBody Saglasnost saglasnost) {
        saglasnostService.dodajSaglasnost(saglasnost);
        return ResponseEntity.ok("Saglasnost uspesno dodata");
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Saglasnost> dobaviSaglasnost(@PathVariable String id) {
        return ResponseEntity.ok(saglasnostService.dobaviSaglasnost(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> dopuniEvidenciju(@RequestBody DopuniEvidencijuDto dopuniEvidencijuDto) {
        saglasnostService.dopuniEvidenciju(dopuniEvidencijuDto);
        return ResponseEntity.ok("Saglasnost uspesno dodata");
    }
}
