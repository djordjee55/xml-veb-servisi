package com.tim123.vaccinationmain.repository;

import com.tim123.vaccinationmain.model.Korisnik;
import com.tim123.vaccinationmain.service.ConverterService;
import com.tim123.vaccinationmain.service.MarshallUnmarshallService;
import com.tim123.vaccinationmain.service.XPathService;
import com.tim123.vaccinationmain.util.RepositoryUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        repositoryUtil.save("db/imunizacioni_sistem/sluzbenik", documentId, marshallUnmarshallService.marshall(entity, Korisnik.class));
        return entity;
    }

    @Override
    public Korisnik findById(String id) throws Exception {
        return null;
    }

    public Korisnik findByEmail(String email) {
        List<Korisnik> resultSet = new ArrayList<>();
        try {
            ResourceSet result = xPathService.executeXPath("db/imunizacioni_sistem/sluzbenik", String.format("//Korisnik[Email='%s']", email), "");
            resultSet = converterService.convert(result, Korisnik.class);

            if (resultSet.isEmpty())
                return null;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet.get(0);
    }
}