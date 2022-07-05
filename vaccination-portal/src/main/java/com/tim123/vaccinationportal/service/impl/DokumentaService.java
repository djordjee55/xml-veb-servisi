package com.tim123.vaccinationportal.service.impl;

import com.tim123.vaccinationportal.model.dto.dokumenta.Dokument;
import com.tim123.vaccinationportal.model.dto.dokumenta.ListaDokumenata;
import com.tim123.vaccinationportal.model.dto.dokumenta.TipDokumenta;
import com.tim123.vaccinationportal.model.interesovanje.Interesovanje;
import com.tim123.vaccinationportal.model.saglasnost.Saglasnost;
import com.tim123.vaccinationportal.model.zahtev.Zahtev;
import com.tim123.vaccinationportal.service.InteresovanjeService;
import com.tim123.vaccinationportal.service.SaglasnostService;
import com.tim123.vaccinationportal.service.ZahtevService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DokumentaService {

    private final InteresovanjeService interesovanjeService;
    private final SaglasnostService saglasnostService;
    private final ZahtevService zahtevService;

    public ListaDokumenata mojaDokumenta(String email) {
        ListaDokumenata listaDokumenata = new ListaDokumenata(new ArrayList<>());
        Interesovanje interesovanje = interesovanjeService.dobaviZaKorisnika(email);
        if(interesovanje != null) listaDokumenata.getDokumenta().add(new Dokument(TipDokumenta.INTERESOVANJE, interesovanje.getDatum().getValue(), interesovanje.getId()));

        List<Saglasnost> saglasnosti = saglasnostService.dobaviZaKorisnika(email);
        for(Saglasnost saglasnost : saglasnosti) {
            listaDokumenata.getDokumenta().add(new Dokument(TipDokumenta.SAGLASNOST, saglasnost.getDatum().getValue(), saglasnost.getId()));
        }
        List<Zahtev> zahtevi = zahtevService.dobaviZaKorisnika(email);
        for(Zahtev zahtev : zahtevi) {
            listaDokumenata.getDokumenta().add(new Dokument(TipDokumenta.ZAHTEV, zahtev.getDatum().getValue(), zahtev.getId()));
        }
        return listaDokumenata;
    }
}
