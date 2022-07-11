package com.tim123.vaccinationmain.controller;

import com.tim123.vaccinationmain.service.DocumentSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class DocumentSearchController {

    private final DocumentSearchService documentSearchService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_XML_VALUE)
    public String searchByString(@RequestParam String searchedString) {
        return documentSearchService.searchDocumentsByString(searchedString);
    }

//    @GetMapping("/interesovanje")
//    public String getInteresovanjeAdvenced(@RequestParam(required = false) String ime, @RequestParam(required = false) String prezime,
//                                           @RequestParam(required = false) String id_broj, @RequestParam(required = false) String lokacija) {
//        return pretragaService.getInteresovanjeAdvenced(ime, prezime, id_broj, lokacija);
//    }
//
//    @GetMapping("/saglasnosti")
//    public String getSaglasnostAdvenced(@RequestParam(required = false) String ime, @RequestParam(required = false) String prezime,
//                                        @RequestParam(required = false) String id_broj, @RequestParam(required = false) String lokacija, @RequestParam(required = false) String pol) {
//        return pretragaService.getSaglasnostAdvenced(ime, prezime, id_broj, lokacija, pol);
//    }
//
//    @GetMapping("/potvrda")
//    public String getPotvrdaAdvenced(@RequestParam(required = false) String ime, @RequestParam(required = false) String prezime,
//                                     @RequestParam(required = false) String id_broj) {
//        return pretragaService.getPotvrdaAdvenced(ime, prezime, id_broj);
//    }
//
//    @GetMapping("/sertifikat")
//    public String getSertifikatAdvenced(@RequestParam(required = false) String ime, @RequestParam(required = false) String prezime,
//                                        @RequestParam(required = false) String id_broj, @RequestParam(required = false) String pol) {
//        return pretragaService.getSertifikatAdvenced(ime, prezime, id_broj, pol);
//    }
//
//    @GetMapping("/zahtev")
//    public String getZahtevAdvenced(@RequestParam(required = false) String ime, @RequestParam(required = false) String prezime,
//                                    @RequestParam(required = false) String id_broj, @RequestParam(required = false) String pol) {
//        return pretragaService.getZahtevAdvenced(ime, prezime, id_broj, pol);
//    }
}
