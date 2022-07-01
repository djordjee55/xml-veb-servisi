package com.tim123.vaccinationmain.dto;

import com.tim123.vaccinationmain.model.vakcina.TipVakcine;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@XmlRootElement(name = "azuriranje")
public class AzurirajVakcinuDTO {
    private TipVakcine vakcina;
    private int kolicina;
    private String opstina;
    private String id;
}
