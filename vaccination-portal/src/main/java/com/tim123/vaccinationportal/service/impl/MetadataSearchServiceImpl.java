package com.tim123.vaccinationportal.service.impl;

import com.tim123.vaccinationportal.model.dto.dokumenta.Dokument;
import com.tim123.vaccinationportal.model.dto.dokumenta.ListaDokumenata;
import com.tim123.vaccinationportal.model.dto.dokumenta.TipDokumenta;
import com.tim123.vaccinationportal.service.MetadataSearchService;
import com.tim123.vaccinationportal.util.JenaAuthenticationUtilities;
import com.tim123.vaccinationportal.util.SparqlUtil;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;

import static com.tim123.vaccinationportal.util.Constants.*;

@Service
public class MetadataSearchServiceImpl implements MetadataSearchService {

    protected JenaAuthenticationUtilities.JenaConnectionProperties conn;

    public MetadataSearchServiceImpl() {
        try {
            conn = JenaAuthenticationUtilities.loadProperties();
        } catch (Exception ignore) {}
    }

    @Override
    public ListaDokumenata searchInteresovanje(String filter) {
        var condition =
                String.format(
                        "SELECT DISTINCT ?s ?datumIzdavanja\n" +
                                "WHERE {\n" +
                                "  ?s \n" +
                                "    <http://www.xws.org/vacc/#datumIzdavanja> ?datumIzdavanja ;\n" +
                                "    <http://www.xws.org/vacc/#zeljenaVakcina> ?zeljenaVakcina ;\n" +
                                "    <http://www.xws.org/vacc/#davalacKrvi> ?davalacKrvi ;\n" +
                                "    <http://www.xws.org/vacc/#drzavljanin> ?drzavljanin ;\n" +
                                "    \n" +
                                "  FILTER (%s)" +
                                "}", filter
                );
        return execQuery(interesovanjePath, condition, TipDokumenta.INTERESOVANJE);
    }

    @Override
    public ListaDokumenata searchSaglasnost(String filter) {
        var condition =
                String.format(
                        "SELECT DISTINCT ?s ?datumIzdavanja\n" +
                                "WHERE {\n" +
                                "  ?s \n" +
                                "    <http://www.xws.org/vacc/#datumIzdavanja> ?datumIzdavanja ;\n" +
                                "  FILTER (%s)" +
                                "}", filter
                );
        return execQuery(saglasnostPath, condition, TipDokumenta.SAGLASNOST);
    }

    @Override
    public ListaDokumenata searchPotvrda(String filter) {
        var condition =
                String.format(
                        "SELECT DISTINCT ?s ?datumIzdavanja\n" +
                                "WHERE {\n" +
                                "  ?s \n" +
                                "    <http://www.xws.org/vacc/#datumIzdavanja> ?datumIzdavanja ;\n" +
                                "    <http://www.xws.org/vacc/#nazivVakcine> ?nazivVakcine ;\n" +
                                "    <http://www.xws.org/vacc/#datumVakcinisanja> ?datumVakcinisanja ;\n" +
                                "    <http://www.xws.org/vacc/#brojVakcine> ?brojVakcine ;\n" +
                                "    \n" +
                                "  FILTER (%s)" +
                                "}", filter
                );
        return execQuery(potvrdaPath, condition, TipDokumenta.POTVRDA);
    }

    @Override
    public ListaDokumenata searchZahtev(String filter) {
        var condition =
                String.format(
                        "SELECT DISTINCT ?s ?datumIzdavanja\n" +
                                "WHERE {\n" +
                                "  ?s \n" +
                                "    <http://www.xws.org/vacc/#datumIzdavanja> ?datumIzdavanja ;\n" +
                                "  FILTER (%s)" +
                                "}", filter
                );
        return execQuery(zahtevPath, condition, TipDokumenta.ZAHTEV);
    }

    private ListaDokumenata execQuery(String path, String condition, TipDokumenta tip) {
        var query = SparqlUtil.selectData(conn.dataEndpoint + path, condition);
        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(conn.queryEndpoint, query);
        ResultSet resultSet = queryExecution.execSelect();

        ListaDokumenata listaDokumenata = new ListaDokumenata();
        List<Dokument> dokumenta = new ArrayList<>();
        while (resultSet.hasNext()) {
            QuerySolution querySolution = resultSet.nextSolution();
            var id = querySolution.get("s").toString();
            Dokument d = new Dokument();
            d.setId(id.split("#")[1]);
            d.setTipDokumenta(tip);
            XMLGregorianCalendar date = null;
            try {
                var dateRes = querySolution.get("datumIzdavanja").toString().split("\\^\\^")[0];
                date = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateRes);
            } catch (Exception ignore) {}
            d.setCalendar(date);
            dokumenta.add(d);
        }
        queryExecution.close();
        listaDokumenata.setDokumenta(dokumenta);
        return listaDokumenata;
    }
}
