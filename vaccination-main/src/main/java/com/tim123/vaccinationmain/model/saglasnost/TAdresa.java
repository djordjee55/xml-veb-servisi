//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.01.11 at 06:34:26 PM CET 
//


package com.tim123.vaccinationmain.model.saglasnost;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TAdresa complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TAdresa"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Opstina" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Naselje" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Ulica" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Broj"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;pattern value="[0-9]+(/[0-9]+)?"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TAdresa", propOrder = {
    "opstina",
    "naselje",
    "ulica",
    "broj"
})
public class TAdresa {

    @XmlElement(name = "Opstina", required = true)
    protected String opstina;
    @XmlElement(name = "Naselje", required = true)
    protected String naselje;
    @XmlElement(name = "Ulica", required = true)
    protected String ulica;
    @XmlElement(name = "Broj", required = true)
    protected String broj;

    /**
     * Gets the value of the opstina property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpstina() {
        return opstina;
    }

    /**
     * Sets the value of the opstina property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpstina(String value) {
        this.opstina = value;
    }

    /**
     * Gets the value of the naselje property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNaselje() {
        return naselje;
    }

    /**
     * Sets the value of the naselje property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNaselje(String value) {
        this.naselje = value;
    }

    /**
     * Gets the value of the ulica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUlica() {
        return ulica;
    }

    /**
     * Sets the value of the ulica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUlica(String value) {
        this.ulica = value;
    }

    /**
     * Gets the value of the broj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBroj() {
        return broj;
    }

    /**
     * Sets the value of the broj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBroj(String value) {
        this.broj = value;
    }

}
