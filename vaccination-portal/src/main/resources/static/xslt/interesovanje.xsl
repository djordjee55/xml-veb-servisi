<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:i="http://www.xws.org/interesovanje"
                xmlns:tip="http://www.xws.org/tipovi" version="2.0">
    <xsl:template match="/">
        <html>
            <head>
                <title>Исказивање интересовања за вакцинисање против COVID-19</title>
                <style type="text/css">
                    body {
                    margin: 0;
                    font-family: Arial, sans-serif;
                    overflow-x: hidden;
                    }
                    h1 {
                    text-align: center;
                    padding: 20px;
                    font-weight: bold;
                    }
                    .indent-vaccine {
                    margin: 2px 0px 0px 20vw;
                    margin-left: 20vw;
                    font-size: 1.3em;
                    padding: 0px;
                    }
                    .indent-paragraph {
                    margin-left: 10vw;
                    font-size: 1.5em;
                    }
                </style>
            </head>
            <body>
                <h1 style="margin-top: 10vh;">Исказивање интересовања за вакцинисање против COVID-19</h1>
                <xsl:if test="//i:Drzavljanin_Republike_Srbije">
                    <p class="indent-paragraph">Држављанство:
                        <b>Држављанин Републике Србије</b>
                    </p>
                </xsl:if>
                <xsl:if test="//i:Strani_drzavljanin_sa_boravkom_u_RS">
                    <p class="indent-paragraph">Држављанство:
                        <b>Страни држављанин са боравком у РС</b>
                    </p>
                </xsl:if>
                <xsl:if test="//i:Strani_drzavljanin_bez_boravka_u_RS">
                    <p class="indent-paragraph">Држављанство:
                        <b>Страни држављанин без боравка у РС</b>
                    </p>
                </xsl:if>
                <xsl:choose>
                    <xsl:when test="string-length(//tip:JMBG/text()) = 13">
                        <p class="indent-paragraph">ЈМБГ:
                            <b>
                                <xsl:value-of select="concat(' ', //tip:JMBG)"/>
                            </b>
                        </p>
                    </xsl:when>
                    <xsl:otherwise>
                        <p class="indent-paragraph">Број пасоша:
                            <b>
                                <xsl:value-of select="concat(' ', //tip:JMBG)"/>
                            </b>
                        </p>
                    </xsl:otherwise>
                </xsl:choose>
                <p class="indent-paragraph">Име:
                    <b>
                        <xsl:value-of select="//i:Primalac/tip:Ime"/>
                    </b>
                </p>
                    <p class="indent-paragraph">Презиме:
                        <b>
                            <xsl:value-of select="//i:Primalac/tip:Prezime"/>
                        </b>
                    </p>
                <p class="indent-paragraph">Адреса електорнске поште:
                    <b>
                        <xsl:value-of select="//i:Primalac/tip:Kontakt/tip:E_mail"/>
                    </b>
                </p>
                <p class="indent-paragraph">Број мобилног телефона:
                    <b>
                        <xsl:value-of select="//i:Primalac/tip:Kontakt/tip:Mobilni_telefon"/>
                    </b>
                </p>
                <p class="indent-paragraph">Број фиксног телефона:
                    <b>
                        <xsl:value-of select="//i:Primalac/tip:Kontakt/tip:Fiksni_telefon"/>
                    </b>
                </p>
                <p class="indent-paragraph">Локација (општина) у којој желите да примите вакцину:
                    <b>
                        <xsl:value-of select="//i:Zeljena_opstina_vakcinacije"/>
                    </b>
                </p>
                <p class="indent-paragraph" style="margin-right: 10vw;">
                    Исказујем интересовађње да примим искључиво вакцину следећих произвођача за који Агенција
                    за лекове и медицинска средства поврди безбедност, ефиасност и квалитет и изда дозволу за употребу
                    лека:
                </p>
                <xsl:for-each select="//i:Zeljena_vakcina/*">
                    <p class="indent-vaccine">
                        <b>
                            <xsl:value-of select="text()"/>
                        </b>
                    </p>
                </xsl:for-each>
                <p class="indent-paragraph">Да ли сте добровољни давалац крви:</p>
                <xsl:choose>
                    <xsl:when test="//i:Dobrovoljni_davalac_krvi/text() = 'true'">
                        <p class="indent-vaccine">
                            <b>Да</b>
                        </p>
                    </xsl:when>
                    <xsl:otherwise>
                        <p class="indent-vaccine">
                            <b>Не</b>
                        </p>
                    </xsl:otherwise>
                </xsl:choose>
                <p class="indent-paragraph">Датум подношења интересовања:
                    <b>
                        <xsl:value-of select="//i:Datum"/>
                    </b>
                </p>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>