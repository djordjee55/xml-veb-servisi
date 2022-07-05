package com.tim123.vaccinationportal.repository;

import com.tim123.vaccinationportal.model.Korisnik;
import com.tim123.vaccinationportal.model.interesovanje.Interesovanje;
import com.tim123.vaccinationportal.model.saglasnost.Saglasnost;
import com.tim123.vaccinationportal.model.zahtev.Zahtev;
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

import static com.tim123.vaccinationportal.util.Constants.*;

@Repository
@RequiredArgsConstructor
public class ZahtevRepository implements CRUDRepository<Zahtev> {

    private final RepositoryUtil repositoryUtil;
    private final XPathService xPathService;
    private final ConverterService<Zahtev> converterService;
    private final MarshallUnmarshallService<Zahtev> marshallUnmarshallService;

    @Override
    public Zahtev save(Zahtev entity) throws Exception {
        String documentId = entity.getId();
        if (documentId == null || documentId.isBlank()) {
            UUID uuid = UUID.randomUUID();
            documentId = uuid.toString();
            entity.setId(documentId);
            entity.setAbout(String.format("%s#%s", zahtevBase, documentId));
        }
        repositoryUtil.save(zahtevCollection, documentId, marshallUnmarshallService.marshall(entity, Zahtev.class));
        return entity;
    }

    @Override
    public Zahtev findById(String id) throws Exception {
        var result = repositoryUtil.findByDocumentId(zahtevCollection, id);
        return marshallUnmarshallService.unmarshall(result, Zahtev.class);
    }

    public List<Zahtev> findAll() {
        List<Zahtev> resultSet = new ArrayList<>();
        try {
            ResourceSet result = xPathService.executeXPath(zahtevCollection, "//*[local-name()='Zahtev']", "");
            resultSet = converterService.convert(result, Zahtev.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public List<Zahtev> getForUserEmail(String email) {
        List<Zahtev> resultSet = new ArrayList<>();
        Korisnik korisnik = korisnikRepository.findByEmail(email);
        try {
            ResourceSet result = xPathService.executeXPath(zahtevCollection, "//*[local-name()='Zahtev']", "");
            resultSet = converterService.convert(result, Zahtev.class);
            resultSet = resultSet.stream().filter(res -> res.getId() != null).collect(Collectors.toList());
            resultSet = resultSet.stream().filter(res -> res.getPodnosilac().getJMBG().getValue().equals(korisnik.getJmbg()) || res.getPodnosilac().getBrojPasosa().getValue().equals(korisnik.getPasos())).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
