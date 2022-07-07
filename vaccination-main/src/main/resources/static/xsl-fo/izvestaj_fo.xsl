<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:i="http://www.xws.org/izvestaj"
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
                        Извештај о имунизацији
                    </fo:block>

                    <fo:block font-family="Times" font-size="13px" padding="10px">
                        Извештај се односи на период од
                        <fo:inline>
                            <xsl:value-of select="//i:Izvestaj/i:Period/i:Od"/>
                            до
                            <xsl:value-of select="//i:Izvestaj/i:Period/i:Do"/>.
                        </fo:inline>
                    </fo:block>

                    <fo:block font-family="Times" font-size="13px" padding="10px">
                        У напоменутом временском интервалу је:
                        <fo:block font-family="Times" font-size="13px" padding="10px">
                            - поднето
                            <fo:inline>
                                <xsl:value-of select="//i:Izvestaj/i:Broj_podnetih_dokumenata_o_interesovanju"/>
                                докумената о интересовању за имнуизацију;
                            </fo:inline>
                        </fo:block>
                        <fo:block font-family="Times" font-size="13px" padding="10px">
                            - примљено
                            <fo:inline>
                                <xsl:value-of
                                        select="//i:Izvestaj/i:Broj_primljenih_zahteva_za_digitalni_zeleni_sertifikat"/>
                                захтева за дигитални зелени сертификат, од којих је
                                <xsl:value-of
                                        select="//i:Izvestaj/i:Broj_izdatih_zahteva_za_digitalni_zeleni_sertifikat"/>
                                издато.
                            </fo:inline>
                        </fo:block>
                    </fo:block>

                    <fo:block font-family="Times" font-size="13px" padding="10px">
                        Дато је
                        <fo:inline>
                            <xsl:value-of select="//i:Izvestaj/i:Ukupan_broj_datih_doza_vakcine"/>
                            доза баквине против COVID-19 вируса у следећој количини:
                        </fo:inline>
                    </fo:block>

                    <fo:block font-size="10px">
                        <fo:table font-family="Times" margin="5px auto 5px auto">
                            <fo:table-column column-width="50%" border="1px solid darkgrey"/>
                            <fo:table-column column-width="50%" border="1px solid darkgrey"/>
                            <fo:table-body>
                            <fo:table-row border="1px solid darkgrey">
                                <fo:table-cell font-family="Times" padding="2px">
                                    <fo:block>Редни број дозе</fo:block>
                                </fo:table-cell>
                                <fo:table-cell font-family="Times" padding="2px">
                                    <fo:block>Број датих доза</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <xsl:for-each select="//i:Izvestaj/i:Doze/i:Doza">
                                <fo:table-row border="1px solid darkgrey">
                                    <fo:table-cell padding="2px">
                                        <fo:block>
                                            <xsl:value-of select="position()"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding="2px">
                                        <fo:block>
                                            <xsl:value-of select="i:Broj_datih_doza"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:for-each>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>

                    <fo:block font-family="Times" font-size="13px" padding="10px">
                        Расподела по произвођачима је:
                        <xsl:for-each select="//i:Izvestaj/i:Raspodela_po_proizvodjacima/i:Proizvodjac">
                            <fo:block font-family="Times" font-size="13px" padding="10px">
                                <xsl:value-of select="i:Naziv"/> -
                                <xsl:value-of select="i:Broj_doza"/> доза;
                            </fo:block>
                        </xsl:for-each>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
