package com.tim123.vaccinationportal.service.impl;

import com.tim123.vaccinationportal.model.Korisnik;
import com.tim123.vaccinationportal.model.dto.dokumenta.Dokument;
import com.tim123.vaccinationportal.model.dto.dokumenta.ListaDokumenata;
import com.tim123.vaccinationportal.model.dto.dokumenta.TipDokumenta;
import com.tim123.vaccinationportal.model.interesovanje.Interesovanje;
import com.tim123.vaccinationportal.model.potvrda.Potvrda;
import com.tim123.vaccinationportal.model.saglasnost.Saglasnost;
import com.tim123.vaccinationportal.model.zahtev.Zahtev;
import com.tim123.vaccinationportal.repository.KorisnikRepository;
import com.tim123.vaccinationportal.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DokumentaService {

    private final InteresovanjeService interesovanjeService;
    private final SaglasnostService saglasnostService;
    private final ZahtevService zahtevService;
    private final PotvrdaService potvrdaService;
    private final RestTemplate restTemplate;
    private final KorisnikRepository korisnikRepository;
    private final KorisnikService korisnikService;

    public ListaDokumenata mojaDokumenta(String email) {
        ListaDokumenata listaDokumenata = new ListaDokumenata(new ArrayList<>());
        Interesovanje interesovanje = interesovanjeService.dobaviZaKorisnika(email);
        if (interesovanje != null)
            listaDokumenata.getDokumenta().add(new Dokument(TipDokumenta.INTERESOVANJE, interesovanje.getDatum().getValue(), interesovanje.getId()));

        List<Saglasnost> saglasnosti = saglasnostService.dobaviZaKorisnika(email);
        for (Saglasnost saglasnost : saglasnosti) {
            listaDokumenata.getDokumenta().add(new Dokument(TipDokumenta.SAGLASNOST, saglasnost.getDatum().getValue(), saglasnost.getId()));
        }
        List<Zahtev> zahtevi = zahtevService.dobaviZaKorisnika(email);
        for (Zahtev zahtev : zahtevi) {
            listaDokumenata.getDokumenta().add(new Dokument(TipDokumenta.ZAHTEV, zahtev.getDatum().getValue(), zahtev.getId()));
        }

        List<Potvrda> potvrde = potvrdaService.dobaviZaKorisnika(email);
        for (Potvrda potvrda : potvrde) {
            listaDokumenata.getDokumenta().add(new Dokument(TipDokumenta.POTVRDA, potvrda.getDatumIzdavanja().getValue(), potvrda.getSifra()));
        }
        Korisnik korisnik = korisnikRepository.findByEmail(email);
        ListaDokumenata listaSertifikata = dobaviSertifikate(korisnik.getJmbg(), korisnik.getPasos());
        if (listaSertifikata.getDokumenta() != null) {
            for (Dokument dokument : listaSertifikata.getDokumenta()) {
                listaDokumenata.getDokumenta().add(dokument);
            }
        }

        return listaDokumenata;
    }

    private ListaDokumenata dobaviSertifikate(String jmbg, String pasos) {
        if (jmbg == null) jmbg = "";
        if (pasos == null) pasos = "";
        return restTemplate.getForObject("http://localhost:8081/api/sertifikat/moji-sertifikati?jmbg=" + jmbg + "&pasos=" + pasos, ListaDokumenata.class);
    }

    public ListaDokumenata korisnikovaDokumenta(String id) {
        Zahtev zahtev = zahtevService.dobaviZahtev(id);
        Korisnik korisnik = korisnikService.findByDocumentId(zahtev.getPodnosilac().getJMBG().getValue(),
                zahtev.getPodnosilac().getBrojPasosa().getValue());

        return mojaDokumenta(korisnik.getEmail());
    }
}
