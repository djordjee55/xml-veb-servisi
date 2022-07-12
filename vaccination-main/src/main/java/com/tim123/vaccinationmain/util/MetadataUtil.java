package com.tim123.vaccinationmain.util;

import com.tim123.vaccinationmain.dto.dokumenta.TipDokumenta;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MetadataUtil {

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
            return String.format("%scontains((lcase(str(?%s))), \"%s\")", neg, p, o);
        }
        // Literal
        else if (expType == 'l') {
            return String.format("?%s%s=%s", p, neg, o);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nepoznat predikat");
    }
}
