package com.tim123.vaccinationportal;

import com.tim123.vaccinationportal.model.Korisnik;
import com.tim123.vaccinationportal.model.enumi.Drzavljanstvo;
import com.tim123.vaccinationportal.model.enumi.Uloga;
import com.tim123.vaccinationportal.model.tipovi.TPol;
import com.tim123.vaccinationportal.service.KorisnikService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    private final KorisnikService korisnikService;

    @Override
    public void run(String... args) throws Exception {
        //Dodavanje zdravstvenog radnika
        Korisnik doktor = new Korisnik();
        doktor.setId("doktor1");
        doktor.setDrzavljanstvo(Drzavljanstvo.DRZAVLJANIN_REPUBLIKE_SRBIJE);
        doktor.setEmail("doktor@gmail.com");
        doktor.setIme("Branimir");
        doktor.setPrezime("Nestorovic");
        doktor.setJmbg("2904998183926");
        doktor.setPasos("");
        doktor.setPol(TPol.M);
        doktor.setLozinka("$2a$10$X49twdNbOSwROo2MaHDczu/yb8yoshE1dn/6AAPinBNtcF7qH3rsG"); // lozinka1
        doktor.setUloga(Uloga.ZDRAVSTVENI_RADNIK);
        korisnikService.save(doktor);
    }
}
