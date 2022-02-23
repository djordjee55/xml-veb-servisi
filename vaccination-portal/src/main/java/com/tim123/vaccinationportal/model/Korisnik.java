package com.tim123.vaccinationportal.model;

import com.tim123.vaccinationportal.model.enumi.Drzavljanstvo;
import com.tim123.vaccinationportal.model.enumi.Uloga;
import com.tim123.vaccinationportal.model.tipovi.TPol;
import lombok.*;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "Korisnik")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "id",
        "email",
        "lozinka",
        "ime",
        "prezime",
        "drzavljanstvo",
        "pol",
        "datumRodjenja",
        "jmbg",
        "pasos",
        "uloga"
})
public class Korisnik {
    @XmlElement(name = "Id")
    private String id;

    @XmlElement(name = "Email", required = true)
    private String email;

    @XmlElement(name = "Lozinka", required = true)
    private String lozinka;

    @XmlElement(name = "Ime", required = true)
    private String ime;

    @XmlElement(name = "Prezime", required = true)
    private String prezime;

    @XmlElement(name = "Drzavljanstvo", required = true)
    private Drzavljanstvo drzavljanstvo;

    @XmlElement(name = "Pol", required = true)
    private TPol pol;

    @XmlElement(name = "Datum_rodjenja", required = true)
    @XmlSchemaType(name = "date")
    private XMLGregorianCalendar datumRodjenja;

    @XmlElement(name = "JMBG", nillable = true)
    private String jmbg;

    @XmlElement(name = "Pasos", nillable = true)
    private String pasos;

    @XmlElement(name = "Uloga")
    private Uloga uloga;

}
