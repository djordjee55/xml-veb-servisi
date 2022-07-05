package com.tim123.vaccinationportal.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "OdbijZahtev")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "razlog",
})
public class OdbijZahtevDto {
    @XmlElement(name = "Razlog", required = true)
    private String razlog;
}