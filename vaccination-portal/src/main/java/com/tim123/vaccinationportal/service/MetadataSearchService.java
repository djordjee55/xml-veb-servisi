package com.tim123.vaccinationportal.service;

import com.tim123.vaccinationportal.model.dto.dokumenta.ListaDokumenata;

public interface MetadataSearchService {
    ListaDokumenata searchInteresovanje(String filter);
}
