package com.tim123.vaccinationportal.model.dto;

import com.tim123.vaccinationportal.model.saglasnost.TEvidencija;
import com.tim123.vaccinationportal.model.saglasnost.TVakcina;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlRootElement(name = "DopuniEvidencijuDto")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "ZdravstvenaUstanova",
        "VakcinacijskiPunkt",
        "NazivVakcine",
        "Proizvodjac",
        "Serija",
        "NacinDavanja",
        "LEkstremitet",
        "REkstremitet",
        "NezeljenaReakcija",
        "DatumKontraindikacije",
        "OdlukaKomisije",
        "Kontraindikacije"

})
public class DopuniEvidencijuDto {

    @XmlElement(name = "ZdravstvenaUstanova", required = true)
    public String ZdravstvenaUstanova;
    @XmlElement(name = "VakcinacijskiPunkt", required = true)
    public String VakcinacijskiPunkt;
    @XmlElement(name = "Kontraindikacije")
    public String Kontraindikacije;
    @XmlElement(name = "OdlukaKomisije")
    public String OdlukaKomisije;
    @XmlElement(name = "DatumKontraindikacije")
    @XmlSchemaType(name = "date")
    public XMLGregorianCalendar DatumKontraindikacije;

    //deo vakcina...

    @XmlElement(name = "NazivVakcine", required = true)
    public String NazivVakcine;
    @XmlElement(name = "Proizvodjac", required = true)
    public String Proizvodjac;
    @XmlElement(name = "NacinDavanja", required = true)
    public String NacinDavanja;
    @XmlElement(name = "LEkstremitet", required = true)
    public String LEkstremitet;
    @XmlElement(name = "REkstremitet", required = true)
    public String REkstremitet;
    @XmlElement(name = "Serija", required = true)
    public String Serija;
    @XmlElement(name = "NezeljenaReakcija", required = true)
    public String NezeljenaReakcija;
}
