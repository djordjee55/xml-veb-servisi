package com.tim123.vaccinationportal.service.impl;

import com.tim123.vaccinationportal.grddl.MetadataExtractor;
import com.tim123.vaccinationportal.service.RDFService;
import com.tim123.vaccinationportal.util.JenaAuthenticationUtilities;
import com.tim123.vaccinationportal.util.MarshallUnmarshallFactory;
import com.tim123.vaccinationportal.util.SparqlUtil;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecution;
import org.apache.jena.update.UpdateFactory;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;

@Service
public class RDFServiceImpl implements RDFService {

    @Override
    public void extractMetadata(String xmlDocument, String graphPath) throws IOException, TransformerException {
        var conn = JenaAuthenticationUtilities.loadProperties();
        var metadataExtractor = new MetadataExtractor();
        var rdfData = metadataExtractor.extractMetadata(new ByteArrayInputStream(xmlDocument.getBytes()));

        var model = ModelFactory.createDefaultModel();
        model.read(new ByteArrayInputStream(rdfData.getBytes()), null);
        var out = new ByteArrayOutputStream();
        model.write(out, SparqlUtil.NTRIPLES);
        // model.write(System.out, SparqlUtil.RDF_XML);

        var sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + graphPath, out.toString());
        var updateRequest = UpdateFactory.create(sparqlUpdate);

        var processor = UpdateExecution.service(conn.updateEndpoint).update(updateRequest).build();
        processor.execute();
    }

    @Override
    public void extractMetadata(Object object, Class<?> objectClass, String graphPath) throws IOException, TransformerException, JAXBException {
        var stringWriter = new StringWriter();
        var marshaller = MarshallUnmarshallFactory.getMarshaller(objectClass);
        marshaller.marshal(object, stringWriter);
        extractMetadata(stringWriter.toString(), graphPath);
    }

    @Override
    public void dropSchema() throws IOException {
        var conn = JenaAuthenticationUtilities.loadProperties();
        var updateRequest = UpdateFactory.create() ;
        updateRequest.add(SparqlUtil.dropAll());
        var processor = UpdateExecution.service(conn.updateEndpoint).update(updateRequest).build();
        processor.execute();
    }
}