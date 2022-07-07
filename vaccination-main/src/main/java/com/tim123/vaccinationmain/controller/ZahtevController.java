package com.tim123.vaccinationmain.controller;

import com.tim123.vaccinationmain.dto.dokumenta.ListaDokumenata;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/api/zahtev")
@RequiredArgsConstructor
public class ZahtevController {

    private final RestTemplate restTemplate;

    @GetMapping(value ="/novi")
    public ListaDokumenata noviZahtevi() {
        return restTemplate.getForObject("http://localhost:8082/zahtev/novi", ListaDokumenata.class);
    }
}
