package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.service.DocumentSearchService;
import com.tim123.vaccinationmain.service.PotvrdaService;
import com.tim123.vaccinationmain.service.SertifikatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class DocumentSearchServiceImpl implements DocumentSearchService {

    private final RestTemplate restTemplate;
    private final PotvrdaService potvrdaService;
    private final SertifikatService sertifikatService;

    @Override
    public String searchDocumentsByString(String searchedString) {

        ResponseEntity<String> interesovanja = searchInteresovanjaByString(searchedString);
        ResponseEntity<String> saglasnosti = searchSaglasnostiByString(searchedString);
        ResponseEntity<String> zahtevi = searchZahteviByString(searchedString);

        String potvrde = searchPotvrdeByString(searchedString);
        String sertifikati = searchSertifikatiByString(searchedString);

        return parseResults(interesovanja, potvrde, saglasnosti, zahtevi, sertifikati);
    }


    private ResponseEntity<String> searchInteresovanjaByString(String searchedString) {
        return restTemplate.getForEntity("http://localhost:8082/api/interesovanje/search-by-string?searchedString=" + searchedString, String.class);
    }

    private ResponseEntity<String> searchSaglasnostiByString(String searchedString) {
        return restTemplate.getForEntity("http://localhost:8082/api/saglasnost/search-by-string?searchedString=" + searchedString, String.class);
    }

    private ResponseEntity<String> searchZahteviByString(String searchedString) {
        return restTemplate.getForEntity("http://localhost:8082/api/zahtev/search-by-string?searchedString=" + searchedString, String.class);
    }

    private String searchPotvrdeByString(String searchedString) {
        return potvrdaService.searchByString(searchedString);
    }

    private String searchSertifikatiByString(String searchedString) {
        return sertifikatService.searchByString(searchedString);
    }

    private String parseResults(ResponseEntity<String> interesovanja, String potvrde, ResponseEntity<String> saglasnosti,
                                ResponseEntity<String> zahtevi, String sertifikati) {

        return "<Documents>" +
                interesovanja.getBody() +
                potvrde +
                saglasnosti.getBody() +
                zahtevi.getBody() +
                sertifikati +
                "</Documents>";
    }
}
