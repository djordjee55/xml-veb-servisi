package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.model.sertifikat.Sertifikat;
import com.tim123.vaccinationmain.repository.CRUDRepository;
import com.tim123.vaccinationmain.repository.SertifikatRepository;
import com.tim123.vaccinationmain.service.SertifikatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SertifikatServiceImpl extends CRUDServiceImpl<Sertifikat> implements SertifikatService {

    private final SertifikatRepository sertifikatRepository;

    @Override
    protected CRUDRepository<Sertifikat> getRepository() {
        return null;
    }

    @Override
    public int prebrojSertifikateZaPeriod(String startDate, String endDate) throws ParseException {

        List<Sertifikat> sertifikati =  sertifikatRepository.findAll();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar intervalStart = Calendar.getInstance();
        intervalStart.setTime(formatter.parse(startDate));
        Calendar intervalEnd = Calendar.getInstance();
        intervalStart.setTime(formatter.parse(endDate));

        int numberOfDocumentsInPeriod = 0;

        for (Sertifikat sertifikat : sertifikati) {

            Calendar documentDate = sertifikat.getDatumVreme().getValue().toGregorianCalendar();
            if (documentDate.compareTo(intervalStart) > 0 && documentDate.compareTo(intervalEnd) < 0) {
                numberOfDocumentsInPeriod++;
            }

        }
        return numberOfDocumentsInPeriod;
    }
}
