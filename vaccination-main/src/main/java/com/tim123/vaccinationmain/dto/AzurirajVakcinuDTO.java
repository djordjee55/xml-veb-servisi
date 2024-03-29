package com.tim123.vaccinationmain.dto;

import com.tim123.vaccinationmain.model.vakcina.TipVakcine;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@XmlRootElement(name = "azuriranje")
@XmlAccessorType(XmlAccessType.FIELD)
public class AzurirajVakcinuDTO {
    @XmlElement(name="vakcina")
    private TipVakcine vakcina;

    @XmlElement(name="kolicina")
    private int kolicina;

    @XmlElement(name="ustanova")
    private String ustanova;

    @XmlElement(name="id")
    private String id;
}
