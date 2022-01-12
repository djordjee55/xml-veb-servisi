//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.01.11 at 06:34:26 PM CET 
//


package com.tim123.vaccinationmain.model.potvrda;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.tim123.vaccinationmain.model.tipovi.TCRedniBrojVakcine;
import com.tim123.vaccinationmain.model.tipovi.TCVakcina;
import com.tim123.vaccinationmain.model.tipovi.TDatumVakcinacije;


/**
 * <p>Java class for TDoza complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TDoza"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Tip_vakcine" type="{http://www.xws.org/tipovi}TCVakcina"/&gt;
 *         &lt;element name="Datum_davanja" type="{http://www.xws.org/tipovi}TDatumVakcinacije"/&gt;
 *         &lt;element name="Broj_serije" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Redni_broj" type="{http://www.xws.org/tipovi}TCRedniBrojVakcine"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TDoza", propOrder = {
    "tipVakcine",
    "datumDavanja",
    "brojSerije",
    "redniBroj"
})
public class TDoza {

    @XmlElement(name = "Tip_vakcine", required = true)
    protected TCVakcina tipVakcine;
    @XmlElement(name = "Datum_davanja", required = true)
    protected TDatumVakcinacije datumDavanja;
    @XmlElement(name = "Broj_serije", required = true)
    protected String brojSerije;
    @XmlElement(name = "Redni_broj", required = true)
    protected TCRedniBrojVakcine redniBroj;

    /**
     * Gets the value of the tipVakcine property.
     * 
     * @return
     *     possible object is
     *     {@link TCVakcina }
     *     
     */
    public TCVakcina getTipVakcine() {
        return tipVakcine;
    }

    /**
     * Sets the value of the tipVakcine property.
     * 
     * @param value
     *     allowed object is
     *     {@link TCVakcina }
     *     
     */
    public void setTipVakcine(TCVakcina value) {
        this.tipVakcine = value;
    }

    /**
     * Gets the value of the datumDavanja property.
     * 
     * @return
     *     possible object is
     *     {@link TDatumVakcinacije }
     *     
     */
    public TDatumVakcinacije getDatumDavanja() {
        return datumDavanja;
    }

    /**
     * Sets the value of the datumDavanja property.
     * 
     * @param value
     *     allowed object is
     *     {@link TDatumVakcinacije }
     *     
     */
    public void setDatumDavanja(TDatumVakcinacije value) {
        this.datumDavanja = value;
    }

    /**
     * Gets the value of the brojSerije property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrojSerije() {
        return brojSerije;
    }

    /**
     * Sets the value of the brojSerije property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrojSerije(String value) {
        this.brojSerije = value;
    }

    /**
     * Gets the value of the redniBroj property.
     * 
     * @return
     *     possible object is
     *     {@link TCRedniBrojVakcine }
     *     
     */
    public TCRedniBrojVakcine getRedniBroj() {
        return redniBroj;
    }

    /**
     * Sets the value of the redniBroj property.
     * 
     * @param value
     *     allowed object is
     *     {@link TCRedniBrojVakcine }
     *     
     */
    public void setRedniBroj(TCRedniBrojVakcine value) {
        this.redniBroj = value;
    }

}
