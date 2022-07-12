package com.tim123.vaccinationportal.service.impl;

import com.tim123.vaccinationportal.model.potvrda.Potvrda;
import com.tim123.vaccinationportal.model.potvrda.TDoza;
import com.tim123.vaccinationportal.model.potvrda.TVakcinacija;
import com.tim123.vaccinationportal.model.saglasnost.Saglasnost;
import com.tim123.vaccinationportal.model.saglasnost.TVakcina;
import com.tim123.vaccinationportal.model.tipovi.*;
import com.tim123.vaccinationportal.repository.CRUDRepository;
import com.tim123.vaccinationportal.repository.PotvrdaRepository;
import com.tim123.vaccinationportal.service.PotvrdaService;
import com.tim123.vaccinationportal.service.RDFService;
import com.tim123.vaccinationportal.service.SaglasnostService;
import com.tim123.vaccinationportal.util.HTMLTransformer;
import com.tim123.vaccinationportal.util.PDFTransformer;
import com.tim123.vaccinationportal.util.SearchUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static com.tim123.vaccinationportal.util.Constants.potvrdaPath;

@Service
@RequiredArgsConstructor
public class PotvrdaServiceImpl extends CRUDServiceImpl<Potvrda> implements PotvrdaService {

    private final PotvrdaRepository potvrdaRepository;
    private final SaglasnostService saglasnostService;
    private final PDFTransformer pdfTransformer;
    private final HTMLTransformer htmlTransformer;
    private final MarshallUnmarshallServiceImpl<Potvrda> marshallUnmarshallService;
    private final SearchUtil searchUtil;
    private final RDFService rdfService;

    @Override
    public Potvrda save(Potvrda entity) throws Exception {
        return null;
    }

    @Override
    protected CRUDRepository<Potvrda> getRepository() {
        return potvrdaRepository;
    }

