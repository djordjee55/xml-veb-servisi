package com.tim123.vaccinationportal.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "ZeljenaVakcina")
public class ZeljenaVakcinaDto {
    @XmlValue
    private String value;
}
