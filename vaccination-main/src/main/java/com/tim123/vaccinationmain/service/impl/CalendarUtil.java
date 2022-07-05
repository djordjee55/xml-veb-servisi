package com.tim123.vaccinationmain.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.GregorianCalendar;

public class CalendarUtil {

    public static XMLGregorianCalendar toXmlGregorianCalendar(final long date) {
        try {
            final GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTimeInMillis(date);
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(
                    calendar);
        }
        catch (final DatatypeConfigurationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nemoguce konvertovati u datum");
        }
    }

}