    @Override
    public Optional<Potvrda> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Potvrda update(Potvrda entity) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public ByteArrayInputStream generisiHTML(String id) {
        Potvrda potvrda;
        try {
            potvrda = potvrdaRepository.findById(id);
            return htmlTransformer.generateHTML(marshallUnmarshallService.marshall(potvrda, Potvrda.class), Potvrda.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public ByteArrayInputStream generisiPDF(String id) {

        Potvrda potvrda;
        try {
            potvrda = potvrdaRepository.findById(id);
            return pdfTransformer.generatePDF(marshallUnmarshallService.marshall(potvrda, Potvrda.class), Potvrda.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Potvrda generisiPotvrdu(String saglasnostId) {

        Saglasnost saglasnost;
        TDatumKreiranja danasnjiDatum;
        try {
            saglasnost = saglasnostService.findById(saglasnostId).orElseThrow(RuntimeException::new);
            danasnjiDatum = new TDatumKreiranja();
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(new Date());
            XMLGregorianCalendar xmlgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
            danasnjiDatum.setValue(xmlgc);
            danasnjiDatum.setDatatype("http://www.w3.org/2001/XMLSchema#date");
            danasnjiDatum.setProperty("pred:datumIzdavanja");
        } catch (Exception e) {
            return null;
        }

        //info o pacijentu

        TPrimalacPotvrde pacijent = new TPrimalacPotvrde();
        pacijent.setIme(saglasnost.getPacijent().getIme());
        pacijent.setPrezime(saglasnost.getPacijent().getPrezime());
        pacijent.setDatumRodjenja(saglasnost.getPacijent().getDatumRodjenja());
        pacijent.setPol(saglasnost.getPacijent().getPol());
        // if(saglasnost.getPacijent().getDrzavljanstvo().getJMBG() == null)
        // pacijent.setJMBG(saglasnost.getPacijent().getDrzavljanstvo().getBrojPasosa());
        pacijent.setJMBG(saglasnost.getPacijent().getDrzavljanstvo().getJMBG());

        //Doze vakcinacije

        List<TDoza> dozeList;


        Potvrda potvrda = new Potvrda();
        potvrda.setDatumIzdavanja(danasnjiDatum);
        potvrda.setQrKod("NEMAZASAD");
        potvrda.setPrimalac(pacijent);

        if(saglasnost.getEvidencijaOVakcinaciji() == null)
            return null;


        if(saglasnost.getEvidencijaOVakcinaciji().getVakcine() != null) {
            List<TVakcina> sveDateVakcine = saglasnost.getEvidencijaOVakcinaciji().getVakcine().getVakcina();
            if (sveDateVakcine == null || sveDateVakcine.size() == 0) {
                dozeList = new ArrayList<>();
            } else {
                dozeList = new ArrayList<>();
                for(int i=0; i< sveDateVakcine.size(); i++)
                    dozeList.add(makeDozaFromTVakcina(sveDateVakcine.get(i), i+1));
            }

            TVakcinacija doze = new TVakcinacija();
            doze.setDoza(dozeList);
            potvrda.setDoze(doze);
            potvrda.setZdravstvenaUstanova(saglasnost.getEvidencijaOVakcinaciji().getZdravstvenaUstanova());

        }
        else return null;
        try {
            potvrda = potvrdaRepository.save(potvrda);
            rdfService.extractMetadata(potvrda, Potvrda.class, potvrdaPath);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nije moguce sacuvati potvrdu");
        }
        return potvrda;
    }

    @Override
    public TDoza makeDozaFromTVakcina(TVakcina tvakc, int redniBroj) {
        TDoza doza = new TDoza();

        TDatumVakcinacije vacDatum = new TDatumVakcinacije();
        vacDatum.setValue(tvakc.getDatumDavanja());
        vacDatum.setDatatype("http://www.w3.org/2001/XMLSchema#date");
        vacDatum.setProperty("pred:datumVakcinisanja");

        TCRedniBrojVakcine redniBrojVakcine = new TCRedniBrojVakcine();
        redniBrojVakcine.setProperty("pred:brojVakcine");
        redniBrojVakcine.setValue(BigInteger.valueOf(redniBroj));
        redniBrojVakcine.setDatatype("http://www.w3.org/2001/XMLSchema#positiveInteger");

        TCVakcina tipVakcine = new TCVakcina();
        tipVakcine.setProperty("pred:nazivVakcine");
        tipVakcine.setDatatype("http://www.w3.org/2001/XMLSchema#string");
        tipVakcine.setValue(com.tim123.vaccinationportal.model.tipovi.TVakcina.fromVakcinaString(tvakc.getNaziv()));

        doza.setDatumDavanja(vacDatum);
        doza.setRedniBroj(redniBrojVakcine);
        doza.setTipVakcine(tipVakcine);
        doza.setBrojSerije(tvakc.getSerija());

        return doza;
    }

    @Override
    public List<Potvrda> dobaviZaKorisnika(String email) {
        return potvrdaRepository.getForUserEmail(email);
    }

    @Override
    public String izadaj(String saglasnostId) {
        Potvrda potvrda = generisiPotvrdu(saglasnostId);
        if (potvrda == null)
            return null;
        return potvrda.getSifra();
    }

    @Override
    public Integer countDosesByNo(String startDate, String endDate, int numberOfDose) {

        List<Potvrda> potvrde = potvrdaRepository.findAll();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate intervalStart = LocalDate.parse(startDate, formatter);
        LocalDate intervalEnd = LocalDate.parse(endDate, formatter);

        int doseCounter = 0;
        for (Potvrda potvrda : potvrde) {
            LocalDate documentDate = potvrda.getDatumIzdavanja().getValue().toGregorianCalendar().toZonedDateTime().toLocalDate();
            if (documentDate.compareTo(intervalStart) > 0 && documentDate.compareTo(intervalEnd) < 0) {
                if (potvrda.getDoze().getDoza().size() == numberOfDose) {
                    doseCounter++;
                }
            }
        }
        return doseCounter;
    }

    @Override
    public Integer countDosesByManufacturer(String startDate, String endDate, String manufacturer) {

        List<Potvrda> potvrde = potvrdaRepository.findAll();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate intervalStart = LocalDate.parse(startDate, formatter);
        LocalDate intervalEnd = LocalDate.parse(endDate, formatter);

        int manufacturerCounter = 0;
        for (Potvrda potvrda : potvrde) {
            LocalDate documentDate = potvrda.getDatumIzdavanja().getValue().toGregorianCalendar().toZonedDateTime().toLocalDate();
            if (documentDate.compareTo(intervalStart) > 0 && documentDate.compareTo(intervalEnd) < 0) {
                List<TDoza> doses = potvrda.getDoze().getDoza();
                if (doses.get(doses.size() - 1).getTipVakcine().getValue() == com.tim123.vaccinationportal.model.tipovi.TVakcina.fromValue(manufacturer)) {
                    manufacturerCounter++;
                }
            }
        }
        return manufacturerCounter;
    }

    @Override
    public String searchByString(String searchedString) {

        List<Potvrda> potvrde = potvrdaRepository.findAll();

        potvrde = potvrde.stream().filter(potvrda -> potvrda.getSifra() != null).collect(Collectors.toList());

        List<String> potvrdeConverted = potvrde.stream().map(potvrda -> {
            try {
                return marshallUnmarshallService.marshall(potvrda, Potvrda.class);
            } catch (JAXBException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList());

        return searchUtil.parseSearchResult(potvrdeConverted, "potvrda", searchedString);
    }

    @Override
    public Potvrda dobaviPotvrdu(String id) {
        try {
            return potvrdaRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
