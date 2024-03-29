//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.01.11 at 06:34:26 PM CET 
//


package com.tim123.vaccinationmain.model.saglasnost;

import com.tim123.vaccinationmain.model.tipovi.TKontakt;
import com.tim123.vaccinationmain.model.tipovi.TLice;
import com.tim123.vaccinationmain.model.tipovi.TPol;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for TPacijent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TPacijent"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.xws.org/tipovi}TLice"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Drzavljanstvo" type="{http://www.xws.org/saglasnost}TDrzavljanstvo"/&gt;
 *         &lt;element name="Ime_roditelja" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Pol" type="{http://www.xws.org/tipovi}TPol"/&gt;
 *         &lt;element name="Mesto_rodjenja" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Adresa" type="{http://www.xws.org/saglasnost}TAdresa"/&gt;
 *         &lt;element name="Kontakt" type="{http://www.xws.org/tipovi}TKontakt"/&gt;
 *         &lt;element name="Radni_status"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;choice&gt;
 *                   &lt;element name="Zaposlen"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;choice&gt;
 *                             &lt;element name="Zdravstvena_zastita" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *                             &lt;element name="Socijalna_zastita" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *                             &lt;element name="Prosveta" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *                             &lt;element name="MUP" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *                             &lt;element name="Vojska_RS" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *                             &lt;element name="Drugo" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *                           &lt;/choice&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Nezaposlen" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *                   &lt;element name="Penzioner" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *                   &lt;element name="Ucenik" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *                   &lt;element name="Student" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *                   &lt;element name="Dete" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *                 &lt;/choice&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;choice&gt;
 *           &lt;element name="Korisnik_ustanove_socijalne_zasttite" minOccurs="0"&gt;
 *             &lt;complexType&gt;
 *               &lt;complexContent&gt;
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                   &lt;all&gt;
 *                     &lt;element name="Sediste"&gt;
 *                       &lt;complexType&gt;
 *                         &lt;complexContent&gt;
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                             &lt;all&gt;
 *                               &lt;element name="Naziv" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                               &lt;element name="Opstina" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;/all&gt;
 *                           &lt;/restriction&gt;
 *                         &lt;/complexContent&gt;
 *                       &lt;/complexType&gt;
 *                     &lt;/element&gt;
 *                   &lt;/all&gt;
 *                 &lt;/restriction&gt;
 *               &lt;/complexContent&gt;
 *             &lt;/complexType&gt;
 *           &lt;/element&gt;
 *         &lt;/choice&gt;
 *         &lt;choice&gt;
 *           &lt;element name="Saglasnost" minOccurs="0"&gt;
 *             &lt;complexType&gt;
 *               &lt;complexContent&gt;
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                   &lt;all&gt;
 *                     &lt;element name="Naziv_imunoloskog_leka" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;/all&gt;
 *                 &lt;/restriction&gt;
 *               &lt;/complexContent&gt;
 *             &lt;/complexType&gt;
 *           &lt;/element&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPacijent", propOrder = {
    "drzavljanstvo",
    "imeRoditelja",
    "pol",
    "mestoRodjenja",
    "adresa",
    "kontakt",
    "radniStatus",
    "korisnikUstanoveSocijalneZasttite",
    "saglasnost"
})
public class TPacijent
    extends TLice
{

    @XmlElement(name = "Drzavljanstvo", required = true)
    protected TDrzavljanstvo drzavljanstvo;
    @XmlElement(name = "Ime_roditelja", required = true)
    protected String imeRoditelja;
    @XmlElement(name = "Pol", required = true)
    @XmlSchemaType(name = "string")
    protected TPol pol;
    @XmlElement(name = "Mesto_rodjenja", required = true)
    protected String mestoRodjenja;
    @XmlElement(name = "Adresa", required = true)
    protected TAdresa adresa;
    @XmlElement(name = "Kontakt", required = true)
    protected TKontakt kontakt;
    @XmlElement(name = "Radni_status", required = true)
    protected RadniStatus radniStatus;
    @XmlElement(name = "Korisnik_ustanove_socijalne_zasttite")
    protected KorisnikUstanoveSocijalneZasttite korisnikUstanoveSocijalneZasttite;
    @XmlElement(name = "Saglasnost")
    protected Saglasnost saglasnost;

    /**
     * Gets the value of the drzavljanstvo property.
     * 
     * @return
     *     possible object is
     *     {@link TDrzavljanstvo }
     *     
     */
    public TDrzavljanstvo getDrzavljanstvo() {
        return drzavljanstvo;
    }

    /**
     * Sets the value of the drzavljanstvo property.
     * 
     * @param value
     *     allowed object is
     *     {@link TDrzavljanstvo }
     *     
     */
    public void setDrzavljanstvo(TDrzavljanstvo value) {
        this.drzavljanstvo = value;
    }

    /**
     * Gets the value of the imeRoditelja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImeRoditelja() {
        return imeRoditelja;
    }

    /**
     * Sets the value of the imeRoditelja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImeRoditelja(String value) {
        this.imeRoditelja = value;
    }

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
     * Gets the value of the mestoRodjenja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMestoRodjenja() {
        return mestoRodjenja;
    }

    /**
     * Sets the value of the mestoRodjenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMestoRodjenja(String value) {
        this.mestoRodjenja = value;
    }

    /**
     * Gets the value of the adresa property.
     * 
     * @return
     *     possible object is
     *     {@link TAdresa }
     *     
     */
    public TAdresa getAdresa() {
        return adresa;
    }

    /**
     * Sets the value of the adresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link TAdresa }
     *     
     */
    public void setAdresa(TAdresa value) {
        this.adresa = value;
    }

    /**
     * Gets the value of the kontakt property.
     * 
     * @return
     *     possible object is
     *     {@link TKontakt }
     *     
     */
    public TKontakt getKontakt() {
        return kontakt;
    }

    /**
     * Sets the value of the kontakt property.
     * 
     * @param value
     *     allowed object is
     *     {@link TKontakt }
     *     
     */
    public void setKontakt(TKontakt value) {
        this.kontakt = value;
    }

    /**
     * Gets the value of the radniStatus property.
     * 
     * @return
     *     possible object is
     *     {@link RadniStatus }
     *     
     */
    public RadniStatus getRadniStatus() {
        return radniStatus;
    }

    /**
     * Sets the value of the radniStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link RadniStatus }
     *     
     */
    public void setRadniStatus(RadniStatus value) {
        this.radniStatus = value;
    }

    /**
     * Gets the value of the korisnikUstanoveSocijalneZasttite property.
     * 
     * @return
     *     possible object is
     *     {@link KorisnikUstanoveSocijalneZasttite }
     *     
     */
    public KorisnikUstanoveSocijalneZasttite getKorisnikUstanoveSocijalneZasttite() {
        return korisnikUstanoveSocijalneZasttite;
    }

    /**
     * Sets the value of the korisnikUstanoveSocijalneZasttite property.
     * 
     * @param value
     *     allowed object is
     *     {@link KorisnikUstanoveSocijalneZasttite }
     *     
     */
    public void setKorisnikUstanoveSocijalneZasttite(KorisnikUstanoveSocijalneZasttite value) {
        this.korisnikUstanoveSocijalneZasttite = value;
    }

    /**
     * Gets the value of the saglasnost property.
     * 
     * @return
     *     possible object is
     *     {@link Saglasnost }
     *     
     */
    public Saglasnost getSaglasnost() {
        return saglasnost;
    }

    /**
     * Sets the value of the saglasnost property.
     * 
     * @param value
     *     allowed object is
     *     {@link Saglasnost }
     *     
     */
    public void setSaglasnost(Saglasnost value) {
        this.saglasnost = value;
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
     *       &lt;all&gt;
     *         &lt;element name="Sediste"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;all&gt;
     *                   &lt;element name="Naziv" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="Opstina" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                 &lt;/all&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/all&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class KorisnikUstanoveSocijalneZasttite {

        @XmlElement(name = "Sediste", required = true)
        protected Sediste sediste;

        /**
         * Gets the value of the sediste property.
         * 
         * @return
         *     possible object is
         *     {@link Sediste }
         *     
         */
        public Sediste getSediste() {
            return sediste;
        }

        /**
         * Sets the value of the sediste property.
         * 
         * @param value
         *     allowed object is
         *     {@link Sediste }
         *     
         */
        public void setSediste(Sediste value) {
            this.sediste = value;
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
         *       &lt;all&gt;
         *         &lt;element name="Naziv" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="Opstina" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *       &lt;/all&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {

        })
        public static class Sediste {

            @XmlElement(name = "Naziv", required = true)
            protected String naziv;
            @XmlElement(name = "Opstina", required = true)
            protected String opstina;

            /**
             * Gets the value of the naziv property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNaziv() {
                return naziv;
            }

            /**
             * Sets the value of the naziv property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNaziv(String value) {
                this.naziv = value;
            }

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
     *       &lt;choice&gt;
     *         &lt;element name="Zaposlen"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;choice&gt;
     *                   &lt;element name="Zdravstvena_zastita" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
     *                   &lt;element name="Socijalna_zastita" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
     *                   &lt;element name="Prosveta" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
     *                   &lt;element name="MUP" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
     *                   &lt;element name="Vojska_RS" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
     *                   &lt;element name="Drugo" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
     *                 &lt;/choice&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Nezaposlen" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
     *         &lt;element name="Penzioner" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
     *         &lt;element name="Ucenik" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
     *         &lt;element name="Student" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
     *         &lt;element name="Dete" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
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
        "zaposlen",
        "nezaposlen",
        "penzioner",
        "ucenik",
        "student",
        "dete"
    })
    public static class RadniStatus {

        @XmlElement(name = "Zaposlen")
        protected Zaposlen zaposlen;
        @XmlElement(name = "Nezaposlen")
        protected Object nezaposlen;
        @XmlElement(name = "Penzioner")
        protected Object penzioner;
        @XmlElement(name = "Ucenik")
        protected Object ucenik;
        @XmlElement(name = "Student")
        protected Object student;
        @XmlElement(name = "Dete")
        protected Object dete;

        /**
         * Gets the value of the zaposlen property.
         * 
         * @return
         *     possible object is
         *     {@link Zaposlen }
         *     
         */
        public Zaposlen getZaposlen() {
            return zaposlen;
        }

        /**
         * Sets the value of the zaposlen property.
         * 
         * @param value
         *     allowed object is
         *     {@link Zaposlen }
         *     
         */
        public void setZaposlen(Zaposlen value) {
            this.zaposlen = value;
        }

        /**
         * Gets the value of the nezaposlen property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getNezaposlen() {
            return nezaposlen;
        }

        /**
         * Sets the value of the nezaposlen property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setNezaposlen(Object value) {
            this.nezaposlen = value;
        }

        /**
         * Gets the value of the penzioner property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getPenzioner() {
            return penzioner;
        }

        /**
         * Sets the value of the penzioner property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setPenzioner(Object value) {
            this.penzioner = value;
        }

        /**
         * Gets the value of the ucenik property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getUcenik() {
            return ucenik;
        }

        /**
         * Sets the value of the ucenik property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setUcenik(Object value) {
            this.ucenik = value;
        }

        /**
         * Gets the value of the student property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getStudent() {
            return student;
        }

        /**
         * Sets the value of the student property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setStudent(Object value) {
            this.student = value;
        }

        /**
         * Gets the value of the dete property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getDete() {
            return dete;
        }

        /**
         * Sets the value of the dete property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setDete(Object value) {
            this.dete = value;
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
         *         &lt;element name="Zdravstvena_zastita" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
         *         &lt;element name="Socijalna_zastita" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
         *         &lt;element name="Prosveta" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
         *         &lt;element name="MUP" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
         *         &lt;element name="Vojska_RS" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
         *         &lt;element name="Drugo" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
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
            "zdravstvenaZastita",
            "socijalnaZastita",
            "prosveta",
            "mup",
            "vojskaRS",
            "drugo"
        })
        public static class Zaposlen {

            @XmlElement(name = "Zdravstvena_zastita")
            protected Object zdravstvenaZastita;
            @XmlElement(name = "Socijalna_zastita")
            protected Object socijalnaZastita;
            @XmlElement(name = "Prosveta")
            protected Object prosveta;
            @XmlElement(name = "MUP")
            protected Object mup;
            @XmlElement(name = "Vojska_RS")
            protected Object vojskaRS;
            @XmlElement(name = "Drugo")
            protected Object drugo;

            /**
             * Gets the value of the zdravstvenaZastita property.
             * 
             * @return
             *     possible object is
             *     {@link Object }
             *     
             */
            public Object getZdravstvenaZastita() {
                return zdravstvenaZastita;
            }

            /**
             * Sets the value of the zdravstvenaZastita property.
             * 
             * @param value
             *     allowed object is
             *     {@link Object }
             *     
             */
            public void setZdravstvenaZastita(Object value) {
                this.zdravstvenaZastita = value;
            }

            /**
             * Gets the value of the socijalnaZastita property.
             * 
             * @return
             *     possible object is
             *     {@link Object }
             *     
             */
            public Object getSocijalnaZastita() {
                return socijalnaZastita;
            }

            /**
             * Sets the value of the socijalnaZastita property.
             * 
             * @param value
             *     allowed object is
             *     {@link Object }
             *     
             */
            public void setSocijalnaZastita(Object value) {
                this.socijalnaZastita = value;
            }

            /**
             * Gets the value of the prosveta property.
             * 
             * @return
             *     possible object is
             *     {@link Object }
             *     
             */
            public Object getProsveta() {
                return prosveta;
            }

            /**
             * Sets the value of the prosveta property.
             * 
             * @param value
             *     allowed object is
             *     {@link Object }
             *     
             */
            public void setProsveta(Object value) {
                this.prosveta = value;
            }

            /**
             * Gets the value of the mup property.
             * 
             * @return
             *     possible object is
             *     {@link Object }
             *     
             */
            public Object getMUP() {
                return mup;
            }

            /**
             * Sets the value of the mup property.
             * 
             * @param value
             *     allowed object is
             *     {@link Object }
             *     
             */
            public void setMUP(Object value) {
                this.mup = value;
            }

            /**
             * Gets the value of the vojskaRS property.
             * 
             * @return
             *     possible object is
             *     {@link Object }
             *     
             */
            public Object getVojskaRS() {
                return vojskaRS;
            }

            /**
             * Sets the value of the vojskaRS property.
             * 
             * @param value
             *     allowed object is
             *     {@link Object }
             *     
             */
            public void setVojskaRS(Object value) {
                this.vojskaRS = value;
            }

            /**
             * Gets the value of the drugo property.
             * 
             * @return
             *     possible object is
             *     {@link Object }
             *     
             */
            public Object getDrugo() {
                return drugo;
            }

            /**
             * Sets the value of the drugo property.
             * 
             * @param value
             *     allowed object is
             *     {@link Object }
             *     
             */
            public void setDrugo(Object value) {
                this.drugo = value;
            }

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
     *       &lt;all&gt;
     *         &lt;element name="Naziv_imunoloskog_leka" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *       &lt;/all&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class Saglasnost {

        @XmlElement(name = "Naziv_imunoloskog_leka", required = true)
        protected String nazivImunoloskogLeka;

        /**
         * Gets the value of the nazivImunoloskogLeka property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNazivImunoloskogLeka() {
            return nazivImunoloskogLeka;
        }

        /**
         * Sets the value of the nazivImunoloskogLeka property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNazivImunoloskogLeka(String value) {
            this.nazivImunoloskogLeka = value;
        }

    }

}
