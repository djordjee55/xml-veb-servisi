//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.01.04 at 09:24:56 PM CET 
//


package com.tim123.vaccinationmain.model.izvestaj;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.xws.izvestaj package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.xws.izvestaj
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Izvestaj }
     * 
     */
    public Izvestaj createIzvestaj() {
        return new Izvestaj();
    }

    /**
     * Create an instance of {@link Izvestaj.RaspodelaPoProizvodjacima }
     * 
     */
    public Izvestaj.RaspodelaPoProizvodjacima createIzvestajRaspodelaPoProizvodjacima() {
        return new Izvestaj.RaspodelaPoProizvodjacima();
    }

    /**
     * Create an instance of {@link Izvestaj.Doze }
     * 
     */
    public Izvestaj.Doze createIzvestajDoze() {
        return new Izvestaj.Doze();
    }

    /**
     * Create an instance of {@link Izvestaj.Period }
     * 
     */
    public Izvestaj.Period createIzvestajPeriod() {
        return new Izvestaj.Period();
    }

    /**
     * Create an instance of {@link Izvestaj.RaspodelaPoProizvodjacima.Proizvodjac }
     * 
     */
    public Izvestaj.RaspodelaPoProizvodjacima.Proizvodjac createIzvestajRaspodelaPoProizvodjacimaProizvodjac() {
        return new Izvestaj.RaspodelaPoProizvodjacima.Proizvodjac();
    }

    /**
     * Create an instance of {@link Izvestaj.Doze.Doza }
     * 
     */
    public Izvestaj.Doze.Doza createIzvestajDozeDoza() {
        return new Izvestaj.Doze.Doza();
    }

}