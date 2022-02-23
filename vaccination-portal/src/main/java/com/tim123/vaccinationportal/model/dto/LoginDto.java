package com.tim123.vaccinationportal.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Login")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "email",
        "lozinka"
})
public class LoginDto {
    @XmlElement(name = "Email", required = true)
    private String email;
    @XmlElement(name = "Lozinka", required = true)
    private String lozinka;
}
