<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:i="http://www.xws.org/izvestaj"
                version="2.0">
    <xsl:template match="/">
        <html>
            <head>
                <title>Извештај о имунизацији</title>
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
                    .flex-row-between {
                    display: flex;
                    flex-direction: row;
                    align-items: center;
                    justify-content: space-between;
                    }
                    table, th, td {
                    border: 1px solid grey;
                    border-collapse: collapse;
                    }
                    th, td {
                    padding: 5px;
                    text-align: center;
                    }
                </style>
            </head>
            <body>
                <h1 style="margin-top: 10vh;">Извештај о имунизацији</h1>
                <p class="indent-paragraph" style="margin-top: 10vh;">Извештај се односи на период од
                    <b>
                        <xsl:value-of select="//i:Izvestaj/i:Period/i:Od"/>
                    </b>
                    до
                    <b>
                        <xsl:value-of select="//i:Izvestaj/i:Period/i:Do"/>.
                    </b>
                </p>
                <p class="indent-paragraph" style="margin-top: 10vh;">
                    У напоменутом временском интервалу је:
                    <p class="indent-paragraph" style="margin-left: 20vw;">
                        - поднето
                        <b>
                            <xsl:value-of select="//i:Izvestaj/i:Broj_podnetih_dokumenata_o_interesovanju"/>
                        </b>
                        докумената о интересовању за имнуизацију;
                    </p>
                    <p class="indent-paragraph" style="margin-left: 20vw;">
                        - примљено
                        <b>
                            <xsl:value-of
                                    select="//i:Izvestaj/i:Broj_primljenih_zahteva_za_digitalni_zeleni_sertifikat"/>
                        </b>
                        захтева за дигитални зелени сертификат, од којих је
                        <b>
                            <xsl:value-of select="//i:Izvestaj/i:Broj_izdatih_zahteva_za_digitalni_zeleni_sertifikat"/>
                        </b>
                        издато.
                    </p>
                </p>
                <p class="indent-paragraph" style="margin-top: 10vh;">
                    Дато је
                    <b>
                        <xsl:value-of select="//i:Izvestaj/i:Ukupan_broj_datih_doza_vakcine"/>
                    </b>
                    доза баквине против COVID-19 вируса у следећој количини:
                </p>
                <table style="width: 80vw; margin: 20px 10vw 20px 10vw;">
                    <tr>
                        <td>
                            <b>Редни број дозе</b>
                        </td>
                        <td>
                            <b>Број датих доза</b>
                        </td>
                    </tr>
                    <xsl:for-each select="//i:Izvestaj/i:Doze/i:Doza">
                        <tr>
                            <td>
                                <xsl:value-of select="position()"/>
                            </td>
                            <td>
                                <xsl:value-of select="i:Broj_datih_doza"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
                <p class="indent-paragraph" style="margin-top: 10vh;">
                    Расподела по произвођачима је:
                    <xsl:for-each select="//i:Izvestaj/i:Raspodela_po_proizvodjacima/i:Proizvodjac">
                        <p class="indent-paragraph" style="margin-left: 20vw;">
                            <b>
                                <xsl:value-of select="i:Naziv"/> - <xsl:value-of select="i:Broj_doza"/>  доза;
                            </b>
                        </p>
                    </xsl:for-each>
                </p>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>