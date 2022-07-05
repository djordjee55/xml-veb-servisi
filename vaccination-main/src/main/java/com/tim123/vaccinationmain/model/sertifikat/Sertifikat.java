//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.01.11 at 06:34:26 PM CET 
//


package com.tim123.vaccinationmain.model.sertifikat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;
import com.tim123.vaccinationmain.model.tipovi.TVakcinisanoLice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


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
 *         &lt;element name="Datum_vreme"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;dateTime"&gt;
 *                 &lt;attribute name="property" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" fixed="pred:datumIzdavanja" /&gt;
 *                 &lt;attribute name="datatype" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" fixed="xs:dateTime" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Qr_kod" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Primalac" type="{http://www.xws.org/tipovi}TVakcinisanoLice"/&gt;
 *         &lt;element name="Vakcinacija" type="{http://www.xws.org/sertifikat}TVakcinacija"/&gt;
 *         &lt;element name="Testovi" type="{http://www.xws.org/sertifikat}TTestovi"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="broj_sertifikata" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="about" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "datumVreme",
    "qrKod",
    "primalac",
    "vakcinacija",
    "testovi"
})
@XmlRootElement(name = "Sertifikat")
public class Sertifikat {

    @XmlElement(name = "Datum_vreme", required = true)
    protected DatumVreme datumVreme;
    @XmlElement(name = "Qr_kod", required = true)
    protected String qrKod;
    @XmlElement(name = "Primalac", required = true)
    protected TVakcinisanoLice primalac;
    @XmlElement(name = "Vakcinacija", required = true)
    protected TVakcinacija vakcinacija;
    @XmlElement(name = "Testovi", required = true)
    protected TTestovi testovi;
    @XmlAttribute(name = "broj_sertifikata")
    protected String brojSertifikata;
    @XmlAttribute(name = "about", required = true)
    protected String about;

    /**
     * Gets the value of the datumVreme property.
     * 
     * @return
     *     possible object is
     *     {@link DatumVreme }
     *     
     */
    public DatumVreme getDatumVreme() {
        return datumVreme;
    }

    /**
     * Sets the value of the datumVreme property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatumVreme }
     *     
     */
    public void setDatumVreme(DatumVreme value) {
        this.datumVreme = value;
    }

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
     *     {@link TVakcinisanoLice }
     *     
     */
    public TVakcinisanoLice getPrimalac() {
        return primalac;
    }

    /**
     * Sets the value of the primalac property.
     * 
     * @param value
     *     allowed object is
     *     {@link TVakcinisanoLice }
     *     
     */
    public void setPrimalac(TVakcinisanoLice value) {
        this.primalac = value;
    }

    /**
     * Gets the value of the vakcinacija property.
     * 
     * @return
     *     possible object is
     *     {@link TVakcinacija }
     *     
     */
    public TVakcinacija getVakcinacija() {
        return vakcinacija;
    }

    /**
     * Sets the value of the vakcinacija property.
     * 
     * @param value
     *     allowed object is
     *     {@link TVakcinacija }
     *     
     */
    public void setVakcinacija(TVakcinacija value) {
        this.vakcinacija = value;
    }

    /**
     * Gets the value of the testovi property.
     * 
     * @return
     *     possible object is
     *     {@link TTestovi }
     *     
     */
    public TTestovi getTestovi() {
        return testovi;
    }

    /**
     * Sets the value of the testovi property.
     * 
     * @param value
     *     allowed object is
     *     {@link TTestovi }
     *     
     */
    public void setTestovi(TTestovi value) {
        this.testovi = value;
    }

    /**
     * Gets the value of the brojSertifikata property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrojSertifikata() {
        return brojSertifikata;
    }

    /**
     * Sets the value of the brojSertifikata property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrojSertifikata(String value) {
        this.brojSertifikata = value;
    }

    /**
     * Gets the value of the about property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbout() {
        return about;
    }

    /**
     * Sets the value of the about property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbout(String value) {
        this.about = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;dateTime"&gt;
     *       &lt;attribute name="property" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" fixed="pred:datumIzdavanja" /&gt;
     *       &lt;attribute name="datatype" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" fixed="xs:dateTime" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class DatumVreme {

        @XmlValue
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar value;
        @XmlAttribute(name = "property", required = true)
        @XmlSchemaType(name = "anySimpleType")
        protected String property;
        @XmlAttribute(name = "datatype", required = true)
        @XmlSchemaType(name = "anySimpleType")
        protected String datatype;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setValue(XMLGregorianCalendar value) {
            this.value = value;
        }

        /**
         * Gets the value of the property property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProperty() {
            if (property == null) {
                return "pred:datumIzdavanja";
            } else {
                return property;
            }
        }

        /**
         * Sets the value of the property property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProperty(String value) {
            this.property = value;
        }

        /**
         * Gets the value of the datatype property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDatatype() {
            if (datatype == null) {
                return "xs:dateTime";
            } else {
                return datatype;
            }
        }

        /**
         * Sets the value of the datatype property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDatatype(String value) {
            this.datatype = value;
        }

    }

}
