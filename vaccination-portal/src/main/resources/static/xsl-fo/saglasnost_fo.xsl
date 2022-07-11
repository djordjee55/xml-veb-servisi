<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:s="http://www.xws.org/saglasnost"
                xmlns:tip="http://www.xws.org/tipovi"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master
                        master-name="saglasnost"
                        page-height="29.7cm"
                        page-width="21cm"
                        margin-top="1.3cm"
                        margin-bottom="2cm"
                        margin-left="1.3cm"
                        margin-right="1.3cm">
                    <fo:region-body/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="saglasnost">

                <fo:flow flow-name="xsl-region-body">
                    <fo:block-container>
                        <fo:block-container width="66%" left="0in" top="0in" position="absolute">
                            <fo:block font-family="Times" font-size="19px">
                                САГЛАСНОСТ ЗА СПРОВОЂЕЊЕ
                                ПРЕПОРУЧЕНЕ ИМУНИЗАЦИЈЕ
                            </fo:block>
                        </fo:block-container>
                        <fo:block-container width="34%" left="66%" top="0in" position="absolute">
                            <fo:block text-align="right">
                                <fo:external-graphic
                                        src="url('data:image/jpeg;base64,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')"/>
                            </fo:block>
                        </fo:block-container>
                    </fo:block-container>
                    <fo:block font-family="Times" font-size="13px" margin-top="45px">
                        (попунио пацијент)
                    </fo:block>
                    <fo:block font-family="Times" font-size="12px" padding="5px" margin-top="10px">
                        <fo:inline>
                            Држављанство
                        </fo:inline>
                        <fo:inline>
                            1) Република Србија | ЈМБГ
                        </fo:inline>
                        <xsl:if test="//s:Drzavljanstvo/s:JMBG">
                            <fo:inline font-weight="bold">
                                <xsl:value-of select="concat(' ', //s:Drzavljanstvo/s:JMBG)"/>
                            </fo:inline>
                        </xsl:if>
                    </fo:block>
                    <fo:block font-family="Times" font-size="12px" padding="5px" text-indent="85px">
                        <fo:inline>
                            2) Назив страног држављанства
                        </fo:inline>
                        <xsl:if test="//s:Drzavljanstvo/s:Naziv_stranog_drzavljanstva">
                            <fo:inline font-weight="bold">
                                <xsl:value-of
                                        select="concat(' ', //s:Drzavljanstvo/s:Naziv_stranog_drzavljanstva, ' ')"/>
                            </fo:inline>
                        </xsl:if>
                        | Број пасоша или ЕБС
                        <xsl:if test="//s:Drzavljanstvo/s:EBS">
                            <fo:inline font-weight="bold">
                                <xsl:value-of select="concat(' ', //s:Drzavljanstvo/s:EBS, ' ')"/>
                            </fo:inline>
                        </xsl:if>
                        <xsl:if test="//s:Drzavljanstvo/s:Broj_pasosa">
                            <fo:inline font-weight="bold">
                                <xsl:value-of select="concat(' ', //s:Drzavljanstvo/s:Broj_pasosa, ' ')"/>
                            </fo:inline>
                        </xsl:if>
                    </fo:block>
                    <fo:block font-family="Times" font-size="12px" padding="5px">
                        <fo:inline>Презиме</fo:inline>
                        <fo:inline>
                            <xsl:value-of select="concat(' ', //tip:Prezime, ' | ')"/>
                        </fo:inline>
                        <fo:inline>Име</fo:inline>
                        <fo:inline>
                            <xsl:value-of select="concat(' ', //tip:Ime, ' | ')"/>
                        </fo:inline>
                        <fo:inline>Име родитеља</fo:inline>
                        <fo:inline>
                            <xsl:value-of select="concat(' ', //s:Ime_roditelja)"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="Times" font-size="12px" padding="5px">
                        <fo:inline>Пол:</fo:inline>
                        <fo:inline>
                            <xsl:value-of select="concat(' ', //s:Pol, ' | ')"/>
                        </fo:inline>
                        <fo:inline>Датум рођења</fo:inline>
                        <fo:inline>
                            <xsl:value-of select="concat(' ', //tip:Datum_rodjenja, ' | ')"/>
                        </fo:inline>
                        <fo:inline>Место рођења</fo:inline>
                        <fo:inline>
                            <xsl:value-of select="concat(' ', //s:Mesto_rodjenja)"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="Times" font-size="12px" padding="5px">
                        <fo:inline>Адреса (улица и број)</fo:inline>
                        <fo:inline>
                            <xsl:value-of select="concat(' ', //s:Adresa/s:Ulica, ' ', //s:Adresa/s:Broj, ' | ')"/>
                        </fo:inline>
                        <fo:inline>Место/Насеље</fo:inline>
                        <fo:inline>
                            <xsl:value-of select="concat(' ', //s:Adresa/s:Naselje)"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="Times" font-size="12px" padding="5px">
                        <fo:inline>Општина/Град</fo:inline>
                        <fo:inline>
                            <xsl:value-of select="concat(' ', //s:Adresa/s:Opstina, ' | ')"/>
                        </fo:inline>
                        <fo:inline>Тел. фиксни</fo:inline>
                        <fo:inline>
                            <xsl:value-of select="concat(' ', //s:Kontakt/tip:Fiksni_telefon)"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="Times" font-size="12px" padding="5px">
                        <fo:inline>Тел. мобилни</fo:inline>
                        <fo:inline>
                            <xsl:value-of select="concat(' ', //s:Kontakt/tip:Mobilni_telefon, ' | ')"/>
                        </fo:inline>
                        <fo:inline>имејл</fo:inline>
                        <fo:inline>
                            <xsl:value-of select="concat(' ', //s:Kontakt/tip:E_mail)"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="Times" font-size="12px" padding="5px">
                        <fo:inline>Радни статус:</fo:inline>
                        <fo:inline>
                            <xsl:if test="//s:Radni_status/s:Zaposlen">
                                запослен
                            </xsl:if>
                            <xsl:if test="//s:Radni_status/s:Nezaposlen">
                                незапослен
                            </xsl:if>
                            <xsl:if test="//s:Radni_status/s:Penzioner">
                                пензионер
                            </xsl:if>
                            <xsl:if test="//s:Radni_status/s:Ucenik">
                                ученик
                            </xsl:if>
                            <xsl:if test="//s:Radni_status/s:Student">
                                студент
                            </xsl:if>
                            <xsl:if test="//s:Radni_status/s:Dete">
                                дете
                            </xsl:if>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="Times" font-size="12px" padding="5px">
                        <xsl:if test="//s:Radni_status/s:Zaposlen">
                            <fo:inline>Занимање запосленог:</fo:inline>
                            <fo:inline>
                                <xsl:if test="//s:Radni_status/s:Zaposlen/s:Zdravstvena_zastita">
                                    здравствена заштита
                                </xsl:if>
                                <xsl:if test="//s:Radni_status/s:Zaposlen/s:Socijalna_zastita">
                                    социјална заштита
                                </xsl:if>
                                <xsl:if test="//s:Radni_status/s:Zaposlen/s:Prosveta">
                                    просвета
                                </xsl:if>
                                <xsl:if test="//s:Radni_status/s:Zaposlen/s:MUP">
                                    МУП
                                </xsl:if>
                                <xsl:if test="//s:Radni_status/s:Zaposlen/s:Vojska_RS">
                                    Војска РС
                                </xsl:if>
                                <xsl:if test="//s:Radni_status/s:Zaposlen/s:Drugo">
                                    друго
                                </xsl:if>
                            </fo:inline>
                        </xsl:if>
                    </fo:block>
                    <fo:block font-family="Times" font-size="12px" padding="5px">
                        <fo:inline>Корисник установе соц. зашт.</fo:inline>
                        <xsl:choose>
                            <xsl:when test="//s:Korisnik_ustanove_socijalne_zasttite">
                                <fo:inline>Да</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                                <fo:inline>Не</fo:inline>
                            </xsl:otherwise>
                        </xsl:choose>
                        <xsl:if test="//s:Korisnik_ustanove_socijalne_zasttite">
                            <fo:inline>
                                | Назив и општина седишта
                            </fo:inline>
                            <fo:inline>
                                <xsl:value-of select="concat(' ', //s:Korisnik_ustanove_socijalne_zasttite/s:Naziv, ' ', //s:Korisnik_ustanove_socijalne_zasttite/s:Opstina)"/>
                            </fo:inline>
                        </xsl:if>
                    </fo:block>
                    <fo:block font-family="Times" font-size="12px" padding="5px">
                        <fo:inline>Изјављујем да: САГЛАСАН САМ </fo:inline>
                        <fo:inline>
                            са спровођењем активне/пасивне имунизације (назив имунолошког лека):
                            <xsl:value-of select="concat(' ', //s:Saglasnost//s:Naziv_imunoloskog_leka)"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="Times" font-size="12px" padding="5px">
                        Лекар ми је објаснио предности и ризике од спровођења активне/пасивне имунизације наведеним
                        имунолошким леком.
                    </fo:block>
                    <fo:block font-family="Times" font-size="5px" padding="5px">
                        ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
                        ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
                        ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
                        ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
                    </fo:block>
                    <xsl:if test="//s:Evidencija_o_vakcinaciji">
                        <fo:block font-family="Times" font-size="16px" padding="5px" text-align="center">
                            ЕВИДЕНЦИЈА О ВАКЦИНАЦИЈИ ПРОТИВ COVID-19
                        </fo:block>
                        <fo:block font-family="Times" font-size="12px" margin-bottom="5px" text-align="center">
                            (попуњава здравствени радник)
                        </fo:block>
                        <fo:block font-family="Times" font-size="12px" margin-bottom="5px">
                            Здравствена установа
                            <fo:inline font-weight="bold">
                                <xsl:value-of select="concat(' ', //s:Evidencija_o_vakcinaciji/s:Zdravstvena_ustanova, ' ')"/>
                            </fo:inline>
                            Вакцинацијски пункт
                            <fo:inline font-weight="bold">
                                <xsl:value-of select="concat(' ', //s:Evidencija_o_vakcinaciji/s:Vakcinacijski_punkt)"/>
                            </fo:inline>
                        </fo:block>
                        <fo:block font-family="Times" font-size="12px" margin-bottom="5px">
                            Име, презиме, факсимил и бр. телефона лекара:
                            <fo:inline font-weight="bold">
                                <xsl:value-of
                                        select="concat(' ', //s:Evidencija_o_vakcinaciji/s:Lekar/s:Ime, ', ', //s:Evidencija_o_vakcinaciji/s:Lekar/s:Prezime, ', ', //s:Evidencija_o_vakcinaciji/s:Lekar/s:Broj_telefona)"/>
                            </fo:inline>
                        </fo:block>
                        <fo:block font-family="Times" font-size="12px" margin-bottom="5px">
                            Пре давања вакцине прегледати особу и упознати је са користима и могућим нежељеним
                            реакцијама после вакцинације. Обавезно уписати сваку дату
                            вакцину и све тражене податке у овај образац и податке унети у лични картон о извршеним
                            имунизацијама и здравствени картон.
                        </fo:block>
                        <fo:block font-size="10px">
                            <fo:table font-family="Times" margin="5px auto 5px auto">
                                <fo:table-column column-width="18%" border="1px solid darkgrey"/>
                                <fo:table-column column-width="17%" border="1px solid darkgrey"/>
                                <fo:table-column column-width="8%" border="1px solid darkgrey"/>
                                <fo:table-column column-width="12%" border="1px solid darkgrey"/>
                                <fo:table-column column-width="10%" border="1px solid darkgrey"/>
                                <fo:table-column column-width="13%" border="1px solid darkgrey"/>
                                <fo:table-column column-width="15%" border="1px solid darkgrey"/>
                                <fo:table-column column-width="7%" border="1px solid darkgrey"/>
                                <fo:table-body>
                                    <fo:table-row border="1px solid darkgrey">
                                        <fo:table-cell font-family="Times" padding="2px">
                                            <fo:block>Назив вакцине</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-family="Times" padding="2px">
                                            <fo:block>Датум давања вакцине (V1 i V2)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-family="Times" padding="2px">
                                            <fo:block>Налин давања вакцине</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-family="Times" padding="2px">
                                            <fo:block>Екстремитет</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-family="Times" padding="2px">
                                            <fo:block>SСерија вакцине (лот)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-family="Times" padding="2px">
                                            <fo:block>Произвођач</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-family="Times" padding="2px">
                                            <fo:block>Нежељена реакција</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <xsl:for-each select="//s:Evidencija_o_vakcinaciji/s:Vakcine/s:Vakcina">
                                        <fo:table-row border="1px solid darkgrey">
                                            <fo:table-cell padding="2px">
                                                <fo:block>
                                                    <xsl:value-of select="s:Naziv"/>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell padding="2px">
                                                <fo:block>
                                                    <xsl:value-of select="s:Datum_davanja"/>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell padding="2px">
                                                <fo:block>
                                                    <xsl:value-of select="s:Nacin_davanja"/>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell padding="2px">
                                                <fo:block>
                                                    <xsl:choose>
                                                        <xsl:when test="s:Ekstremitet/s:LR">
                                                            ЛР
                                                        </xsl:when>
                                                        <xsl:otherwise>
                                                            ДР
                                                        </xsl:otherwise>
                                                    </xsl:choose>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell padding="2px">
                                                <fo:block>
                                                    <xsl:value-of select="s:Serija"/>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell padding="2px">
                                                <fo:block>
                                                    <xsl:value-of select="s:Proizvodjac"/>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell padding="2px">
                                                <fo:block>
                                                    <xsl:value-of select="s:Nezeljena_reakcija"/>
                                                </fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </xsl:for-each>
                                    <fo:table-row>
                                        <fo:table-cell number-columns-spanned="7">
                                            <fo:block>
                                                Привремене контрадикције (датум утврђивања и дијагноза):
                                                <xsl:if test="//s:Privremene_kontraindikacije/s:Privremena_kontraindikacija">
                                                    <xsl:for-each select="//s:Evidencija_o_vakcinaciji/s:Privremene_kontraindikacije/s:Privremena_kontraindikacija">
                                                        <fo:inline>
                                                            <xsl:value-of select="s:Dijagnoza"/>
                                                            (<xsl:value-of select="s:Datum_utvrdjivanja"/>)
                                                        </fo:inline>
                                                    </xsl:for-each>
                                                </xsl:if>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell number-columns-spanned="7">
                                            <fo:block>
                                                Одлука комисије за трајне контрадикције (ако постоји, уписати Да)
                                                <xsl:if test="//s:Trajne_kontraindikacije">
                                                    <fo:inline>Да</fo:inline>
                                                </xsl:if>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>
                        <fo:block font-family="Times" font-size="12px" margin-bottom="5px">
                            <fo:inline>Напомена:</fo:inline>
                            Образац се чува као део медицинске документације пацијента.
                        </fo:block>
                    </xsl:if>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
