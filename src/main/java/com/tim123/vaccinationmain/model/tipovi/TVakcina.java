//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.01.04 at 09:24:56 PM CET 
//


package com.tim123.vaccinationmain.model.tipovi;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TVakcina.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TVakcina"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Pfizer-BioNTech"/&gt;
 *     &lt;enumeration value="Sputnik V"/&gt;
 *     &lt;enumeration value="Sinopharm"/&gt;
 *     &lt;enumeration value="AstraZeneca"/&gt;
 *     &lt;enumeration value="Moderna"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "TVakcina")
@XmlEnum
public enum TVakcina {

    @XmlEnumValue("Pfizer-BioNTech")
    PFIZER_BIO_N_TECH("Pfizer-BioNTech"),
    @XmlEnumValue("Sputnik V")
    SPUTNIK_V("Sputnik V"),
    @XmlEnumValue("Sinopharm")
    SINOPHARM("Sinopharm"),
    @XmlEnumValue("AstraZeneca")
    ASTRA_ZENECA("AstraZeneca"),
    @XmlEnumValue("Moderna")
    MODERNA("Moderna");
    private final String value;

    TVakcina(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TVakcina fromValue(String v) {
        for (TVakcina c: TVakcina.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
