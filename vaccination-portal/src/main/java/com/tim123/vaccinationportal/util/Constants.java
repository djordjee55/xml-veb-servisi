package com.tim123.vaccinationportal.util;

public class Constants {
    private static final String base = "http://www.xws.org";

    public static final String interesovanjeBase = String.format("%s/interesovanje", base);
    public static final String interesovanjePath = "/fuseki/interesovanje";
    public static final String interesovanjeCollection = "db/vakcinisanje/interesovanje";

    public static final String saglasnostBase = String.format("%s/saglasnost", base);
    public static final String saglasnostPath = "/fuseki/saglasnost";
    public static final String saglasnostCollection = "db/vakcinisanje/saglasnost";

    public static final String zahtevBase = String.format("%s/zahtev", base);
    public static final String zahtevPath = "/fuseki/zahtev";
    public static final String zahtevCollection = "db/vakcinisanje/zahtev";
}
