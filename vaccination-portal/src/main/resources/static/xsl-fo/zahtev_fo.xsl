<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:zahtev="http://www.xws.org/zahtev"
                xmlns:t="http://www.xws.org/tipovi"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master
                        master-name="zahtev"
                        page-height="29.7cm"
                        page-width="21cm"
                        margin-top="1.3cm"
                        margin-bottom="2cm"
                        margin-left="3cm"
                        margin-right="3cm">
                    <fo:region-body/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="zahtev">

                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-family="Times" font-size="15px" margin-top="70px"
                              text-align="center">З А Х Т Е В
                    </fo:block>
                    <fo:block font-family="Times" font-size="13px" padding="2px"
                              text-align="center">
                        за издавање дигиталног зеленог сертификата
                    </fo:block>
                    <fo:block font-family="Times" font-size="12px" padding="5px" margin-top="25px" text-align="justify" text-indent="3rem">
                        У складу са одредбом Републике Србије о издавању дигиталног зеленог
                        сертификата као потврде о извршеној вакцинацији против COVID-19, резултатима тестирања на
                        заразну болест SARS-CoV-2
                        или опоравку од болести COVID-19, подносим захтев за издавање дигиталног зеленог сертификата.
                    </fo:block>
                    <fo:block font-family="Times" font-size="12px" padding="5px" margin-top="20px">
                        Подносилац захтева:
                    </fo:block>
                    <fo:block font-family="Times" font-size="12px" margin-top="15px">
                        Име и презиме:
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="concat(' ', //t:Ime, ' ', //t:Prezime)"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="Times" font-size="12px">
                        Датум рођења:
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="concat(' ', //t:Datum_rodjenja)"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="Times" font-size="12px" >
                        Пол:
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="concat(' ', //t:Pol)"/>
                        </fo:inline>
                    </fo:block>
                    <xsl:choose>
                        <xsl:when test="string-length(//t:JMBG) = 13">
                            <fo:block font-family="Times" font-size="12px">
                                Јединствени матични број грађанина:
                                <fo:inline font-weight="bold">
                                    <xsl:value-of select="concat(' ', //t:JMBG)"/>
                                </fo:inline>
                            </fo:block>
                        </xsl:when>
                        <xsl:otherwise>
                            <fo:block font-family="Times" font-size="12px">
                                Број пасоша:
                                <fo:inline font-weight="bold">
                                    <xsl:value-of select="concat(' ', //t:Broj_pasosa)"/>
                                </fo:inline>
                            </fo:block>
                        </xsl:otherwise>
                    </xsl:choose>
                    <fo:block font-family="Times" font-size="12px" margin-top="10px">
                        Разлог за подношење захтева:
                    </fo:block>
                    <fo:block font-family="Times" font-size="12px">
                        <xsl:value-of select="//zahtev:Razlog"/>
                    </fo:block>
                    <fo:block font-family="Times" font-size="8px" padding="5px" text-align="center">
                        (навести што прецизнији разлог за подношење захтева за издавање дигиталног пасоша)
                    </fo:block>
                    <fo:block font-family="Times" font-size="13px" padding="5px" margin-top="35px">
                        У
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="concat(' ', //zahtev:Mesto)"/>,
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="Times" font-size="12px" padding="5px" margin-top="10px">
                        Датум:
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="concat(' ', //zahtev:Datum)"/>
                        </fo:inline>
                         године 
                    </fo:block>
                    <fo:block-container>
                        <fo:block-container width="35%" left="60%" top="20px" position="absolute">
                            <fo:block font-family="Times" font-size="12px" text-align="center"
                                      linefeed-treatment="preserve" margin="0" border-top="1px solid black">
                                Потпис
                            </fo:block>
                        </fo:block-container>
                    </fo:block-container>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>