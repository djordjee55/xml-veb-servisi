package com.tim123.vaccinationmain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "Ustanove")
@XmlAccessorType(XmlAccessType.FIELD)
public class UstanoveDto {

    @XmlElement(name="UstanovaVakcineDto")
    private List<UstanovaVakcineDto> ustanovaVakcineDtoList;
}
