package com.tim123.vaccinationmain.model.vakcina;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "Vakcina")
@XmlAccessorType(XmlAccessType.FIELD)
public class Vakcina {
    private TipVakcine naziv;
    private int kolicina;

    @XmlAttribute
    private String ustanova;

    @XmlAttribute
    private String id;
}
