package com.tim123.vaccinationportal.controller;

import com.tim123.vaccinationportal.model.dto.DopuniEvidencijuDto;
import com.tim123.vaccinationportal.model.dto.vakcine.GetUstanovaStringDto;
import com.tim123.vaccinationportal.model.dto.vakcine.GetVakcinaStringDto;
import com.tim123.vaccinationportal.model.saglasnost.Saglasnost;
import com.tim123.vaccinationportal.service.SaglasnostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/saglasnost")
public class SaglasnostController {

    private final SaglasnostService saglasnostService;

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAnyAuthority('GRADJANIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Saglasnost dodajSaglasnost(@RequestBody Saglasnost saglasnost, Authentication authentication) {
        return saglasnostService.dodajSaglasnost(saglasnost, authentication.getName());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Saglasnost> dobaviSaglasnost(@PathVariable String id) {
        return ResponseEntity.ok(saglasnostService.dobaviSaglasnost(id));
    }

    @PostMapping(value = "/evidencija/{id}", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> dopuniEvidenciju(@PathVariable String id, @RequestBody DopuniEvidencijuDto dopuniEvidencijuDto) {
        try {
            saglasnostService.dopuniEvidenciju(id, dopuniEvidencijuDto);
        } catch (Exception e) {
            e.printStackTrace();
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }
        return ResponseEntity.ok("Evidencija uspesno dopunjena");
    }

    @GetMapping(value = "/vakcina-by-user/{userEmail}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> vakcinaByUsername(@PathVariable String userEmail) {
            List<String> retVal = saglasnostService.vakcinaByUsername(userEmail);
        return ResponseEntity.ok(new GetVakcinaStringDto(retVal));

    }

    @GetMapping(value = "/ustanova-za-vakcinisanje/{userEmail}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> ustanovaByUsername(@PathVariable String userEmail) {
        List<String> retVal = saglasnostService.ustanovaByUsername(userEmail);
        return ResponseEntity.ok(new GetUstanovaStringDto(retVal));

    }


}
