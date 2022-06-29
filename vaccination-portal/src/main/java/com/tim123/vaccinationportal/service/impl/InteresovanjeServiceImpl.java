package com.tim123.vaccinationportal.service.impl;

import com.tim123.vaccinationportal.exception.InterestAlreadyExists;
import com.tim123.vaccinationportal.model.Korisnik;
import com.tim123.vaccinationportal.model.interesovanje.Interesovanje;
import com.tim123.vaccinationportal.model.tipovi.TCJMBG;
import com.tim123.vaccinationportal.model.tipovi.TZainteresovanoLice;
import com.tim123.vaccinationportal.repository.CRUDRepository;
import com.tim123.vaccinationportal.repository.InteresovanjeRepository;
import com.tim123.vaccinationportal.service.InteresovanjeService;
import com.tim123.vaccinationportal.service.KorisnikService;
import com.tim123.vaccinationportal.service.RDFService;
import com.tim123.vaccinationportal.service.TerminService;
import com.tim123.vaccinationportal.util.HTMLTransformer;
import com.tim123.vaccinationportal.util.PDFTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayInputStream;

import static com.tim123.vaccinationportal.util.Constants.interesovanjePath;

@Service
@RequiredArgsConstructor
public class InteresovanjeServiceImpl extends CRUDServiceImpl<Interesovanje> implements InteresovanjeService {

    private final InteresovanjeRepository interesovanjeRepository;
    private final TerminService terminService;
    private final RDFService rdfService;
    private final KorisnikService korisnikService;
    private final PDFTransformer pdfTransformer;
    private final HTMLTransformer htmlTransformer;
    private final EmailService emailService;

    @Override
    protected CRUDRepository<Interesovanje> getRepository() {
        return interesovanjeRepository;
    }

    @Override
    public Interesovanje dodajInteresovanje(Interesovanje interesovanje, String email) {
        postaviInfoOKorisniku(interesovanje, email);
        if (interesovanjeRepository.findForUser(korisnikService.findByEmail(email).getJmbg()) != null)
            throw new InterestAlreadyExists(String.format("Korisnik %s je vec podneo interesovanje!", email));

        try {
            var i = this.save(interesovanje);
            interesovanjePrimljenoEmail(i, email);
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

    @Override
    public ByteArrayInputStream generisiHTML(String id) {
        Interesovanje interesovanje = dobaviInteresovanje(id);
        return htmlTransformer.generateHTML(interesovanje.toString(), Interesovanje.class);
    }

    @Override
    public ByteArrayInputStream generisiPDF(String id) {
        Interesovanje interesovanje = dobaviInteresovanje(id);
        return pdfTransformer.generatePDF(interesovanje.toString(), Interesovanje.class);
    }

    private void postaviInfoOKorisniku(Interesovanje interesovanje, String email) {
        Korisnik korisnik = korisnikService.findByEmail(email);
        TCJMBG tcjmbg = new TCJMBG();
        tcjmbg.setValue(korisnik.getJmbg());
        tcjmbg.setRel("pred:identifikatorKorisnika");
        tcjmbg.setHref(String.format("http://www.xws.org/korisnici#%s", korisnik.getId()));

        TZainteresovanoLice tZainteresovanoLice = new TZainteresovanoLice();
        tZainteresovanoLice.setJMBG(tcjmbg);
        tZainteresovanoLice.setIme(korisnik.getIme());
        tZainteresovanoLice.setPrezime(korisnik.getPrezime());
        tZainteresovanoLice.setDatumRodjenja(korisnik.getDatumRodjenja());
        tZainteresovanoLice.setKontakt(interesovanje.getPrimalac().getKontakt());
        interesovanje.setPrimalac(tZainteresovanoLice);
    }

    private void interesovanjePrimljenoEmail(Interesovanje interesovanje, String email) {
        emailService.sendEmail("", email, "Interesovanje primljeno", "Ovo se mora srediti");
    }
}
