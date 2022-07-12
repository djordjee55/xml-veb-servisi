package com.tim123.vaccinationportal.service.impl;

import com.tim123.vaccinationportal.model.Korisnik;
import com.tim123.vaccinationportal.model.potvrda.TDoza;
import com.tim123.vaccinationportal.model.potvrda.TVakcinacija;
import com.tim123.vaccinationportal.model.saglasnost.TVakcina;
import com.tim123.vaccinationportal.repository.CRUDRepository;
import com.tim123.vaccinationportal.repository.KorisnikRepository;
import com.tim123.vaccinationportal.service.KorisnikService;
import com.tim123.vaccinationportal.service.PotvrdaService;
import com.tim123.vaccinationportal.service.SaglasnostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KorisnikServiceImpl extends CRUDServiceImpl<Korisnik> implements KorisnikService {

    private final KorisnikRepository korisnikRepository;
    private final SaglasnostService saglasnostService;
    private final PotvrdaService potvrdaService;

    @Override
    protected CRUDRepository<Korisnik> getRepository() {
        return this.korisnikRepository;
    }

    @Override
    public Korisnik findByEmail(String email) {
        return korisnikRepository.findByEmail(email);
    }

    @Override
    public Korisnik findByDocumentId(String jmbg, String passport) {
        Korisnik k = null;
        if (jmbg != null && !jmbg.isBlank()) {
            k = this.findByJMBG(jmbg);
        } else if (passport != null && !passport.isBlank()) {
            k = this.findByPassport(passport);
        }
        return k;
    }

    @Override
    public TVakcinacija dobaviVakcine(String jmbg, String pasos) {
        var total = saglasnostService.dobaviZaKorisnika(jmbg, pasos);
        total = total.stream().filter(s -> s.getEvidencijaOVakcinaciji() != null).collect(Collectors.toList());
        total.sort((o1, o2) -> o2.getDatum().getValue().compare(o1.getDatum().getValue()));
        List<TDoza> dozeList = new ArrayList<>();
        if (total.size() > 0) {
            var poslednja = total.get(0);
            if(poslednja.getEvidencijaOVakcinaciji().getVakcine() != null) {
                List<TVakcina> sveDateVakcine = poslednja.getEvidencijaOVakcinaciji().getVakcine().getVakcina();
                if (sveDateVakcine == null || sveDateVakcine.size() == 0) {
                    dozeList = new ArrayList<>();
                } else {
                    dozeList = new ArrayList<>();
                    for(int i = 0; i < sveDateVakcine.size(); i++)
                        dozeList.add(potvrdaService.makeDozaFromTVakcina(sveDateVakcine.get(i), i+1));
                }
            }
        }
        return TVakcinacija.builder().doza(dozeList).build();
    }

    @Override
    public Korisnik findByJMBG(String jmbg) {
        return korisnikRepository.findByJMBG(jmbg);
    }

    @Override
    public Korisnik findByPassport(String passport) {
        return korisnikRepository.findByPassport(passport);
    }

}
