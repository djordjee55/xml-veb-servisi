package com.tim123.vaccinationmain.controller;

import com.tim123.vaccinationmain.dto.GetVakcinaStringDto;
import com.tim123.vaccinationmain.model.termin.Termin;
import com.tim123.vaccinationmain.model.termin.TerminUstanova;
import com.tim123.vaccinationmain.model.vakcina.ZeljeneVakcine;
import com.tim123.vaccinationmain.service.ZdravstvenaUstanovaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zdravstvena-ustanova")
@RequiredArgsConstructor
public class ZdravstvenaUstanovaController {

    private final ZdravstvenaUstanovaService zdravstvenaUstanovaService;

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public TerminUstanova dodeliTermin(@RequestBody ZeljeneVakcine zeljeneVakcine) {
        return zdravstvenaUstanovaService.dodeliTermin(zeljeneVakcine);
    }

    @GetMapping(value = "/vakcina-by-user/{userEmail}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> vakcinaByUsername(@PathVariable String userEmail) {
        List<String> retVal = zdravstvenaUstanovaService.vakcinaByUsername(userEmail);
        return ResponseEntity.ok(new GetVakcinaStringDto(retVal));
    }

    @GetMapping(value = "/ustanova-za-vakcinisanje/{userEmail}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> ustanovaByUsername(@PathVariable String userEmail) {
        List<String> retVal = zdravstvenaUstanovaService.ustanovaByUsername(userEmail);
        return ResponseEntity.ok(new GetVakcinaStringDto(retVal));

    }
}
