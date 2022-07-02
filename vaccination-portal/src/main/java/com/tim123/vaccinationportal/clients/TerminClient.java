package com.tim123.vaccinationportal.clients;

import com.tim123.vaccinationportal.model.dto.ZeljenaVakcinaDto;
import com.tim123.vaccinationportal.model.dto.ZeljeneVakcineDto;
import com.tim123.vaccinationportal.model.interesovanje.Interesovanje;
import com.tim123.vaccinationportal.model.termin.Termin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TerminClient {
    private final RestTemplate restTemplate = new RestTemplate();

    public Termin dodeliTermin(Interesovanje interesovanje, String email) {
        List<ZeljenaVakcinaDto> lista = interesovanje.getZeljenaVakcina()
                .getPfizerBioNTechOrSputnikVOrSinopharm().stream()
                .map(v -> new ZeljenaVakcinaDto(getName(Arrays.stream(Arrays.stream(v.toString().split("\\$"))
                                .collect(Collectors.toList()).get(2).split("@"))
                        .collect(Collectors.toList()).get(0))))
                .collect(Collectors.toList());
        ZeljeneVakcineDto zeljeneVakcineDto = new ZeljeneVakcineDto(lista, interesovanje.getZeljenaOpstinaVakcinacije(), email);
        return restTemplate.postForObject("http://localhost:8081/api/zdravstvena-ustanova", zeljeneVakcineDto, Termin.class);
    }

    private String getName(String vakcina) {
        switch (vakcina){
            case "PfizerBioNTech": return "PHIZER_BIONTECH";
            case "SputnikV": return "SPUTNIK_V";
            case "Sinopharm": return "SINOPHARM";
            case "AstraZeneca": return "ASTRA_ZENECA";
            default: return "MODERNA";
        }
    }
}
