package com.tim123.vaccinationmain.dto.dokumenta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.XMLGregorianCalendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "Dokument")
@XmlAccessorType(XmlAccessType.FIELD)
public class Dokument {
    @XmlElement(name="TipDokumenta")
    private TipDokumenta tipDokumenta;

    @XmlElement(name="Kreiran")
    private XMLGregorianCalendar calendar;

    @XmlElement(name="Id")
    private String id;
}
