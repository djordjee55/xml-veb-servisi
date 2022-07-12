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
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class MetadataExtractorServiceImpl implements MetadataExtractorService {

    private final SertifikatRepository sertifikatRepository;
    private final MarshallUnmarshallService<Sertifikat> sertifikatMarshallUnmarshallService;
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
        }
        var metadata = "";
        try {
            metadata = extractor.extractMetadata(new ByteArrayInputStream(xmlDoc.getBytes()));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nije moguce izdvojiti metapodatke iz sertifikata");
        }

        switch (vrsta) {
            case RDF:
                var model = ModelFactory.createDefaultModel();
                model.read(new ByteArrayInputStream(metadata.getBytes()), null);
                var out = new ByteArrayOutputStream();
                model.write(out, SparqlUtil.RDF_XML);
                System.out.println(out.toString());
                break;
            case JSON:
                System.out.println("JSON");
                break;
        }
        return null;
    }
}
