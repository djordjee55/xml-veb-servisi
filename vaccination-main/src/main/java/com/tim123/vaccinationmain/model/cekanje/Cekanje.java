package com.tim123.vaccinationmain.model.cekanje;

import com.tim123.vaccinationmain.model.vakcina.ZeljenaVakcina;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Cekanje {
    @XmlElement(name="ZeljeneVakcine")
    private List<ZeljenaVakcina> zeljeneVakcine;

    @XmlAttribute
    private String opstina;

    @XmlAttribute
    private String pacijent;

    @XmlAttribute
    private String id;

    @XmlAttribute
    private boolean obradjen;

}
