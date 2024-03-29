package com.tim123.vaccinationportal.controller;

import com.tim123.vaccinationportal.model.dto.DopuniEvidencijuDto;
import com.tim123.vaccinationportal.model.dto.vakcine.GetUstanovaStringDto;
import com.tim123.vaccinationportal.model.dto.vakcine.GetVakcinaStringDto;
import com.tim123.vaccinationportal.model.interesovanje.Interesovanje;
import com.tim123.vaccinationportal.model.saglasnost.Saglasnost;
import com.tim123.vaccinationportal.service.MarshallUnmarshallService;
import com.tim123.vaccinationportal.service.SaglasnostService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
import java.io.ByteArrayInputStream;
import java.util.UUID;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/saglasnost")
public class SaglasnostController {

    private final SaglasnostService saglasnostService;
    private final MarshallUnmarshallService<Saglasnost> saglasnostMarshallUnmarshallService;

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

    @GetMapping(value = "/str/{id}")
    public ResponseEntity<String> dobaviInteresovanjeStr(@PathVariable String id) throws JAXBException {
        var i = saglasnostService.dobaviSaglasnost(id);
        var s = saglasnostMarshallUnmarshallService.marshall(i, Saglasnost.class);
        return ResponseEntity.ok(s);
    }

    @PostMapping(value = "/evidencija/{id}", consumes = MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAnyAuthority('ZDRAVSTVENI_RADNIK')")
    public ResponseEntity<?> dopuniEvidenciju(@PathVariable String id, @RequestBody DopuniEvidencijuDto dopuniEvidencijuDto, Authentication authentication) {
        try {
            saglasnostService.dopuniEvidenciju(id, dopuniEvidencijuDto, authentication.getName());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok("Evidencija uspesno dopunjena");
    }

    @GetMapping(value = "/html/{id}")
    public ResponseEntity<InputStreamResource> generisiHTML(@PathVariable UUID id) throws Exception {
        ByteArrayInputStream stream = saglasnostService.generisiHTML(id.toString());
        if(stream == null)
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=details.html");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.TEXT_HTML)
                .body(new InputStreamResource(stream));
    }

    @GetMapping(value = "/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generisiPDF(@PathVariable UUID id) throws Exception {
        ByteArrayInputStream stream = saglasnostService.generisiPDF(id.toString());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=details.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(stream));
    }
    
    @GetMapping(value = "/vakcina-by-user/{userEmail}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> vakcinaByUsername(@PathVariable String userEmail) {
        return ResponseEntity.ok(saglasnostService.vakcinaByUsername(userEmail));
    }

    @GetMapping(value = "/ustanova-za-vakcinisanje/{userEmail}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> ustanovaByUsername(@PathVariable String userEmail) {
        return ResponseEntity.ok(saglasnostService.ustanovaByUsername(userEmail));
    }

    @GetMapping(value = "/search-by-string")
    public String searchByString(@RequestParam String searchedString) {
        return saglasnostService.searchByString(searchedString);
    }
}
