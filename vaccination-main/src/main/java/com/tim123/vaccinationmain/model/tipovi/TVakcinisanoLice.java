//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.01.11 at 06:34:26 PM CET 
//


package com.tim123.vaccinationmain.model.tipovi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TVakcinisanoLice complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TVakcinisanoLice"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.xws.org/tipovi}TLice"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Pol" type="{http://www.xws.org/tipovi}TPol"/&gt;
 *         &lt;element name="JMBG" type="{http://www.xws.org/tipovi}TCJMBG"/&gt;
 *         &lt;element name="Broj_pasosa" type="{http://www.xws.org/tipovi}TCBrojPasosa"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TVakcinisanoLice", propOrder = {
    "pol",
    "jmbg",
    "brojPasosa"
})
public class TVakcinisanoLice
    extends TLice
{

    @XmlElement(name = "Pol", required = true)
    @XmlSchemaType(name = "string")
    protected TPol pol;
    @XmlElement(name = "JMBG", required = true)
    protected TCJMBG jmbg;
    @XmlElement(name = "Broj_pasosa", required = true)
    protected TCBrojPasosa brojPasosa;

    /**
     * Gets the value of the pol property.
     * 
     * @return
     *     possible object is
     *     {@link TPol }
     *     
     */
    public TPol getPol() {
        return pol;
    }

    /**
     * Sets the value of the pol property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPol }
     *     
     */
    public void setPol(TPol value) {
        this.pol = value;
    }

    /**
     * Gets the value of the jmbg property.
     * 
     * @return
     *     possible object is
     *     {@link TCJMBG }
     *     
     */
    public TCJMBG getJMBG() {
        return jmbg;
    }

    /**
     * Sets the value of the jmbg property.
     * 
     * @param value
     *     allowed object is
     *     {@link TCJMBG }
     *     
     */
    public void setJMBG(TCJMBG value) {
        this.jmbg = value;
    }

    /**
     * Gets the value of the brojPasosa property.
     * 
     * @return
     *     possible object is
     *     {@link TCBrojPasosa }
     *     
     */
    public TCBrojPasosa getBrojPasosa() {
        return brojPasosa;
    }

    /**
     * Sets the value of the brojPasosa property.
     * 
     * @param value
     *     allowed object is
     *     {@link TCBrojPasosa }
     *     
     */
    public void setBrojPasosa(TCBrojPasosa value) {
        this.brojPasosa = value;
    }

}
