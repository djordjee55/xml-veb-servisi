package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.model.potvrda.Potvrda;
import com.tim123.vaccinationmain.model.potvrda.TDoza;
import com.tim123.vaccinationmain.model.tipovi.TVakcina;
import com.tim123.vaccinationmain.repository.CRUDRepository;
import com.tim123.vaccinationmain.repository.PotvrdaRepository;
import com.tim123.vaccinationmain.service.PotvrdaService;
import com.tim123.vaccinationmain.util.SearchUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PotvrdaServiceImpl extends CRUDServiceImpl<Potvrda> implements PotvrdaService {

    @Override
    protected CRUDRepository<Potvrda> getRepository() {
        return null;
    }

    private final PotvrdaRepository potvrdaRepository;
    private final MarshallUnmarshallServiceImpl<Potvrda> marshallUnmarshallService;
    private final SearchUtil searchUtil;

    public int countDozeByNo(int numberOfDose, String startDate, String endDate) throws ParseException {

        List<Potvrda> potvrde = potvrdaRepository.findAll();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar intervalStart = Calendar.getInstance();
        intervalStart.setTime(formatter.parse(startDate));
        Calendar intervalEnd = Calendar.getInstance();
        intervalStart.setTime(formatter.parse(endDate));

        int doseCounter = 0;
        for (Potvrda potvrda : potvrde) {
            Calendar documentDate = potvrda.getDatumIzdavanja().getValue().toGregorianCalendar();
            if (documentDate.compareTo(intervalStart) > 0 && documentDate.compareTo(intervalEnd) < 0) {
                if (potvrda.getDoze().getDoza().size() == numberOfDose) {
                    doseCounter++;
                }
            }
        }
        return doseCounter;
    }

    @Override
    public int countDozeByManufacturer(TVakcina manufacturer, String startDate, String endDate) throws ParseException {

        List<Potvrda> potvrde = potvrdaRepository.findAll();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar intervalStart = Calendar.getInstance();
        intervalStart.setTime(formatter.parse(startDate));
        Calendar intervalEnd = Calendar.getInstance();
        intervalStart.setTime(formatter.parse(endDate));

        int manufacturerCounter = 0;
        for (Potvrda potvrda : potvrde) {
            Calendar documentDate = potvrda.getDatumIzdavanja().getValue().toGregorianCalendar();
            if (documentDate.compareTo(intervalStart) > 0 && documentDate.compareTo(intervalEnd) < 0) {
                List<TDoza> doses = potvrda.getDoze().getDoza();
                if (doses.get(doses.size() - 1).getTipVakcine().getValue() == manufacturer) {
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
}
