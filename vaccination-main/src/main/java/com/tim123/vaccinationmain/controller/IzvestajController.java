package com.tim123.vaccinationmain.controller;

import com.tim123.vaccinationmain.service.IzvestajService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/izvestaj")
@RequiredArgsConstructor
public class IzvestajController {

    private final IzvestajService izvestajService;

    @GetMapping("/{startDate}/{endDate}")
    public String getIzvestaj(@PathVariable String startDate, @PathVariable String endDate) throws Exception {
        return izvestajService.getIzvestajForPeriod(startDate, endDate);
    }
}
