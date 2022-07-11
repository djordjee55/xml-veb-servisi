package com.tim123.vaccinationmain.service;

import com.tim123.vaccinationmain.dto.dokumenta.ListaDokumenata;
import com.tim123.vaccinationmain.dto.dokumenta.TipDokumenta;

public interface MetadataSearchService {
     ListaDokumenata search(TipDokumenta type, String query);
}
