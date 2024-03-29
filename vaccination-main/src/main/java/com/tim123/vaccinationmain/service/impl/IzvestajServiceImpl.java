package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.model.izvestaj.Izvestaj;
import com.tim123.vaccinationmain.model.tipovi.TVakcina;
import com.tim123.vaccinationmain.repository.CRUDRepository;
import com.tim123.vaccinationmain.repository.IzvestajRepository;
import com.tim123.vaccinationmain.service.IzvestajService;
import com.tim123.vaccinationmain.service.MarshallUnmarshallService;
import com.tim123.vaccinationmain.service.SertifikatService;
import com.tim123.vaccinationmain.util.HTMLTransformer;
import com.tim123.vaccinationmain.util.PDFTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IzvestajServiceImpl extends CRUDServiceImpl<Izvestaj> implements IzvestajService {

    private final MarshallUnmarshallService<Izvestaj> marshallUnmarshallService;
    private final IzvestajRepository izvestajRepository;
    private final RestTemplate restTemplate;
    private final SertifikatService sertifikatService;
    private final PDFTransformer pdfTransformer;
    private final HTMLTransformer htmlTransformer;

    @Override
    protected CRUDRepository<Izvestaj> getRepository() {
        return izvestajRepository;
    }

    @Override
    public ByteArrayInputStream generisiHTML(String id) throws JAXBException {
        return htmlTransformer.generateHTML(marshallUnmarshallService.marshall(dobaviIzvestaj(id), Izvestaj.class), Izvestaj.class);
    }

    @Override
    public ByteArrayInputStream generisiPDF(String id) throws JAXBException {
        return pdfTransformer.generatePDF(marshallUnmarshallService.marshall(dobaviIzvestaj(id), Izvestaj.class), Izvestaj.class);
    }

    private Izvestaj dobaviIzvestaj(String id) {
        var interesovanje = this.findById(id);
        if (interesovanje.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Interesovanje nije pronadjeno");
        }
        return interesovanje.get();
    }

    @Override
    public String getIzvestajForPeriod(String startDate, String endDate) throws Exception {

        Izvestaj existingIzvestaj = izvestajRepository.findByPeriod(startDate, endDate);
        if (existingIzvestaj != null) {
            return existingIzvestaj.getId();
        }

        XMLGregorianCalendar currentDate = getCurrentDate();
        Izvestaj.Period period = Izvestaj.Period.builder()
                .od(extractDateFromString(startDate))
                ._do(extractDateFromString(endDate))
                .build();

        BigInteger countInteresovanje = countInteresovanjeForPeriod(startDate, endDate);
        BigInteger countZahtevi = countZahteviForPeriod(startDate, endDate);
        BigInteger countSertifikat = countSertifikatForPeriod(startDate, endDate);

        List<Izvestaj.Doze.Doza> doze = getDosesNoDistribution(startDate, endDate);
        int dozeSum = doze.stream().mapToInt(doza -> doza.getBrojDatihDoza().intValue()).sum();

        Izvestaj.RaspodelaPoProizvodjacima vaccineDistribution = getVaccineManufacturerDistribution(startDate, endDate);

        Izvestaj izvestaj = Izvestaj.builder()
                .datumIzdavanja(currentDate)
                .period(period)
                .brojPodnetihDokumenataOInteresovanju(countInteresovanje)
                .brojPrimljenihZahtevaZaDigitalniZeleniSertifikat(countZahtevi)
                .brojIzdatihZahtevaZaDigitalniZeleniSertifikat(countSertifikat)
                .ukupanBrojDatihDozaVakcine(BigInteger.valueOf(dozeSum))
                .doze(Izvestaj.Doze.builder()
                        .doza(doze)
                        .build())
                .raspodelaPoProizvodjacima(vaccineDistribution)
                .build();

        izvestajRepository.save(izvestaj);

        return izvestaj.getId();
    }

    private Izvestaj.RaspodelaPoProizvodjacima getVaccineManufacturerDistribution(String startDate, String endDate) throws ParseException {

        Izvestaj.RaspodelaPoProizvodjacima.Proizvodjac proizvodjac1 = Izvestaj.RaspodelaPoProizvodjacima.Proizvodjac.builder()
                .naziv(TVakcina.SPUTNIK_V)
                .brojDoza(countDosesByManufacturer(startDate, endDate, TVakcina.SPUTNIK_V.value()))
                .build();

        Izvestaj.RaspodelaPoProizvodjacima.Proizvodjac proizvodjac2 = Izvestaj.RaspodelaPoProizvodjacima.Proizvodjac.builder()
                .naziv(TVakcina.ASTRA_ZENECA)
                .brojDoza(countDosesByManufacturer(startDate, endDate, TVakcina.ASTRA_ZENECA.value()))
                .build();

        Izvestaj.RaspodelaPoProizvodjacima.Proizvodjac proizvodjac3 = Izvestaj.RaspodelaPoProizvodjacima.Proizvodjac.builder()
                .naziv(TVakcina.MODERNA)
                .brojDoza(countDosesByManufacturer(startDate, endDate, TVakcina.MODERNA.value()))
                .build();

        Izvestaj.RaspodelaPoProizvodjacima.Proizvodjac proizvodjac4 = Izvestaj.RaspodelaPoProizvodjacima.Proizvodjac.builder()
                .naziv(TVakcina.PFIZER_BIO_N_TECH)
                .brojDoza(countDosesByManufacturer(startDate, endDate, TVakcina.PFIZER_BIO_N_TECH.value()))
                .build();

        Izvestaj.RaspodelaPoProizvodjacima.Proizvodjac proizvodjac5 = Izvestaj.RaspodelaPoProizvodjacima.Proizvodjac.builder()
                .naziv(TVakcina.SINOPHARM)
                .brojDoza(countDosesByManufacturer(startDate, endDate, TVakcina.SINOPHARM.value()))
                .build();

        return Izvestaj.RaspodelaPoProizvodjacima.builder()
                .proizvodjac(List.of(proizvodjac1, proizvodjac2, proizvodjac3, proizvodjac4, proizvodjac5))
                .build();
    }

    private List<Izvestaj.Doze.Doza> getDosesNoDistribution(String startDate, String endDate) throws ParseException {

        Izvestaj.Doze.Doza dose1 = Izvestaj.Doze.Doza.builder()
                .redniBroj(BigInteger.valueOf(1))
                .brojDatihDoza(countDosesByNo(startDate, endDate, 1))
                .build();

        Izvestaj.Doze.Doza dose2 = Izvestaj.Doze.Doza.builder()
                .redniBroj(BigInteger.valueOf(2))
                .brojDatihDoza(countDosesByNo(startDate, endDate, 2))
                .build();

        return List.of(dose1, dose2);
    }

    private XMLGregorianCalendar getCurrentDate() {

        try {
            GregorianCalendar calendar = new GregorianCalendar();

            return DatatypeFactory.newInstance().newXMLGregorianCalendarDate(
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);
        } catch (DatatypeConfigurationException ignored) {
        }

        return null;
    }

    private XMLGregorianCalendar extractDateFromString(String date) {

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            GregorianCalendar calendar = new GregorianCalendar();
            Date periodOdDate = formatter.parse(date);
            calendar.setTime(periodOdDate);

            return DatatypeFactory.newInstance().newXMLGregorianCalendarDate(
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);
        } catch (ParseException | DatatypeConfigurationException ignored) {
        }

        return null;
    }

    private BigInteger countInteresovanjeForPeriod(String startDate, String endDate) {

        ResponseEntity<Integer> response = restTemplate.getForEntity("http://localhost:8082/api/interesovanje/count/" + startDate + "/" + endDate, Integer.class);

        return BigInteger.valueOf(response.getBody());
    }

    private BigInteger countZahteviForPeriod(String startDate, String endDate) {

        ResponseEntity<Integer> response = restTemplate.getForEntity("http://localhost:8082/api/zahtev/count/" + startDate + "/" + endDate, Integer.class);

        return BigInteger.valueOf(response.getBody());
    }

    private BigInteger countSertifikatForPeriod(String startDate, String endDate) throws ParseException {

        int numberOfDocuments = sertifikatService.prebrojSertifikateZaPeriod(startDate, endDate);

        return BigInteger.valueOf(numberOfDocuments);
    }

    private BigInteger countDosesByNo(String startDate, String endDate, Integer numberOfDose) {

        ResponseEntity<Integer> response = restTemplate.getForEntity("http://localhost:8082/api/potvrda/count-doses?startDate=" + startDate + "&endDate=" + endDate + "&numberOfDose=" + numberOfDose, Integer.class);

        return BigInteger.valueOf(response.getBody());
    }

    private BigInteger countDosesByManufacturer(String startDate, String endDate, String manufacturer) {

        ResponseEntity<Integer> response = restTemplate.getForEntity("http://localhost:8082/api/potvrda/count-doses-by-manufacturer?startDate=" + startDate + "&endDate=" + endDate + "&manufacturer=" + manufacturer, Integer.class);

        return BigInteger.valueOf(response.getBody());
    }
}
