package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.dto.dokumenta.ListaDokumenata;
import com.tim123.vaccinationmain.dto.dokumenta.TipDokumenta;
import com.tim123.vaccinationmain.service.MetadataSearchService;
import com.tim123.vaccinationmain.util.MetadataUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MetadataSearchServiceImpl implements MetadataSearchService {

    private final MetadataUtil metadataUtil;

    @Override
    public ListaDokumenata search(TipDokumenta type, String query) {
        String filter = parseQuery(type, query);
        return metadataUtil.sparqlQuery(type, filter);
    }

    private String parseQuery(TipDokumenta type, String query) {
        // query=#name=value#||(#name=value#&&#name=value#||(#name=value#&&#name=value#))

        // Parsiraj query - ima isti oblik kao filter, exp se samo menjaju na osnovu tipa
        StringBuilder mdFilter = new StringBuilder();
        // Ukloni whitespace karaktere
        query = query.replaceAll("\\s", "");
        query = query.strip();
        int idx = 0;
        while (idx < query.length()) {
            var c = query.charAt(idx);
            // Dozvoli query sa praznim karakterima
            if (c == ' ') {
                continue;
            }
            // Specijalnie karaktere ( ) || && ubacuj u izraz
            else if (c == '(' || c == ')' || c == '|' || c == '&') {
                mdFilter.append(c);
            }
            // Kada naidjes na '^' kreni da vadis exp koji se nalazi do sledeceg '^'
            else if (c == '#') {
                var end = query.indexOf('#', idx + 1);
                var exp = query.substring(idx + 1, end);
                // Obradi izraz tako da kreira oblik pogodan za sparql upit i dodaj u filter
                mdFilter.append(parseExp(type, exp));
                // Preskoci do kraja izraza
                idx = end;
            }
            ++idx;
        }
        return mdFilter.toString();
    }

    private String parseExp(TipDokumenta type, String exp) {
        switch (type) {
            case ZAHTEV:
                return metadataUtil.zahtevConverter(exp);
            case POTVRDA:
                return metadataUtil.potvrdaConverter(exp);
            case SAGLASNOST:
                return metadataUtil.saglasnostConverter(exp);
            case SERTIFIKAT:
                return metadataUtil.sertifikatConverter(exp);
            case INTERESOVANJE:
                return metadataUtil.interesovanjeConverter(exp);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nepoznat tip dokumenta");
    }

}
