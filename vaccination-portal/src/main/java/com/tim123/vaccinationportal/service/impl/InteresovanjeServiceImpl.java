package com.tim123.vaccinationportal.service.impl;

import com.tim123.vaccinationportal.model.interesovanje.Interesovanje;
import com.tim123.vaccinationportal.repository.CRUDRepository;
import com.tim123.vaccinationportal.repository.InteresovanjeRepository;
import com.tim123.vaccinationportal.service.InteresovanjeService;
import com.tim123.vaccinationportal.service.RDFService;
import com.tim123.vaccinationportal.service.TerminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import static com.tim123.vaccinationportal.util.Constants.interesovanjePath;

@Service
@RequiredArgsConstructor
public class InteresovanjeServiceImpl extends CRUDServiceImpl<Interesovanje> implements InteresovanjeService {

    private final InteresovanjeRepository interesovanjeRepository;
    private final TerminService terminService;
    private final RDFService rdfService;

    @Override
    protected CRUDRepository<Interesovanje> getRepository() {
        return interesovanjeRepository;
    }

    @Override
    public Interesovanje dodajInteresovanje(Interesovanje interesovanje) {
        try {
            var i = this.save(interesovanje);
            //terminService.kreirajNoviTermin("DOM_ZDRAVLJA_???", LocalDate.now().plusWeeks(2));//refaktorisati
            //posalji mejl o terminu
            rdfService.extractMetadata(interesovanje, Interesovanje.class, interesovanjePath);
            return i;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lose interesovanje");
        }
    }

    @Override
    public Interesovanje dobaviInteresovanje(String id) {
        var interesovanje = this.findById(id);
        if (interesovanje.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Interesovanje nije pronadjeno");
        }
        return interesovanje.get();
    }
}
