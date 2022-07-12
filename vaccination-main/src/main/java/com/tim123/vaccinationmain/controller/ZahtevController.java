package com.tim123.vaccinationmain.controller;

import com.tim123.vaccinationmain.dto.OdbijZahtevDto;
import com.tim123.vaccinationmain.dto.dokumenta.ListaDokumenata;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/api/zahtev")
@RequiredArgsConstructor
public class ZahtevController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping(value ="/novi", produces = MediaType.APPLICATION_XML_VALUE)
    public ListaDokumenata noviZahtevi() {
        return restTemplate.getForObject("http://localhost:8082/api/zahtev/novi", ListaDokumenata.class);
    }

    @GetMapping(value = "/dokumenta-za-zahtev/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ListaDokumenata getDokumentaZaPodnosiocaZahteva(@PathVariable String id) {
        return restTemplate.getForObject("http://localhost:8082/api/dokumenta/dokumenta-za-zahtev/" + id, ListaDokumenata.class);
    }

    @PostMapping(value = "/accept/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Boolean> prihvatiZahtev(@PathVariable String id) {
        var b = restTemplate.getForObject("http://localhost:8082/api/zahtev/accept/"+id,  Boolean.class);
        return ResponseEntity.ok(b);
    }

    @PostMapping(value = "/reject/{id}/{razlog}")
    public ResponseEntity<Boolean> odbijZahtev(@PathVariable String id, @PathVariable String razlog) {
        return restTemplate.postForEntity("http://localhost:8082/api/zahtev/reject/"+id + "/" + razlog, null, Boolean.class);
    }
}
