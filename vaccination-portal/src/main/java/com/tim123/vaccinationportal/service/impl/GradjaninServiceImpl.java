package com.tim123.vaccinationportal.service.impl;

import com.tim123.vaccinationportal.exception.InvalidDataException;
import com.tim123.vaccinationportal.model.Gradjanin;
import com.tim123.vaccinationportal.model.dto.GradjaninDto;
import com.tim123.vaccinationportal.repository.CRUDRepository;
import com.tim123.vaccinationportal.repository.GradjaninRepository;
import com.tim123.vaccinationportal.service.GradjaninService;
import com.tim123.vaccinationportal.service.RDFService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GradjaninServiceImpl extends CRUDServiceImpl<Gradjanin> implements GradjaninService {

    private final GradjaninRepository gradjaninRepository;
    private final RDFService rdfService;

    @Override
    protected CRUDRepository<Gradjanin> getRepository() {
        return gradjaninRepository;
    }

    @Override
    public boolean registracija(GradjaninDto gradjaninDto) {

        validirajEmail(gradjaninDto.getEmail());
        validirajJMBG(gradjaninDto.getJMBG());
        //sacuvaj novi entitet u bazi
        return true;
    }

    private void validirajEmail(String email) {

        //validiraj da li je jedinstven
        EmailValidator validator = EmailValidator.getInstance();
        if (!validator.isValid(email)) {
            throw new InvalidDataException("Email adresa nije validna!");
        }
    }

    private void validirajJMBG(String jmbg) {

        //validiraj da li je jedinstven

        if (jmbg.length() != 13) {
            throw new InvalidDataException("JMBG nema validan broj karaktera!");
        }

        List<Integer> digits = new ArrayList<>();
        for (char c : jmbg.toCharArray()) {
            digits.add((int) c);
        }

        int checksumNumber = digits.get(12);
        int checksum = 11 - ((7 * (digits.get(0) + digits.get(6) + 6 * (digits.get(1) + digits.get(7)) + 5 * (digits.get(2) + digits.get(8)) + 4 * (digits.get(3) + digits.get(9)) + 3 * (digits.get(4) + digits.get(10)) + 2 * (digits.get(5) + digits.get(11))) % 11));
        if (checksum <= 9) {
            if (checksumNumber != checksum) {
                throw new InvalidDataException("JMBG nije validan!");
            }
        } else {
            if (checksumNumber != 0) {
                throw new InvalidDataException("JMBG nije validan!");
            }
        }
    }
}
