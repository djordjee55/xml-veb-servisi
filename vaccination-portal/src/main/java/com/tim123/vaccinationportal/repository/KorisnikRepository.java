package com.tim123.vaccinationportal.repository;

import com.tim123.vaccinationportal.model.Korisnik;
import com.tim123.vaccinationportal.service.ConverterService;
import com.tim123.vaccinationportal.service.MarshallUnmarshallService;
import com.tim123.vaccinationportal.service.XPathService;
import lombok.RequiredArgsConstructor;
import org.exist.xmldb.EXistResource;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.tim123.vaccinationportal.util.Constants.korisnikCollection;

@Repository
@RequiredArgsConstructor
public class KorisnikRepository implements CRUDRepository<Korisnik>{

    private final RepositoryUtil repositoryUtil;
    private final MarshallUnmarshallService<Korisnik> marshallUnmarshallService;
    private final XPathService xPathService;
    private final ConverterService<Korisnik> converterService;

    @Override
    public Korisnik save(Korisnik entity) throws Exception {
        UUID uuid = UUID.randomUUID();
        String documentId = uuid.toString();
        entity.setId(documentId);
//        entity.setAbout(String.format("%s#%s", interesovanjeBase, documentId));
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

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet.get(0);
    }
}
