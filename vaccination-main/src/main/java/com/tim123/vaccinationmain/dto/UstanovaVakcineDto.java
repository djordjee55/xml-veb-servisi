package com.tim123.vaccinationmain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "UstanovaVakcine")
@XmlAccessorType(XmlAccessType.FIELD)
public class UstanovaVakcineDto {

    @XmlElement(name="id")
    private String id;
    @XmlElement(name = "Ustanova")
    private String ustanova;
    @XmlElement(name = "Opstina")
    private String opstina;
    @XmlElement(name="Vakcine")
    private List<VakcinaKolicinaDto> vakcine;

}
