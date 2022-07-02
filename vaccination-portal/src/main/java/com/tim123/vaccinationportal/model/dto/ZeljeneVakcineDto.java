package com.tim123.vaccinationportal.model.dto;

import com.tim123.vaccinationportal.model.interesovanje.Interesovanje;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "zeljeneVakcine")
@XmlAccessorType(XmlAccessType.FIELD)
public class ZeljeneVakcineDto {
    @XmlElement(name = "ZeljenaVakcina")
    private List<ZeljenaVakcinaDto> zeljeneVakcine;

    @XmlAttribute
    private String opstina;

    @XmlAttribute
    private String pacijent;
}
