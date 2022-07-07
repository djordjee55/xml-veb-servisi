package com.tim123.vaccinationmain.model.enumi;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum
public enum Drzavljanstvo {
    DRZAVLJANIN_REPUBLIKE_SRBIJE,
    STRANI_DRZAVLJANIN_SA_BORAVKOM_U_RS,
    STRANI_DRZAVLJANIN_BEZ_BORAVKA_U_RS,
}
