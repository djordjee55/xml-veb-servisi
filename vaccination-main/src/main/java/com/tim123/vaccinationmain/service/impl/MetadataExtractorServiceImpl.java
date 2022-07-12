package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.dto.dokumenta.Metapodatak;
import com.tim123.vaccinationmain.dto.dokumenta.TipDokumenta;
import com.tim123.vaccinationmain.grddl.MetadataExtractor;
import com.tim123.vaccinationmain.model.sertifikat.Sertifikat;
import com.tim123.vaccinationmain.repository.SertifikatRepository;
import com.tim123.vaccinationmain.service.MarshallUnmarshallService;
import com.tim123.vaccinationmain.service.MetadataExtractorService;
import com.tim123.vaccinationmain.util.SparqlUtil;
import lombok.RequiredArgsConstructor;
import org.apache.jena.rdf.model.ModelFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import org.json.JSONObject;
import org.json.XML;
import org.json.JSONException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class MetadataExtractorServiceImpl implements MetadataExtractorService {

    private final SertifikatRepository sertifikatRepository;
    private final MarshallUnmarshallService<Sertifikat> sertifikatMarshallUnmarshallService;
    private final RestTemplate restTemplate;

    @Override
    public ByteArrayInputStream extractMetadata(TipDokumenta tip, Metapodatak vrsta, String id) {
        var extractor = new MetadataExtractor();
        var xmlDoc = "";
        switch (tip) {
            case SERTIFIKAT:
                try {
                    var s = sertifikatRepository.findById(id);
                    xmlDoc = sertifikatMarshallUnmarshallService.marshall(s, Sertifikat.class);
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Los sertifikat");
                }
                break;
            case INTERESOVANJE:
                try {
                    xmlDoc = restTemplate.getForObject(
                            "http://localhost:8082/api/interesovanje/str/" + id,
                            String.class);
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lose interesovanje");
                }
                break;
            case SAGLASNOST:
                try {
                    xmlDoc = restTemplate.getForObject(
                            "http://localhost:8082/api/saglasnost/str/" + id,
                            String.class);
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Losa saglasnost");
                }
                break;
            case POTVRDA:
                try {
                    xmlDoc = restTemplate.getForObject(
                            "http://localhost:8082/api/potvrda/str/" + id,
                            String.class);
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Losa potvrda");
                }
                break;
            case ZAHTEV:
                try {
                    xmlDoc = restTemplate.getForObject(
                            "http://localhost:8082/api/zahtev/str/" + id,
                            String.class);
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Los zahtev");
                }
                break;
        }
        var metadata = "";
        try {
            metadata = extractor.extractMetadata(new ByteArrayInputStream(xmlDoc.getBytes()));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nije moguce izdvojiti metapodatke");
        }
        var model = ModelFactory.createDefaultModel();
        model.read(new ByteArrayInputStream(metadata.getBytes()), null);
        var out = new ByteArrayOutputStream();
        model.write(out, SparqlUtil.RDF_XML);
        switch (vrsta) {
            case RDF:
                System.out.println(out);
                return new ByteArrayInputStream(out.toByteArray());
            case JSON:
                try {
                    JSONObject json = XML.toJSONObject(out.toString());
                    String jsonString = json.toString();
                    System.out.println(jsonString);
                    return new ByteArrayInputStream(jsonString.getBytes());
                } catch (JSONException e) {
                    return new ByteArrayInputStream("".getBytes());
                }
        }
        return null;
    }
}
