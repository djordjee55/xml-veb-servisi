package com.tim123.vaccinationmain.service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.tim123.vaccinationmain.model.sertifikat.Sertifikat;
import com.tim123.vaccinationmain.model.sertifikat.TDoza;
import com.tim123.vaccinationmain.model.sertifikat.TTestovi;
import com.tim123.vaccinationmain.model.sertifikat.TVakcinacija;
import com.tim123.vaccinationmain.model.tipovi.TVakcinisanoLice;
import com.tim123.vaccinationmain.repository.CRUDRepository;
import com.tim123.vaccinationmain.repository.SertifikatRepository;
import com.tim123.vaccinationmain.service.SertifikatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.tim123.vaccinationmain.util.QRUtil.getQRImage;

@Service
@RequiredArgsConstructor
public class SertifikatServiceImpl extends CRUDServiceImpl<Sertifikat> implements SertifikatService {

    private final SertifikatRepository sertifikatRepository;
    private final RestTemplate restTemplate;

    @Override
    protected CRUDRepository<Sertifikat> getRepository() {
        return null;
    }

    @Override
    public int prebrojSertifikateZaPeriod(String startDate, String endDate) {

        List<Sertifikat> sertifikati =  sertifikatRepository.findAll();

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
        } catch (Exception ignored) {}
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

    private TVakcinacija dobaviVakcinaciju(String jmbg, String pasos) {
        ResponseEntity<TVakcinacija> vakcine = restTemplate.getForEntity(
                String.format("http://localhost:8082/api/korisnik/vakcine?jmbg=%s&pasos=%s", jmbg, pasos),
                TVakcinacija.class);
        return Objects.requireNonNull(vakcine.getBody());
    }
}
