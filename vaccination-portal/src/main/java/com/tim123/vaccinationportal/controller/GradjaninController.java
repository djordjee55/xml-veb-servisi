package com.tim123.vaccinationportal.controller;

import com.tim123.vaccinationportal.model.dto.GradjaninDto;
import com.tim123.vaccinationportal.service.GradjaninService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gradjanin")
public class GradjaninController {

    private final GradjaninService gradjaninService;

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> registracija(@RequestBody GradjaninDto gradjaninDto) {

        if (gradjaninService.registracija(gradjaninDto)) {
            return ResponseEntity.ok("Uspesna registracija");
        }

        return ResponseEntity.badRequest().body("Registracija neuspesna");
    }
}
