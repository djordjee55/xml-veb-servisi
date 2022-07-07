package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.model.termin.Termin;
import com.tim123.vaccinationmain.model.zdravstvenaUstanova.ZdravstvenaUstanova;
import com.tim123.vaccinationmain.repository.ZdravstvenaUstanovaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TerminService {

    private final ZdravstvenaUstanovaRepository zdravstvenaUstanovaRepository;

    public Termin napraviTermin(ZdravstvenaUstanova zdravstvenaUstanova, String pacijent, String vakcina) throws DatatypeConfigurationException {
        LocalDateTime novi;
        if(zdravstvenaUstanova.getTermini() == null) zdravstvenaUstanova.setTermini(new ArrayList<>());
        if (zdravstvenaUstanova.getTermini().size() == 0) {
            novi = LocalDateTime.now().plusDays(1);
            novi = novi.withHour(LocalTime.parse(zdravstvenaUstanova.getPocetakRadnogVremena()).getHour());
            novi = novi.withMinute(LocalTime.parse(zdravstvenaUstanova.getPocetakRadnogVremena()).getMinute());
        } else {
            Termin poslednji = zdravstvenaUstanova.getTermini().get(zdravstvenaUstanova.getTermini().size() - 1);
            novi = poslednji.getDatumVreme().toGregorianCalendar().toZonedDateTime().toLocalDateTime().plusMinutes(15);
            if (novi.toLocalTime().isAfter(LocalTime.parse(zdravstvenaUstanova.getKrajRadnogVremena()))) {
                novi = poslednji.getDatumVreme().toGregorianCalendar().toZonedDateTime().toLocalDateTime().plusDays(1);
                novi = novi.withHour(LocalTime.parse(zdravstvenaUstanova.getPocetakRadnogVremena()).getHour());
                novi = novi.withMinute(LocalTime.parse(zdravstvenaUstanova.getPocetakRadnogVremena()).getMinute());
            }
        }

        Termin noviTermin = new Termin(DatatypeFactory.newInstance().newXMLGregorianCalendar(novi.toString()), pacijent, vakcina);
        zdravstvenaUstanova.getTermini().add(noviTermin);
        try {
            zdravstvenaUstanovaRepository.save(zdravstvenaUstanova);
            return noviTermin;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Termin napraviTerminZaREvakcinaciju(ZdravstvenaUstanova zU, String eMail, String nazivVakcine, int brojDanaDoSledece, XMLGregorianCalendar datumZadnjegDavanja) throws DatatypeConfigurationException {
        LocalDateTime novi = datumZadnjegDavanja.toGregorianCalendar().toZonedDateTime().toLocalDateTime();
        novi = novi.plusDays(brojDanaDoSledece);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String formattedDate = novi.format(formatter);
        XMLGregorianCalendar xmlgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(formattedDate);

        Termin noviTermin = new Termin(xmlgc, eMail, nazivVakcine);
        zU.getTermini().add(noviTermin);
        try {
            zdravstvenaUstanovaRepository.save(zU);
            return noviTermin;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
