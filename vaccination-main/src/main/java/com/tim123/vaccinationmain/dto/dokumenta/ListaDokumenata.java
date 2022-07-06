package com.tim123.vaccinationmain.dto.dokumenta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "Dokumenta")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaDokumenata {
    @XmlElement(name="Dokument")
    private List<Dokument> dokumenta;
}
