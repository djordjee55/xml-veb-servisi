# XML i WEB servisi
Predmetni projekat iz predmeta xml i web servisi, 2021/2022 god.

## Autori:
  * Luka Kureljušić SW-23-2018
  * Đorđe Njegić SW-12-2018
  * Njegoš Blagojević SW-12-2018
  * Dejan Todorović SW-17-2018

## Opis projekta

Potrebno je bilo napraviti informacioni sistem koji ce pomoći zdravstvenim institucijama, kao i običnim gradđanima u bržem procesu imunizacije. 
Sastoji se od vise podsistema, konkretno postoje dve aplikacije.
Prvu koriste službenici dok drugu zdravstveni radnici i pacijenti.

## Tehnologije

Serverska (backend) strana, kako aplikacije za službenike tako i aplikacije za doktore i pacijente su odrađene korišćenjem Spring Boot-a.
Alat za build projekta koji je korišćen je maven.
Klijentska strana obe aplikacije je implementirana uz pomoć React-a. Kompletna komunikacija se odvija u okviru xml formata.
Xml dokumenta su se čuvala u okviru exist baze podataka dok su metapodaci u okviru fuseki baze.

## Pokretanje

Najpre je potrebno klonirati repozitorijum komandom ```git clone https://github.com/djordjee55/xml-veb-servisi ```.
Zatim je potrebno pokrenuti tomcat server i u okviru njega exist i fuseki bazu (najpre je potrebno instalirati exist i fuseki).
Nakon toga treba pokrenuti obe backend aplikacije da li kroz cmd ili uz pomoć nekog radnog okruženja.
I na kraju, neophodno je pokrenuti obe klijentske aplikacije. 
One se nalaze u posebnim repozitorijumima:
  * [Aplikacija za sluzbenike](https://github.com/Lule99/manager-front)
  * [Aplikacija za doktore/pacijente](https://github.com/Lule99/vaccination-users-front)
  
## Kredencijali
 
  * Službenik: username -> sluzbenik@gmail.com; password -> lozinka1
  * Doktor: username -> doktor@gmail.com; password -> lozinka1
