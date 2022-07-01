package com.tim123.vaccinationmain.model.vakcina;

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
public class ZeljeneVakcine {
    @XmlElement(name = "ZeljenaVakcina")
    private List<ZeljenaVakcina> zeljeneVakcine;

    @XmlAttribute
    private String opstina;

    @XmlAttribute
    private String pacijent;
}
