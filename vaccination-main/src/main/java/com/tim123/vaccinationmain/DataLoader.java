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

        Vakcina vakcina11 = new Vakcina(TipVakcine.PHIZER_BIONTECH, 100, "DZ1", "V11");
        Vakcina vakcina12 = new Vakcina(TipVakcine.SPUTNIK_V, 100, "DZ1", "V12");
        Vakcina vakcina13 = new Vakcina(TipVakcine.SINOPHARM, 100, "DZ1", "V13");
        Vakcina vakcina14 = new Vakcina(TipVakcine.ASTRA_ZENECA, 100, "DZ1", "V14");
        Vakcina vakcina15 = new Vakcina(TipVakcine.MODERNA, 100, "DZ1", "V15");

        Vakcina vakcina21 = new Vakcina(TipVakcine.PHIZER_BIONTECH, 100, "DZ2", "V21");
        Vakcina vakcina22 = new Vakcina(TipVakcine.SPUTNIK_V, 100, "DZ2", "V22");
        Vakcina vakcina23 = new Vakcina(TipVakcine.SINOPHARM, 100, "DZ2", "V23");
        Vakcina vakcina24 = new Vakcina(TipVakcine.ASTRA_ZENECA, 100, "DZ2", "V24");
        Vakcina vakcina25 = new Vakcina(TipVakcine.MODERNA, 100, "DZ2", "V25");

        Vakcina vakcina31 = new Vakcina(TipVakcine.PHIZER_BIONTECH, 100, "DZ3", "V31");
        Vakcina vakcina32 = new Vakcina(TipVakcine.SPUTNIK_V, 100, "DZ3", "V32");
        Vakcina vakcina33 = new Vakcina(TipVakcine.SINOPHARM, 100, "DZ3", "V33");
        Vakcina vakcina34 = new Vakcina(TipVakcine.ASTRA_ZENECA, 100, "DZ3", "V34");
        Vakcina vakcina35 = new Vakcina(TipVakcine.MODERNA, 100, "DZ3", "V35");

        Vakcina vakcina41 = new Vakcina(TipVakcine.PHIZER_BIONTECH, 100, "DZ4", "V41");
        Vakcina vakcina42 = new Vakcina(TipVakcine.SPUTNIK_V, 100, "DZ4", "V42");
        Vakcina vakcina43 = new Vakcina(TipVakcine.SINOPHARM, 100, "DZ4", "V43");
        Vakcina vakcina44 = new Vakcina(TipVakcine.ASTRA_ZENECA, 100, "DZ4", "V44");
        Vakcina vakcina45 = new Vakcina(TipVakcine.MODERNA, 100, "DZ4", "V45");

        Vakcina vakcina51 = new Vakcina(TipVakcine.PHIZER_BIONTECH, 100, "DZ5", "V51");
        Vakcina vakcina52 = new Vakcina(TipVakcine.SPUTNIK_V, 100, "DZ5", "V52");
        Vakcina vakcina53 = new Vakcina(TipVakcine.SINOPHARM, 100, "DZ5", "V53");
        Vakcina vakcina54 = new Vakcina(TipVakcine.ASTRA_ZENECA, 100, "DZ5", "V54");
        Vakcina vakcina55 = new Vakcina(TipVakcine.MODERNA, 100, "DZ5", "V55");

        Vakcina vakcina61 = new Vakcina(TipVakcine.PHIZER_BIONTECH, 100, "DZ6", "V61");
        Vakcina vakcina62 = new Vakcina(TipVakcine.SPUTNIK_V, 100, "DZ6", "V62");
        Vakcina vakcina63 = new Vakcina(TipVakcine.SINOPHARM, 100, "DZ6", "V63");
        Vakcina vakcina64 = new Vakcina(TipVakcine.ASTRA_ZENECA, 100, "DZ6", "V64");
        Vakcina vakcina65 = new Vakcina(TipVakcine.MODERNA, 100, "DZ6", "V65");

        Vakcina vakcina71 = new Vakcina(TipVakcine.PHIZER_BIONTECH, 100, "DZ7", "V71");
        Vakcina vakcina72 = new Vakcina(TipVakcine.SPUTNIK_V, 100, "DZ7", "V72");
        Vakcina vakcina73 = new Vakcina(TipVakcine.SINOPHARM, 100, "DZ7", "V73");
        Vakcina vakcina74 = new Vakcina(TipVakcine.ASTRA_ZENECA, 100, "DZ7", "V74");
        Vakcina vakcina75 = new Vakcina(TipVakcine.MODERNA, 100, "DZ7", "V75");

        Vakcina vakcina81 = new Vakcina(TipVakcine.PHIZER_BIONTECH, 100, "DZ8", "V81");
        Vakcina vakcina82 = new Vakcina(TipVakcine.SPUTNIK_V, 100, "DZ8", "V82");
        Vakcina vakcina83 = new Vakcina(TipVakcine.SINOPHARM, 100, "DZ8", "V83");
        Vakcina vakcina84 = new Vakcina(TipVakcine.ASTRA_ZENECA, 100, "DZ8", "V84");
        Vakcina vakcina85 = new Vakcina(TipVakcine.MODERNA, 100, "DZ8", "V85");

        Vakcina vakcina91 = new Vakcina(TipVakcine.PHIZER_BIONTECH, 100, "DZ9", "V91");
        Vakcina vakcina92 = new Vakcina(TipVakcine.SPUTNIK_V, 100, "DZ9", "V92");
        Vakcina vakcina93 = new Vakcina(TipVakcine.SINOPHARM, 100, "DZ9", "V93");
        Vakcina vakcina94 = new Vakcina(TipVakcine.ASTRA_ZENECA, 100, "DZ9", "V94");
        Vakcina vakcina95 = new Vakcina(TipVakcine.MODERNA, 100, "DZ9", "V95");

        Vakcina vakcina101 = new Vakcina(TipVakcine.PHIZER_BIONTECH, 100, "DZ10", "V101");
        Vakcina vakcina102 = new Vakcina(TipVakcine.SPUTNIK_V, 100, "DZ10", "V102");
        Vakcina vakcina103 = new Vakcina(TipVakcine.SINOPHARM, 100, "DZ10", "V103");
        Vakcina vakcina104 = new Vakcina(TipVakcine.ASTRA_ZENECA, 100, "DZ10", "V104");
        Vakcina vakcina105 = new Vakcina(TipVakcine.MODERNA, 100, "DZ10", "V105");

        Vakcina vakcina111 = new Vakcina(TipVakcine.PHIZER_BIONTECH, 100, "DZ11", "V111");
        Vakcina vakcina112 = new Vakcina(TipVakcine.SPUTNIK_V, 100, "DZ11", "V112");
        Vakcina vakcina113 = new Vakcina(TipVakcine.SINOPHARM, 100, "DZ11", "V113");
        Vakcina vakcina114 = new Vakcina(TipVakcine.ASTRA_ZENECA, 100, "DZ11", "V114");
        Vakcina vakcina115 = new Vakcina(TipVakcine.MODERNA, 100, "DZ11", "V115");

        vakcinaService.save(vakcina11);
        vakcinaService.save(vakcina12);
        vakcinaService.save(vakcina13);
        vakcinaService.save(vakcina14);
        vakcinaService.save(vakcina15);

        vakcinaService.save(vakcina21);
        vakcinaService.save(vakcina22);
        vakcinaService.save(vakcina23);
        vakcinaService.save(vakcina24);
        vakcinaService.save(vakcina25);

        vakcinaService.save(vakcina31);
        vakcinaService.save(vakcina32);
        vakcinaService.save(vakcina33);
        vakcinaService.save(vakcina34);
        vakcinaService.save(vakcina35);

        vakcinaService.save(vakcina41);
        vakcinaService.save(vakcina42);
        vakcinaService.save(vakcina43);
        vakcinaService.save(vakcina44);
        vakcinaService.save(vakcina45);

        vakcinaService.save(vakcina51);
        vakcinaService.save(vakcina52);
        vakcinaService.save(vakcina53);
        vakcinaService.save(vakcina54);
        vakcinaService.save(vakcina55);

        vakcinaService.save(vakcina61);
        vakcinaService.save(vakcina62);
        vakcinaService.save(vakcina63);
        vakcinaService.save(vakcina64);
        vakcinaService.save(vakcina65);

        vakcinaService.save(vakcina71);
        vakcinaService.save(vakcina72);
        vakcinaService.save(vakcina73);
        vakcinaService.save(vakcina74);
        vakcinaService.save(vakcina75);

        vakcinaService.save(vakcina81);
        vakcinaService.save(vakcina82);
        vakcinaService.save(vakcina83);
        vakcinaService.save(vakcina84);
        vakcinaService.save(vakcina85);

        vakcinaService.save(vakcina91);
        vakcinaService.save(vakcina92);
        vakcinaService.save(vakcina93);
        vakcinaService.save(vakcina94);
        vakcinaService.save(vakcina95);

        vakcinaService.save(vakcina101);
        vakcinaService.save(vakcina102);
        vakcinaService.save(vakcina103);
        vakcinaService.save(vakcina104);
        vakcinaService.save(vakcina105);

        vakcinaService.save(vakcina111);
        vakcinaService.save(vakcina112);
        vakcinaService.save(vakcina113);
        vakcinaService.save(vakcina114);
        vakcinaService.save(vakcina115);

    }
}
