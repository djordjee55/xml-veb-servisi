<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:i="http://www.xws.org/interesovanje"
                xmlns:tip="http://www.xws.org/tipovi"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master
                        master-name="interesovanje"
                        page-height="29.7cm"
                        page-width="21cm"
                        margin-top="2.5cm"
                        margin-bottom="2cm"
                        margin-left="2cm"
                        margin-right="2cm">
                    <fo:region-body/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="interesovanje">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-family="Times" font-size="15px" padding="20px"
                              text-align="center">
                        Исказивање интересовања за вакцинисање против COVID-19
                    </fo:block>
                    <xsl:if test="//i:Drzavljanin_Republike_Srbije">
                        <fo:block font-family="Times" font-size="13px" padding="10px">
                            Држављанство:
                            <fo:inline>Држављанин Републике Србије</fo:inline>
                        </fo:block>
                    </xsl:if>
                    <xsl:if test="//i:Strani_drzavljanin_sa_boravkom_u_RS">
                        <fo:block font-family="Times" font-size="13px" padding="10px">
                            Држављанство:
                            <fo:inline>Страни држављанин са боравком у РС</fo:inline>
                        </fo:block>
                    </xsl:if>
                    <xsl:if test="//i:Strani_drzavljanin_bez_boravka_u_RS">
                        <fo:block font-family="Times" font-size="13px" padding="10px">
                            Држављанство:
                            <fo:inline>Страни држављанин без боравка у РС</fo:inline>
                        </fo:block>
                    </xsl:if>
                    <xsl:choose>
                        <xsl:when test="string-length(//tip:JMBG/text()) = 13">
                            <fo:block font-family="Times" font-size="13px" padding="10px">
                                ЈМБГ:
                                <fo:inline font-weight="bold">
                                    <xsl:value-of select="concat(' ', //tip:JMBG)"/>
                                </fo:inline>>
                            </fo:block>
                        </xsl:when>
                        <xsl:otherwise>
                            <fo:block font-family="Times" font-size="13px" padding="10px">
                                Број пасоша:
                                <fo:inline font-weight="bold">
                                    <xsl:value-of select="concat(' ', //tip:JMBG)"/>
                                </fo:inline>>
                            </fo:block>
                        </xsl:otherwise>
                    </xsl:choose>
                    <fo:block font-family="Times" font-size="13px" padding="10px">
                        Име:
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="//i:Primalac/tip:Ime"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="Times" font-size="13px" padding="10px">
                        Презиме:
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="//i:Primalac/tip:Prezime"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="Times" font-size="13px" padding="10px">
                        Адреса електорнске поште:
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="//i:Primalac/tip:Kontakt/tip:E_mail"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="Times" font-size="13px" padding="10px">
                        Број мобилног телефона:
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="//i:Primalac/tip:Kontakt/tip:Mobilni_telefon"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="Times" font-size="13px" padding="10px">
                        Број фиксног телефона:
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="//i:Primalac/tip:Kontakt/tip:Fiksni_telefon"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="Times" font-size="13px" padding="10px">
                        Локација (општина) у којој желите да примите вакцину:
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="//i:Zeljena_opstina_vakcinacije"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="Times" font-size="13px" padding="20px">
                        Исказујем интересовађње да примим искључиво вакцину следећих произвођача за који Агенција
                        за лекове и медицинска средства поврди безбедност, ефиасност и квалитет и изда дозволу за употребу
                        лека:
                    </fo:block>
                    <xsl:for-each select="//i:Zeljena_vakcina/*">
                        <fo:block font-size="13px" text-indent="40px" padding="2px" margin="0">
                            <fo:inline font-weight="bold">
                                <xsl:value-of select="text()"/>
                            </fo:inline>
                        </fo:block>
                    </xsl:for-each>
                    <fo:block font-family="Times" font-size="13px" padding="20px">
                        Да ли сте добровољни давалац крви:
                    </fo:block>
                    <xsl:choose>
                        <xsl:when test="//i:Dobrovoljni_davalac_krvi/text() = 'true'">
                            <fo:block font-family="Times" font-size="13px" text-indent="40px" padding="2px" margin="0">
                                <fo:inline>Да</fo:inline>
                            </fo:block>
                        </xsl:when>
                        <xsl:otherwise>
                            <fo:block font-family="Times" font-size="13px" text-indent="40px" padding="2px" margin="0">
                                <fo:inline>Не</fo:inline>
                            </fo:block>
                        </xsl:otherwise>
                    </xsl:choose>
                    <fo:block font-family="Times" font-size="13px" padding="20px">
                        Датум подношења интересовања:
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="//i:Datum"/>
                        </fo:inline>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>