package com.tim123.vaccinationmain.dto;

import com.tim123.vaccinationmain.model.vakcina.TipVakcine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "Vakcina")
@XmlAccessorType(XmlAccessType.FIELD)
public class VakcinaKolicinaDto {
    @XmlElement(name="id")
    private String id;
    @XmlElement(name = "TipVakcine")
    private TipVakcine tipVakcine;
    @XmlElement(name = "Kolicina")
    private Integer kolicina;
}
