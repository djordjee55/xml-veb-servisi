package com.tim123.vaccinationportal.repository;

import com.tim123.vaccinationportal.model.Korisnik;
import com.tim123.vaccinationportal.service.ConverterService;
import com.tim123.vaccinationportal.service.MarshallUnmarshallService;
import com.tim123.vaccinationportal.service.XPathService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.tim123.vaccinationportal.util.Constants.korisnikCollection;

@Repository
@RequiredArgsConstructor
public class KorisnikRepository implements CRUDRepository<Korisnik> {

    private final RepositoryUtil repositoryUtil;
    private final MarshallUnmarshallService<Korisnik> marshallUnmarshallService;
    private final XPathService xPathService;
    private final ConverterService<Korisnik> converterService;

    @Override
    public Korisnik save(Korisnik entity) throws Exception {
        UUID uuid = UUID.randomUUID();
        String documentId = uuid.toString();
        entity.setId(documentId);
        repositoryUtil.save(korisnikCollection, documentId, marshallUnmarshallService.marshall(entity, Korisnik.class));
        return entity;
    }

    @Override
    public Korisnik findById(String id) throws Exception {
        return null;
    }

    public Korisnik findByEmail(String email) {
        List<Korisnik> resultSet = new ArrayList<>();
        try {
            ResourceSet result = xPathService.executeXPath(korisnikCollection, String.format("//Korisnik[Email='%s']", email), "");
            resultSet = converterService.convert(result, Korisnik.class);

            if (resultSet.isEmpty())
                return null;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet.get(0);
    }

    public Korisnik findByJMBG(String jmbg) {
        // TODO use findByElementValue
        List<Korisnik> resultSet = new ArrayList<>();
        try {
            ResourceSet result = xPathService.executeXPath(korisnikCollection, String.format("//Korisnik[JMBG='%s']", jmbg), "");
            resultSet = converterService.convert(result, Korisnik.class);

            if (resultSet.isEmpty())
                return null;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet.get(0);
    }

    public Korisnik findByPassport(String passport) {
        // TODO use findByElementValue
        List<Korisnik> resultSet = new ArrayList<>();
        try {
            ResourceSet result = xPathService.executeXPath(korisnikCollection, String.format("//Korisnik[Pasos='%s']", passport), "");
            resultSet = converterService.convert(result, Korisnik.class);

            if (resultSet.isEmpty())
                return null;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet.get(0);
    }

    private Korisnik findByElementValue(String elementName, String elementValue) {
        List<Korisnik> resultSet = new ArrayList<>();
        try {
            ResourceSet result = xPathService.executeXPath(
                    korisnikCollection,
                    String.format("//Korisnik[%s='%s']", elementName, elementValue), "");
            resultSet = converterService.convert(result, Korisnik.class);

            if (resultSet.isEmpty())
                return null;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet.get(0);
    }

    public Korisnik findByJMBGorPassport(String jmbg, String pasos) {
        List<Korisnik> resultSet = new ArrayList<>();
        try {
            ResourceSet result = xPathService.executeXPath(
                    korisnikCollection,
                    "//Korisnik", "");
            resultSet = converterService.convert(result, Korisnik.class);

            resultSet = resultSet.stream().filter(korisnik -> korisnik.getPasos().equals(pasos) || korisnik.getJmbg().equals(jmbg)).collect(Collectors.toList());

            if (resultSet.isEmpty())
                return null;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet.get(0);
    }
}
