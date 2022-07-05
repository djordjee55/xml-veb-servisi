package com.tim123.vaccinationmain.util;

public class Constants {
    private static final String base = "http://www.xws.org";

    public static final String vakcinaCollection = "db/vakcinisanje/vakcina";
    public static final String ustanoveCollection = "db/vakcinisanje/ustanove";
    public static final String cekanjeCollection = "db/vakcinisanje/cekanje";
    public static final String sertifikatBase = String.format("%s/sertifikat", base);
    public static final String sertifikatPath = "/fuseki/sertifikat";
    public static final String sertifikatCollection = "db/vakcinisanje/sertifikat";
    public static final String izvestajBase = String.format("%s/izvestaj", base);
    public static final String izvestajPath = "/fuseki/izvestaj";
    public static final String izvestajCollection = "db/vakcinisanje/izvestaj";

}
