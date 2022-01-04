//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.01.04 at 09:24:56 PM CET 
//


package com.tim123.vaccinationmain.model.interesovanje;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import com.tim123.vaccinationmain.model.tipovi.TZainteresovanoLice;


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
 *         &lt;element name="Drzavljanstvo"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;choice&gt;
 *                   &lt;element name="Drzavljanin_Republike_Srbije" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *                   &lt;element name="Strani_drzavljanin_sa_boravkom_u_RS" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *                   &lt;element name="Strani_drzavljanin_bez_boravka_u_RS" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *                 &lt;/choice&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Zeljena_opstina_vakcinacije" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Zeljena_vakcina"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;choice maxOccurs="unbounded"&gt;
 *                   &lt;element name="Pfizer-BioNTech" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *                   &lt;element name="Sputnik_V" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *                   &lt;element name="Sinopharm" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *                   &lt;element name="AstraZeneca" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *                   &lt;element name="Moderna" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *                   &lt;element name="Bilo_koja" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *                 &lt;/choice&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Dobrovoljni_davalac_krvi" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="Datum" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="Primalac" type="{http://www.xws.org/tipovi}TZainteresovanoLice"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "drzavljanstvo",
    "zeljenaOpstinaVakcinacije",
    "zeljenaVakcina",
    "dobrovoljniDavalacKrvi",
    "datum",
    "primalac"
})
@XmlRootElement(name = "Interesovanje")
public class Interesovanje {

    @XmlElement(name = "Drzavljanstvo", required = true)
    protected Interesovanje.Drzavljanstvo drzavljanstvo;
    @XmlElement(name = "Zeljena_opstina_vakcinacije", required = true)
    protected String zeljenaOpstinaVakcinacije;
    @XmlElement(name = "Zeljena_vakcina", required = true)
    protected Interesovanje.ZeljenaVakcina zeljenaVakcina;
    @XmlElement(name = "Dobrovoljni_davalac_krvi")
    protected boolean dobrovoljniDavalacKrvi;
    @XmlElement(name = "Datum", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datum;
    @XmlElement(name = "Primalac", required = true)
    protected TZainteresovanoLice primalac;
    @XmlAttribute(name = "id")
    protected String id;

    /**
     * Gets the value of the drzavljanstvo property.
     * 
     * @return
     *     possible object is
     *     {@link Interesovanje.Drzavljanstvo }
     *     
     */
    public Interesovanje.Drzavljanstvo getDrzavljanstvo() {
        return drzavljanstvo;
    }

    /**
     * Sets the value of the drzavljanstvo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Interesovanje.Drzavljanstvo }
     *     
     */
    public void setDrzavljanstvo(Interesovanje.Drzavljanstvo value) {
        this.drzavljanstvo = value;
    }

    /**
     * Gets the value of the zeljenaOpstinaVakcinacije property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZeljenaOpstinaVakcinacije() {
        return zeljenaOpstinaVakcinacije;
    }

    /**
     * Sets the value of the zeljenaOpstinaVakcinacije property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZeljenaOpstinaVakcinacije(String value) {
        this.zeljenaOpstinaVakcinacije = value;
    }

    /**
     * Gets the value of the zeljenaVakcina property.
     * 
     * @return
     *     possible object is
     *     {@link Interesovanje.ZeljenaVakcina }
     *     
     */
    public Interesovanje.ZeljenaVakcina getZeljenaVakcina() {
        return zeljenaVakcina;
    }

    /**
     * Sets the value of the zeljenaVakcina property.
     * 
     * @param value
     *     allowed object is
     *     {@link Interesovanje.ZeljenaVakcina }
     *     
     */
    public void setZeljenaVakcina(Interesovanje.ZeljenaVakcina value) {
        this.zeljenaVakcina = value;
    }

    /**
     * Gets the value of the dobrovoljniDavalacKrvi property.
     * 
     */
    public boolean isDobrovoljniDavalacKrvi() {
        return dobrovoljniDavalacKrvi;
    }

    /**
     * Sets the value of the dobrovoljniDavalacKrvi property.
     * 
     */
    public void setDobrovoljniDavalacKrvi(boolean value) {
        this.dobrovoljniDavalacKrvi = value;
    }

    /**
     * Gets the value of the datum property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatum() {
        return datum;
    }

    /**
     * Sets the value of the datum property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatum(XMLGregorianCalendar value) {
        this.datum = value;
    }

    /**
     * Gets the value of the primalac property.
     * 
     * @return
     *     possible object is
     *     {@link TZainteresovanoLice }
     *     
     */
    public TZainteresovanoLice getPrimalac() {
        return primalac;
    }

    /**
     * Sets the value of the primalac property.
     * 
     * @param value
     *     allowed object is
     *     {@link TZainteresovanoLice }
     *     
     */
    public void setPrimalac(TZainteresovanoLice value) {
        this.primalac = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;choice&gt;
     *         &lt;element name="Drzavljanin_Republike_Srbije" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
     *         &lt;element name="Strani_drzavljanin_sa_boravkom_u_RS" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
     *         &lt;element name="Strani_drzavljanin_bez_boravka_u_RS" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
     *       &lt;/choice&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "drzavljaninRepublikeSrbije",
        "straniDrzavljaninSaBoravkomURS",
        "straniDrzavljaninBezBoravkaURS"
    })
    public static class Drzavljanstvo {

