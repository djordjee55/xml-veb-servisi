package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.dto.dokumenta.ListaDokumenata;
import com.tim123.vaccinationmain.dto.dokumenta.TipDokumenta;
import com.tim123.vaccinationmain.service.MetadataSearchService;
import org.springframework.stereotype.Service;

@Service
public class MetadataSearchServiceImpl implements MetadataSearchService {
    @Override
    public ListaDokumenata search(TipDokumenta type, String query) {
        // Parsiraj query - ima isti oblik kao filter, exp se samo menjaju na osnovu tipa
            // Moze da se radi o datumu
            // Literalu
            // String vrednosti (contains lowercase)
        // 1. Specijalnie karaktere ( ) || && ubacuj u izraz
        // 2. Kada naidjes na '^' kreni da vadis exp koji se nalazi do sledeceg '^'
        // 3. Obradi taj exp (kreiraj odgovarajuci sparql upit) na osnovu naziva metapodatka po kome se filtrira
        // Nadji o kom se dokumentu radi
        // Za dati dokument napravi filter na osnovu sablona - imenuj objekte (poklapa se sa nazivom) za date predikate
        // Uputi zahtev ka bazi
        // Vrati rezultat
        return new ListaDokumenata();
    }
}
