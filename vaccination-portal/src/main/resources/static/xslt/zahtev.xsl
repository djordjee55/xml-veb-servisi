<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:zahtev="http://www.xws.org/zahtev"
                xmlns:t="http://www.xws.org/tipovi"
                version="2.0">
    <xsl:template match="/">
        <html>
            <head>
                <title>Захтев за зелени сертификат</title>
                <style type="text/css">
                    body {
	                    margin: 0;
	                    padding: 0;
	                    font-family: "Times New Roman", sans-serif;
                    }
                    
                    h1 {
	                    text-align: center;
	                    font-weight: bold;
                    }
                    
                    .paragraph {
	                    margin-left: 10vw;
	                    margin-right: 10vw;
	                    font-size: 1.5em;
	                    text-align: justify;
                    }
                    
                    .potpis {
	                    border-top: 1px solid black;
	                    width: 25%;
	                    margin: 10px 0px 10vh 60%;
	                    text-align: center;
                    }
                  
                </style>
            </head>
            <body>
                <h1 style="margin-top: 10vh;">З А Х Т Е В</h1>
                <h2 style="text-align: center; margin-top: 0">за издавање дигиталног зеленог сертификата</h2>
                <div class="subtitle">
                	<p class="paragraph">
                    У складу са одредбом Републике Србије о издавању дигиталног зеленог
                    сертификата као потврде о извршеној вакцинацији против COVID-19, резултатима тестирања на
                    заразну болест SARS-CoV-2
                    или опоравку од болести COVID-19, подносим захтев за издавање дигиталног зеленог сертификата.
                	</p>
                </div>
                <p class="paragraph">Подносилац захтева:</p>
                <p class="paragraph">Име и презиме:
                    <b>
                        <xsl:value-of select="concat(' ', //t:Ime, ' ', //t:Prezime)"/>
                    </b>
                </p>
                <p class="paragraph">Датум рођења:
                    <b>
                        <xsl:value-of select="concat(' ', //t:Datum_rodjenja)"/>
                    </b>
                </p>
                <p class="paragraph">Пол:
                    <b>
                        <xsl:value-of select="concat(' ', //t:Pol)"/>
                    </b>
                </p>
                <xsl:choose>
                    <xsl:when test="string-length(//t:JMBG/text()) = 13">
                        <p class="paragraph">Јединствени матични број грађанина:
                            <b>
                                <xsl:value-of select="concat(' ', //t:JMBG)"/>
                            </b>
                        </p>
                    </xsl:when>
                    <xsl:otherwise>
                        <p class="paragraph">Број пасоша:
                            <b>
                                <xsl:value-of select="concat(' ', //t:JMBG)"/>
                            </b>
                        </p>
                    </xsl:otherwise>
                </xsl:choose>
                <p class="paragraph">Разлог за подношење захтева:</p>
                <p class="paragraph">
                    <xsl:value-of select="//zahtev:Razlog"/>
                </p>
                <p style="text-align: center; font-size: 1em;">(навести што прецизнији разлог за подношење захтева за издавање дигиталног пасоша)
                </p>
                <p class="paragraph">У
                    <b><xsl:value-of select="concat(' ', //zahtev:Mesto)"/>,
                    </b>
                </p>
                <p class="paragraph">
                    <b>
                        <xsl:value-of select="concat(' ', //zahtev:Datum)"/>
                    </b>
                </p>
                <p class="potpis">Потпис</p>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>