package com.tim123.vaccinationportal.service.impl;

import com.tim123.vaccinationportal.model.sertifikat.Sertifikat;
import com.tim123.vaccinationportal.service.MarshallUnmarshallService;
import com.tim123.vaccinationportal.service.SertifikatService;
import com.tim123.vaccinationportal.util.HTMLTransformer;
import com.tim123.vaccinationportal.util.PDFTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
@RequiredArgsConstructor
public class SertifikatServiceImpl implements SertifikatService {

    private final PDFTransformer pdfTransformer;
    private final HTMLTransformer htmlTransformer;

    private final MarshallUnmarshallService<Sertifikat> marshallUnmarshallService;


    @Override
    public ByteArrayInputStream generisiHTML(String id) {
        return htmlTransformer.generateHTML(dobaviSertifikat(id), Sertifikat.class);
    }

    @Override
    public ByteArrayInputStream generisiPDF(String id) {
        return pdfTransformer.generatePDF(dobaviSertifikat(id), Sertifikat.class);
    }

    @Override
    public Sertifikat getSertifikatById(String id) {
        //Todo ako zatreba
        String sertifikatString = dobaviSertifikat(id);
        return null;
    }

    @Override
    public String dobaviSertifikat(String id) {

        //TODO restTemplate poziv ka drugoj aplikaciji... sad samo test...

        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Sertifikat  xmlns:vc=\"http://www.w3.org/2007/XMLSchema-versioning\"\n" +
                "             xmlns=\"http://www.xws.org/sertifikat\"\n" +
                "             xmlns:tip=\"http://www.xws.org/tipovi\"\n" +
                "             xmlns:pred=\"http://www.xws.org/predicate\"\n" +
                "             xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "             xsi:schemaLocation=\"http://www.xws.org/sertifikat file:../xsd/sertifikat.xsd\"\n" +
                "             id=\"48483fc3-38ef-4826-b061-e78ce0bc2cb0\"\n" +
                "             about=\"http://www.xws.org/sertifikat#48483fc3-38ef-4826-b061-e78ce0bc2cb0\">\n" +
                "    <Datum_vreme property=\"pred:datumIzdavanja\" datatype=\"xs:dateTime\">2006-05-04T18:13:51.0</Datum_vreme>\n" +
                "    <Qr_kod>Qr_kod0</Qr_kod>\n" +
                "    <Primalac>\n" +
                "        <tip:Ime>Ime0</tip:Ime>\n" +
                "        <tip:Prezime>Prezime0</tip:Prezime>\n" +
                "        <tip:Datum_rodjenja>2006-05-04</tip:Datum_rodjenja>\n" +
                "        <tip:Pol>M</tip:Pol>\n" +
                "        <tip:JMBG rel=\"pred:identifikatorKorisnika\" href=\"http://www.xws.org/korisnici#921909aa-8ff1-47f4-b761-4cbac06929ad\">0000000000000</tip:JMBG>\n" +
                "        <tip:Broj_pasosa rel=\"pred:identifikatorKorisnika\" href=\"http://www.xws.org/korisnici#921909aa-8ff1-47f4-b761-4cbac06929ad\">Broj_pasosa0</tip:Broj_pasosa>\n" +
                "    </Primalac>\n" +
                "    <Vakcinacija>\n" +
                "        <Doza redni_broj=\"1\">\n" +
                "            <Tip_vakcine>Tip_vakcine0</Tip_vakcine>\n" +
                "            <Proizvodjac>Proizvodjac0</Proizvodjac>\n" +
                "            <Serija>Serija0</Serija>\n" +
                "            <Datum>2006-05-04</Datum>\n" +
                "            <Zdravstvena_ustanova>Zdravstvena_ustanova0</Zdravstvena_ustanova>\n" +
                "        </Doza>\n" +
                "        <Doza redni_broj=\"2\">\n" +
                "            <Tip_vakcine>Tip_vakcine1</Tip_vakcine>\n" +
                "            <Proizvodjac>Proizvodjac1</Proizvodjac>\n" +
                "            <Serija>Serija1</Serija>\n" +
                "            <Datum>2006-05-04</Datum>\n" +
                "            <Zdravstvena_ustanova>Zdravstvena_ustanova1</Zdravstvena_ustanova>\n" +
                "        </Doza>\n" +
                "    </Vakcinacija>\n" +
                "    <Testovi>\n" +
                "    </Testovi>\n" +
                "</Sertifikat>\n";
    }

    @Override
    public ByteArrayInputStream generisiHTML(Sertifikat s) {
        try {
            return htmlTransformer.generateHTML(marshallUnmarshallService.marshall(s, Sertifikat.class), Sertifikat.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ByteArrayInputStream generisiPDF(Sertifikat s) {
        try {
            return pdfTransformer.generatePDF(marshallUnmarshallService.marshall(s, Sertifikat.class), Sertifikat.class);
        } catch (Exception e) {
            return null;
        }
    }
}
