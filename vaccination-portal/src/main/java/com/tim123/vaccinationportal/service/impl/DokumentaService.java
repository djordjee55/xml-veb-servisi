package com.tim123.vaccinationportal.service.impl;

import com.tim123.vaccinationportal.model.dto.dokumenta.Dokument;
import com.tim123.vaccinationportal.model.dto.dokumenta.ListaDokumenata;
import com.tim123.vaccinationportal.model.dto.dokumenta.TipDokumenta;
import com.tim123.vaccinationportal.model.interesovanje.Interesovanje;
import com.tim123.vaccinationportal.model.saglasnost.Saglasnost;
import com.tim123.vaccinationportal.service.InteresovanjeService;
import com.tim123.vaccinationportal.service.SaglasnostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DokumentaService {

    private final InteresovanjeService interesovanjeService;
    private final SaglasnostService saglasnostService;

    public ListaDokumenata mojaDokumenta(String email) {
        ListaDokumenata listaDokumenata = new ListaDokumenata(new ArrayList<>());
        Interesovanje interesovanje = interesovanjeService.dobaviZaKorisnika(email);
        if(interesovanje != null) listaDokumenata.getDokumenta().add(new Dokument(TipDokumenta.INTERESOVANJE, interesovanje.getDatum().getValue(), interesovanje.getId()));

        List<Saglasnost> saglasnosti = saglasnostService.dobaviZaKorisnika(email);
        for(Saglasnost saglasnost : saglasnosti) {
            listaDokumenata.getDokumenta().add(new Dokument(TipDokumenta.SAGLASNOST, saglasnost.getDatum().getValue(), saglasnost.getId()));
        }
        //TODO implementirati i za ostale tipove dodavanje
        return listaDokumenata;
    }
}
