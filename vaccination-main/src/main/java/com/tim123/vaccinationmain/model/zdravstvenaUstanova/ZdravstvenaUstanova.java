package com.tim123.vaccinationmain.model.zdravstvenaUstanova;

import com.tim123.vaccinationmain.model.termin.Termin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "ZdravstvenaUstanova")
@XmlAccessorType(XmlAccessType.FIELD)
public class ZdravstvenaUstanova {

    @XmlElement(name = "Termini")
    private List<Termin> termini;

    @XmlAttribute
    private String naziv;

    @XmlAttribute
    private String opstina;

    @XmlAttribute
    private String id;

    @XmlAttribute(name="od")
    private String pocetakRadnogVremena;

    @XmlAttribute(name="do")
    private String krajRadnogVremena;
}
