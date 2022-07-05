package com.tim123.vaccinationportal.service.impl;

import com.tim123.vaccinationportal.model.Korisnik;
import com.tim123.vaccinationportal.model.dto.DopuniEvidencijuDto;
import com.tim123.vaccinationportal.model.dto.vakcine.GetVakcinaStringDto;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.ByteArrayInputStream;
import java.util.*;
import java.util.stream.Collectors;

import static com.tim123.vaccinationportal.util.Constants.saglasnostPath;

@Service
@RequiredArgsConstructor
public class SaglasnostServiceImpl extends CRUDServiceImpl<Saglasnost> implements SaglasnostService {

    private final SaglasnostRepository saglasnostRepository;
    private final KorisnikService korisnikService;
    private final RDFService rdfService;
    private final PDFTransformer pdfTransformer;
    private final HTMLTransformer htmlTransformer;
    private final MarshallUnmarshallServiceImpl<Saglasnost> marshallUnmarshallService;

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
            return htmlTransformer.generateHTML(marshallUnmarshallService.marshall(saglasnost, Saglasnost.class), Saglasnost.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ByteArrayInputStream generisiPDF(String id) {
        Saglasnost saglasnost = dobaviSaglasnost(id);
        try {
            return pdfTransformer.generatePDF(marshallUnmarshallService.marshall(saglasnost, Saglasnost.class), Saglasnost.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Saglasnost> dobaviZaKorisnika(String email) {
        return saglasnostRepository.getForUserEmail(email);
    }

    public void dopuniEvidenciju(String id, DopuniEvidencijuDto dopuniEvidencijuDto, String doctorEmail) throws Exception {
        Saglasnost saglasnost = dobaviSaglasnost(id);

        List<Saglasnost> stareSaglasnosti = saglasnostRepository.getForUserEmail(saglasnost.getPacijent().getKontakt().getEMail());
        //izbaci najnoviju
        stareSaglasnosti = stareSaglasnosti.stream().filter(sgl -> sgl.getId() != id).collect(Collectors.toList());
        //sortirano descending pa mi je najstarija na 0. poziciji...
        Collections.sort(stareSaglasnosti, (sgl1, sgl2) -> sgl2.getDatum().getValue().compare(sgl1.getDatum().getValue()));
        Saglasnost staraSaglasnost = stareSaglasnosti.get(0);

        //ako nema jos napravi vakcine
        if (saglasnost.getEvidencijaOVakcinaciji().getVakcine() == null) {
            TEvidencija.Vakcine vakc = new TEvidencija.Vakcine();
            vakc.setVakcina(new ArrayList<>());
            saglasnost.getEvidencijaOVakcinaciji().setVakcine(vakc);
        }

        //ubacivanje svih straih vakcina
        for(TVakcina v : staraSaglasnost.getEvidencijaOVakcinaciji().getVakcine().getVakcina())
            saglasnost.getEvidencijaOVakcinaciji().getVakcine().getVakcina().add(v);


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
        if (dopuniEvidencijuDto.Kontraindikacije != null && !dopuniEvidencijuDto.Kontraindikacije.isBlank()) {
            TEvidencija.PrivremeneKontraindikacije.PrivremenaKontraindikacija ki =
                    new TEvidencija.PrivremeneKontraindikacije.PrivremenaKontraindikacija();
            ki.setDijagnoza(dopuniEvidencijuDto.Kontraindikacije);
            ki.setDatumUtvrdjivanja(dopuniEvidencijuDto.DatumKontraindikacije);

            if (saglasnost.getEvidencijaOVakcinaciji().getPrivremeneKontraindikacije() == null) {
                TEvidencija.PrivremeneKontraindikacije privremeneKontraindikacije = new TEvidencija.PrivremeneKontraindikacije();
                privremeneKontraindikacije.setPrivremenaKontraindikacija(new ArrayList<>());
                saglasnost.getEvidencijaOVakcinaciji().setPrivremeneKontraindikacije(privremeneKontraindikacije);
            }
            saglasnost.getEvidencijaOVakcinaciji().getPrivremeneKontraindikacije().getPrivremenaKontraindikacija().add(ki);
        }

        Korisnik doktor = korisnikService.findByEmail(doctorEmail);
        if (saglasnost.getEvidencijaOVakcinaciji().getLekar() == null) {
            TEvidencija.Lekar lekar = new TEvidencija.Lekar();
            saglasnost.getEvidencijaOVakcinaciji().setLekar(lekar);
        }
        saglasnost.getEvidencijaOVakcinaciji().getLekar().setIme(doktor.getIme());
        saglasnost.getEvidencijaOVakcinaciji().getLekar().setPrezime(doktor.getPrezime());
        //saglasnost.getEvidencijaOVakcinaciji().getLekar().setBrojTelefona(doktor.getBrojTelefona());


        saglasnostRepository.save(saglasnost);
    }

    @Override
    public GetVakcinaStringDto vakcinaByUsername(String username) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/api/zdravstvena-ustanova/vakcina-by-user/" + username;
        ResponseEntity<GetVakcinaStringDto> response =
                restTemplate.getForEntity(url, GetVakcinaStringDto.class);
        if (!response.hasBody())
            return null;

        return response.getBody();
    }

    @Override
    public GetVakcinaStringDto ustanovaByUsername(String userEmail) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/api/zdravstvena-ustanova/ustanova-za-vakcinisanje/" + userEmail;
        ResponseEntity<GetVakcinaStringDto> response =
                restTemplate.getForEntity(url, GetVakcinaStringDto.class);
        if (!response.hasBody())
            return null;

        return response.getBody();
    }
}
