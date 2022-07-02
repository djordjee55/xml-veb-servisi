package com.tim123.vaccinationmain.model.termin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.XMLGregorianCalendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name="Termin")
@XmlAccessorType(XmlAccessType.FIELD)
public class Termin {
    @XmlAttribute(name = "datum_vreme")
    private XMLGregorianCalendar datumVreme;

    @XmlAttribute
    private String pacijent;

    @XmlAttribute
    private String vakcina;

}