        @XmlElement(name = "Drzavljanin_Republike_Srbije")
        protected Object drzavljaninRepublikeSrbije;
        @XmlElement(name = "Strani_drzavljanin_sa_boravkom_u_RS")
        protected Object straniDrzavljaninSaBoravkomURS;
        @XmlElement(name = "Strani_drzavljanin_bez_boravka_u_RS")
        protected Object straniDrzavljaninBezBoravkaURS;

        /**
         * Gets the value of the drzavljaninRepublikeSrbije property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getDrzavljaninRepublikeSrbije() {
            return drzavljaninRepublikeSrbije;
        }

        /**
         * Sets the value of the drzavljaninRepublikeSrbije property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setDrzavljaninRepublikeSrbije(Object value) {
            this.drzavljaninRepublikeSrbije = value;
        }

        /**
         * Gets the value of the straniDrzavljaninSaBoravkomURS property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getStraniDrzavljaninSaBoravkomURS() {
            return straniDrzavljaninSaBoravkomURS;
        }

        /**
         * Sets the value of the straniDrzavljaninSaBoravkomURS property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setStraniDrzavljaninSaBoravkomURS(Object value) {
            this.straniDrzavljaninSaBoravkomURS = value;
        }

        /**
         * Gets the value of the straniDrzavljaninBezBoravkaURS property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getStraniDrzavljaninBezBoravkaURS() {
            return straniDrzavljaninBezBoravkaURS;
        }

        /**
         * Sets the value of the straniDrzavljaninBezBoravkaURS property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setStraniDrzavljaninBezBoravkaURS(Object value) {
            this.straniDrzavljaninBezBoravkaURS = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;choice maxOccurs="unbounded"&gt;
     *         &lt;element name="Pfizer-BioNTech" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
     *         &lt;element name="Sputnik_V" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
     *         &lt;element name="Sinopharm" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
     *         &lt;element name="AstraZeneca" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
     *         &lt;element name="Moderna" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
     *         &lt;element name="Bilo_koja" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
     *       &lt;/choice&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "pfizerBioNTechOrSputnikVOrSinopharm"
    })
    public static class ZeljenaVakcina {

        @XmlElementRefs({
            @XmlElementRef(name = "Sinopharm", namespace = "http://www.xws.org/interesovanje", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "Sputnik_V", namespace = "http://www.xws.org/interesovanje", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "Pfizer-BioNTech", namespace = "http://www.xws.org/interesovanje", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "Moderna", namespace = "http://www.xws.org/interesovanje", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "AstraZeneca", namespace = "http://www.xws.org/interesovanje", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "Bilo_koja", namespace = "http://www.xws.org/interesovanje", type = JAXBElement.class, required = false)
        })
        protected List<JAXBElement<Object>> pfizerBioNTechOrSputnikVOrSinopharm;

        /**
         * Gets the value of the pfizerBioNTechOrSputnikVOrSinopharm property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the pfizerBioNTechOrSputnikVOrSinopharm property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPfizerBioNTechOrSputnikVOrSinopharm().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link JAXBElement }{@code <}{@link Object }{@code >}
         * {@link JAXBElement }{@code <}{@link Object }{@code >}
         * {@link JAXBElement }{@code <}{@link Object }{@code >}
         * {@link JAXBElement }{@code <}{@link Object }{@code >}
         * {@link JAXBElement }{@code <}{@link Object }{@code >}
         * {@link JAXBElement }{@code <}{@link Object }{@code >}
         * 
         * 
         */
        public List<JAXBElement<Object>> getPfizerBioNTechOrSputnikVOrSinopharm() {
            if (pfizerBioNTechOrSputnikVOrSinopharm == null) {
                pfizerBioNTechOrSputnikVOrSinopharm = new ArrayList<JAXBElement<Object>>();
            }
            return this.pfizerBioNTechOrSputnikVOrSinopharm;
        }

    }

}
