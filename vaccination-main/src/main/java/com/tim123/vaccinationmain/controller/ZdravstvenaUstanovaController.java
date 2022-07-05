package com.tim123.vaccinationmain.controller;

import com.tim123.vaccinationmain.model.termin.Termin;
import com.tim123.vaccinationmain.model.termin.TerminUstanova;
import com.tim123.vaccinationmain.model.vakcina.ZeljeneVakcine;
import com.tim123.vaccinationmain.service.ZdravstvenaUstanovaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/zdravstvena-ustanova")
@RequiredArgsConstructor
public class ZdravstvenaUstanovaController {

    private final ZdravstvenaUstanovaService zdravstvenaUstanovaService;

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public TerminUstanova dodeliTermin(@RequestBody ZeljeneVakcine zeljeneVakcine) {
        return zdravstvenaUstanovaService.dodeliTermin(zeljeneVakcine);
    }
}
