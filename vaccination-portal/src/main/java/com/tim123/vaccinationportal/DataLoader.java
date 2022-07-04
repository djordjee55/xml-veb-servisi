package com.tim123.vaccinationportal;

import com.tim123.vaccinationportal.model.saglasnost.Saglasnost;
import com.tim123.vaccinationportal.repository.SaglasnostRepository;
import com.tim123.vaccinationportal.service.MarshallUnmarshallService;
import com.tim123.vaccinationportal.service.SaglasnostService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    private final SaglasnostService saglasnostService;
    private final SaglasnostRepository saglasnostRepository;
    private final MarshallUnmarshallService<Saglasnost> saglasnostmarshallUnmarshallService;

    @Override
    public void run(String... args) throws Exception {


        String saglasnostString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Saglasnost  xmlns:tip=\"http://www.xws.org/tipovi\"\n" +
                "             xmlns:vc=\"http://www.w3.org/2007/XMLSchema-versioning\"\n" +
                "             xmlns=\"http://www.xws.org/saglasnost\"\n" +
                "             xmlns:pred=\"http://www.xws.org/predicate\"\n" +
                "             xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "             xsi:schemaLocation=\"http://www.xws.org/saglasnost file:../xsd/saglasnost.xsd\"\n" +
                "             id=\"d68ae50a-7cca-4684-9500-f2ea908013f3\"\n" +
                "             about=\"http://www.xws.org/saglasnost#d68ae50a-7cca-4684-9500-f2ea908013f3\">\n" +
                "    <Datum property=\"pred:datumIzdavanja\" datatype=\"xs:date\">2006-05-04</Datum>\n" +
                "    <Pacijent>\n" +
                "        <tip:Ime>Ime0</tip:Ime>\n" +
                "        <tip:Prezime>Prezime0</tip:Prezime>\n" +
                "        <tip:Datum_rodjenja>2006-05-04</tip:Datum_rodjenja>\n" +
                "        <Drzavljanstvo>\n" +
                "            <Naziv_stranog_drzavljanstva>Naziv_stranog_drzavljanstva0</Naziv_stranog_drzavljanstva>\n" +
                "            <Broj_pasosa rel=\"pred:identifikatorKorisnika\" href=\"http://www.xws.org/korisnici#921909aa-8ff1-47f4-b761-4cbac06929ad\">Broj_pasosa0</Broj_pasosa>\n" +
                "        </Drzavljanstvo>\n" +
                "        <Ime_roditelja>Ime_roditelja0</Ime_roditelja>\n" +
                "        <Pol>M</Pol>\n" +
                "        <Mesto_rodjenja>Mesto_rodjenja0</Mesto_rodjenja>\n" +
                "        <Adresa>\n" +
                "            <Opstina>Opstina0</Opstina>\n" +
                "            <Naselje>Naselje0</Naselje>\n" +
                "            <Ulica>Ulica0</Ulica>\n" +
                "            <Broj>0</Broj>\n" +
                "        </Adresa>\n" +
                "        <Kontakt>\n" +
                "            <tip:E_mail>djordjenjegic@email.com</tip:E_mail>\n" +
                "            <tip:Fiksni_telefon>023456789</tip:Fiksni_telefon>\n" +
                "            <tip:Mobilni_telefon>060123456789</tip:Mobilni_telefon>\n" +
                "        </Kontakt>" +
                "        <Radni_status>\n" +
                "            <Penzioner>\n" +
                "            </Penzioner>\n" +
                "        </Radni_status>\n" +
                "        <Saglasnost>\n" +
                "            <Naziv_imunoloskog_leka>neki naziv</Naziv_imunoloskog_leka>\n" +
                "        </Saglasnost>\n" +
                "    </Pacijent>\n" +
                "    <Evidencija_o_vakcinaciji>\n" +
                "        <Zdravstvena_ustanova>Zdravstvena_ustanova0</Zdravstvena_ustanova>\n" +
                "        <Vakcinacijski_punkt>Vakcinacijski_punkt0</Vakcinacijski_punkt>\n" +
                "        <Lekar>\n" +
                "            <Ime>Ime0</Ime>\n" +
                "            <Prezime>Prezime0</Prezime>\n" +
                "            <Broj_telefona>0</Broj_telefona>\n" +
                "        </Lekar>\n" +
                "        <Vakcine>\n" +
                "            <Vakcina>\n" +
                "                <Naziv>naziv vakcine</Naziv>\n" +
                "                <Datum_davanja>neki datum</Datum_davanja>\n" +
                "                <Nacin_davanja>neki nacin</Nacin_davanja>\n" +
                "                <Ekstremitet>\n" +
                "                    <LR></LR>\n" +
                "                </Ekstremitet>\n" +
                "                <Serija>neka serija</Serija>\n" +
                "                <Proizvodjac>neki proizvodjac</Proizvodjac>\n" +
                "                <Nezeljena_reakcija></Nezeljena_reakcija>\n" +
                "            </Vakcina>\n" +
                "        </Vakcine>" +
                "        <Privremene_kontraindikacije>\n" +
                "            <Privremena_kontraindikacija>\n" +
                "                <Datum_utvrdjivanja>datum1</Datum_utvrdjivanja>\n" +
                "                <Dijagnoza>nesto1</Dijagnoza>\n" +
                "            </Privremena_kontraindikacija>\n" +
                "            <Privremena_kontraindikacija>\n" +
                "                <Datum_utvrdjivanja>datum2</Datum_utvrdjivanja>\n" +
                "                <Dijagnoza>nesto2</Dijagnoza>\n" +
                "            </Privremena_kontraindikacija>\n" +
                "        </Privremene_kontraindikacije>\n" +
                "        <Trajne_kontraindikacije></Trajne_kontraindikacije>\n" +
                "    </Evidencija_o_vakcinaciji>\n" +
                "</Saglasnost>\n";


        Saglasnost saglasnost = saglasnostmarshallUnmarshallService.unmarshall(saglasnostString, Saglasnost.class);
        saglasnostService.save(saglasnost);





    }
}
