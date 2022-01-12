//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.01.04 at 09:24:56 PM CET 
//


package com.tim123.vaccinationmain.model.potvrda;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import com.tim123.vaccinationmain.model.tipovi.TPrimalacPotvrde;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Qr_kod" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Primalac" type="{http://www.xws.org/tipovi}TPrimalacPotvrde"/&gt;
 *         &lt;element name="Doze" type="{http://www.xws.org/potvrda}TVakcinacija"/&gt;
 *         &lt;element name="Zdravstvena_ustanova" type="{http://www.xws.org/tipovi}TZdravstvenaUstanova"/&gt;
 *         &lt;element name="Datum_izdavanja" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="sifra" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "qrKod",
    "primalac",
    "doze",
    "zdravstvenaUstanova",
    "datumIzdavanja"
})
@XmlRootElement(name = "Potvrda")
public class Potvrda {

    @XmlElement(name = "Qr_kod", required = true)
    protected String qrKod;
    @XmlElement(name = "Primalac", required = true)
    protected TPrimalacPotvrde primalac;
    @XmlElement(name = "Doze", required = true)
    protected TVakcinacija doze;
    @XmlElement(name = "Zdravstvena_ustanova", required = true)
    protected String zdravstvenaUstanova;
    @XmlElement(name = "Datum_izdavanja", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumIzdavanja;
    @XmlAttribute(name = "sifra")
    protected String sifra;

    /**
     * Gets the value of the qrKod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQrKod() {
        return qrKod;
    }

    /**
     * Sets the value of the qrKod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQrKod(String value) {
        this.qrKod = value;
    }

    /**
     * Gets the value of the primalac property.
     * 
     * @return
     *     possible object is
     *     {@link TPrimalacPotvrde }
     *     
     */
    public TPrimalacPotvrde getPrimalac() {
        return primalac;
    }

    /**
     * Sets the value of the primalac property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPrimalacPotvrde }
     *     
     */
    public void setPrimalac(TPrimalacPotvrde value) {
        this.primalac = value;
    }

    /**
     * Gets the value of the doze property.
     * 
     * @return
     *     possible object is
     *     {@link TVakcinacija }
     *     
     */
    public TVakcinacija getDoze() {
        return doze;
    }

    /**
     * Sets the value of the doze property.
     * 
     * @param value
     *     allowed object is
     *     {@link TVakcinacija }
     *     
     */
    public void setDoze(TVakcinacija value) {
        this.doze = value;
    }

    /**
     * Gets the value of the zdravstvenaUstanova property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZdravstvenaUstanova() {
        return zdravstvenaUstanova;
    }

    /**
     * Sets the value of the zdravstvenaUstanova property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZdravstvenaUstanova(String value) {
        this.zdravstvenaUstanova = value;
    }

    /**
     * Gets the value of the datumIzdavanja property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumIzdavanja() {
        return datumIzdavanja;
    }

    /**
     * Sets the value of the datumIzdavanja property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumIzdavanja(XMLGregorianCalendar value) {
        this.datumIzdavanja = value;
    }

    /**
     * Gets the value of the sifra property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSifra() {
        return sifra;
    }

    /**
     * Sets the value of the sifra property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSifra(String value) {
        this.sifra = value;
    }

}