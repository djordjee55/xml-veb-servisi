package com.tim123.vaccinationmain.util;

import com.tim123.vaccinationmain.dto.dokumenta.Dokument;
import com.tim123.vaccinationmain.dto.dokumenta.ListaDokumenata;
import com.tim123.vaccinationmain.dto.dokumenta.TipDokumenta;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.tim123.vaccinationmain.util.Constants.sertifikatPath;

@Component
public class MetadataUtil {

    protected JenaAuthenticationUtilities.JenaConnectionProperties conn;
    @Autowired
    private final RestTemplate restTemplate;

    @Autowired
    public MetadataUtil(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        try {
            conn = JenaAuthenticationUtilities.loadProperties();
        } catch (Exception ignore) {}
    }

    public String interesovanjeConverter(String exp) {
        String[] splitExp = splitExp(exp);
        String p = splitExp[0];
        String o = splitExp[1];
        String op = splitExp[2];
        char expType = 'x';
        switch (p) {
            case "datumIzdavanja":
                expType = 'd';
                break;
            case "drzavljanin":
            case "zeljenaVakcina":
                expType = 'r';
                break;
            case "davalacKrvi":
                expType = 'l';
                break;
        }
        return prepareExp(p, o, op, expType);
    }

    public String zahtevConverter(String exp) {
        String[] splitExp = splitExp(exp);
        String p = splitExp[0];
        String o = splitExp[1];
        String op = splitExp[2];
        char expType = 'x';
        if ("datumIzdavanja".equals(p)) {
            expType = 'd';
        }
        return prepareExp(p, o, op, expType);
    }

    public String sertifikatConverter(String exp) {
        String[] splitExp = splitExp(exp);
        String p = splitExp[0];
        String o = splitExp[1];
        String op = splitExp[2];
        char expType = 'x';
        if ("datumIzdavanja".equals(p)) {
            expType = 'd';
        }
        return prepareExp(p, o, op, expType);
    }

    public String saglasnostConverter(String exp) {
        String[] splitExp = splitExp(exp);
        String p = splitExp[0];
        String o = splitExp[1];
        String op = splitExp[2];
        char expType = 'x';
        if ("datumIzdavanja".equals(p)) {
            expType = 'd';
        }
        return prepareExp(p, o, op, expType);
    }

    public String potvrdaConverter(String exp) {
        String[] splitExp = splitExp(exp);
        String p = splitExp[0];
        String o = splitExp[1];
        String op = splitExp[2];
        char expType = 'x';
        switch (p) {
            case "datumIzdavanja":
            case "datumVakcinisanja":
                expType = 'd';
                break;
            case "nazivVakcine":
                expType = 'r';
                break;
            case "brojVakcine":
                expType = 'l';
                break;
        }
        return prepareExp(p, o, op, expType);
    }

    private String[] splitExp(String exp) {
        var nameval = exp.split("=|!=");
        var op = exp.substring(nameval[0].length(), exp.length() - nameval[1].length());
        List<String> arrlist = new ArrayList<>(Arrays.asList(nameval));
        arrlist.add(op);
        return arrlist.toArray(nameval);
    }


    private String prepareExp(String p, String o, String op, char expType) {
        var isNeg = op.charAt(0) == '!';
        var neg = isNeg ? "!" : "";
        // Date
        if (expType == 'd') {
            var xsdDate = "<http://www.w3.org/2001/XMLSchema#date>";
            return String.format("?%s%s=\"%s\"^^%s", p, neg, o, xsdDate);
        }
        // Resource
        else if (expType == 'r') {
            return String.format("%scontains((lcase(str(?%s))),\"%s\")", neg, p, o);
        }
        // Literal
        else if (expType == 'l') {
            return String.format("?%s%s=%s", p, neg, o);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nepoznat predikat");
    }

    public ListaDokumenata sparqlQuery(TipDokumenta type, String filter) {
        switch (type) {
            case ZAHTEV:
                return zahtevQuery(filter);
            case POTVRDA:
                return potvrdaQuery(filter);
            case SAGLASNOST:
                return saglasnostQuery(filter);
            case SERTIFIKAT:
                return sertifikatQuery(filter);
            case INTERESOVANJE:
                return interesovanjeQuery(filter);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nepoznat tip dokumenta");
    }

    private ListaDokumenata interesovanjeQuery(String filter) {
        String url = encodeFilter("http://localhost:8082/api/metadata-search/interesovanje", filter);
        var response = restTemplate.exchange(url, HttpMethod.GET,
                null, ListaDokumenata.class);
        return response.getBody();
    }

    private ListaDokumenata sertifikatQuery(String filter) {
        var condition =
                String.format(
                        "SELECT DISTINCT ?s ?datumIzdavanja\n" +
                                "WHERE {\n" +
                                "  ?s \n" +
                                "    <http://www.xws.org/vacc/#datumIzdavanja> ?datumIzdavanja ;\n" +
                                "  FILTER (%s)" +
                                "}", filter
                );
        return execQuery(sertifikatPath, condition, TipDokumenta.SERTIFIKAT);
    }

    private ListaDokumenata saglasnostQuery(String filter) {
        String url = encodeFilter("http://localhost:8082/api/metadata-search/saglasnost", filter);
        var response = restTemplate.exchange(url, HttpMethod.GET,
                null, ListaDokumenata.class);
        return response.getBody();
    }

    private ListaDokumenata potvrdaQuery(String filter) {
        String url = encodeFilter("http://localhost:8082/api/metadata-search/potvrda", filter);
        var response = restTemplate.exchange(url, HttpMethod.GET,
                null, ListaDokumenata.class);
        return response.getBody();
    }

    private ListaDokumenata zahtevQuery(String filter) {
        String url = encodeFilter("http://localhost:8082/api/metadata-search/zahtev", filter);
        var response = restTemplate.exchange(url, HttpMethod.GET,
                null, ListaDokumenata.class);
        return response.getBody();
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
            d.setId(id);
            d.setTipDokumenta(tip);
            XMLGregorianCalendar date = null;
            try {
                var dateRes = querySolution.get("datumIzdavanja").toString();
                date = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateRes);
            } catch (Exception ignore) {}
            d.setCalendar(date);
            dokumenta.add(d);
        }
        queryExecution.close();
        listaDokumenata.setDokumenta(dokumenta);
        return listaDokumenata;
    }

    private String encodeFilter(String path, String filter) {
        URIBuilder ub;
        try {
            ub = new URIBuilder(path);
        } catch (URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nevalidan filter");
        }
        ub.addParameter("filter", filter);
        return ub.toString();
    }
}
