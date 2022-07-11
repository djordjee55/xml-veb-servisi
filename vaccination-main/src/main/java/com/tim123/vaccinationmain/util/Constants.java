package com.tim123.vaccinationmain.util;

public class Constants {
    private static final String base = "http://www.xws.org";

    public static final String vakcinaCollection = "db/imunizacioni_sistem/vakcina";
    public static final String ustanoveCollection = "db/imunizacioni_sistem/ustanove";
    public static final String cekanjeCollection = "db/imunizacioni_sistem/cekanje";
    public static final String sertifikatBase = String.format("%s/sertifikat", base);
    public static final String sertifikatPath = "/fuseki/sertifikat";
    public static final String sertifikatCollection = "db/imunizacioni_sistem/sertifikat";
    public static final String izvestajBase = String.format("%s/izvestaj", base);
    public static final String izvestajPath = "/fuseki/izvestaj";
    public static final String izvestajCollection = "db/imunizacioni_sistem/izvestaj";

}
