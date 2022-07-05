package com.tim123.vaccinationmain.service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.tim123.vaccinationmain.model.sertifikat.Sertifikat;
import com.tim123.vaccinationmain.model.sertifikat.TTestovi;
import com.tim123.vaccinationmain.model.tipovi.TVakcinisanoLice;
import com.tim123.vaccinationmain.repository.CRUDRepository;
import com.tim123.vaccinationmain.repository.SertifikatRepository;
import com.tim123.vaccinationmain.service.SertifikatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SertifikatServiceImpl extends CRUDServiceImpl<Sertifikat> implements SertifikatService {

    private final SertifikatRepository sertifikatRepository;

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
        var QRPath = String.format("http://localhost:8081/sertifikati/pdf/%s", broj);
        var QRImage = "";
        try {
            QRImage = getQRImage(QRPath);
        } catch (Exception ignored) {}
        var testovi = TTestovi.builder().build();
        var sertifikat = Sertifikat.builder()
                .brojSertifikata(broj)
                .datumVreme(dv)
                .primalac(podnosilac)
                .qrKod(QRImage)
                // TODO restTemplate poziv ka portalu
                .vakcinacija(null)
                .testovi(testovi)
                .build();
        try {
            sertifikatRepository.save(sertifikat);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nije moguce sacuvati sertifikat");
        }

        return sertifikat;
    }

    private String getQRImage(String text) throws IOException, WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 100, 100);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageConfig con = new MatrixToImageConfig( 0xFF000002 , 0xFFFFC041 ) ;

        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream, con);
        byte[] pngData = pngOutputStream.toByteArray();

        return Base64.getEncoder().encodeToString(pngData);
    }
}
