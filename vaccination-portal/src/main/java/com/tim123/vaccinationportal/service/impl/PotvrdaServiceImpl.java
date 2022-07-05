package com.tim123.vaccinationportal.service.impl;

import com.tim123.vaccinationportal.exception.ExistDbException;
import com.tim123.vaccinationportal.model.potvrda.Potvrda;
import com.tim123.vaccinationportal.model.potvrda.TDoza;
import com.tim123.vaccinationportal.model.potvrda.TVakcinacija;
import com.tim123.vaccinationportal.model.saglasnost.Saglasnost;
import com.tim123.vaccinationportal.model.saglasnost.TVakcina;
import com.tim123.vaccinationportal.model.tipovi.*;
import com.tim123.vaccinationportal.repository.CRUDRepository;
import com.tim123.vaccinationportal.repository.PotvrdaRepository;
import com.tim123.vaccinationportal.service.PotvrdaService;
import com.tim123.vaccinationportal.service.SaglasnostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PotvrdaServiceImpl extends CRUDServiceImpl<Potvrda> implements PotvrdaService {

    private final PotvrdaRepository potvrdaRepository;
    private final SaglasnostService saglasnostService;


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
        return null;
    }


    @Override
    public ByteArrayInputStream generisiPDF(String id) {
        return null;
    }


    private Potvrda generisiPotvrdu(String saglasnostId) {

        Saglasnost saglasnost;
        TDatumKreiranja danasnjiDatum;
        try {
            saglasnost = saglasnostService.findById(saglasnostId).orElseThrow(RuntimeException::new);
            danasnjiDatum = new TDatumKreiranja();
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(new Date());
            XMLGregorianCalendar xmlgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
            danasnjiDatum.setValue(xmlgc);
            danasnjiDatum.setDatatype("xs:date");
            danasnjiDatum.setProperty("pred:datumIzdavanja");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        //info o pacijentu

        TPrimalacPotvrde pacijent = new TPrimalacPotvrde();
        pacijent.setIme(saglasnost.getPacijent().getIme());
        pacijent.setPrezime(saglasnost.getPacijent().getPrezime());
        pacijent.setDatumRodjenja(saglasnost.getPacijent().getDatumRodjenja());
        pacijent.setPol(saglasnost.getPacijent().getPol());
        pacijent.setJMBG(saglasnost.getPacijent().getDrzavljanstvo().getJMBG());

        //Doze vakcinacije

        List<TDoza> dozeList;

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


        Potvrda potvrda = new Potvrda();
        potvrda.setDatumIzdavanja(danasnjiDatum);
        potvrda.setQrKod("NEMAZASAD");
        potvrda.setPrimalac(pacijent);
        potvrda.setDoze(doze);
        potvrda.setZdravstvenaUstanova(saglasnost.getEvidencijaOVakcinaciji().getZdravstvenaUstanova());

        return potvrda;
    }

    private TDoza makeDozaFromTVakcina(TVakcina tvakc, int redniBroj) {
        TDoza doza = new TDoza();

        TDatumVakcinacije vacDatum = new TDatumVakcinacije();
        vacDatum.setValue(tvakc.getDatumDavanja());
        vacDatum.setDatatype("xs:date");
        vacDatum.setProperty("pred:datumIzdavanja");

        TCRedniBrojVakcine redniBrojVakcine = new TCRedniBrojVakcine();
        redniBrojVakcine.setProperty("pred:brojVakcine");
        redniBrojVakcine.setValue(BigInteger.valueOf(redniBroj));
        redniBrojVakcine.setDatatype("xs:positiveInteger");


        TCVakcina tipVakcine = new TCVakcina();
        tipVakcine.setProperty("pred:nazivVakcine");
        tipVakcine.setDatatype("xs:string");
        tipVakcine.setValue(com.tim123.vaccinationportal.model.tipovi.TVakcina.fromValue(tvakc.getNaziv()));


        doza.setDatumDavanja(vacDatum);
        doza.setRedniBroj(redniBrojVakcine);
        doza.setTipVakcine(tipVakcine);
        doza.setBrojSerije(tvakc.getSerija());

        return doza;
    }
}
