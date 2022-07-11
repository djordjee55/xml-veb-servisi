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
}
