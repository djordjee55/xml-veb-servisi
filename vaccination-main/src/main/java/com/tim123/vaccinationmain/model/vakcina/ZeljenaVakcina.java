package com.tim123.vaccinationmain.model.vakcina;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class ZeljenaVakcina {
    @XmlValue
    private String value;
}
