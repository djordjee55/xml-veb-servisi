package com.tim123.vaccinationportal.service;

import com.tim123.vaccinationportal.model.Gradjanin;
import com.tim123.vaccinationportal.model.dto.GradjaninDto;

public interface GradjaninService extends CRUDService<Gradjanin> {

    boolean registracija(GradjaninDto gradjaninDto);
}