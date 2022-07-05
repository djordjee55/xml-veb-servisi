package com.tim123.vaccinationportal.service.impl;

import com.tim123.vaccinationportal.model.Korisnik;
import com.tim123.vaccinationportal.model.dto.DopuniEvidencijuDto;
import com.tim123.vaccinationportal.model.saglasnost.Saglasnost;
import com.tim123.vaccinationportal.model.saglasnost.TEvidencija;
import com.tim123.vaccinationportal.model.saglasnost.TVakcina;
import com.tim123.vaccinationportal.model.tipovi.TCJMBG;
import com.tim123.vaccinationportal.repository.CRUDRepository;
import com.tim123.vaccinationportal.repository.SaglasnostRepository;
import com.tim123.vaccinationportal.service.KorisnikService;
import com.tim123.vaccinationportal.service.RDFService;
import com.tim123.vaccinationportal.service.SaglasnostService;
import com.tim123.vaccinationportal.util.HTMLTransformer;
import com.tim123.vaccinationportal.util.PDFTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static com.tim123.vaccinationportal.util.Constants.saglasnostPath;

@Service
@RequiredArgsConstructor
public class SaglasnostServiceImpl extends CRUDServiceImpl<Saglasnost> implements SaglasnostService {

    private final SaglasnostRepository saglasnostRepository;
    private final KorisnikService korisnikService;
    private final RDFService rdfService;
    private final PDFTransformer pdfTransformer;
    private final HTMLTransformer htmlTransformer;
    private final MarshallUnmarshallServiceImpl<Saglasnost> saglasnostMarshallUnmarshallService;

    @Override
    protected CRUDRepository<Saglasnost> getRepository() {
        return saglasnostRepository;
    }

    @Override
    public Saglasnost dodajSaglasnost(Saglasnost saglasnost, String email) {
        //da li postoji termin, i da nije izvrsena vec vakcinacija u okviru njega
        Korisnik korisnik = korisnikService.findByEmail(email);

        dodajInfoOKorisniku(saglasnost, korisnik);

        try {
            var i = this.save(saglasnost);
            rdfService.extractMetadata(saglasnost, Saglasnost.class, saglasnostPath);
            return i;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Losa saglasnost");
        }
    }

    private void dodajInfoOKorisniku(Saglasnost saglasnost, Korisnik korisnik) {
        TCJMBG jmbg = new TCJMBG();
        jmbg.setValue(korisnik.getJmbg());
        saglasnost.getPacijent().getDrzavljanstvo().setJMBG(jmbg);
        saglasnost.getPacijent().setIme(korisnik.getIme());
        saglasnost.getPacijent().setPol(korisnik.getPol());
        saglasnost.getPacijent().setPrezime(korisnik.getPrezime());
        saglasnost.getPacijent().setDatumRodjenja(korisnik.getDatumRodjenja());
        saglasnost.getPacijent().getKontakt().setEMail(korisnik.getEmail());
    }

    @Override
    public Saglasnost dobaviSaglasnost(String id) {

        var saglasnost = this.findById(id);
        if (saglasnost.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Saglasnost nije pronadjena");
        }
        return saglasnost.get();
    }

    @Override
    public ByteArrayInputStream generisiHTML(String id) {
        Saglasnost saglasnost = dobaviSaglasnost(id);
        try {
            return htmlTransformer.generateHTML(saglasnostMarshallUnmarshallService.marshall(saglasnost, Saglasnost.class), Saglasnost.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return htmlTransformer.generateHTML(dobaviSaglanost2(), Saglasnost.class);
    }

    @Override
    public ByteArrayInputStream generisiPDF(String id) {
        Saglasnost saglasnost = dobaviSaglasnost(id);
        try {
            return htmlTransformer.generateHTML(saglasnostMarshallUnmarshallService.marshall(saglasnost, Saglasnost.class), Saglasnost.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return pdfTransformer.generatePDF(dobaviSaglanost2(), Saglasnost.class);
    }

    @Override
    public List<Saglasnost> dobaviZaKorisnika(String email) {
        return saglasnostRepository.getForUserEmail(email);
    }
    
    public void dopuniEvidenciju(String id, DopuniEvidencijuDto dopuniEvidencijuDto) throws Exception {
        Saglasnost saglasnost = dobaviSaglasnost(id);
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(new Date());
        XMLGregorianCalendar xmlgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);

        TVakcina.Ekstremitet ekstremitet = new TVakcina.Ekstremitet();
        ekstremitet.setDR(dopuniEvidencijuDto.REkstremitet);
        ekstremitet.setDR(dopuniEvidencijuDto.LEkstremitet);

        TVakcina vakcina = new TVakcina();
        vakcina.setDatumDavanja(xmlgc);
        vakcina.setNaziv(dopuniEvidencijuDto.NazivVakcine);
        vakcina.setProizvodjac(dopuniEvidencijuDto.Proizvodjac);
        vakcina.setSerija(dopuniEvidencijuDto.Serija);
        vakcina.setNacinDavanja(dopuniEvidencijuDto.NacinDavanja);
        vakcina.setNezeljenaReakcija(dopuniEvidencijuDto.NezeljenaReakcija);
        vakcina.setEkstremitet(ekstremitet);



        saglasnost.getEvidencijaOVakcinaciji().setZdravstvenaUstanova(dopuniEvidencijuDto.ZdravstvenaUstanova);
        saglasnost.getEvidencijaOVakcinaciji().setVakcinacijskiPunkt(dopuniEvidencijuDto.VakcinacijskiPunkt);
        saglasnost.getEvidencijaOVakcinaciji().getVakcine().getVakcina().add(vakcina);
        if(dopuniEvidencijuDto.Kontraindikacije != null && !dopuniEvidencijuDto.Kontraindikacije.isBlank())
        {
            TEvidencija.PrivremeneKontraindikacije.PrivremenaKontraindikacija ki =
                    new TEvidencija.PrivremeneKontraindikacije.PrivremenaKontraindikacija();
            ki.setDijagnoza(dopuniEvidencijuDto.Kontraindikacije);
            ki.setDatumUtvrdjivanja(dopuniEvidencijuDto.DatumKontraindikacije);

            saglasnost.getEvidencijaOVakcinaciji().getPrivremeneKontraindikacije().getPrivremenaKontraindikacija().add(ki);
        }

        saglasnostRepository.save(saglasnost);
    }

    @Override
    public List<String> vakcinaByUsername(String username) {
        //TODO restTemplate poziv
        return List.of("Sinopharm-WuHan Institute Of Virus");
    }

    @Override
    public List<String> ustanovaByUsername(String userEmail) {
        //TODO restTemplate poziv
        return List.of("Zdravstvena Ustanova 1");
    }


    private String dobaviSaglanost2() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
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
    }
}
