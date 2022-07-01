package com.tim123.vaccinationmain.model.cekanje;

import com.tim123.vaccinationmain.model.vakcina.ZeljenaVakcina;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class Cekanje {
    @XmlElement(name="ZeljeneVakcine")
    private List<ZeljenaVakcina> zeljeneVakcine;

    @XmlAttribute
    private String opstina;

    @XmlAttribute
    private String pacijent;

    @XmlAttribute
    private String id;

}
