package com.tim123.vaccinationmain.service;

import com.tim123.vaccinationmain.dto.dokumenta.Metapodatak;
import com.tim123.vaccinationmain.dto.dokumenta.TipDokumenta;
import org.springframework.core.io.InputStreamResource;

import java.io.ByteArrayInputStream;

public interface MetadataExtractorService {
    ByteArrayInputStream extractMetadata(TipDokumenta tip, Metapodatak vrsta, String id);
}
