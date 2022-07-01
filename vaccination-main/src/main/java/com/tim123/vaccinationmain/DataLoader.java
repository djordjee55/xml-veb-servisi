package com.tim123.vaccinationmain;

import com.tim123.vaccinationmain.model.vakcina.TipVakcine;
import com.tim123.vaccinationmain.model.vakcina.Vakcina;
import com.tim123.vaccinationmain.model.zdravstvenaUstanova.ZdravstvenaUstanova;
import com.tim123.vaccinationmain.service.VakcinaService;
import com.tim123.vaccinationmain.service.ZdravstvenaUstanovaService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final ZdravstvenaUstanovaService zdravstvenaUstanovaService;
    private final VakcinaService vakcinaService;

    @Override
    public void run(String... args) throws Exception {
        ZdravstvenaUstanova zU1 = new ZdravstvenaUstanova(new ArrayList<>(), "Dom zdravlja Zvezdara", "Beograd", "DZ1", LocalTime.of(8, 0).toString(), LocalTime.of(20, 0).toString());
        ZdravstvenaUstanova zU2 = new ZdravstvenaUstanova(new ArrayList<>(), "Institut Batut", "Beograd", "DZ2", LocalTime.of(7, 0).toString(), LocalTime.of(20, 0).toString());
        ZdravstvenaUstanova zU3 = new ZdravstvenaUstanova(new ArrayList<>(), "Dom zdravlja Novi Beograd", "Novi Beograd", "DZ3", LocalTime.of(7, 0).toString(), LocalTime.of(20, 0).toString());
        ZdravstvenaUstanova zU4 = new ZdravstvenaUstanova(new ArrayList<>(), "Dom zdravlja Vozdovac", "Beograd", "DZ4", LocalTime.of(6, 0).toString(), LocalTime.of(20, 0).toString());
        ZdravstvenaUstanova zU5 = new ZdravstvenaUstanova(new ArrayList<>(), "Dom zdravlja Vracar", "Beograd", "DZ5", LocalTime.of(6, 0).toString(), LocalTime.of(20, 0).toString());
        ZdravstvenaUstanova zU6 = new ZdravstvenaUstanova(new ArrayList<>(), "Klinicki centar Vojvodine", "Novi Sad", "DZ6", LocalTime.of(8, 0).toString(), LocalTime.of(20, 0).toString());
        ZdravstvenaUstanova zU7 = new ZdravstvenaUstanova(new ArrayList<>(), "Vojna Bolnica", "Novi Sad", "DZ7", LocalTime.of(8, 0).toString(), LocalTime.of(20, 0).toString());
        ZdravstvenaUstanova zU8 = new ZdravstvenaUstanova(new ArrayList<>(), "Dom zdravlja Detelinara", "Novi Sad", "DZ8", LocalTime.of(8, 0).toString(), LocalTime.of(20, 0).toString());
        ZdravstvenaUstanova zU9 = new ZdravstvenaUstanova(new ArrayList<>(), "Dom zdravlja1", "Nis", "DZ9", LocalTime.of(8, 0).toString(), LocalTime.of(20, 0).toString());
        ZdravstvenaUstanova zU10 = new ZdravstvenaUstanova(new ArrayList<>(), "Dom zdravlja2", "Nis", "DZ10", LocalTime.of(8, 0).toString(), LocalTime.of(20, 0).toString());
        ZdravstvenaUstanova zU11 = new ZdravstvenaUstanova(new ArrayList<>(), "Poliklinika", "Kragujevac", "DZ11", LocalTime.of(8, 0).toString(), LocalTime.of(20, 0).toString());

        zdravstvenaUstanovaService.save(zU1);
        zdravstvenaUstanovaService.save(zU2);
        zdravstvenaUstanovaService.save(zU3);
        zdravstvenaUstanovaService.save(zU4);
        zdravstvenaUstanovaService.save(zU5);
        zdravstvenaUstanovaService.save(zU6);
        zdravstvenaUstanovaService.save(zU7);
        zdravstvenaUstanovaService.save(zU8);
        zdravstvenaUstanovaService.save(zU9);
        zdravstvenaUstanovaService.save(zU10);
        zdravstvenaUstanovaService.save(zU11);

        Vakcina vakcina1 = new Vakcina(TipVakcine.PHIZER_BIONTECH, 100, "DZ1", "V1");
        Vakcina vakcina2 = new Vakcina(TipVakcine.PHIZER_BIONTECH, 100, "DZ2", "V2");
        Vakcina vakcina3 = new Vakcina(TipVakcine.PHIZER_BIONTECH, 100, "DZ3", "V3");
        Vakcina vakcina4 = new Vakcina(TipVakcine.PHIZER_BIONTECH, 100, "DZ4", "V4");
        Vakcina vakcina5 = new Vakcina(TipVakcine.PHIZER_BIONTECH, 100, "DZ5", "V5");
        Vakcina vakcina6 = new Vakcina(TipVakcine.PHIZER_BIONTECH, 100, "DZ6", "V6");
        Vakcina vakcina7 = new Vakcina(TipVakcine.PHIZER_BIONTECH, 100, "DZ7", "V7");
        Vakcina vakcina8 = new Vakcina(TipVakcine.PHIZER_BIONTECH, 100, "DZ8", "V8");
        Vakcina vakcina9 = new Vakcina(TipVakcine.PHIZER_BIONTECH, 100, "DZ9", "V9");
        Vakcina vakcina10 = new Vakcina(TipVakcine.PHIZER_BIONTECH, 100, "DZ10", "V10");
        Vakcina vakcina11 = new Vakcina(TipVakcine.PHIZER_BIONTECH, 100, "DZ11", "V11");

        vakcinaService.save(vakcina1);
        vakcinaService.save(vakcina2);
        vakcinaService.save(vakcina3);
        vakcinaService.save(vakcina4);
        vakcinaService.save(vakcina5);
        vakcinaService.save(vakcina6);
        vakcinaService.save(vakcina7);
        vakcinaService.save(vakcina8);
        vakcinaService.save(vakcina9);
        vakcinaService.save(vakcina10);
        vakcinaService.save(vakcina11);
    }
}
