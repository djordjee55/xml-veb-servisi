package com.tim123.vaccinationportal.repository;

import com.tim123.vaccinationportal.model.Korisnik;
import com.tim123.vaccinationportal.model.potvrda.Potvrda;
import com.tim123.vaccinationportal.model.zahtev.Zahtev;
import com.tim123.vaccinationportal.service.ConverterService;
import com.tim123.vaccinationportal.service.MarshallUnmarshallService;
import com.tim123.vaccinationportal.service.XPathService;
import com.tim123.vaccinationportal.util.QRUtil;
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
public class PotvrdaRepository implements CRUDRepository<Potvrda> {

    private final RepositoryUtil repositoryUtil;
    private final MarshallUnmarshallService<Potvrda> marshallUnmarshallService;
    private final XPathService xPathService;
    private final KorisnikRepository korisnikRepository;
    private final ConverterService<Potvrda> converterService;

    @Override
    public Potvrda save(Potvrda entity) throws Exception {
        String documentId = entity.getSifra();
        if (documentId == null || documentId.isBlank()) {
            UUID uuid = UUID.randomUUID();
            documentId = uuid.toString();
            entity.setSifra(documentId);
            entity.setAbout(String.format("%s#%s", potvrdaBase, documentId));
            entity.setQrKod(QRUtil.getQRImage("http://localhost:8082/api/potvrda/html/" + uuid));
        }
        repositoryUtil.save(potvrdaCollection, documentId, marshallUnmarshallService.marshall(entity, Potvrda.class));
        return entity;
    }

    @Override
    public Potvrda findById(String id) throws Exception {
        var result = repositoryUtil.findByDocumentId(potvrdaCollection, id);
        return marshallUnmarshallService.unmarshall(result, Potvrda.class);
    }

    public List<Potvrda> getForUserEmail(String email) {
        List<Potvrda> resultSet = new ArrayList<>();
        Korisnik korisnik = korisnikRepository.findByEmail(email);
        try {
            ResourceSet result = xPathService.executeXPath(potvrdaCollection, "//*[local-name()='Potvrda']", "");
            resultSet = converterService.convert(result, Potvrda.class);
            resultSet = resultSet.stream().filter(res -> res.getSifra() != null).collect(Collectors.toList());
            resultSet = resultSet.stream().filter(res -> res.getPrimalac().getJMBG().getValue().equals(korisnik.getJmbg())).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
