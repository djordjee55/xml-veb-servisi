package com.tim123.vaccinationportal.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Token {
    @XmlElement(name = "Token")
    private String token;
}
