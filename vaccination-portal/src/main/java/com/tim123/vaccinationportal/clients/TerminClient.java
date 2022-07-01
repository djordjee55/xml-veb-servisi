package com.tim123.vaccinationportal.clients;

import com.tim123.vaccinationportal.model.dto.ZeljenaVakcinaDto;
import com.tim123.vaccinationportal.model.dto.ZeljeneVakcineDto;
import com.tim123.vaccinationportal.model.interesovanje.Interesovanje;
import com.tim123.vaccinationportal.model.termin.Termin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TerminClient {
    private final RestTemplate restTemplate;

    public Termin dodeliTermin(Interesovanje interesovanje, String email) {
        List<ZeljenaVakcinaDto> lista = interesovanje.getZeljenaVakcina().getPfizerBioNTechOrSputnikVOrSinopharm().stream().map(v -> new ZeljenaVakcinaDto(v.toString())).collect(Collectors.toList());
        ZeljeneVakcineDto zeljeneVakcineDto = new ZeljeneVakcineDto(lista, interesovanje.getZeljenaOpstinaVakcinacije(), email);
        return restTemplate.postForObject("http://localhost:8081/api/zdravstvena-ustanova", zeljeneVakcineDto, Termin.class);
    }
}
