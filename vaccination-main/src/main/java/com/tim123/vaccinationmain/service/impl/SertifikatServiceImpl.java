package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.dto.dokumenta.Dokument;
import com.tim123.vaccinationmain.dto.dokumenta.ListaDokumenata;
import com.tim123.vaccinationmain.dto.dokumenta.TipDokumenta;
import com.tim123.vaccinationmain.model.sertifikat.Sertifikat;
import com.tim123.vaccinationmain.model.sertifikat.TTestovi;
import com.tim123.vaccinationmain.model.sertifikat.TVakcinacija;
import com.tim123.vaccinationmain.model.tipovi.TVakcinisanoLice;
import com.tim123.vaccinationmain.repository.CRUDRepository;
import com.tim123.vaccinationmain.repository.SertifikatRepository;
import com.tim123.vaccinationmain.service.MarshallUnmarshallService;
import com.tim123.vaccinationmain.service.SertifikatService;
import com.tim123.vaccinationmain.util.PDFTransformer;
import com.tim123.vaccinationmain.util.SearchUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.bind.JAXBException;
import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.tim123.vaccinationmain.util.QRUtil.getQRImage;

@Service
@RequiredArgsConstructor
public class SertifikatServiceImpl extends CRUDServiceImpl<Sertifikat> implements SertifikatService {

    private final SertifikatRepository sertifikatRepository;
    private final MarshallUnmarshallService<Sertifikat> marshallUnmarshallService;
    private final RestTemplate restTemplate;
    private final PDFTransformer pdfTransformer;
    private final SearchUtil searchUtil;

    @Override
    protected CRUDRepository<Sertifikat> getRepository() {
        return null;
    }

    @Override
    public int prebrojSertifikateZaPeriod(String startDate, String endDate) {

        List<Sertifikat> sertifikati = sertifikatRepository.findAll();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate intervalStart = LocalDate.parse(startDate, formatter);
        LocalDate intervalEnd = LocalDate.parse(endDate, formatter);

        int numberOfDocumentsInPeriod = 0;

        for (Sertifikat sertifikat : sertifikati) {

            LocalDate documentDate = sertifikat.getDatumVreme().getValue().toGregorianCalendar().toZonedDateTime().toLocalDate();

            if (documentDate.compareTo(intervalStart) > 0 && documentDate.compareTo(intervalEnd) < 0) {
                numberOfDocumentsInPeriod++;
            }

        }
        return numberOfDocumentsInPeriod;
    }

    @Override
    public Sertifikat generisiSertifikat(TVakcinisanoLice podnosilac) {
        var broj = UUID.randomUUID().toString();
        var dv = Sertifikat.DatumVreme.builder()
                .value(CalendarUtil.toXmlGregorianCalendar(System.currentTimeMillis()))
                .property("pred:datumIzdavanja")
                .build();
        var QRPath = String.format("http://localhost:8081/api/sertifikat/pdf/%s", broj);
        var QRImage = "";
        try {
            QRImage = getQRImage(QRPath);
        } catch (Exception ignored) {
        }
        var testovi = TTestovi.builder().build();
        var vakcinacija = dobaviVakcinaciju(
                podnosilac.getJMBG().getValue(),
                podnosilac.getBrojPasosa().getValue());
        var sertifikat = Sertifikat.builder()
                .brojSertifikata(broj)
                .datumVreme(dv)
                .primalac(podnosilac)
                .qrKod(QRImage)
                .vakcinacija(vakcinacija)
                .testovi(testovi)
                .build();
        try {
            sertifikatRepository.save(sertifikat);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nije moguce sacuvati sertifikat");
        }

        return sertifikat;
    }

    @Override
    public ByteArrayInputStream generisiPDF(String id) {
        try {
            Sertifikat sertifikat = sertifikatRepository.findById(id);
            return pdfTransformer.generatePDF(marshallUnmarshallService.marshall(sertifikat, Sertifikat.class), Sertifikat.class);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sertifikat nije pronadjen");
        }
    }

    @Override
    public ListaDokumenata getZaKorisnika(String jmbg, String pasos) {
        List<Sertifikat> sertifikati = sertifikatRepository.getZaKorisnika(jmbg, pasos);
        List<Dokument> dokumenta = sertifikati.stream().map(ser ->
                new Dokument(TipDokumenta.SERTIFIKAT, ser.getDatumVreme().getValue(),
                        ser.getBrojSertifikata())).collect(Collectors.toList());
        return new ListaDokumenata(dokumenta);
    }

    @Override
    public String searchByString(String searchedString) {

        List<Sertifikat> sertifikati = sertifikatRepository.findAll();

        sertifikati = sertifikati.stream().filter(sertifikat -> sertifikat.getBrojSertifikata() != null).collect(Collectors.toList());

        List<String> sertifikatiConverted = sertifikati.stream().map(sertifikat -> {
            try {
                return marshallUnmarshallService.marshall(sertifikat, Sertifikat.class);
            } catch (JAXBException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList());

        return searchUtil.parseSearchResult(sertifikatiConverted, "sertifikat", searchedString);
    }

    private TVakcinacija dobaviVakcinaciju(String jmbg, String pasos) {
        ResponseEntity<TVakcinacija> vakcine = restTemplate.getForEntity(
                String.format("http://localhost:8082/api/korisnik/vakcine?jmbg=%s&pasos=%s", jmbg, pasos),
                TVakcinacija.class);
        return Objects.requireNonNull(vakcine.getBody());
    }
}
