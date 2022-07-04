package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.model.izvestaj.Izvestaj;
import com.tim123.vaccinationmain.model.potvrda.Potvrda;
import com.tim123.vaccinationmain.model.potvrda.TDoza;
import com.tim123.vaccinationmain.model.tipovi.TVakcina;
import com.tim123.vaccinationmain.repository.CRUDRepository;
import com.tim123.vaccinationmain.repository.PotvrdaRepository;
import com.tim123.vaccinationmain.service.PotvrdaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PotvrdaServiceImpl extends CRUDServiceImpl<Potvrda> implements PotvrdaService {

    @Override
    protected CRUDRepository<Potvrda> getRepository() {
        return null;
    }

    private final PotvrdaRepository potvrdaRepository;

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
}
