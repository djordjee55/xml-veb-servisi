<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:p="http://www.xws.org/potvrda"
                xmlns:tip="http://www.xws.org/tipovi"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master
                        master-name="potvrda"
                        page-height="29.7cm"
                        page-width="21cm"
                        margin-top="1.3cm"
                        margin-bottom="2cm"
                        margin-left="1.3cm"
                        margin-right="1.3cm">
                    <fo:region-body/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="potvrda">

                <fo:flow flow-name="xsl-region-body">
                    <fo:block-container>
                        <fo:block-container width="30%" left="0in" top="0in" position="absolute" height="80px">
                            <fo:block>
                                <fo:external-graphic
                                        src="url('data:image/jpeg;base64,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')"/>
                            </fo:block>
                        </fo:block-container>
                        <fo:block-container width="70%" left="30%" top="0in" position="absolute">
                            <fo:block font-family="Times" font-size="14px" text-align="center"
                                      linefeed-treatment="preserve" margin="0">
                                ИНСТИТУТ ЗА ЈАВНО ЗДРАВЉЕ СРБИЈЕ&#xA;"Др Милан Јанковић Батут"
                                <fo:inline color="gray" font-weight="normal" linefeed-treatment="preserve"
                                           font-size="13px" margin-top="-10px">
                                    INSTITUT ZA JAVNO ZDRAVLJE SRBIJE&#xA;"Dr Milan Jankovic Batut"
                                    INSTITUTE OF PUBLIC HEALTH OF SERBIA&#xA;"Dr Milan Jankovic Batut"
                                </fo:inline>
                            </fo:block>
                        </fo:block-container>
                    </fo:block-container>
                    <fo:block font-family="Times" font-size="16px" text-align="center"
                              margin-top="180px">
                        ПОТВРДА О ИЗВРШЕНОЈ ВАКЦИНАЦИЈИ ПРОТИВ COVID-19
                    </fo:block>
                    <fo:block font-family="Times" font-size="13px" text-align="center" color="gray">
                        POTVRDA O IZVRSENOJ VAKCINACIJI
                        <fo:inline font-weight="bold">PROTIV COVID 19</fo:inline>
                    </fo:block>
                    <fo:block font-family="Times" font-size="13px" text-align="center" color="gray">
                        CONFIRMATION OF THE
                        <fo:inline font-weight="bold">COVID 19</fo:inline>
                        VACCINATION
                    </fo:block>
                    <fo:block font-family="Times" font-size="13px" padding="5px">
                        <fo:inline>Име и презиме:</fo:inline>
                        <fo:inline>
                            <xsl:value-of select="concat(' ', //tip:Ime, ' ', //tip:Prezime)"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="Times" color="gray" font-size="12px">Ime i prezime / First and Last
                        Name
                    </fo:block>
                    <fo:block font-family="Times" font-size="13px" padding="5px">
                        <fo:inline>Датум рођења:</fo:inline>
                        <fo:inline>
                            <xsl:value-of select="concat(' ', //tip:Datum_rodjenja)"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="Times" color="gray" font-size="12px">Datum rodjenja / Date Of Birth
                    </fo:block>
                    <fo:block font-family="Times" font-size="13px" padding="5px">
                        <fo:inline>Пол:</fo:inline>
                        <fo:inline>
                            <xsl:if test="//tip:Pol = 'M'">
                            Мушко
                        </xsl:if>
                        <xsl:if test="//tip:Pol = 'Z'">
                            Женско
                        </xsl:if>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="Times" color="gray" font-size="12px">
                        Pol:
                        <xsl:if test="//tip:Pol = 'M'">
                            Musko
                        </xsl:if>
                        <xsl:if test="//tip:Pol = 'Z'">
                            Zensko
                        </xsl:if>
                         /Gender:
                        <xsl:if test="//tip:Pol = 'M'">
                            Male
                        </xsl:if>
                        <xsl:if test="//tip:Pol = 'Z'">
                            Female
                        </xsl:if>
                    </fo:block>
                    <xsl:choose>
                        <xsl:when test="string-length(//tip:JMBG/text()) = 13">
                            <fo:block font-family="Times" font-size="13px" padding="5px">
                                <fo:inline>ЈМБГ:</fo:inline>
                                <fo:inline>
                                    <xsl:value-of select="concat(' ', //tip:JMBG)"/>
                                </fo:inline>
                            </fo:block>
                            <fo:block font-family="Times" color="gray" font-size="12px">JMBG / Personal No.
                            </fo:block>
                        </xsl:when>
                       
                    </xsl:choose>
                    <xsl:for-each select="//p:Doza">
                        <fo:block font-family="Times" font-size="13px" padding="5px">
                            <fo:inline>
                                Датум давања и број серије
                                <xsl:choose>
                                    <xsl:when test="p:Redni_broj = 1">прве</xsl:when>
                                    <xsl:otherwise>друге</xsl:otherwise>
                                </xsl:choose>
                                дозе вакцине:
                            </fo:inline>
                            <fo:inline>
                                <xsl:value-of select="concat(' ', p:Datum_davanja)"/>
                                , серија: 
                                <xsl:value-of select="concat(' ', p:Broj_serije)"/>
                            </fo:inline>
                        </fo:block>
                        <fo:block font-family="Times" color="gray" font-size="12px">Datum
                            <xsl:if test="p:Redni_broj = 2">druge</xsl:if>
                            vakcinacije /
                            <xsl:if test="p:Redni_broj = 2">Second</xsl:if>
                            Vaccination Date
                        </fo:block>
                        <fo:block font-family="Times" font-size="13px" padding="5px">
                        <fo:inline>Назив вакцине:</fo:inline>
                        <fo:inline>
                            <xsl:value-of select="concat(' ', p:Tip_vakcine)"/>
                        </fo:inline>
                    </fo:block>
                        <fo:block font-family="Times" color="gray" font-size="12px">Naziv vakcine / Name of vaccine
                    	</fo:block>
                    </xsl:for-each>
                    <fo:block font-family="Times" font-size="13px" padding="5px">
                        <fo:inline>Здравствена установа која вакцинише:</fo:inline>
                        <fo:inline>
                            <xsl:value-of select="concat(' ', p:Zdravstvena_ustanova)"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="Times" color="gray" font-size="12px">Zdravstvena ustanova koja vakcinise
                        / Health care institution of vaccination
                    </fo:block>
                
                    <fo:block font-family="Times" font-size="13px" padding="5px">
                        <fo:inline>Датум издавања потврде:</fo:inline>
                        <fo:inline>
                            <xsl:value-of select="concat(' ', //p:Datum_izdavanja)"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="Times" color="gray" font-size="12px">Datum izdavanja potvrde /
                        Confirmation Release Date
                    </fo:block>
                    <fo:block font-family="Times" font-size="13px" padding="5px"
                              text-align="right">
                        <fo:inline>Здравствена установа:</fo:inline>
                        <fo:inline>
                            <xsl:value-of select="concat(' ', //p:Zdravstvena_ustanova)"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="Times" color="gray" font-size="12px" text-align="right">Zdravstvena
                        ustanova / Medical institution
                    </fo:block>
                    <fo:block-container>
                        <fo:block-container width="75%" left="0in" top="0in" position="absolute">
                            <fo:block font-family="Times" font-size="11px" padding="5px" margin-top="90px">Ова потврда важи без потписа и печата
                            </fo:block>
                            <fo:block font-family="Times" color="gray" font-size="10px">
                                Ova potvrda vazi bez potpisa i pecata / This certification is valid without signatures
                                and seals
                            </fo:block>
                        </fo:block-container>
                        <fo:block-container width="25%" left="75%" top="0in" position="absolute">
                            <fo:block>
                                <xsl:variable name="qrcode" select="//p:Qr_kod/text()"/>
                                <fo:external-graphic src="url('data:image/jpeg;base64,{$qrcode}')"/>
                            </fo:block>
                        </fo:block-container>
                    </fo:block-container>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>