# 1 Analýza súčasného stavu

## 1.1 Súťažné mechaniky vo videohrách

### 1.1.1 Definícia súťaživosti
Súťažný aspekt vo videohrách sa nezakladá len na priamom strete hráčov, ale prejavuje sa aj v snahe používateľa dosahovať stanovené ciele, prekonávať herné výzvy a zlepšovať vlastné výsledky. Súťaženie môže prebiehať tak v interakcii s herným prostredím a so samým sebou (vnútorné súťaženie), ako aj medzi viacerými používateľmi prostredníctvom priameho alebo nepriameho súperenia. Jeho prejavmi môžu byť plnenie herných pokrokov, prekonávanie obmedzení sandboxového prostredia, porovnávanie efektivity prechádzania hry (speedrunning) a boj o herné zdroje. Empirické výskumy ukazujú, že integrácia súťažných a kooperatívnych mechaník výrazne zvyšuje angažovanosť používateľov a úroveň nimi vynakladaného úsilia v porovnaní s izolovaným hraním pre jedného hráča bez stanovených metrík úspechu (Novak et al., 2014).

Kľúčovou hnacou silou každého súťažného procesu je herná motivácia. Podľa teórie sebaurčenia (Self-Determination Theory) závisí herná motivácia v kontexte videohier vo veľkej miere od uspokojenia troch základných psychologických potrieb: kompetencie (pocit vlastného majstrovstva), autonómie (sloboda voľby a kontroly nad hernými akciami) a spriaznenosti (pocit sociálneho prepojenia s ostatnými hráčmi) (Ryan et al., 2006).

Pre pochopenie rozmanitosti herných motivácií je vhodné obrátiť sa na klasickú taxonómiu typov hráčov, ktorú navrhol Richard Bartle. Táto klasifikácia vyčleňuje štyri základné typy hráčov v závislosti od ich preferovaného štýlu interakcie s herným svetom a ostatnými účastníkmi (Bartle, 1996; Hodges a Buckley, 2018):
- Dosahovatelia (Achievers) sú zameraní na budovanie statusu a rýchle získavanie herných odmien. Súťaženie pre nich spočíva v skompletizovaní 100 % obsahu (splnenie všetkých pokrokov, maximalizácia úrovne alebo štatistík).
- Prieskumníci (Explorers) sú motivovaní spoznávaním mechaník, hľadaním tajomstiev a vzácnych zdrojov. Ich súťažný prvok sa prejavuje v snahe objaviť skryté možnosti alebo získať nedostatkové artefakty ako prví.
- Socializátori (Socializers) nachádzajú potešenie v interakcii s inými ľuďmi. Súťažné aspekty sú pre nich sekundárne a častejšie sa realizujú prostredníctvom kooperatívnej tímovej hry, kde je dôležitý spoločný zážitok.
- Zabijaci (Killers) sú orientovaní na dominanciu nad ostatnými účastníkmi. Ich hlavným záujmom je priamy bojový stret, lov náročných bossov alebo iných hráčov (PvP).

Je potrebné poznamenať, že motivačné profily hráčov sa môžu prelínať a meniť v závislosti od herného kontextu. Komplexnejšia neurobiologická typológia hráčov BrainHex (Nacke et al., 2014) rozširuje klasifikáciu na sedem archetypov, pričom sa opiera o stimuláciu konkrétnych neurotransmiterov. Medzi nimi je pre žáner sandboxových hier obzvlášť zaujímavý archetyp „Preživšieho“ (Survivor), ktorého motivácia je postavená na získavaní intenzívnych zážitkov z prekonávania strachu a úniku pred silnejšími virtuálnymi hrozbami.

Táto biologická potreba priamo korešponduje so základným vektorom psychologického stimulu: motiváciou dosahovania („K“) a motiváciou vyhýbania sa („OD“). Ako potvrdzujú výskumy v oblasti kognitívnej psychológie, motivácia vyhýbania sa má na psychiku silnejší vplyv... (ďalej pokračuje váš text o Baumeister et al., 2001).

### 1.1.2 Priame a nepriame súperenie
V teórii herného dizajnu a návrhu interaktívnych systémov sa súťažná interakcia tradične rozdeľuje na dva formáty: priame a nepriame súperenie (Schmidt-Kraepelin et al., 2018).

Priame súperenie (Direct Competition) sa vyznačuje otvoreným stretom, pri ktorom používatelia plnia tú istú úlohu, priamo si porovnávajú svoju efektivitu a akcie jedného účastníka môžu fyzicky alebo virtuálne blokovať, ničiť či spomaľovať postup druhého (Schmidt-Kraepelin et al., 2018). V klasických e-športových disciplínach (ako sú Dota 2, Valorant, Counter-Strike 2) tvorí jadro hrateľnosti práve priama symetrická konfrontácia, kde jediným cieľom je víťazstvo nad súperom v rámci obmedzenej hernej relácie vďakaiprevahy v reakciách a taktike.

Súťažné prvky však presahujú rámec priameho bojového konfliktu. Nepriame súperenie (Indirect Competition) sa formuje aj v izolovaných alebo kooperatívnych projektoch. V tomto prípade si hráči nespôsobujú priame poškodenie, ale súťažia v globálnej efektivite prostredníctvom neosobných alebo externých metrík (Schmidt-Kraepelin et al., 2018). Nástrojmi takéhoto nepriameho súťaženia sú systémy herných pokrokov, globálne tabuľky štatistík, ekonomické preteky o neobnoviteľné zdroje, ako aj kultúra rýchlostného prechádzania hier (speedrunning).

Implementácia oboch foriem súťaživosti má preukázateľne pozitívny vplyv na angažovanosť a rýchlosť učenia sa hráčov, čo ich vedie k investovaniu väčšieho množstva času do osvojovania si herných mechaník (Cagiltay et al., 2015). Nepriame metriky úspechu sú obzvlášť dôležité, pretože priamo uspokojujú základnú psychologickú potrebu kompetencie: získavanie herných pokrokov a postup v tabuľkách štatistík poskytujú hráčovi jasnú spätnú väzbu o jeho majstrovstve (Sailer et al., 2017). Tieto mechanizmy priamo uspokojujú potreby viacerých motivačných psychotypov súčasne (Kahn et al., 2015): tak výrazných „Súperov“ (Competitors), ktorí sa usilujú o popredné priečky v rebríčkoch, ako aj „Perfekcionistov“ (Completionists), motivovaných skompletizovaním 100 % obsahu na čas.

### 1.1.3 Vzájomný vzťah medzi PvP a PvE
Herný proces sa podľa smerovania konfliktu fundamentálne delí na dve osi: PvP (Player versus Player — hráč proti hráčovi) a PvE (Player versus Environment — hráč proti prostrediu).

PvP predstavuje priamu konfrontáciu medzi živými používateľmi, zatiaľ čo PvE sa zameriava na prekonávanie umelej inteligencie, monštier, pascí a výziev samotného herného prostredia. Je dôležité poznamenať, že PvE samo o sebe nemusí byť nevyhnutne súťažným prvkom. Súťaženie v PvE prostredí vzniká nepriamo, keď si hráči konkurujú v rýchlosti prechádzania hry (speedrunning), monopolizácii vzácneho zdroja alebo v efektivite plnenia úloh.

V kontexte komplexných sandboxových hier a hier žánru „prežitie“ (survival) tieto dva aspekty neexistujú izolovane — tvoria úzke symbiotické prepojenie. PvE formuje základný odpor sveta (hrozba hladu, teplotný režim, agresívna fauna), čím núti hráčov vynakladať čas a zdroje na adaptáciu. Prítomnosť PvP hrozby zas robí PvE prvky výrazne napínavejšími. Hráči sú nútení nielen bojovať medzi sebou, ale aj využívať agresívne prostredie ako taktickú výhodu (napríklad vlákaním protivníka do nebezpečného biómu). PvE tak udáva ekonomické tempo a základnú hodnotu života postavy, zatiaľ čo PvP vystupuje ako nepredvídateľný katalyzátor, ktorý hráčom nedovoľuje poľaviť v ostražitosti ani po osvojení si základných mechaník prežitia.

### 1.1.4 Symetrický a asymetrický herný dizajn
Rovnováha štartovacích podmienok v hrách pre viacerých hráčov určuje vektor vývoja hrateľnosti.

Symetrická hrateľnosť predpokladá, že všetci hráči začínajú v identických podmienkach s rovnakou sadou základných štatistík, pravidiel a mechaník. Takýto formát je výrazne jednoduchší na technickú implementáciu a matematické vyvažovanie, avšak môže obmedzovať rozmanitosť herných rolí a viesť k stagnácii taktických rozhodnutí.

Naopak, asymetrická hrateľnosť poskytuje hráčom rôzne štartovacie možnosti, unikátne pravidlá interakcie so svetom a odlišné charakteristiky (Neto et al., 2022). V odbornej literatúre sa rozlišuje „asymetria schopností“ (ability asymmetry) a „asymetria výziev“ (challenge asymmetry), ktoré zabezpečujú vysokú znovuhrateľnosť a variabilný používateľský zážitok (Neto et al., 2022). Asymetrické systémy však patria medzi najzložitejšie z hľadiska návrhu: vývojár musí zaručiť, aby žiadna z unikátnych sád charakteristík nevytvárala dominantnú stratégiu (dominant strategy), čo si vyžaduje zložité modelovanie a jemné ladenie systému bŕzd a protiváh (Beau a Bakkes, 2016; Rupp a Eckert, 2024).

### 1.1.5 Horizontálna a vertikálna progresia
Rozvoj postavy (progresia) je najdôležitejším stimulom súťažného prostredia, ktorý sa v teórii videohier buduje podľa jedného z dvoch vektorov.

Vertikálna progresia sa prejavuje priamym lineárnym rastom číselných ukazovateľov hráča (zvyšovanie zásoby zdravia, základného poškodenia, ukazovateľov obrany). Výskumy systémov odmeňovania ukazujú, že nadmerný dôraz na externé číselné odmeny presúva pozornosť hráča z radosti zo samotného procesu na povinné monotónne získavanie zdrojov — tzv. grind (Cruz et al., 2017). Vertikálny rast štatistík si od vývojárov vyžaduje neustále zvyšovanie náročnosti obsahu (napríklad implementáciu protivníkov s obrovskou zásobou zdravia), kde aj najmenšia matematická chyba môže viesť k narušeniu hernej rovnováhy (Jeon et al., 2024).

Horizontálna progresia naopak vylučuje exponenciálny rast číselných hodnôt. Namiesto toho, aby lineárne zvyšovala silu postavy v číselnom vyjadrení, rozširuje spektrum jej taktických možností a nástrojov. Horizontálny rozvoj sprístupňuje nové mechaniky a alternatívne spôsoby interakcie so svetom (sidegrades). Takýto prístup pomáha predchádzať inflácii sily (power creep) a udržiava dlhodobý prieskumný záujem, pretože núti hráča adaptovať sa na situácie pomocou variabilnej taktiky, a nie len prostredníctvom číselnej prevahy (Tyni et al., 2023).

## 1.2 Súťaženie v hre Minecraft

### 1.2.1 Špecifiká hry a relevantné vlastnosti pre súťaženie
Minecraft predstavuje unikátny voxelový sandbox s otvoreným svetom, ktorý spája prvky prežitia, stavania, inžinierstva a dobrodružstva. Kľúčovou vlastnosťou hry je skutočnosť, že používateľovi nevnucuje jediný povinný lineárny cieľ. Tradičné meradlá úspechu sú tu neostré a víťazstvo nie je striktne definované herným enginom. V dôsledku absencie pevných pravidiel sa súťažný aspekt v hre Minecraft prejavuje mnohostranne a často je formovaný samotnými hráčmi prostredníctvom nepriamych metrík. Výraznými príkladmi takéhoto emergentného súťaženia v rámci základnej hry (Vanilla) sú rýchlostné prechádzanie hry (speedrunning — zabitie Ender draka na čas), nepísané preteky v získaní všetkých herných pokrokov (advancements), architektonické súťaže na serveroch, ako aj boj o monopolizáciu vzácnych zdrojov. Tieto formáty dokazujú, že súťaženie v sandboxovom prostredí presahuje rámec priameho boja a zahŕňa aj ekonomickú a kreatívnu sféru.

Z hľadiska herného dizajnu sa základná architektúra hry Minecraft opiera o princíp multifunkčnosti a horizontálneho rozvoja (Bergensten, 2019). Akýkoľvek pokus o zavedenie vertikálnej progresie (napríklad nadmerné posilnenie štatistík postavy) vedie k deštrukcii rovnováhy, pretože v hre chýba mechanizmus dynamického prispôsobovania náročnosti prostredia (Yannakakis a Togelius, 2018). Nekontrolovaný rast sily postavy robí základné hrozby sandboxu (lávu, monštrá, pády) irelevantnými. Jedným z možných spôsobov prehĺbenia súťažného prostredia v hre Minecraft je preto využitie horizontálnej progresie v kombinácii s asymetrickým dizajnom (Neto et al., 2022).

Ďalším faktorom ovplyvňujúcim súťažný potenciál je fenomén nadmernej kazualizácie, ktorý je príznačný pre súčasnú etapu vývoja hry. Znižovanie vstupného prahu a minimalizácia trestov za chyby (Juul, 2010; Alexander et al., 2013) vedú k tomu, že hráči strácajú stav ponorenia, tzv. flow (Arzate Cruz a Ramirez Uresti, 2017). Podrobná analýza vplyvu hyperkazualizácie, ako aj historickej evolúcie herného dizajnu Minecraftu (od nekompromisnej vízie Markusa Perssona až po súčasné zameranie spoločnosti Microsoft) je uvedená v Prílohe B.

### 1.2.2 Formy súperenia v hre pre viacerých hráčov
Prostredie pre viacerých hráčov v hre Minecraft ponúka široké spektrum súťažných formátov, ktoré možno rozdeliť na izolované relačné režimy a globálne súperenie v otvorenom svete.

Relačné minihry (Bedwars, Skywars, Hunger Games):
Tieto formáty ponúkajúzhustený a úzko zameraný súťažný zážitok. Hoci sa v nich čiastočne zachovávajú prvky ťažby a stavania (napríklad budovanie mostov z blokov), fundamentálne makromechaniky sandboxu sú v nich umelo obmedzené rámcom krátkeho zápasu. Hrateľnosť v takýchto režimoch je podriadená krátkodobej bojovej interakcii (PvP) a zručnostiam priestorového pohybu. Podobné relácie nemôžu slúžiť ako objekt výskumu komplexného súťaženia, pretože sú v nich eliminované aspekty makroekonomiky, dlhodobého logistického plánovania a adaptácie na agresívne biómy.

Globálne súperenie v otvorenom svete:
Najvýraznejším príkladom globálneho súperenia v otvorenom svete sú servery žánru „anarchia“ (napríklad 2b2t) (Caldwell, 2016), ako aj servery s politicko-ekonomickým zameraním. V podmienkach dlhodobého prežitia ustupuje priamy PvP stret s mečmi do úzadia. Súťaženie sa transformuje na komplexné preteky zahŕňajúce nasledujúce formy:
- Ekonomické a zdrojové súperenie: Hráči si konkurujú v získavaní neobnoviteľných a vzácnych zdrojov, ako aj v kontrole nad kľúčovými generovanými štruktúrami. Napríklad kontrola nad dimenziou End (The End) umožňuje klanom monopolizovať ťažbu pancierov shulkerov (na prepravu zdrojov) a krídel Elytra (na vzdušnú dominanciu), čo im zabezpečuje absolútnu prevahu nad ostatnými účastníkmi servera.
- Preteky v hyperautomatizácii: V súčasnom Minecrafte sú hráči schopní navrhovať rozsiahle priemyselné základne a generovať zdroje v priemyselnom meradle bez manuálneho zásahu, pričom vytvárajú napríklad cyklické automatizované farmy s kolosálnou výkonnosťou (Mojang Studios, 2021). Na základe tohto faktu možno dospieť k záveru, že na serveroch dlhodobého prežitia nie je dominancia často zabezpečovaná bojovými zručnosťami, ale práve schopnosťou skupiny hráčov realizovať podobnú inžiniersku automatizáciu a monopolizovať ekonomiku.
- Teritoriálne súperenie: Formovanie aliancií na ochranu vlastných logistických uzlov a ničenie infraštruktúry konkurentov.

### 1.2.3 Dlhodobé prežitie ako zvolený kontext výskumu
Vychádzajúc zo špecifík herného dizajnu sandboxu sa ako kontext pre prostredie navrhované v tejto práci volí globálne dlhodobé prežitie v otvorenom svete (Survival SMP — Survival Multiplayer).

Výber práve tohto kontextu je odôvodnený tým, že dlhodobé prežitie zapája všetky vrstvy herných mechaník (prieskum, ťažbu, vyrábanie predmetov, obchodovanie, stavanie a boj), na rozdiel od obmedzených relačných minihier. Pre zabezpečenie dlhodobej angažovanosti hráčov je dôležité zohľadňovať ich základné psychologické potreby vrátane kompetencie, autonómie a spriaznenosti (Ryan et al., 2006). V kontexte Survival SMP môžu súťažné mechaniky prispievať k uspokojeniu týchto potrieb prostredníctvom vytvárania nedostatku zdrojov, nevyhnutnosti adaptácie a interakcie medzi hráčmi. Formát Survival SMP tak poskytuje optimálnu analytickú základňu na testovanie nových asymetrických pravidiel: umožňuje sledovať, ako zmena fundamentálnych vzorcov interakcie s prostredím formuje emergentné (prirodzene vznikajúce) súperenie medzi používateľmi.

## 1.3 Analýza a zhodnotenie existujúcich riešení
S cieľom odôvodniť potrebu vývoja novej softvérovej modifikácie a výberu optimálnej architektúry herného dizajnu sme vykonali komplexnú analýzu existujúcich používateľských projektov. Ako ukazuje akademická prax, analýza najväčších centralizovaných platforiem na distribúciu modifikácií (ako sú Nexus Mods alebo CurseForge) predstavuje optimálnu empirickú metódu na skúmanie ekosystému používateľského obsahu (Lee et al., 2020). Ako hlavná databáza pre náš výskum bola zvolená platforma CurseForge. Napriek existencii modernejších alternatív (ako je Modrinth) je CurseForge historicky najväčším repozitárom používateľského obsahu pre Minecraft a disponuje najúplnejším archívom komplexných modifikácií.

### 1.3.1 Postup výberu a výberové kritériá
Proces výberu modifikácií na analýzu bol založený na kombinácii empirických herných skúseností a cieleného vyhľadávania. Primárne vyhľadávanie prebiehalo podľa značiek (tagov) a kľúčových slov („races“, „origins“, „classes“, „survival overhaul“), ako aj prostredníctvom analýzy videorecenzií od odbornej komunity. Vzhľadom na to, že kvalitných projektov, ktoré od základu menia entitu hráča, je v komunite relatívne málo, výberová vzorka bola formovaná na základe nasledujúcich prísnych kritérií zaradenia:
1.  Kompatibilita: Modifikácia musí podporovať moderné verzie hry (v rozsahu od 1.19.2 po 1.21.1) a fungovať na ktoromkoľvek z populárnych zavádzačov modifikácií (Forge, Fabric alebo NeoForge).
2.  Vplyv na hrateľnosť: Projekt musí implementovať mechaniky dlhodobej progresie, meniť základné štatistiky hráča alebo hlboko integrovať asymetrické pravidlá prežitia.
3.  Potenciál pre viacerých hráčov: Prítomnosť mechaník, ktoré stimulujú alebo sťažujú priamu a nepriamu interakciu hráčov na SMP serveroch.

Z výslednej vzorky boli zámerne vylúčené uzavreté komerčné serverové zásuvné moduly (pluginy), zastarané projekty bez aktualizácií, ako aj málo známe modifikácie nachádzajúce sa v štádiu raného testovania (nevyzreté alfa verzie). Výsledný zoznam 16 relevantných modifikácií bol rozdelený na projekty implementujúce systémy rás/tried (najbližšie k téme bakalárskej práce) a projekty formujúce súťažné prostredie prostredníctvom zmeny progresie alebo ekosystému.

### 1.3.2 Podrobná analýza systémov rás a tried
Implementácia asymetrickej hrateľnosti zmenou samotnej entity hráča (pridaním rás) je náročná úloha, ktorá si vyžaduje rovnováhu medzi technickou flexibilitou a výkonom. V súčasnosti sú na platforme zastúpené tri hlavné prístupy k jej riešeniu.

1. Modifikácia „Origins“ (Apace100, 2021) — Data-Driven architektúra
Origins predstavuje široko používaný framework na implementáciu rasových mechaník v hre Minecraft. Ako ukazuje prax tvorby modifikácií (moddingu), v prípade zložitých oblastí (ako je vytvorenie unikátneho a ľahko rozšíriteľného systému herných rás) sa komunita často konsoliduje okolo jedného flexibilne nastaviteľného frameworku. Pri vstupe do sveta sa hráčovi zobrazí grafické rozhranie výberu rasy (pôvodu), ktorej profil sa uloží do NBT údajov postavy.
- Architektúra a výkon: Origins je predovšetkým flexibilný framework postavený na paradigme Data-Driven (údajovo riadenej architektúry). Celá logika schopností, podmienok a modifikátorov je riadená prostredníctvom externých súborov JSON s využitím továrenských metód (factory methods). Framework preberá náročnú prácu spojenú s implementáciou obrazovky výberu triedy, sledovaním NBT značiek a synchronizáciou schopností medzi klientom a serverom. Hlavná výhoda spočíva v tom, že koncoví používatelia môžu vytvárať vlastné rasy bez písania kódu v jazyku Java. Data-Driven architektúra však môže vytvárať dodatočnú výpočtovú réžiu pri pravidelnom vyhodnocovaní veľkého množstva zložitých, vnorených podmienok a modifikátorov. Ako sa zdôrazňuje vo výskumoch softvérového inžinierstva videohier, zabezpečenie stabilnej architektúry a vysokého výkonu (vrátane snímkovej frekvencie a odozvy servera) je kritickou požiadavkou, ktorá odlišuje vývoj hier od tradičného softvéru (Ampatzoglou a Stamelos, 2010). Skutočný vplyv týchto procesov na výkon servera (TPS) nie je konštantný a priamo závisí od konkrétnej implementácie, ako aj od miery optimalizácie logiky autormi používateľských doplnkov (addonov).
- Herný dizajn a rovnováha doplnkov: Keďže Origins je len základom, zodpovednosť za herný dizajn spočíva na autoroch konkrétnych rás a doplnkov. Analýza základného balíka rás ukazuje, že často vykazujú vychýlenú rovnováhu (Beau a Bakkes, 2016). Najspornejším dizajnovým rozhodnutím základnej modifikácie je prítomnosť „Obyčajného človeka“ (Human) v zozname volieb, ktorý nemá žiadne nevýhody. V praxi si značná časť hráčov volí práve túto triedu v snahe vyhnúť sa postihom pri prežití, čo znižuje potenciál formovania asymetrického súťažného prostredia.
- Rozsiahly doplnok „Medieval Origins Revival“ (MuonR, 2026): Príkladom rozšírenia ekosystému Origins je tento doplnok, ktorý zavádza rasy založené na mytológii a stredovekom fantasy (napríklad Pixie, Upíri-Revenanti, Víly). Projekt demonštruje funkčnosť frameworku, no zároveň odhaľuje jeho architektonické kompromisy. Pre plnohodnotnú realizáciu zložitých mechaník (ako je zmena veľkosti kolízneho boxu — hitboxu alebo integrácia magických atribútov) si doplnok v určitých zostavách vyžaduje inštaláciu ďalších knižníc, ako sú *Pehkui* alebo *Spell Power Attributes*, čo komplikuje administráciu servera. Okrem toho implementácia aktívnych schopností v Origins, ktoré sa spúšťajú stlačením klávesov, často nekorešponduje s autentickým dizajnom hry Minecraft, kde aktívnou funkcionalitou tradične disponujú nástroje, a nie samotné telo postavy (Bergensten, 2019). Napokon, v dôsledku univerzálnosti frameworku trpí hlboká integrácia s hrou: rasové poškodenie (napríklad z vody) je často sprevádzané štandardnými zvukmi topenia sa, čo znižuje celkovú konzistentnosť herného procesu.

2. Modifikácia „Dragon Survival“ (BlackAures, 2026) — Globálna úplná transformácia
- Princíp fungovania: Globálna modifikácia ponúkajúca radikálny prístup k asymetrii — hráč sa úplne transformuje na draka jedného z troch typov (Lesný, Jaskynný alebo Morský), so zmenou hitboxu, modelu a základných mechaník interakcie so svetom. Modifikácia nevyužíva externé frameworky a je napísaná s využitím hlbokého pevného kódovania — hardcodingu (Java).
- Kritické zhodnotenie: Projekt je výrazným príkladom vytvorenia hlbokej asymetrie. Zmena typu entity hráča mení samotný princíp prežitia. V kontexte súťažného PvP prežitia (SMP) však modifikácia vytvára príliš veľkú priepasť medzi ľudskými hráčmi a dračími hráčmi. Projekt je orientovaný skôr na kooperatívne hranie rolí (RPG) a tematické balíky modifikácií, keďže mechaniky drakov (lietanie, mágia, unikátne hniezdenie) je ťažké vyvážiť so základnou (vanilla) ekonomikou bez narušenia horizontálnej progresie.

3. Modifikácia „Class Enhancement“ (M3t4l_G34r, 2023) — Statické profesie
Projekt s uzavretým zdrojovým kódom, ktorý ponúka výber profesie (triedy) pri prvom objavení sa vo svete.
- Kritické zhodnotenie: Tento projekt vykazuje obmedzený rozsah a neformuje dlhodobú súťažnú asymetriu. Zmeny sa redukujú na triviálnu manipuláciu so základnými číselnými atribútmi (zdravie, rýchlosť pohybu) a prideľovaná štartovacia sada predmetov (napríklad meč a štít pre triedu „Rytier“) stráca v raných fázach hry aktuálnosť. Modifikácia neponúka unikátne mechaniky interakcie so svetom.

### 1.3.3 Stručný prehľad ostatných súťažných modifikácií
Okrem systémov rás sú v komunite populárne modifikácie, ktoré sa snažia sformovať súťažné prostredie prostredníctvom zmeny progresie alebo rozšírenia mechaník. Podrobný opis a rozbor mechaník 12 skúmaných modifikácií z týchto kategórií sú uvedené v Prílohe C. V rámci tejto časti ich možno zhrnúť nasledovne:
- Projekty s vertikálnou progresiou: Modifikácie preberajúce prvky klasických RPG (napríklad Passive Skill Tree, L_Ender's Cataclysm, Iron's Spells, Immersive Aircraft). Ich spoločným kritickým problémom je vyvolávanie „inflácie sily“ (power creep). Zavedenie lineárneho rastu štatistík, mágie alebo silného brnenia bez prítomnosti automatického škálovania úrovne sveta vedie k úplnému znehodnoteniu základných hrozieb sandboxu (Jeon et al., 2024). Herný proces sa redukuje na monotónny grind a pôvodný obsah je nahrádzaný úzko špecializovanými predmetmi, čo porušuje fundamentálne pravidlo multifunkčnosti v hre Minecraft (Bergensten, 2019).
- Projekty s horizontálnou progresiou: Modifikácie demonštrujúce organickú integráciu do ekosystému hry (napríklad Savage & Ravage, Create, Species, Sortilege). Súťažné prostredie neformujú cez čísla, ale prostredníctvom rozšírenia taktických a inžinierskych nástrojov (Tyni et al., 2023). Hráči súťažia v navrhovaní zložitých mechanizmov (Create), hľadaní vzácnych trofejí (Species) alebo kontrole nad unikátnymi očarovaniami (Sortilege). Tieto projekty dokazujú, že úspešné súťažné rozšírenie hry Minecraft je možné len bez narušenia jej horizontálnej rovnováhy.

### 1.3.4 Porovnávacia analýza
Na systematizáciu výsledkov analýzy bolo všetkých 16 skúmaných modifikácií porovnaných podľa kľúčových kritérií herného dizajnu a architektúry (Tabuľka 1).

Tabuľka 1 Porovnávacia analýza existujúcich modifikácií

| Modifikácia (Kategória) | Typ progresie | Zachovanie rovnováhy základu | Asymetria hráča | Architektúra |
| :--- | :--- | :--- | :--- | :--- |
| Passive Skill Tree (Daripher, 2023) | Vertikálna (rast atribútov) | Nízke (trivializácia hrozieb) | Nie (len lineárne bonusy) | Strom údajov (JSON / Config) |
| L_Ender's Cataclysm (L_Ender, 2022) | Vertikálna (výbava) | Kriticky nízke (power creep) | Nie (závislosť od brnenia) | Hardcode (Java) |
| Iron's Spells (Iron431, 2023) | Vertikálna (poškodenie kúziel) | Nízke (nahradenie elixírov a očarovaní) | Aktívne bojové zručnosti | Hybridná (Java + JSON) |
| Born in Chaos (Mongoose_Artist, 2022) | Horizontálno-vertikálna | Stredné (izolovaná výroba) | Nie | Hardcode (Java) |
| Immersive Aircraft (Conczin, 2022) | Vertikálna (zrýchlenie) | Nízke (znehodnotenie krídel Elytra) | Nie | Hardcode / Data |
| Epic Fight (Maninthe_home, 2020) | Vertikálno-mechanická | Nízke (konflikt s fyzikou) | Bojové postoje a animácie | Hardcode (Java) |
| Savage & Ravage (TeamAbnormals, 2020) | Horizontálna | Vysoké (rozšírenie nájazdov) | Nie | Hardcode (Java) |
| Heart Crystals (Rosemods, 2023) | Horizontálno-obmedzujúca | Vysoké (prepojenie s alchýmiou) | Štartovací limit zdravia | Hardcode + Config |
| Sortilege (Limitable, 2024) | Horizontálna (sidegrade) | Vysoké (rovnováha očarovaní a palíc) | Nie | Hardcode + Config |
| Species (Ninni, 2023) | Horizontálna | Vysoké (integrácia mechaník) | Nie | Hardcode (Java) |
| Field Guide (Evocation, 2024) | Horizontálna (informačná) | Vysoké (využitie ďalekohľadu) | Nie | Hardcode + Data |
| Create (Simibubi, 2019) | Horizontálna (inžinierska) | Vysoké (mechanické úlohy) | Nie | Modulárne OOP (Java) |
| Class Enhancement (M3t4l_G34r, 2023) | Statická (štartovacia) | Vysoké | Minimálna (základné štatistiky) | Uzavretý kód (Config) |
| Origins (Apace100, 2021) | Horizontálna (asymetrická) | Stredné (slabá rovnováha rás) | Vysoká (pasívne + aktívne schopnosti) | Data-Driven (JSON) |
| Medieval Origins Revival (MuonR, 2026) | Horizontálna (asymetrická) | Nízke (závislosť od modifikácií) | Vysoká (silná mágia) | Data-Driven (JSON) |
| Dragon Survival (BlackAures, 2026) | Úplná transformácia (RPG) | Nízke (presun ťažiska na RPG) | Radikálna (zmena tela) | Hardcode (Java) |

Zdroj: vlastné spracovanie

## 1.4 Technologické možnosti rozšírenia hry Minecraft
Integrácia komplexných herných mechaník si vyžaduje využitie moderných nástrojov moddingu a prísne dodržiavanie štandardov softvérovej architektúry. Výber konkrétnych technológií pri vývoji je podmienený požiadavkami na výkon, škálovateľnosť a ochranu rovnováhy súťažného prostredia. Technologický zásobník (stack) moddingu sa opiera o niekoľko kľúčových aspektov:

- Platformy a modifikačné API: Hlavným nástrojom na tvorbu používateľského obsahu sú zavádzače modifikácií (mod loadery). V súčasnosti v komunite dominujú tri architektonické štandardy: Forge, Fabric a NeoForge. Platforma Minecraft Forge (Minecraft Forge Contributors, 2024) poskytuje robustné, no vyčerpávajúce rozhranie nevyhnutné na hlboký zásah do logiky hry. Fabric sa vyznačuje ľahkosťou a modularitou, čím zabezpečuje rýchlu adaptáciu na nové verzie hry, no od vývojárov si vyžaduje samostatnú implementáciu mnohých systémových záchytných bodov — tzv. hookov (FabricMC, 2024). NeoForge vystupuje ako moderný fork platformy Forge, ktorý sa usiluje optimalizovať zastarané architektonické riešenia. Výber konkrétnej platformy určuje dostupné nástroje na manipuláciu so základnými udalosťami herného sveta.
- Možnosti konfigurácie (Data-Driven a Hardcoding): Pri návrhu architektúry sa vývojári rozhodujú medzi architektúrou riadenou údajmi (Data-Driven na báze súborov JSON) a prísnym objektovo orientovaným prístupom (Hardcoding v jazyku Java). Je dôležité poznamenať, že tieto prístupy sa navzájom nevylučujú. Prístup Data-Driven poskytuje vysokú flexibilitu, pretože umožňuje administrátorom meniť mechaniky bez programátorských zručností a rekompilácie kódu. Logika takýchto modifikácií načítava schémy JSON do operačnej pamäte pri inicializácii zdrojov, avšak nadmerné využívanie továrenských metód na spracovanie týchto údajov môže skomplikovať architektúru. Prísna implementácia logiky v bajtkóde (Java) zas garantuje typovú bezpečnosť v čase kompilácie (Compile-time safety), umožňuje vytvárať zložité matematické algoritmy a pevne fixuje parametre rovnováhy, čím zabraňuje ich neoprávnenej zmene. Optimálnym riešením je často hybridný model: hlboká logika sa programuje v jazyku Java a koeficienty vyváženia sa vyčleňujú do konfiguračných súborov.
- Uchovávanie perzistentných údajov (Data Storage): Pre realizáciu dlhodobých súťažných systémov je kriticky dôležité zabezpečiť spoľahlivé ukladanie postupu. V ekosystéme hry Minecraft sa na uchovávanie údajov hráča a sveta tradične používa binárny formát NBT (Named Binary Tag). Mechanizmy pripájania vlastných údajov sa však líšia v závislosti od platformy a jej verzie. V starších a stabilných verziách Minecraft Forge (do verzie 1.20) bol štandardom systém `Capabilities`, ktorý umožňoval bezpečne pripájať premenné k entitám alebo častiam sveta (chunkom). V súčasných podmienkach prešla platforma NeoForge (a aktualizovaný Forge) na optimalizovaný systém `Data Attachments` (NeoForged, 2024), ktorý rieši problémy s výkonom a synchronizáciou predchádzajúcej generácie. Oba mechanizmy umožňujú bezpečne uchovávať zložité údajové štruktúry pri reštarte servera bez narušenia integrity základných súborov uloženia sveta.
- Sieťová synchronizácia (Client-Server Sync): Pri pridávaní súťažných prvkov sa presná synchronizácia údajov medzi klientskou aplikáciou a dedikovaným serverom stáva kritickou požiadavkou. Aby sa predišlo desynchronizácii stavov (desync) a zabránilo sa nečestnému správaniu (podvádzaniu — cheatingu), architektúra sa musí opierať o princíp serverovej autority. Všetky kľúčové výpočty, vyhodnocovanie podmienok prostredia a modifikácie herných štatistík sa musia spracovávať výhradne na strane servera. Výsledky týchto výpočtov sa prenášajú klientovi pomocou špecializovaných sieťových paketov, zatiaľ čo klient zodpovedá predovšetkým za vykresľovanie (rendering) vizuálnej reprezentácie (Engelbrecht a Schiele, 2014; Yeung a Lui, 2008).
- Zber analytických údajov a telemetria: V kontexte súťažného herného dizajnu je zber herných štatistík dôležitým nástrojom na následné vyvažovanie mechaník (Beau a Bakkes, 2016). Moderné modifikácie môžu implementovať systémy vstavanej telemetrie prostredníctvom asynchrónneho odosielania požiadaviek HTTP na externé servery REST. Pri návrhu podobných systémov je kritickou požiadavkou dodržiavanie zásad ochrany osobných údajov (napríklad GDPR), čo sa rieši kryptografickým hašovaním unikátnych identifikátorov hráčov (UUID). Okrem toho sieťové volania nesmú blokovať hlavné vlákno servera (Server Thread), aby sa predišlo poklesu výkonu servera (TPS).

## 1.5 Zhrnutie výsledkov a definovanie riešeného problému
Vykonaná analýza predmetnej oblasti odhalila, že integrácia súťažných prvkov do režimu prežitia v hre Minecraft sa v komunite teší vysokému dopytu. Existujúce používateľské riešenia poskytujú rozmanité nástroje na rozšírenie hry, avšak vykazujú viacero významných architektonických a dizajnových obmedzení:
- Konceptuálne rozpory progresie: Väčšina riešení sa uberá cestou zavádzania vertikálnej číselnej progresie a extrémne silnej výbavy. To nevyhnutne vedie k inflácii sily (power creep) a úplnému znehodnoteniu základných hrozieb sandboxu. Dodatočná implementácia aktívnych bojových zručností nie je v súlade s autentickým dizajnom ovládania hry.
- Nadmerná kazualizácia: Populárne modifikácie často umelo zjednodušujú herný proces (pridávanie systémov okamžitej teleportácie, radarov, vypnutie straty inventára), čím odstraňujú psychologické stimuly vyhýbania sa a radikálne znižujú celkovú hodnotu prežitia v súťažnom prostredí.
- Technické a balančné nedostatky asymetrických systémov: Existujúce alternatívy v segmente rasových modifikácií (napríklad Origins) sa sústreďujú na maximálnu technickú flexibilitu prostredníctvom využívania konfiguračných súborov, čo má negatívny vplyv na výkon servera (TPS). Zachovanie neutrálnej základnej rasy („človeka“) v takýchto systémoch navyše stiera samotnú myšlienku súťažnej asymetrie, keďže hráči sa hromadne vyhýbajú výberu rás so zraniteľnosťami.

### 1.5.1 Definovanie riešeného problému a zameranie vlastného projektu
Na základe identifikovaných obmedzení sa formuje hlavný problém výskumu: v ekosystéme modifikácií hry Minecraft existuje akútna potreba vývoja vysokovýkonného softvérového systému, ktorý by zabezpečoval dlhodobú súťažnú asymetriu bez narušenia pôvodnej štylistiky hry (Vanilla+).

V rámci tejto bakalárskej práce bude pozornosť zameraná na návrh a softvérovú implementáciu vlastnej modifikácie, ktorá rieši identifikované problémy. Projekt sa sústredí na vývoj hlboko vyváženého systému pasívnych rasových výhod a citeľných fyziologických zraniteľností pri úplnom upustení od neutrálnych štartovacích podmienok.

Na vyriešenie problému poklesu výkonu a na ochranu integrity súťažných pravidiel (prevenciu podvádzania) sa práca zameria na odklon od flexibilných konfiguračných paradigiem v prospech vytvorenia optimalizovanej architektúry klient-server s prísnou fixáciou biznis logiky na strane servera. Vyvíjaný softvér má týmto spôsobom prinavrátiť do hry súťažnú hodnotu prežitia a vytvoriť stabilné prostredie pre nepriame aj priame súperenie hráčov na makroekonomickej úrovni.

---

# 2 Ciele práce
Hlavným cieľom tejto bakalárskej práce je návrh, softvérová implementácia a testovanie komplexnej modifikácie „Finesse Races“ pre platformu Minecraft Forge. Vyvíjaný softvér má zaviesť súťažné asymetrické prostredie prežitia prostredníctvom unikátnych herných rás, opierať sa o špeciálne vyvinutý nezávislý grafický API framework „Remedy Core“ a zahŕňať integrovaný systém zberu telemetrie na následnú analýzu herného procesu a vyvažovanie.

## 2.1 Čiastkové ciele práce
Pre úspešné dosiahnutie hlavného cieľa boli stanovené nasledujúce čiastkové kroky (podciele):
1. Analyzovať teoretické aspekty herného dizajnu súťažných sandboxových hier a porovnať existujúce softvérové riešenia (používateľské modifikácie) s cieľom identifikovať ich architektonické, technické a balančné obmedzenia.
2. Navrhnúť konceptuálny model asymetrického prežitia vrátane presnej špecifikácie herných rás s vyvážením pasívnych výhod a zraniteľností a naplánovať architektúru prísnej synchronizácie medzi klientom a serverom.
3. Vyvinúť nezávislý softvérový framework Remedy Core na unifikované vykresľovanie modulárnych a adaptívnych grafických používateľských rozhraní (GUI) s využitím algoritmov škálovania (Nine-Slice Scaling).
4. Implementovať hlavnú biznis logiku modifikácie Finesse Races v jazyku Java (Hardcoding) vrátane správcu atribútov, fyziologických mechaník rás (diéta, výdrž, vplyv teploty) a serverovej validácie paketov na zníženie možnosti manipulácie s hernými údajmi zo strany klienta.
5. Zaviesť zabezpečený asynchrónny systém telemetrie (TelemetryManager) s využitím kryptografického hašovania (SHA-256) na zber analytických metadát o úmrtiach hráčov, zmene rás a súťažných metrikách bez vplyvu na výkon servera.
6. Otestovať stabilitu sieťového kódu klient-server, vyhodnotiť výkon (vplyv na serverové TPS) a overiť kompatibilitu vyvinutého softvéru s inými populárnymi modifikáciami.

---

# 3 Návrh a metodológia
Táto kapitola je venovaná návrhu softvéru, opisu konceptuálneho modelu modifikácie Finesse Races, špecifikácii herných rás, ako aj metodológii vývoja a testovania aplikovanej počas realizácie projektu.

## 3.1 Návrh softvéru
Na zabezpečenie dlhodobej angažovanosti je modifikácia Finesse Races vyvíjaná s ohľadom na pôvodnú slobodu základného (vanilla) sandboxu, bez spoliehania sa na externé zásuvné moduly a umelé serverové obmedzenia. Navrhovaný systém rás poskytuje rozmanité stimuly pre všetky kategórie hráčov: pre dosahovateľov náročné úlohy spojené s adaptáciou na zraniteľnosti, pre prieskumníkov unikátne spôsoby ťažby vzácnych materiálov, pre socializátorov synergiu v kooperatívnej hre a pre zabijakov nové taktické možnosti v boji.

### 3.1.1 Filozofia a konceptuálny model modifikácie
Ideológia modifikácie Finesse Races predstavuje konceptuálnu syntézu dvoch prístupov k hernému dizajnu, ktoré vystupujú ako hlavné zdroje inšpirácie pre tento projekt. Na jednej strane sa projekt opiera o myšlienku radikálnej zmeny zaužívaných vzorcov interakcie so svetom: narušením základných mechaník cez prizmu prísnych rasových zraniteľností modifikácia vracia do hry ducha objaviteľstva a nevyhnutnosť adaptovať sa na prostredie. Na druhej strane je architektúra modifikácie navrhovaná s opatrnosťou, aby zodpovedala súčasnej paradigme „Vanilla+“ (Bergensten, 2019). To znamená, že nové herné mechaniky sú do hry Minecraft integrované maximálne harmonicky: nenarúšajú vizuálnu estetiku, nepreťažujú rozhranie zbytočnými prvkami a zachovávajú multifunkčnosť pôvodného obsahu. *Finesse Races* tak zavádza výrazné zmeny, no robí to organicky a esteticky vyvážene.

Opierajúc sa o vykonanú analýzu teórie herného dizajnu bola pre modifikáciu sformulovaná koncepcia asymetrickej zmeny samotnej entity hráča. Namiesto pridávania externej vertikálnej výbavy systém zavádza výber unikátnych herných rás inšpirovaný rozmanitosťou rolí v hrách pre viacerých hráčov, kde výber postavy a jej herných vlastností ovplyvňuje individuálny štýl hry a spoluprácu s tímom (Hodges a Buckley, 2018; Neto et al., 2022). Tento prístup je adaptovaný s ohľadom na zákony prežitia v sandboxe Minecraft. V klasických súťažných hrách sú rozdiely medzi postavami často postavené okolo sady aktívnych bojových schopností (skillov). V rámci návrhu *Finesse Races* však bolo prijaté vedomé rozhodnutie upustiť od implementácie aktívnych magických kúziel, aby sa zachoval autentický štýl ovládania, pri ktorom sú aktívnymi funkciami v prvom rade obdarené nástroje v rukách hráča (Bergensten, 2019).

Optimálnou dizajnovou referenciou pre projekt je kooperatívno-súťažný sandbox *Don't Starve Together*, kde je asymetria realizovaná prostredníctvom komplexu pasívnych fyziologických a behaviorálnych vlastností. Na základe tohto prístupu sa v modifikácii *Finesse Races* realizuje nasledujúci konceptuálny model:
- Upustenie od štandardného človeka: Pri vstupe do sveta si hráč musí zvoliť jednu z unikátnych rás; neutrálny variant bez špecifických vlastností je vylúčený, aby sa zabezpečila účasť každého hráča v systéme rasovej asymetrie.
- Prísna rovnováha pasívnych výhod a nevýhod: Každá rasa disponuje unikátnymi pasívnymi mechanikami interakcie so svetom (napríklad adaptácia na vodné prostredie alebo špecifické podmienky stravovania), ktoré sú vyvažované citeľnými zraniteľnosťami (Beau a Bakkes, 2016). Opierajúc sa o dominanciu motivácie vyhýbania sa („OD“) nad motiváciou dosahovania („K“), prítomnosť prísnych rasových zraniteľností stimuluje hráčov citlivejšie reagovať na nevyhnutnosť vyhýbať sa nebezpečenstvu a adaptovať sa na diskomfort.
- Upustenie od aktívnych zručností (skillov) ako princíp návrhu: Celá logika rás funguje pasívne alebo prostredníctvom štandardných základných akcií hráča, čím sa zachováva organické ovládanie a hodnota základných predmetov výbavy.
- Asymetria motivácie a zmena paradigmy prežitia: Keďže každá rasa disponuje vlastnou sadou vlastností a obmedzení, poskytuje hráčovi rôzne herné možnosti a podmienky dosahovania cieľov, čo ovplyvňuje vnímanie kompetencie a autonómie (Ryan et al., 2006). Zmena rasy v strednej fáze hry (mid-game) pomocou Sféry znovuzrodenia nevynuluje celkový postup vo svete, no kardinálne mení podmienky ďalšieho prežitia. Hráč je postavený pred nevyhnutnosť prestavať svoju základňu, logistiku a ekonomické návyky podľa nových fyziologických obmedzení svojej novej rasy.
- Zníženie kazuálnosti: Modifikácia je cielene navrhovaná ako nástroj na zvýšenie náročnosti. Zavedenie rasových zraniteľností výrazne zvyšuje základný vstupný prah do prežitia z „nízkeho“ na „stredný“, čím vracia váhu každému prijatému rozhodnutiu a udržiava hráča v optimálnom kanáli stavu flow (Juul, 2010; Arzate Cruz a Ramirez Uresti, 2017).

Táto koncepcia umožňuje vniesť súťažnú asymetriu do všetkých aspektov hry a meniť samotnú trasu prechádzania hry pre každú zvolenú rasu bez deštrukcie základnej rovnováhy sandboxu.

### 3.1.2 Konceptuálna špecifikácia herných rás
Na realizáciu asymetrickej hrateľnosti bol navrhnutý systém unikátnych rás založený na predefinovaní globálnych fyziologických parametrov. Namiesto pridávania abstraktných magických efektov modifikácia manipuluje so systémovými atribútmi enginu Minecraft a integrovaných modifikácií (napríklad Cold Sweat). Základný systém charakteristík zahŕňa:
- Kinematické atribúty: Základná rýchlosť chôdze, rýchlosť plávania, rýchlosť ponárania a výška kroku (step height, umožňujúca prekonávať bloky bez skoku).
- Termodynamické atribúty: Koeficienty odolnosti voči extrémnym teplotám (horúčave a chladu) a úrovni vlhkosti.
- Systém výdrže a hmotnosti: Individuálne ukazovatele maximálnej nosnosti brnenia, ktoré priamo ovplyvňujú spotrebu ukazovateľa výdrže a kinematiku kotúľov (dodging).
- Metabolické obmedzenia (Diéta): Unikátne zoznamy povolenej potravy. Konzumácia potravín mimo diéty danej rasy ukladá postihy na nasýtenie alebo spôsobuje priame poškodenie.

Aktivácia výhod a nevýhod prebieha algoritmicky pri vyhodnocovaní stavu prostredia serverom (napríklad kontrola kontaktu hitboxu Enderca s blokmi vody alebo kontrola úrovne osvetlenia pre aktiváciu postihov Upíra).

V rámci projektu je navrhnutých 11 unikátnych rás rozdelených do troch konceptuálnych kategórií:
1. Priemyselno-zdrojové rasy: Trpaslík (špecializácia na podzemnú ťažbu s postihmi pri plávaní), Mechar (adaptácia prostredníctvom hardvérových modulov namiesto jedla so zraniteľnosťou voči vode) a Mimozemšťan (imunita voči radiácii so závislosťou od kvót).
2. Elementárne a priestorové rasy: Obojživelník (dominancia vo vode s kritickou zraniteľnosťou voči ohňu), Enderec (majster teleportácie s hydrofóbiou) a Vták (vzdušná mobilita s postihmi v uzavretých priestoroch).
3. Nočné a transformačné rasy: Upír (posilnenie v tme, horenie na slnku), Vlkolak (nočná transformácia so stratou kontroly nad brnením), Nemŕtvy (liečenie poškodením), Lesný duch (závislosť od ročných období) a Démon (ohňovzdornosť s prísnou pekelnou diétou).

Technické špecifikácie, percentuálne hodnoty zmien atribútov, zoznamy diét a podrobný opis unikátnych mechaník pre každú z 11 rás sú uvedené v Prílohe D.

### 3.1.3 Vplyv rasových mechaník na súťažný zážitok a rovnováhu prechádzania hry
Na rozdiel od klasických relačných arén Finesse Races neviaže súťaženie na konkrétnu mapu alebo režim. Zavedenie prísnych rasových obmedzení vytvára asymetrický nedostatok zdrojov, čím generuje emergentné (spontánne vznikajúce) súťaženie v otvorenom svete. Je dôležité poznamenať, že modifikácia nenúti hráčov k priamemu PvP stretu. Súťažný zážitok sa formuje prostredníctvom sťaženia interakcie s prostredím: každý hráč čelí unikátnym podmienkam prechádzania hry, pričom bojuje s vlastnými zraniteľnosťami.

Na zhodnotenie toho, v akej miere implementované zraniteľnosti ovplyvňujú rovnováhu hry a schopnosť prežiť, sa ako hlavný analytický scenár uvažuje **rýchlostné prechádzanie hry (Speedrunning)**. Tento formát nepriameho súťaženia (kto rýchlejšie prejde cestu od objavenia sa vo svete až po zabitie Ender draka) je optimálnou metrikou na odhalenie nevyvážených rás (overpowered alebo underpowered), pretože si od hráča vyžaduje maximálnu efektivitu pri ťažbe zdrojov, logistike a vedení boja.

Scenár testovania rovnováhy: Asymetrický Speedrun
Cieľom tejto teoretickej skúšky je posúdiť, pre ktorú rasu je objektívne najzložitejšie získavať zdroje a prežiť v klasickom cykle hry Minecraft a ktorá rasa má v raných fázach neoprávnenú výhodu.
- Hodnotenie výhody na štarte: Rasa Vták (Avian) disponuje výraznou výhodou v rýchlosti prechádzania hry vďaka vstavaným krídlam. Hráč môže preskočiť fázu zdĺhavého pozemného prieskumu a stavania mostov a rýchlo sa presúvať medzi štruktúrami (dedinami, portálmi). Na druhej strane je rasa *Enderca* úplne oslobodená od nutnosti získavať jedlo (ukazovateľ hladu je zmrazený) a dokáže sa teleportovať, čo šetrí značné množstvo času. Tieto rasy sú silnými kandidátmi na zníženie štatistík (nerf) v budúcich aktualizáciách, ak telemetria preukáže ich totálnu dominanciu v rýchlosti prechádzania hry.
- Hodnotenie náročnosti prežitia: Najväčšie ťažkosti pri pokuse o rýchlostné prechádzanie hry pocíti rasa Obojživelníkov. Klasický speedrun si vyžaduje povinný zostup do dimenzie Nether za účelom získania ohnivých tyčí z ohnivákov (blazov). Pre Obojživelníka, ktorý utrpí zvýšené poškodenie zo zdrojov tepla a je závislý od vody (ktorá sa v dimenzii Nether okamžite vyparuje), sa táto etapa stáva kriticky náročnou prekážkou. Hráč hrajúci za Obojživelníka je nútený stráviť obrovské množstvo času hľadaním alternatívnych spôsobov chladenia (napríklad elixírov alebo špecifického slizu) pred zostupom do pekla. Podobné logistické zdržania pociťuje Trpaslík, ktorého nízka rýchlosť pohybu po povrchu výrazne spomaľuje hľadanie pevnosti (Stronghold).
- Metodológia vyvažovania: Na základe tohto scenára sa formuje pochopenie, že rasy nemôžu a ani nemajú mať rovnaký čas prechádzania hry (time-to-beat). Asymetria predpokladá, že pre Vtáka bude jednoduchšie nájsť portál, no ťažšie zabiť Draka pre vysoké poškodenie z pádu, zatiaľ čo pre Trpaslíka bude náročné presúvať sa, ale jednoduché vydržať poškodenie v uzavretom priestore. Vyvinutý telemetrický modul "Finesse Races Telemetry" sleduje presné príčiny a počet úmrtí postáv rôznych rás. Analýza pomeru frekvencie úmrtí k zvolenej rase umožní v budúcich aktualizáciách matematicky presne oslabovať príliš silné rasy a posilňovať outsiderov, čím sa vyrovná celková súťažná krivka.

### 3.1.4 Architektonický dizajn a technologické riešenia
Návrh a písanie zdrojového kódu prebieha v integrovanom vývojovom prostredí (IDE) IntelliJ IDEA Ultimate Edition s integráciou systému automatického zostavovania Gradle. Toto prostredie disponuje pokročilým vstavaným dekompilátorom bajtkódu Java, čo zaručuje plynulý pracovný proces pri analýze základného kódu herného enginu Minecraft. Pre riadenie projektu bola zvolená architektúra viacmodulového vývoja (Multi-project Build), orientovaná na vytvorenie modifikácie pre platformu Forge 1.20.1 s využitím nezávislej knižnice Remedy Core.

Na optimalizáciu procesu analýzy zdrojového kódu enginu Minecraft a rozhrania API Forge sú implementované moderné mapovania ParchmentMC. Na rozdiel od oficiálnych mapovaní Mojang, Parchment dopĺňa názvy tried a metód o informatívnejšie názvy parametrov a dokumentáciu, čo uľahčuje pochopenie účelu jednotlivých prvkov herného enginu a urýchľuje vývoj (ParchmentMC, 2024).

Vytvorenie vlastného aplikačného programovacieho rozhrania (API) je všeobecne uznávanou praxou v architektúre rozsiahlych modifikácií. Aby sa predišlo porušeniu princípu jedinej zodpovednosti (Single Responsibility Principle) a neprehľadnosti kódovej základne, základná logika bola vyčlenená do nezávislej knižnice Remedy Core, ktorá plní úlohu komplexného inštrumentálneho frameworku. Do jadra tohto API je zapuzdrený rozsiahly znovupoužiteľný kód: systémové mixiny (Mixin), utility pre prácu so sieťovými paketmi, nástroje na zrýchlené vytváranie obsahu (napríklad vytvorenie plnohodnotného paliva pomocou jedného riadku kódu), ako aj štandardizované triedy na budovanie grafických používateľských rozhraní. Abstrakcia základnej funkcionality do samostatného nezávislého modulu (API) výrazne znižuje duplikáciu kódu v hlavnom projekte, zjednodušuje proces ladenia a poskytuje spoľahlivú sadu nástrojov pre efektívny vývoj budúcich modifikácií (Tulach, 2008). Je dôležité zdôrazniť, že Remedy Core je úplne zbavený biznis logiky samotnej modifikácie Finesse Races — vystupuje výlučne ako súbor nástrojov, ktorý mnohonásobne redukuje objem rutinného kódu v hlavnom projekte. Integrácia API a hlavného módu je realizovaná prostredníctvom viacmodulového zostavenia (Multi-project build s direktívou includeFlat), čo umožňuje za behu aplikovať zmeny z kódu API do hlavného projektu s využitím spoločnej vyrovnávacej pamäte (cache) nástroja ForgeGradle.

Systém grafického používateľského rozhrania (GUI) v Remedy Core je navrhnutý s dôrazom na modularitu. Logická štruktúra rozhrania je postavená na systéme izolovaných kontajnerov (GuiPanel), vo vnútri ktorých sú umiestňované a zoskupované tlačidlá, text a ďalšie dcérske prvky podľa lokálnych súradníc. Vizuálne škálovanie a vykresľovanie panelov zabezpečuje algoritmus Nine-Slice Scaling, ktorý zabraňuje skresleniu textúr na okrajoch pri zmene rozmerov. Adaptabilita rozhrania na rôzne rozlíšenia monitorov je riešená metódou dynamického prepočítavania rozmerov (princíp „matematickej gumy“). Na rozdiel od triviálneho stlačenia matice, tento prístup zachováva fixnú veľkosť tlačidiel a písiem, pričom flexibilne zužuje iba panelové pozadia a posúva prvky, čo zabraňuje rozbitiu rozloženia na malých obrazovkách.

Kriticky dôležitým architektonickým riešením sa stala optimalizácia systému spracovania rasových schopností. V pôvodnej architektúre vysokofrekvenčné serverové udalosti (napríklad každý herný tik alebo prijatie poškodenia) vyžadovali dynamickú kontrolu typov (instanceof) a prechádzanie všetkých schopností rasy počas behu programu (Runtime). Na ochranu serverového výkonu (TPS) a klientskeho FPS bol vykonaný hlboký refaktoring s uplatnením princípu oddelenia rozhraní (Interface Segregation Principle). Schopnosti boli rozdelené na úzko zamerané mikro-rozhrania (napríklad IHurtModifier, ITickModifier). Vyhodnocovanie typov bolo úplne presunuté z fázy behu do fázy inicializácie: algoritmus cacheAbilityListeners() jednorazovo rozdelí schopnosti do kolekcií (cache). Týmto spôsobom serverové udalosti pristupujú iba k tým schopnostiam, ktoré sú na ne zaručene prihlásené (rýchlosť prístupu O(1)), čím sa zabezpečuje vysoká škálovateľnosť kódu pri pridávaní desiatok nových rás.

Vzhľadom na výrazný súťažný charakter projektu sa architektúra opiera o prísnu serverovú autoritu (Server Authority). Všetky kľúčové výpočty, modifikácie herných vlastností, výpočty poškodenia a hodnotenie podmienok pre zraniteľnosti rás sa spracovávajú výlučne na strane servera. Tento objektovo orientovaný prístup (Hardcode v Jave) vylučuje presun kritických balančných parametrov do konfiguračných súborov JSON (Data-Driven), čím prísne chráni súťažné prostredie pred neoprávnenou zmenou poškodenia alebo času obnovy (cooldown) schopností zo strany používateľov.

Na zber analytických údajov a následné vyvažovanie súťažného prostredia bol vyvinutý nezávislý modul — Finesse Races Telemetry. Tento komponent zbiera metadáta herného procesu vrátane frekvencie výberu rôznych rás, štatistík ich zmeny a analýzy príčin úmrtí hráčov. Odosielanie paketov JSON na externý telemetrický server je realizované asynchrónne s využitím java.net.http.HttpClient (Java 11+). Metóda sendAsync zaručuje, že samotné sieťové čakanie na odpoveď HTTP neblokuje hlavné vlákno servera (Server Thread).

S cieľom dodržať ochranu súkromia bol zavedený systém kryptografického hašovania unikátnych identifikátorov hráčov (UUID) pomocou algoritmu SHA-256 (trieda MessageDigest). Je dôležité zdôrazniť, že táto metóda zabezpečuje pseudonymizáciu údajov: stabilný haš umožňuje prepojiť herné udalosti s jedným používateľom na štatistické účely, čím sa znižujú riziká priamej deanonymizácie, avšak nezaručuje absolútnu anonymitu, keďže pôvodný identifikátor môže byť potenciálne obnovený pri dostupnosti dodatočných informácií (dúhových tabuliek). Systém taktiež zahŕňa základnú autentifikáciu prostredníctvom vlastných hlavičiek HTTP (X-Finesse-Auth) na ochranu pred nežiaducou prevádzkou.

### 3.1.5 Odôvodnenie implementácie a cieľová skupina
Z hľadiska softvérového inžinierstva je kľúčovou výhodou vyvíjaného systému prísne oddelenie vrstiev aplikácie: základného vývojového inštrumentária (nezávislý framework Remedy Core, úplne zbavený hernej biznis logiky) a vysokoúrovňovej hernej logiky (serverové spracovávatele pravidiel rás a mechaník vo Finesse Races). Toto architektonické riešenie prináša nasledujúce výhody:
- Výkon (TPS/FPS): Presun biznis logiky do skompilovaného bajtkódu Java (namiesto využitia prístupu Data-Driven so súbormi JSON), ako aj ukladanie do vyrovnávacej pamäte (caching) mikro-rozhraní schopností vo fáze inicializácie, zabezpečujú rýchlosť prístupu O(1) a udržiavajú stabilnú taktovaciu frekvenciu servera (TPS).
- Integrita pravidiel (Competitive Integrity): Ochrana biznis logiky pred svojvoľným zásahom a upustenie od rasy „obyčajného človeka“ zaručujú, že všetci účastníci sa nachádzajú v rovnakých asymetrických podmienkach (Beau a Bakkes, 2016; Yeung a Lui, 2008).
- Konceptuálna kompatibilita: Upustenie od aktívnych bojových zručností predchádza konfliktom klávesových skratiek (keybind conflicts) s inými modifikáciami a zachováva aktuálnosť základného obsahu.
- Vizuálna ergonómia: Prvky GUI, realizované na báze Remedy Core, sa automaticky prispôsobujú rôznym rozlíšeniam monitorov bez skreslenia pixelovej mriežky hry, čo zabezpečuje okamžitú čitateľnosť (Visual Clarity) rozhrania v súťažnom procese.

Praktická využiteľnosť softvéru zahŕňa tri segmenty používateľov. Pre koncových hráčov projekt zabezpečuje vysokú znovuhrateľnosť vďaka zmene herných paradigiem pri hraní za rôzne rasy. Administrátori serverov pre viacerých hráčov získavajú hotové súťažné riešenie so spoľahlivou anti-cheat validáciou a predvídateľnou rovnováhou. Vývojári tretích strán môžu využiť knižnicu Remedy Core na skrátenie času uvedenia vlastných modifikácií na trh (Time-to-Market) vďaka hotovým algoritmom škálovania, kontajnerizácie GUI a zjednodušeným utilitám na interakciu s enginom.

## 3.2 Metodológia vývoja a testovania
Metodológia vývoja je založená na iteratívnom inkrementálnom prístupe (Agile). Proces je rozdelený do logických etáp:

1. Návrh architektúry: Vytvorenie základného frameworku Remedy Core a nastavenie zostavovacieho prostredia ForgeGradle.
2. Implementácia globálnych systémov: Programovanie správcu atribútov, systému kontroly výdrže, mechaniky diéty a sieťovej synchronizácie paketov medzi klientom a serverom.
3. Implementácia rás (Iteratívna): Postupné zavádzanie rás počnúc základnými (Trpaslík, Obojživelník) s následným programovaním unikátnych rasových udalostí (zachytávanie základných udalostí zbernice Forge EventBus, ako sú LivingHurtEvent, PlayerTickEvent).
4. Integrácia obsahu a GUI: Pridanie nových predmetov (artefakty, zbrane) a vývoj okien výberu rasy na báze GuiPanel.

Overovanie kvality a životaschopnosti riešenia (Testing) prebieha na viacerých úrovniach s využitím merateľných kritérií na zabezpečenie stability a súťažnej rovnováhy:
- Testovanie mechaník v izolovanom prostredí (Singleplayer): Kontrola správnosti fungovania individuálnych atribútov (poškodenie, rýchlosť, výška kroku), spúšťania rasových zraniteľností (poškodenie z vody, vznietenie) a stability rozhrania Remedy Core. Písanie interných testovacích tried na validáciu matematických výpočtov výdrže a nosnosti.
- Sieťové testovanie (Client-Server): Spustenie lokálneho dedikovaného servera (Dedicated Server) so súčasným pripojením kontrolnej skupiny 2 až 5 klientov na overenie sieťového kódu. Hlavná pozornosť je zameraná na analýzu sieťových paketov, odhaľovanie desynchronizácií (desync) stavov medzi klientom a serverom, ako aj na testovanie mechanizmov ochrany pred podvrhnutím nekorektných údajov zo strany klienta.
- Testovanie kompatibility (Cross-mod Compatibility): Spustenie modifikácie v cieľovom prostredí Minecraft 1.20.1 (na báze zavádzača Forge). Kontrola je rozdelená do dvoch kategórií. Prvou sú povinné technické závislosti (Cold Sweat, Curios API), bez ktorých jadro modifikácie nemôže fungovať. Druhou sú doplnkové a plánované integrácie na rozšírenie emergentných scenárov (Serene Seasons, Create, Alex's Caves, Sleep Tight, Sully's Mod, Antique Atlas). Cieľom je odhaliť konflikty v injekciách Mixin, duplikáciu logiky alebo logické rozpory.
- Záťažové testovanie (Performance profiling): Porovnanie ukazovateľov výkonu základnej hry Minecraft (Vanilla) a hry s nainštalovanou modifikáciou Finesse Races. Meria sa vplyv serverových spracovávaťeľov udalostí (TickHandlers) na serverový ukazovateľ TPS: cieľová hodnota predstavuje stabilných 20 TPS (bez poklesov) počas testovacej relácie v trvaní 1 – 2 hodín s aktívnymi hráčmi. Taktiež sa hodnotí vplyv vykresľovania Remedy Core na klientsky ukazovateľ snímkovej frekvencie (FPS) pri testovacom rozlíšení 2560x1600 (parameter GUI Scale: 3) na referenčnom hardvérovom vybavení (úrovne AMD Ryzen 5 5600H, NVIDIA GeForce RTX 3060).
- Testovanie systému telemetrie: Kontrola správnosti generovania paketov JSON pri rôznych scenároch úmrtí a zmeny rás. Vykonáva sa meranie špičkového počtu požiadaviek, sledovanie pomeru úspešných (HTTP 200 OK) a neúspešných odoslaní údajov, ako aj meranie sieťového oneskorenia (latency) volaní API. Validácia asynchrónneho odosielania údajov (HttpClient) prebieha v podmienkach umelo vytvoreného vysokého zaťaženia servera s cieľom potvrdiť absenciu negatívneho vplyvu na základné TPS.

---

# 4 Výsledky

## 4.1 Výsledky implementácie rás typu „Finesse“
## 4.2 Výsledky testovania hernej mechaniky
## 4.3 Výsledky testovania výkonu
## 4.4 Výsledky testovania kompatibility
## 4.5 Analýza telemetrie

---

# Záver (Náčrt)
Hlavným cieľom tejto bakalárskej práce bol návrh, vývoj a testovanie komplexného softvérového riešenia na integráciu asymetrických súťažných mechaník do prostredia sandboxu Minecraft. Opierajúc sa o výsledky vykonanej analýzy predmetnej oblasti bol stanovený cieľ úspešne dosiahnutý.

V priebehu práce bola implementovaná modifikácia Finesse Races, ktorá transformuje základný zážitok z prežitia prostredníctvom zavedenia unikátnych herných rás s vyváženým pomerom pasívnych výhod a zraniteľností. Kľúčovým vyriešeným problémom sa stala technická optimalizácia súťažného prostredia: odklon od architektúry Data-Driven (JSON) v prospech objektovo orientovaného programovania (Java) umožnil izolovať biznis logiku na strane servera. To zabezpečilo spoľahlivú ochranu pred nečestným správaním klientov (cheatingom) a zachovalo stabilný výkon servera (TPS).

Na podporu projektu bol vyvinutý nezávislý softvérový framework Remedy Core, ktorý úspešne vyriešil problém optimalizácie grafických používateľských rozhraní (GUI) vďaka aplikácii modulárnych panelov a algoritmu Nine-Slice Scaling. Okrem toho bol na zabezpečenie dlhodobej podpory projektu navrhnutý a implementovaný systém asynchrónnej telemetrie (TelemetryManager) na báze Spring Boot, ktorý zabezpečuje zber anonymizovaných (SHA-256) analytických údajov o úmrtiach hráčov a frekvencii výberu rás bez blokovania hlavného vlákna servera.

Napriek úspešnej implementácii základnej architektúry zostávajú v projekte určité nevyriešené úlohy, ktoré otvárajú priestor pre ďalší rozvoj. Hlavnou výzvou je jemné matematické vyvažovanie asymetrických mechaník, ktoré nemožno ukončiť výlučne v laboratórnych podmienkach. Riešenie tohto problému si vyžaduje dlhodobý zber a analýzu telemetrických údajov v podmienkach reálnych serverov pre viacerých hráčov. V budúcich verziách softvéru sa taktiež plánuje realizácia zložitých systémov diét pre rasy, zavedenie mechaník posmrtnej interakcie (režim duchov pre hardcore režim) a rozšírenie zoznamu dostupných postáv.

Výsledky tejto bakalárskej práce majú vysokú praktickú hodnotu a sú pripravené na nasadenie. Modifikáciu Finesse Races môžu využívať administrátori serverov a tvorcovia balíkov modifikácií (modpackov) ako spoľahlivé súťažné jadro stimulujúce emergentnú hrateľnosť. Zdrojový kód knižnice Remedy Core zas predstavuje otvorenú sadu nástrojov, ktorú môžu aplikovať vývojári tretích strán na výrazné urýchlenie a optimalizáciu procesu tvorby grafických rozhraní v ekosystéme Minecraft Forge.

---

# Zoznam bibliografických odkazov

### Kazuálnosť v hrách:
- ARZATE CRUZ, Christian a Jorge Adolfo RAMIREZ URESTI, 2017. Player-centered game AI from a flow perspective: Towards a better understanding of past trends and future directions. Entertainment Computing [online]. 2017, roč. 20, s. 11–24. ISSN 1875-9521. Dostupné na: doi:10.1016/j.entcom.2017.02.003
- JUUL, Jesper, 2010. A Casual Revolution: Reinventing Video Games and Their Players. Cambridge, MA: MIT Press. ISBN 978-0-262-01337-6.
- ALEXANDER, Justin T., John SEAR a Andreas OIKONOMOU, 2013. An investigation of the effects of game difficulty on player enjoyment. Entertainment Computing [online]. 2013, roč. 4, č. 1, s. 53–62. ISSN 1875-9521. Dostupné na: doi:10.1016/j.entcom.2012.09.001

### O súťažení a motivácii hráčov:
- BAUMEISTER, Roy F., Ellen BRATSLAVSKY, Catrin FINKENAUER a Kathleen D. VOHS, 2001. Bad is stronger than good. Review of General Psychology [online]. 2001, roč. 5, č. 4, s. 323–370. ISSN 1089-2680. Dostupné na: doi:10.1037/1089-2680.5.4.323
- RYAN, Richard M., C. Scott RIGBY a Andrew PRZYBYLSKI, 2006. The Motivational Pull of Video Games: A Self-Determination Theory Approach. Motivation and Emotion [online]. 2006, roč. 30, č. 4, s. 344–360. ISSN 0146-7239. Dostupné na: doi:10.1007/s11031-006-9051-8
- BARTLE, Richard A., 1996. Hearts, Clubs, Diamonds, Spades: Players Who Suit MUDs. Journal of MUD Research [online]. 1996, roč. 1, č. 1. Dostupné na: https://mud.co.uk/richard/hcds.htm
- HODGES, Duncan a Oliver BUCKLEY, 2018. Deconstructing who you play: Character choice in online gaming. Entertainment Computing [online]. 2018, roč. 27, s. 170–178. ISSN 1875-9521. Dostupné na: doi:10.1016/j.entcom.2018.06.002
- KAHN, Adam S., Cuihua SHEN, Li LU, Rabindra A. RATAN, Sean COARY, Jinghui HOU, Jingbo MENG, Joseph OSBORN a Dmitri WILLIAMS, 2015. The Trojan Player Typology: A cross-genre, cross-cultural, behaviorally validated scale of video game play motivations. Computers in Human Behavior [online]. 2015, roč. 49, s. 354–361. ISSN 0747-5632. Dostupné na: doi:10.1016/j.chb.2015.03.018
- NOVAK, Domen, Aniket NAGLE, Urs KELLER a Robert RIENER, 2014. Increasing motivation in robot-aided arm rehabilitation with competitive and cooperative gameplay. Journal of NeuroEngineering and Rehabilitation [online]. 2014, roč. 11, č. 64, s. 1–15. ISSN 1743-0003. Dostupné na: doi:10.1186/1743-0003-11-64
- NACKE, Lennart E., Chris BATEMAN a Regan L. MANDRYK, 2014. BrainHex: A neurobiological gamer typology survey. Entertainment Computing [online]. 2014, roč. 5, č. 1, s. 55–62. ISSN 1875-9521. Dostupné na: doi:10.1016/j.entcom.2013.06.002
- SAILER, Michael, Jan Ulrich HENSE, Sarah Katharina MAYR a Heinz MANDL, 2017. How gamification motivates: An experimental study of the effects of specific game design elements on psychological need satisfaction. Computers in Human Behavior [online]. 2017, roč. 69, s. 371–380. ISSN 0747-5632. Dostupné na: doi:10.1016/j.chb.2016.12.033
- CAGILTAY, Nergiz Ercil, Erol OZCELIK a Nese Sahin OZCELIK, 2015. The effect of competition on learning in games. Computers & Education [online]. 2015, roč. 87, s. 35–41. ISSN 0360-1315. Dostupné na: doi:10.1016/j.compedu.2015.04.001
- SCHMIDT-KRAEPELIN, Manuel, Scott THIEBES, Minh Chau TRAN a Ali SUNYAEV, 2018. What's in the Game? Developing a Taxonomy of Gamification Concepts for Health Apps. V: Proceedings of the 51st Hawaii International Conference on System Sciences (HICSS) [online]. IEEE, s. 1217–1226. ISBN 978-0-9981331-1-9. Dostupné na: doi:10.24251/HICSS.2018.150

### O rovnováhe a asymetrickom hernom dizajne:
- NETO, Abel, Pedro CARDOSO a Miguel CARVALHAIS, 2022. Asymmetric Gameplay: Types and Perspectives. V: Advances in Design and Digital Communication III [online]. Cham: Springer, s. 765–778. ISBN 978-3-031-20364-0. Dostupné na: doi:10.1007/978-3-031-20364-0_64
- BEAU, Philipp a Sander BAKKES, 2016. Automated game balancing of asymmetric video games. V: 2016 IEEE Conference on Computational Intelligence and Games (CIG) [online]. Santorini: IEEE, s. 1–8. ISBN 978-1-5090-1883-3. Dostupné na: doi:10.1109/CIG.2016.7860432
- RUPP, Florian, Manuel EBERHARDINGER a Kai ECKERT, 2024. Simulation-Driven Balancing of Competitive Game Levels With Reinforcement Learning. IEEE Transactions on Games [online]. 2024, roč. 16, č. 4, s. 903–913. ISSN 2475-1502. Dostupné na: doi:10.1109/TG.2024.3399536
- YANNAKAKIS, Georgios N. a Julian TOGELIUS, 2018. Artificial Intelligence and Games [online]. Cham: Springer. ISBN 978-3-319-63519-4. Dostupné na: doi:10.1007/978-3-319-63519-4

### O systémoch progresie, grinde a vyvažovaní bossov:
- JEON, Hyeon-Chang, In-Chang BAEK, Cheong-mok BAE, Taehwa PARK, Wonsang YOU, Taegwan HA, Hoyun JUNG, Jinha NOH, Seungwon OH a Kyung-Joong KIM, 2024. RaidEnv: Exploring New Challenges in Automated Content Balancing for Boss Raid Games. IEEE Transactions on Games [online]. 2024, roč. 16, č. 3, s. 645–658. ISSN 2475-1502. Dostupné na: doi:10.1109/TG.2023.3335399
- CRUZ, Carlos, Michael D. HANUS a Jesse FOX, 2017. The need to achieve: Players' perceptions and uses of extrinsic meta-game reward systems for video game consoles. Computers in Human Behavior [online]. 2017, roč. 71, s. 516–524. ISSN 0747-5632. Dostupné na: doi:10.1016/j.chb.2015.08.017
- TYNI, Janne, Aatu TURUNEN, Juho KAHILA, Roman BEDNARIK a Matti TEDRE, 2023. Reward Types in Popular Recreational and Educational Mobile Games. IEEE Access [online]. 2023, roč. 11, s. 1165–1174. ISSN 2169-3536. Dostupné na: doi:10.1109/ACCESS.2022.3231936

### O technológiách (Architektúra hry Minecraft, anti-cheat synchronizácia a dizajn Java API):
- ENGELBRECHT, Herman A. a Gregor SCHIELE, 2014. Transforming Minecraft into a research platform. V: 2014 IEEE 11th Consumer Communications and Networking Conference (CCNC) [online]. Las Vegas: IEEE, s. 561–566. ISBN 978-1-4799-2355-7. Dostupné na: doi:10.1109/CCNC.2014.6866580
- YEUNG, Siu Fung a John C. S. LUI, 2008. Hack-proof synchronization protocol for multi-player online games. Multimedia Tools and Applications [online]. 2008, roč. 40, č. 3, s. 357–368. ISSN 1380-7501. Dostupné na: doi:10.1007/s11042-008-0230-3
- TULACH, Jaroslav, 2008. Practical API Design: Confessions of a Java Framework Architect [online]. Berkeley, CA: Apress. ISBN 978-1-4302-0973-7. Dostupné na: doi:10.1007/978-1-4302-0974-4
- AMPATZOGLOU, Apostolos a Ioannis STAMELOS, 2010. Software engineering research for computer games: A systematic review. Information and Software Technology [online]. 2010, roč. 52, č. 9, s. 888–901. ISSN 0950-5849. Dostupné na: doi:10.1016/j.infsof.2010.05.004
- LEE, Daniel, Dayi LIN, Cor-Paul BEZEMER a Ahmed E. HASSAN, 2020. Building the perfect game – an empirical study of game modifications. Empirical Software Engineering [online]. 2020, roč. 25, č. 4, s. 2485–2518. ISSN 1382-3256. Dostupné na: doi:10.1007/s10664-019-09783-w

### Herné novinky a oznámenia hry Minecraft:
- CHEN, Jackson, 2026. Minecraft Is Getting A New Dimension Called The Sift In 2027. Engadget [online] [cit. 2026-11-01]. Dostupné na: https://www.engadget.com/2269867/minecraft-is-getting-a-new-dimension-called-the-sift-in-2027/
- MOJANG STUDIOS, 2021. Check out this record-breaking survival gold farm! Minecraft.net [online]. [cit. 2026-11-10]. Dostupné na: https://www.minecraft.net/en-us/article/check-out-record-breaking-survival-gold-farm-
- CALDWELL, Brendan, 2016. Ridealong: The Ruin Of Minecraft’s Most Obscene Server. Rock Paper Shotgun [online]. [cit. 2026-11-10]. Dostupné na: https://www.rockpapershotgun.com/minecrafts-most-obscene-server

### O technológiách:
- MINECRAFT FORGE CONTRIBUTORS, 2024. Forge Documentation [online] [cit. 2026-11-09]. Dostupné na: https://docs.minecraftforge.net/
- FABRICMC, 2024. Fabric Wiki and Documentation [online] [cit. 2026-11-09]. Dostupné na: https://docs.fabricmc.net/
- NEOFORGED, 2024. NeoForge Documentation: Data Attachments [online] [cit. 2026-11-09]. Dostupné na: https://docs.neoforged.net/docs/datastorage/attachments/
- PARCHMENTMC, 2024. ParchmentMC Wiki [online] [cit. 2026-11-09]. Dostupné na: https://parchmentmc.org/docs/

### Modifikácie:
- APACE100, 2021. Origins (Forge) — Minecraft Mods. CurseForge [online] [cit. 2026-10-20]. Dostupné na: https://www.curseforge.com/minecraft/mc-mods/origins-forge
- CONCZIN, 2022. Immersive Aircraft [Airships, Biplanes, Quadrocopters] — Minecraft Mods. CurseForge [online] [cit. 2026-10-20]. Dostupné na: https://www.curseforge.com/minecraft/mc-mods/immersive-aircraft
- DARIPHER, 2023. Passive Skill Tree — Minecraft Mods. CurseForge [online] [cit. 2026-10-20]. Dostupné na: https://www.curseforge.com/minecraft/mc-mods/passive-skill-tree
- EVOCATION, 2024. Field Guide — Minecraft Mods. CurseForge [online] [cit. 2026-10-20]. Dostupné na: https://www.curseforge.com/minecraft/mc-mods/field-guide
- IRON431, 2023. Iron's Spells 'n Spellbooks — Minecraft Mods. CurseForge [online] [cit. 2026-10-20]. Dostupné na: https://www.curseforge.com/minecraft/mc-mods/irons-spells-n-spellbooks
- L_ENDER, 2022. L_Ender's Cataclysm — Minecraft Mods. CurseForge [online] [cit. 2026-10-20]. Dostupné na: https://www.curseforge.com/minecraft/mc-mods/lendercataclysm
- LIMITABLE, 2024. Sortilege — Minecraft Mods. CurseForge [online] [cit. 2026-10-20]. Dostupné na: https://www.curseforge.com/minecraft/mc-mods/sortilege
- M3T4L_G34R, 2023. Class Enhancement — Minecraft Mods. CurseForge [online] [cit. 2026-10-20]. Dostupné na: https://www.curseforge.com/minecraft/mc-mods/class-enhancement
- MANINTHE_HOME, 2020. Epic Fight — Minecraft Mods. CurseForge [online] [cit. 2026-10-20]. Dostupné na: https://www.curseforge.com/minecraft/mc-mods/epic-fight-mod
- MONGOOSE_ARTIST, 2022. Born in Chaos — Minecraft Mods. CurseForge [online] [cit. 2026-10-20]. Dostupné na: https://www.curseforge.com/minecraft/mc-mods/born-in-chaos
- NINNI, 2023. Species — Minecraft Mods. CurseForge [online] [cit. 2026-10-20]. Dostupné na: https://www.curseforge.com/minecraft/mc-mods/species
- ROSEMODS, 2023. Heart Crystals — Minecraft Mods. CurseForge [online] [cit. 2026-10-20]. Dostupné na: https://www.curseforge.com/minecraft/mc-mods/heart-crystals
- SIMIBUBI, 2019. Create — Minecraft Mods. CurseForge [online] [cit. 2026-10-20]. Dostupné na: https://www.curseforge.com/minecraft/mc-mods/create
- TEAMABNORMALS, 2020. Savage & Ravage — Minecraft Mods. CurseForge [online] [cit. 2026-10-20]. Dostupné na: https://www.curseforge.com/minecraft/mc-mods/savage-and-ravage
- BLACKAURES, 2026. Dragon Survival — Minecraft Mods. CurseForge [online] [cit. 2026-11-09]. Dostupné na: https://www.curseforge.com/minecraft/mc-mods/dragons-survival
- MUONR, 2026. Medieval Origins Revival — Minecraft Mods. CurseForge [online] [cit. 2026-11-09]. Dostupné na: https://www.curseforge.com/minecraft/mc-mods/medieval-origins-revival

### Kniha JEB:
- BERGENSTEN, Jens, 2019. Minecraft Game Design [online]. Stockholm: Mojang Studios [cit. 2026-10-19]. Dostupné na: https://archive.org/details/minecraftgamedesign_202308/Jeb_ENG

---

# Zoznam príloh

## Príloha A - Zdrojový kód softvéru

### A1 Hlavná modifikácia (Finesse Races)
- Účel: Implementácia systémov asymetrických rás, pasívnych mechaník, integrácie s modifikáciami a používateľského rozhrania.
- Odkaz na repozitár: https://github.com/Bruskych/Finesse-Races
- 
### A2 Modul telemetrie (Finesse Races Telemetry)
- Účel: Serverový doplnok (na báze Spring Boot) na asynchrónny zber analytických metadát a súťažných štatistík.
- Odkaz na repozitár: https://github.com/Bruskych/Finesse-Races-Telemetry

### A3 Základný softvérový framework (Remedy Core)
- Účel: Nezávislá základná knižnica obsahujúca systémové mixiny, pomocné utility, ako aj štandardizované nástroje na vykresľovanie modulárnych prvkov GUI a škálovanie (Nine-Slice Scaling).
- Odkaz na repozitár: https://github.com/Bruskych/Remedy-Core

## Príloha B - Analýza kazualizácie a evolúcie herného dizajnu hry Minecraft

### B1 Fenomén kazualizácie a vstupný prah
V teórii herného dizajnu sa pod kazuálnosťou rozumie vlastnosť videohry byť intuitívne zrozumiteľnou a prístupnou pre čo najširšie publikum. Pri opise fundamentálnych princípov kazuálnosti Jesper Juul zdôrazňuje, že "kazuálny herný dizajn [...] sa vyznačuje zhovievavými trestami za chyby" (Juul, 2010, s. 50), zatiaľ čo hardcore projekty nútia hráča platiť za zlyhanie vážnou stratou postupu. Nadmerná kazuálnosť v sebe nesie deštruktívny potenciál. Opierajúc sa o teóriu stavu flow, výskumníci zdôrazňujú nevyhnutnosť vyvažovania výziev a uvádzajú, že "efektívny systém dynamického škálovania náročnosti, postavený okolo konštruktov stavu flow, musí dokázať prispôsobovať úroveň náročnosti [...], aby sa predišlo negatívnym duševným stavom, akými sú nuda, apatia alebo úzkosť" (Arzate Cruz a Ramirez Uresti, 2017, s. 15).

Na posúdenie vplyvu prísnosti pravidiel je vhodné porovnať Minecraft s etalónom nekompromisného prežitia — hrou Don't Starve. Základný Minecraft disponuje extrémne nízkym vstupným prahom: pravidlá sú intuitívne zrozumiteľné a mechanika smrti je zhovievavá. Naproti tomu hra Don't Starve neodpúšťa chyby a cenou za smrť je nenávratná strata sveta.

Problémom komunity hry Minecraft je umelé posilňovanie kazuálnosti prostredníctvom modifikácií:
- Minimapy a radary stierajú význam priestorovej orientácie.
- Systémy okamžitej teleportácie znehodnocujú logistickú progresiu.
- Vypnutie straty inventára ("keepInventory") odstraňuje základný princíp žánru survival — hodnotu života postavy, čím prudko znižuje záujem skúsených hráčov (Alexander et al., 2013). Tým, že sa hráči pripravia o stimuly z kategórie motivácie „vyhýbania sa“, strácajú zmysel zotrvávania vo svete.

### B2 Evolúcia filozofie dizajnu
Evolúciu hry možno rozdeliť na dve etapy. V ére tvorcu Markusa Perssona (Notch) sa projekt vyvíjal ako nezávislá (indie) hra so zameraním na drsné prežitie (mechanika hladu, dimenzia Nether). Súčasný prístup korporácie Microsoft sa vyznačuje minimalizáciou rizík. Vývojári sa snažia vyhovieť širokému publiku a rozširujú hru „do šírky“ (dekoratívne bloky, mobovia) bez narušenia zaužívanej rovnováhy. Vzácnou výnimkou bolo v roku 2026 oznámenie štvrtej dimenzie The Sift (Chen, 2026), ktoré dokazuje potrebu kardinálnych štrukturálnych zmien na udržanie publika.

## Príloha C - Podrobný prehľad modifikácií tretích strán pre hru Minecraft

### C1 - Modifikácie s vertikálnou progresiou a nekompatibilnými žánrovými mechanikami
Táto kategória modifikácií sa v komunite teší vysokej popularite, keďže preberá známe mechaniky z klasických hier na hrdinov (RPG) a akčných hier. Ich podrobné testovanie a analýza však odhaľujú závažné problémy s kompatibilitou so základným herným dizajnom sandboxu Minecraft.

1. Modifikácia „Passive Skill Tree“ (Daripher, 2023)
- Princíp fungovania: Modifikácia zavádza rozvetvený strom pasívnych zručností analogický systémom z klasických RPG. Hráč míňa body skúseností na odomykanie uzlov stromu, ktoré lineárne zvyšujú základné atribúty postavy (napríklad rýchlosť letu šípov vo vetve lukostrelca, poškodenie v boji nablízko, ukazovatele obrany a zdravia).
- Kritické zhodnotenie: Napriek prvotnej príťažlivosti a súťažnému stimulu k rozvoju postavy vykazuje tento systém konceptuálnu nekompatibilitu so statickým svetom hry Minecraft. V hre chýba mechanizmus dynamického škálovania náročnosti prostredia (automatické prispôsobovanie úrovne sveta). Keďže strom zručností poskytuje výlučne pozitívne modifikátory (buffy) bez akýchkoľvek kompenzačných postihov (debuffov), v stredných a neskorších fázach hry dochádza k úplnému znehodnoteniu základných mechaník prežitia. Herný proces presúva ťažisko vnútornej motivácie na monotónny grind skúseností za účelom zvyšovania číselných ukazovateľov (Cruz et al., 2017). V prostredí pre viacerých hráčov to vytvára neprekonateľnú priepasť medzi novými hráčmi a používateľmi, ktorí na serveri strávili viac času, pričom víťazstvo v PvP nie je určené taktickou zručnosťou, ale počtom odomknutých uzlov v strome vylepšení.

2. Modifikácia „L_Ender's Cataclysm“ (L_Ender, 2022)
- Princíp fungovania: Rozsiahla modifikácia orientovaná na bojovú interakciu vo formáte Boss-rush. Do hry pridáva zložité štruktúry, vysokokvalifikovaných bossov, ako aj unikátne druhy zbraní, brnení a artefaktov vyrábaných z trofejí po víťazstve nad týmito bossmi.
- Kritické zhodnotenie: Projekt je klasickým príkladom „inflácie sily“ (power creep) a parazitného herného dizajnu (parasitic design). Zavedenie silných bossov úplne presúva pozornosť hráča z prieskumnej a tvorivej činnosti na uzavretý bojový cyklus: „zabitie bossa za účelom získania silnej zbrane na zabitie ďalšieho bossa“. Výbava z modifikácie mnohonásobne prevyšuje pôvodné alternatívy (vrátane netheritovej výstroje), čím úplne znehodnocuje štandardný systém progresie (Jeon et al., 2024). Okrem toho modifikácia porušuje fundamentálne princípy herného dizajnu hry Minecraft, ktoré sformuloval hlavný vývojár hry Jens Bergensten (Bergensten, 2019): nové predmety nesmú prísne dominovať nad existujúcimi a znehodnocovať starý obsah a každý nový prvok musí mať minimálne dve a viac využití v rôznych sférach hry. Predmety v L_Ender's Cataclysm majú úzko špecializované, jednorazové využitie výlučne na vedenie boja. Po zničení všetkých bossov strácajú modifikácia aj samotná hra zmysel a zanechávajú hráča s narušenou rovnováhou sveta a absenciou ďalšej motivácie.

3. Modifikácia „Iron's Spells 'n Spellbooks“ (Iron431, 2023)
- Princíp fungovania: Zavádza do hry komplexný systém mágie zahŕňajúci ukazovateľ many, magické palice, zvitky kúziel, špecializované brnenie, ako aj nové štruktúry a nepriateľských mágov. Umožňuje hráčom učiť sa a vylepšovať aktívne bojové a pomocné kúzla.
- Kritické zhodnotenie: Modifikácia trpí kritickou nevyváženosťou a nahrádzaním základných herných podsystémov. Kúzla disponujú nadmerne vysokými ukazovateľmi jednorazového a periodického poškodenia, pre ktoré v štandardnom Minecrafte chýbajú adekvátne ciele a výzvy. V dôsledku toho hráč úplne stráca pocit nebezpečenstva zo strany okolitého sveta. Z hľadiska systémového dizajnu modifikácia znehodnocuje pôvodné mechaniky alchýmie (varenia elixírov) a očarovania: pre hráča zaniká potreba variť elixíry alebo kombinovať knihy očarovaní, pretože magické zvitky a palice poskytujú silnejšie a dostupnejšie ekvivalenty týchto efektov. Zavedenie aktívnych kúziel s časom opätovného nabitia (cooldownom) mení prežitie na arkádovú bojovú hru (slasher), ktorá dokáže fungovať len na úzko špecializovaných serveroch s umelo navýšenými štatistikami monštier.

4. Modifikácia „Born in Chaos“ (Mongoose_Artist, 2022)
- Princíp fungovania: Modifikácia sa sústreďuje na rozšírenie beštiára (pridaním desiatok nových nepriateľských tvorov a minibossov) a arzenálu výzbroje (kosy, dýky, strelné zbrane, špecifické brnenie).
- Kritické zhodnotenie: Na pozadí predchádzajúcich projektov sa táto modifikácia snaží organickejšie zapadnúť do základnej hry a stimulovať súťažného ducha prostredníctvom sťaženia prežitia a variability výberu zbraní. Projekt však trpí problémom „nafukovania obsahu“ (content bloat) a fragmentácie výrobnej ekonomiky. Obrovské množstvo nových monštier disponuje unikátnymi trofejami (korisťou — lootom), ktoré sa využívajú v prísne izolovaných, lineárnych reťazcoch tvorby predmetov (napríklad: zabitie vlkolaka $\rightarrow$ získanie kože $\rightarrow$ výroba jedného konkrétneho plášťa). Tieto materiály nie sú nijako integrované do celkového ekosystému základnej hry (Bergensten, 2019), čo vedie k zahlteniu inventára hráča úzko špecializovanými zdrojmi, ktoré nemajú hodnotu mimo jedného konkrétneho receptu.

5. Modifikácia „Immersive Aircraft“ (Conczin, 2022)
- Princíp fungovania: Modifikácia pridáva do hry rad vzdušných dopravných prostriedkov (vzducholode, dvojplošníky, kvadrokoptéry, vírniky), ktoré sa vyrábajú zo základných zdrojov (drevo, železo, tkanina) v raných fázach prežitia a fungujú na uhlie. Umožňuje inštalovať na dopravné prostriedky výzbroj (guľomety, delá) a vykonávať bombardovanie územia dynamitom.
- Kritické zhodnotenie: Kľúčovým problémom modifikácie je úplné znehodnotenie pôvodného systému logistiky a progresie presunu. V základnej hre majú pozemná doprava (kone, ťavy, ošípané, vozíky) a elixíry rýchlosti prísne obmedzený potenciál, čo pre hráča formuje najdôležitejší dlhodobý cieľ — víťazstvo nad Ender drakom a hľadanie miest v dimenzii End (End Cities) za účelom získania krídel Elytra (predmetu záverečnej fázy hry poskytujúceho slobodu letu). Immersive Aircraft túto cieľovú progresiu rúca tým, že poskytuje lacné a bezpečné lietanie už v prvých hodinách hry. V dôsledku toho stráca prieskum sveta zo zeme aktuálnosť, reliéf terénu prestáva byť prekážkou a hráč stráca stimul na prechádzanie záverečnej dimenzie hry.

6. Modifikácia „Epic Fight“ (Maninthe_home, 2020)
- Princíp fungovania: Globálne prepracováva bojový systém hry Minecraft podľa vzoru hier žánru Souls-like. Zavádza prepínateľný režim „bojového postoja“, v ktorom sú údery sprevádzané dlhými neprerušiteľnými animáciami (animation locks) v závislosti od typu zbrane, a taktiež pridáva systém zdokonaľovania bojových zručností a duplicitný arzenál chladných zbraní.
- Kritické zhodnotenie: Mechanika Souls-like s fixáciou postavy v dlhých animáciách úderov konceptuálne nekorešponduje s fyzikou a dynamikou voxelového sandboxu. V hre Minecraft je bojový systém nerozlučne spätý s priestorovým pohybom (movementom) a okamžitou interakciou s prostredím: počas súboja hráč súčasne stavia prekážky, ničí bloky, používa elixíry, jedlo a pomocné predmety. Umelé rozdelenie ovládania na „stavebný“ a „bojový“ režim narúša celistvosť herného procesu. Zbrane pridávané modifikáciou navyše nenesú unikátnu funkcionalitu pre interakciu so svetom a od základného meča sa líšia výlučne vizuálnymi animáciami a číselnými parametrami poškodenia.

### C2 - Modifikácie s horizontálnou progresiou a organickou integráciou súťažných mechaník
Ako protiklad k projektom s vertikálnou progresiou existuje viacero modifikácií, ktoré demonštrujú vzorové dodržiavanie princípov herného dizajnu hry Minecraft (Bergensten, 2019). Súťažné prostredie formujú prostredníctvom rozširovania taktických možností a prehlbovania základných mechaník.

1. Modifikácia „Savage & Ravage“ (TeamAbnormals, 2020)
- Princíp fungovania: Modifikácia komplexne rozširuje pôvodný systém nájazdov (útokov nájazdníkov na dediny) a mechaniky creeperov. Pridáva nových účastníkov nájazdov s unikátnym taktickým správaním, špecializované brnenie odolné voči výbuchom, ako aj vzácne druhy taktických zbraní a predmetov, ktoré s malou pravdepodobnosťou vypadávajú pri víťazstve nad nájazdníkmi.
- Kritické zhodnotenie: Projekt je príkladom kvalitného horizontálneho rozšírenia hry. V štandardnom Minecrafte sleduje vyvolanie nájazdov jediný cieľ — získanie Totemu nesmrteľnosti. Savage & Ravage obohacuje systém odmeňovania (Tyni et al., 2023) a ponúka hráčom vyvážené, situačne užitočné artefakty (napríklad výstroj odolnú voči výbuchom, ktorá v bežnom boji nenahrádza diamantové brnenie, no proti výbuchom je nenahraditeľná). Ďalším faktorom formovania súťažného prostredia je pravdepodobnostné obmedzenie vzácnych zdrojov: unikátne trofeje (ako sú masky a špecializované zbrane vrátane ľadovej palice) vypadávajú s minimálnou šancou. Nízka pravdepodobnosť získania cenného predmetu mení prechádzanie nájazdov na napínavé súperenie medzi hráčmi o vlastníctvo vzácnych taktických artefaktov, čo motivuje používateľov dobrovoľne zvyšovať úroveň náročnosti nájazdov.

2. Modifikácia „Heart Crystals“ (Rosemods, 2023)
- Princíp fungovania: Konfigurovateľná modifikácia, ktorá mení štartovaciu zásobu zdravia hráča. Na začiatku prežitia je maximálne zdravie postavy umelo obmedzené (napríklad na 3 srdcia namiesto štandardných 10). Na postupné zvýšenie limitu zdravia na základnú hodnotu musí hráč preskúmavať hlboké jaskyne a hľadať vzácne kryštály sŕdc.
- Kritické zhodnotenie: Modifikácia efektívne rieši problém príliš rýchleho štartu v súťažnom prežití. Umelé oslabenie hráča v počiatočnej fáze zvyšuje hodnotu života, spomaľuje tempo prechádzania hry a stimuluje hĺbkový prieskum podzemných biómov. Osobitnú pozornosť si zaslúži dodržanie pravidla multifunkčnosti materiálov (Bergensten, 2019): po tom, čo si hráč obnoví maximálnu zásobu zdravia, sa kryštály sŕdc nestávajú zbytočným zdrojom — autor ich integroval do pôvodného systému varenia elixírov (výroba elixírov regenerácie) a receptov funkčných stavebných blokov.

3. Modifikácia „Sortilege“ (Limitable, 2024)
- Princíp fungovania: Zavádza vyvážený systém mágie a prepracováva mechaniky skúseností a očarovania v štylistike Vanilla+. Zavádza bojové palice na strednú vzdialenosť, systém „protijedov“ (elixírov blokujúcich uloženie určitých efektov), stanovuje základný limit 3 očarovaní na predmet (rozšíriteľný len pomocou vzácneho zdroja) a vyrovnáva matematickú stupnicu úrovní skúseností.
- Kritické zhodnotenie: Na rozdiel od skôr analyzovanej modifikácie Iron's Spells 'n Spellbooks projekt Sortilege nerúca základné podsystémy hry, ale harmonicky ich dopĺňa. Magické palice vystupujú v úlohe horizontálnej alternatívy (sidegrade): obsadzujú segment zbraní stredného dosahu (medzi mečom a lukom) a pri streľbe spotrebúvajú body skúseností hráča namiesto ukazovateľa životnosti. Zavedenie protijedov prehlbuje taktický súťažný potenciál v PvP súbojoch a umožňuje hráčom vopred reagovať na stratégie protivníka. Osobitnú pozornosť si zaslúži systém limitovania očarovaní: obmedzenie počtu očarovaní zabraňuje triviálnej a masovej výrobe „absolútnej“ výbavy v raných fázach hry a núti používateľov robiť premyslené taktické rozhodnutia pri zostavovaní výstroje. Zároveň na serveroch pre viacerých hráčov s uzavretými hranicami sveta (obmedzeným územím) prítomnosť vzácneho neobnoviteľného zdroja, ktorý umožňuje obísť tento limit, formuje napínavé súťažné „preteky o absolútnu výbavu“. Nedostatok tohto materiálu v uzavretom priestore motivuje hráčov k rýchlostnému prieskumu sveta, boju o kontrolu nad miestami výskytu zdrojov a priamemu súpereniu o technologickú prevahu na serveri.

4. Modifikácia „Species“ (Ninni, 2023)
- Princíp fungovania: Do ekosystému hry pridáva prepracované nepriateľské, neutrálne a priateľské tvory viazané na konkrétne biómy, ktoré disponujú unikátnymi mechanikami správania a trofejami.
- Kritické zhodnotenie: Porovnanie projektov Species a Born in Chaos názorne demonštruje rozdiel medzi systémovým a izolovaným herným dizajnom. V modifikácii Species je každý nový zdroj integrovaný do existujúcich pôvodných mechaník s cieľom vyriešiť ich slabé miesta. Napríklad trofejný tesák vlkolaka umožňuje upraviť málo populárnu základnú kušu na rýchlopalnú viacranovú alternatívu. Nová zbraň pritom neznehodnocuje obyčajný luk, pretože má menší dostrel, čím sa zachováva horizontálna rovnováha. Podobným spôsobom modifikácia oživuje pôvodný systém archeológie (ktorý je v základnej hre obmedzený na hľadanie jedného dekoratívneho moba — Ňucháča / Sniffera) pridaním skamenených vajec pravekých tvorov, ktoré disponujú praktickými funkciami pomoci hráčovi pri stavaní. Dôležitý súťažný stimul sa tu formuje vďaka priestorovému obmedzeniu generovaných štruktúr a archeologických zón vo svete. Keďže počet takýchto objektov a v nich obsiahnutej unikátnej koristi je prísne limitovaný (najmä na serveroch s obmedzenou veľkosťou mapy), vznikajú medzi hráčmi prirodzené „preteky prieskumníkov“ o to, kto rýchlejšie objaví štruktúry a monopolizuje vzácne neobnoviteľné zdroje.

5. Modifikácia „Field Guide“ (Evocation, 2024)
- Princíp fungovania: Integruje do hry interaktívny beštiár (encyklopédiu flóry a fauny). Skúmanie tvorov a rastlín prebieha namierením pôvodného ďalekohľadu (Spyglass) na daný objekt. Po naskenovaní sa v príručke otvorí podrobná štatistika objektu (zásoba zdravia, brnenie, tabuľka vypadávajúcich zdrojov s percentami pravdepodobnosti), ako aj možnosť zanechávať vlastné poznámky a zdieľať ich s ostatnými hráčmi.
- Kritické zhodnotenie: Projekt demonštruje, ako možno súťažnú mechaniku realizovať prostredníctvom informačnej prevahy a zberateľstva, čo priamo oslovuje hráčske psychotypy „Prieskumníkov“ a „Perfekcionistov“ (Completionists) (Kahn et al., 2015). Po prvé, modifikácia dáva dôležité praktické využitie pôvodnému ďalekohľadu, ktorý sa v základnej hre prakticky nepoužíva. Po druhé, vypĺňanie beštiára formuje na serveroch pre viacerých hráčov súťažnú metriku postupu (analogicky k systému pokrokov) a odmeňuje hráča užitočnými referenčnými údajmi o okolitom svete.

6. Modifikácia „Create“ (Simibubi, 2019)
- Princíp fungovania: Najväčšia inžiniersko-priemyselná modifikácia pridávajúca systém rotačnej kinetiky (ozubené kolesá, hriadele, vodné kolesá, parné motory), dopravníky a modulárne mechanizmy na automatizáciu ťažby zdrojov, logistiky a stavania.
- Kritické zhodnotenie: Create je etalónom horizontálneho herného dizajnu v moddingu hry Minecraft. Modifikácia nedáva hráčovi hotové „magické bloky“, ktoré vykonávajú prácu stlačením jedného tlačidla, ale poskytuje základné fyzikálne komponenty. Súťažný aspekt je tu úplne presunutý z roviny rýchlosti reakcií v boji do roviny inžinierskeho myslenia: hráči na serveroch súťažia v navrhovaní najkompaktnejších, najvýkonnejších a najoptimalizovanejších automatických tovární a mechanizmov.

## Príloha D — Podrobná špecifikácia a GDD herných rás

Upozornenie (Akademická výhrada): Nižšie uvedená špecifikácia predstavuje „živý“ koncepčný dokument (Game Design Document) v čase písania bakalárskej práce. Vývoj softvéru prebieha podľa flexibilnej iteratívnej metodológie (Agile). V tejto súvislosti je nevyhnutné jasne rozlíšiť stavy nižšie opísaných mechaník:
1. Navrhnuté: Celá konceptuálna logika rás, ich silné a slabé stránky opísané v tejto prílohe sú kompletne navrhnuté na papieri.
2. Implementované: Základné architektonické systémy (predefinovanie rýchlosti chôdze, plávania, výšky kroku), systém telemetrie a základné rasy (Trpaslík, Obojživelník, Enderec) sú už implementované v zdrojovom kóde dostupnom v repozitári na platforme GitHub.
3. Plánované do budúcnosti: Zložité integrácie s modifikáciami tretích strán (napríklad systém mágie pre Mimozemšťana s modifikáciou Alex's Caves alebo závislosť Lesného ducha od ročných období zo Serene Seasons), ako aj finálna etapa nastavenia presných percentuálnych hodnôt atribútov sa nachádzajú v štádiu vývoja a vyvažovania.

Poznámka k hodnotám atribútov:
Keďže matematické vyvažovanie asymetrického systému si vyžaduje dlhodobé testovanie a zber telemetrie od reálnych hráčov, sú v tejto špecifikácii presné číselné a percentuálne hodnoty (napríklad "+15 % k rýchlosti") zámerne nahradené kvalitatívnymi ukazovateľmi vektorov vyvažovania. Tieto značky určujú *smer* zmeny základnej (vanilla) hodnoty atribútu:
- (Silno +): Radikálne zvýšenie základného atribútu, ktoré vystupuje ako hlavná konkurenčná výhoda rasy (výrazný buff).
- (Slabo +): Mierne zlepšenie zabezpečujúce komfort hrania (quality of life).
- (Slabo -): Menší postih vyžadujúci si zvyknutie.
- (Silno -): Kritická zraniteľnosť rasy, ktorá vyvoláva nevyhnutnosť úplnej zmeny stratégie prežitia (výrazný debuff).

### Globálne zmeny pre každú rasu:
1. Atribúty pohybu: Rýchlosť chôdze, rýchlosť plávania, rýchlosť ponárania a výška kroku (umožňuje prekonávať bloky bez skoku).
2. Teplota a vlhkosť: Individuálna odolnosť voči teplote a vlhkosti (integrácia s modifikáciou Cold Sweat).
3. Nosnosť a výdrž: Individuálny ukazovateľ hmotnosti brnenia ovplyvňujúci vzdialenosť kotúľov, rýchlosť ich opätovného nabitia a spotrebu výdrže.
4. Systém diéty: Postihy na nasýtenie pri konzumácii jedla mimo stravovacieho režimu rasy (sladkosti sú výnimkou).
5. Fyziológia a estetika: Individuálny vzrast (ovplyvňujúci hrateľnosť a požadovanú výšku stropov prostredníctvom škálovania jednotlivých častí tela), unikátne zvuky, štartovacia sada predmetov a individuálna textúra ikony teploty.

### Globálne mechaniky a predmety:
1. Sféra znovuzrodenia: Umožňuje zmeniť rasu v strednej/neskorej fáze hry.

2. Nové stavové efekty:
- Mastnota (elixíry neexistujú): Umožňuje rýchlo vyletieť z vody bez klávesu Shift, pasívne vyplávať na hladinu, ignorovať poškodenie z kvapalín a pomaly levitovať počas dažďa.
- Premočenie: Zabraňuje strate vlhkosti.
- Krvavosť: Poskytuje efekt vampirizmu.
- Krvácanie: Spôsobuje poškodenie pri pohybe.
- Prekliatie: Úplne zakazuje regeneráciu zdravia (z jedla, elixírov a iných zdrojov).
- Mor (Čierna smrť): Choroba v 3 štádiách. Získava sa konzumáciou zhnitého mäsa alebo od infikovaných mobov. Prenáša sa na všetky entity v blízkosti. Nelieči sa mliekom, spánkom ani smrťou (vyžaduje sa liek).
- Elektrošok: Dočasná strnulosť a zákaz používania predmetov v rukách. Aplikuje sa bleskom a elektrickými medúzami. K dispozícii je elixír elektrošoku.

3. Vylepšený systém vlhkosti (Cold Sweat):
- Postih klzkých rúk: Pri vysokej vlhkosti (alebo Mastnote) existuje šanca na upustenie predmetu pri kopaní či útoku. Šanca sa zvyšuje vo vode, v daždi alebo pri utŕžení poškodenia.
- Imunity: Rasa Obojživelník má úplnú imunitu voči vypadávaniu predmetov. Trojzubce a Harpúny nie je možné upustiť.
- Ochrana: Spotrebný predmet "Lepidlo" (chráni 3-krát). Očarovania "Úchop / Mŕtvy úchop" (úplná ochrana pred vypadnutím). Očarovania "Dehydratácia / Odzbrojenie" (vybíjajú zbraň nepriateľovi).
- Utopenci: Ich útoky aplikujú vlhkosť a so zvýšenou šancou vybíjajú predmety z rúk.

4. Liečenie a rastliny: Skazokvet (rastie v močiaroch/pod vodou, jednorazovo lieči Mor, komponent pre vakcínu). Výťažok z liečivej plesne (získava sa z machu, lieči Mor a poskytuje imunitu na niekoľko dní).

5. Dáždniky a ochrana:
- Dáždniky (predmet): Vyrábajú sa, majú životnosť, dajú sa farbiť, chránia pred slnkom, vlhkosťou, dažďom a rozprašovačmi z modifikácie Create. Môžu sa používať na plachtenie (spotrebúva sa životnosť, pri vysokej hmotnosti sa lámu). V daždi je let náročnejší a existuje šanca na zásah bleskom. Dedinčania používajú dáždniky počas dažďa.
- Vylepšenia dáždnikov: Medené (bleskozvod), očarovania na vzlietnutie nad táborákmi, levitácia pri podržaní klávesu Shift. V dimenzii Nether horia (vyžaduje sa vylepšenie). K dispozícii je Klobúk s dáždnikom.

6. Ostatné predmety:
- Rybí tuk: Nanáša sa na brnenie/mobov alebo sa konzumuje (poskytuje Mastnotu).
- Uterák: Vyrába sa z tkaniny, má životnosť, odstraňuje vlhkosť (možno ním utierať iných hráčov).
- Prilba so sviečkou: Poskytuje pasívne osvetlenie, sviečka sa postupne spotrebúva.
- Štruktúry: Meteority (obsahujú zdroje pre Trpaslíka a Robota).

---

1. Trpaslík (Dverg, Podhorník) - Plánované
Možnosti tela: Poškodenie holou rukou (Silno +), Chôdza (Slabo -), Plávanie (Slabo -), Ponáranie (Silno +), Chlad (Silno +), Horúčava (Slabo -), Nosnosť a Výdrž (Silno +).
   
Výhody:
- Zvýšená rýchlosť kopania krompáčom a vstavané šťastie pri ťažbe (Fortune).
- Znížené vyčerpávanie hladu pri kopaní.
- Zvýšená odolnosť voči efektom alkoholu.
- Znížené náklady skúseností na opravu, zvýšená životnosť nákovy.
- Možnosť výroby a nosenia artefaktových prsteňov (v slotoch Curios) pre bonusy (šanca na kritický zásah, šanca na korisť, tŕne, rýchlosť útoku, poškodenie, zmena odrazenia — knockbacku).
  
Nevýhody:
- Jaskynná diéta (huby, mach, pavúky, jaskynné jedlo).
- Spánok je možný výlučne hlboko pod zemou.
- Slabosť pri plávaní.
- Znížený dosah pri ukladaní a ničení blokov.
  
Vlastnosti a vizuál: Vstavaný pracovný stôl v inventári. Ukazovateľ alkoholu (nutnosť piť). Brada (poskytuje ochranu pred chladom, dá sa ostrihať na nite). Vzrast 1 – 1.5 bloku. Častice prachu pri ťažbe kameňa/rudy.
Súvisiace predmety/bloky: Nové drahokamy (ignit, rubín, zafír) a stôl drahokamov (dočasné posilnenie zbraní). Opustené podzemné vyhne. Nástroje: Vrták a Píla (z liatiny). Trpasličí šrot (kov zo štruktúr/meteoritov/tavenia v Create). Rum (alkohol z cukrovej trstiny).

2. Obojživelník (Merlin, Vodník) - Plánované
Možnosti tela: Chôdza (Slabo +), Plávanie (Silno +), Ponáranie (Slabo -), Chlad (Silno +), Horúčava (Slabo -), Nosnosť (Slabo -), Výdrž (Silno +).
   
Výhody:
- Rýchlosť ťažby blokov pod vodou sa rovná rýchlosti na súši.
- Zlepšená viditeľnosť pod vodou.
- Zvýšená rýchlosť behu počas dažďa.
- Úplná odolnosť voči mechanike vlhkosti (z modifikácie Cold Sweat).
- Utopenci sú voči hráčovi neutrálni (rovnako aj podvodní nepriatelia z iných modifikácií).
  
Nevýhody:
- Rybia diéta (morské plody, chaluhy).
- Zvýšené poškodenie zo zdrojov tepla (oheň, magma).
- Absencia dýchacieho výstroja (potápačské prístroje a panciere neposkytujú bonusy).
  
Vlastnosti a vizuál: Úplné vypnutie ukazovateľa vzduchu (no môže sa udusiť). Prítomnosť ukazovateľa vlhkosti (dopĺňa sa vodou, dažďom, pitím; v horúčave sa rýchlejšie míňa). Štandardný (vanilla) vzrast, žiabre a plutvy. Vizuálne bublinky pod vodou a stopa pri plávaní.
Súvisiace predmety/bloky: Elixíry zadržania vlhkosti. Prípravky na brnenie (aloe, tuk, vosk na udržanie vlahy). Podvodné plodiny a lastúrové podložie (poskytuje kostnú múčku). Harpúny (podvodná zbraň na priťahovanie nepriateľov/hráča). Mobovia: Sumec, Homár, Medúza (obyčajná, elektrická, svetielkujúca). Gél z medúzy: blok na zvlhčovanie záhonov (umožňuje sadiť v dimenzii Nether). Elektro-gél: zasahuje elektrickým prúdom (Elektrošok). Lumi-gél: svieti a umožňuje pestovať plodiny v tme. Morské hrozno (rozrastá sa od kotvy). Obrovská chaluha (hustá, spomaľuje člny, spracováva sa na dosky/palice).

3. Upír (Krvipijca, Upír, Dhampír) - Plánované
Možnosti tela: Poškodenie holou rukou (Silno +), Chôdza (Silno +), Chlad (Silno +), Horúčava (Slabo -), Výdrž (Silno +).
   
Výhody:
- Výrazné posilnenie vampirizmu (dopĺňa ukazovateľ krvi pri útokoch, kritických zásahoch a zabitiach).
- Zvýšenie rýchlosti pohybu v tme.
  
Nevýhody:
- Agresia dedinčanov a golemov (dedinčania neobchodujú, pokiaľ tvár nie je skrytá maskou/tekvicou).
- Horenie na slnku (tlie, zachraňuje ho brnenie alebo elixíry).
- Kritický strach zo striebra a znížené poškodenie voči striebornej výbave.
  
Vlastnosti a vizuál: Ukazovateľ krvi (dopĺňa sa pitím a útokmi). Spánok výlučne v rakve (integrácia Sleep Tight). Štandardný vzrast (alebo mierne nad 2 bloky), špicaté biele uši. Častice krvi a zvuk uhryznutia pri útoku. Tlmený tlkot srdca v blízkosti živých tvorov. Tmavé častice okolo hráča v noci.
Súvisiace predmety/bloky: Krvavé jedlo (pivo, kokteil, zmrzlina, torta). Špeciálne brnenie proti slnku (stráca životnosť). Rakva a Ostnatá rakva (zabíja ostatné rasy pri pokuse ľahnúť si). Kapsuly/súprava na uchovávanie krvi (integrácia Sully's). Strieborný injektor (odber krvi s poškodením). Strieborné šípy (oslabujú schopnosti, blokujú regeneráciu). Fľaštičky/vedrá s krvou. Očarovania/elixíry vampirizmu. Noví mobovia: upíri (s profesiami hrobár, rituálnik a i.), lovci-illageri (so striebornými šípmi, lovia nemŕtvych/vlkolakov). Upírske zámky, tábory lovcov.

4. Enderec (Enderian) - Plánované
Možnosti tela: Poškodenie holou rukou (Silno +), Výška kroku (Silno +), Chôdza (Silno +), Plávanie (Slabo -), Ponáranie (Silno +), Horúčava (Silno +), Nosnosť a Výdrž (Silno +).
   
Výhody:
- Neutralita endermanov pri očno-očnom kontakte.
- Vysoká ochrana pred ohňom/lávou a zlepšený výhľad pod lávou.
- Zvýšený dosah pri ukladaní a ničení blokov.
- Zvýšený dolet ender perly (bez poškodenia z teleportácie).
- Absencia vyčerpávania ukazovateľa hladu (potrava je potrebná len kvôli efektom/liečeniu).
- Teleportácia k hráčovi/bodu cez Antique Atlas (na úkor perál a zdravia; pri smrteľnej teleportácii ho zachráni totem).
  
Nevýhody:
- Nemožnosť spánku na posteliach (v Overworlde spí postojačky, v dimenziách Nether/End postele vybuchujú/spôsobujú poškodenie). Bod oživenia (spawnpoint) sa nastavuje len v dimenzii Nether.
- Hydrofóbia: poškodenie z vody, kvapalín, vodnatého jedla a nápojov. Poškodenie ignoruje brnenie (zachraňuje ho dáždnik, potápačský prístroj, rybí tuk, elixíry).
- Úplný zákaz interakcie s tekvicami (nemožno ich ničiť, jesť ani nasadzovať).
  
Vlastnosti a vizuál: Ender perla sa v kvapalinách rozbíja. Šanca 10 % na náhodnú teleportáciu pri utŕžení poškodenia z vody. Zraniteľnosť voči maskovaniu (entity v tekviciach sú neviditeľné). Pri zrakovom kontakte s cudzincom sa obrazovka trasie a postava začne neúmyselne útočiť na cieľ. Vysoký vzrast (2.5 - 3 bloky). Častice, zvuk podráždenia pri zrakovom kontakte, stopa (avatar) pri utŕžení poškodenia alebo teleportácii.
Súvisiace predmety/bloky: Vodná pištoľ (náboje: voda, kyselina, limonáda; odráža, spomaľuje, kyselina rozožiera brnenie).

5. Mechar (Robot, Ozubenec) - Plánované
Možnosti tela: Poškodenie holou rukou (Silno +), Chôdza (Silno +), Plávanie (Slabo -), Ponáranie (Silno +), Chlad (Slabo -), Horúčava (Silno +), Nosnosť a Výdrž (Silno +).
   
Výhody:
- Zvýšený počet slotov Curios pre vlastné vylepšovacie moduly (poskytujú unikátne pasívne vlastnosti).
- Nabíjanie energie zo zásahov bleskom (funguje ako bleskozvod, úplná imunita voči poškodeniu z bleskov).
  
Nevýhody:
- Skrat vo vode bez špeciálnych vylepšení (periodické poškodenie a stav Elektrošok).
- Zrýchlené vybíjanie batérie v chlade.
- Diéta pozostáva výlučne z ozubených kolies a kovového šrotu.
  
Vlastnosti a vizuál: Absencia ukazovateľa hladu (nahradený ukazovateľom nabitia; pri vybití sa spomaľuje, no neumiera). Štandardný vzrast, mechanická ruka. Iskry a kovový zvuk pri utŕžení poškodenia. Zvuky pohonov, iskry pri nabíjaní bleskom, zvuky inštalácie súčiastok.
Súvisiace predmety/bloky: Ozubené kolesá a kovový šrot (integrácia Alex's Caves). Nabíjacia stanica (spánok + nabíjanie). Batérie (blok/predmet). Cievka pre Create (pri otáčaní zasahuje prúdom). Vylepšovacie moduly (prázdne a plniteľné cez bioanalyzátor zvierat). Meteority, skládky, noví mobovia zhadzujúci kovový šrot. Typy modulov: spracovanie jedla na palivo, bezpečné plávanie, vstavané osvetlenie (zapína sa z inventára), tepelná ochrana, regenerácia, dvojskok, pružiny, lezenie po stenách, nosnosť, nočné videnie.

6. Vták (Avian, Nebesťan) - Plánované
Možnosti tela: Výška kroku (Silno +), Chôdza (Silno +), Plávanie (Slabo -), Ponáranie (Slabo -), Chlad (Slabo -), Horúčava (Silno +), Nosnosť (Slabo -), Výdrž (Silno +).
   
Výhody:
- Vstavané krídla na plachtenie (prevyšujú pôvodné krídla Elytra).
- Vysoká mobilita vo vzduchu.
- Zníženie cien u všetkých dedinčanov o 20 %.
  
Nevýhody:
- Výrazné zhoršenie letu pri premoknutí/v daždi.
- Krídla sa môžu roztrhnúť pri poškodení do chrbta (obnovujú sa so šancou pri jedení).
- Obmedzenie spánku: len vo veľkej výške alebo v horských hniezdach.
- Zákaz konzumácie vtáčieho mäsa.
- Výrazne zvýšené poškodenie z pádu.
- Zákaz nosenia pôvodných krídel Elytra.
  
Vlastnosti a vizuál: Periodicky znáša vajcia a zhadzuje perie. Štandardný vzrast. Model s krídlami na chrbte a operením na pleciach. Pri pobyte pod zemou dostáva vizuálne/zvukové postihy (klaustrofóbia). Častice peria pri plachtení, zvuk pri vypadnutí vajca.
Súvisiace predmety/bloky: Nové ohňostroje na zrýchlenie. Vajcia a perie vtáka. Dekoratívne bloky (palice na hniezda). Štruktúry ľadových a obyčajných horských hniezd.

7. Vlkolak (Vlkodlak, Vurdalak, Lykantrop) - Plánované
Možnosti tela: Výška kroku (Silno +), Chlad (Silno +), Horúčava (Slabo -), Výdrž (Silno +).
   
Výhody:
- Nočná transformácia do vlčej podoby, ktorá výrazne zvyšuje poškodenie, rýchlosť útoku a pohybu.
- Silné výpady na skrátenie vzdialenosti.
- Výdrž vo vlčej podobe sa prakticky nespotrebúva.
- Úplná imunita voči chladu a horúčave v podobe vlka.
- Dopĺňanie hladu zabíjaním nepriateľov.
- V zime sa pokrýva dodatočnou srsťou (zosilnená ochrana pred chladom).
- Hromadenie ukazovateľa zúrivosti, ktorý zvyšuje štatistiky.
  
Nevýhody:
- Brnenie sa vo vlčej podobe nútene sníma.
- Vlčia diéta: len mäso a kosti, obrovská pažravosť. Nízka zúrivosť zosilňuje hlad.
- Zákaz spánku v noci až do vyliečenia lykantropie.
- Spomalená ťažba blokov v podobe šelmy.
- Dedinčania sa rozpŕchnu, železní golemovia útočia.
- Zvýšené poškodenie zo striebra.
  
Vlastnosti a vizuál: Sila transformácie závisí od fázy Mesiaca (maximálna počas splnu). Štandardný vzrast. Samostatný model vlka (srsť, veľké končatiny, tesáky) s efektom plynulej premeny. Stupňujúce sa zavýjanie pred premenou, praskanie kostí. Ťažké dýchanie, kroky šelmy, vrčanie (podráždené pri nízkej zúrivosti). Zvuky pri zabití (rast zúrivosti).
Súvisiace predmety/bloky: Strieborný amulet (blokuje premenu, láme sa, nefunguje počas splnu). Prilbica (Aconitum / vlčí mor — vzácny horský kvet, nerozmnožuje sa kostnou múčkou, kupuje sa u liečiteľa). Elixíry lykantropie. Strieborné zbrane.

8. Lesný duch (Ent, Verdian) - Plánované (vyžaduje Serene Seasons)
Možnosti tela: Ponáranie (Slabo -).
   
Výhody:
- Voľný prechod cez lístie (mierne spomaľuje ako pavučina).
- Možnosť odrezávať kvety a kôru z vlastného tela (časom dorastajú).
- Skrotenie všetkých zvierat na prvý pokus.
- Imunita voči poškodeniu z rastlín (žihľava, bobule, kaktusy, aloe).
- Pasivita včiel (neútočia pri zbere medu). Získavanie unikátnych materiálov od včiel.
  
Nevýhody:
- Prísna vegetariánska diéta.
- Výrazné zhoršenie štatistík v zimnom období.
- Neschopnosť samostatne sa uhasiť pri vznietení (vyžaduje sa voda).
  
Vlastnosti a vizuál: Počas jedenia generuje kompost. Integrácia s ročnými obdobiami: Leto (telo je tŕnisté, spôsobuje odvetné poškodenie). Jar (regenerácia zo slnka, urýchlenie rastu plodín, včely sa opeľujú o hráča). Jeseň (zvýšená rýchlosť a výdrž). Štandardný vzrast, kvety/konáre/kôra na tele.
Súvisiace predmety/bloky: Rastová formula (kontrola ročných období). Kvety/kôra hráča. Štruktúry: divoké včelíny, zemľanky, včelíny v dedinách. Rôzne druhy kompostu.

9. Démon (Ohník) - Plánované
Možnosti tela: Chlad (Slabo -), Horúčava (Silno +).
   
Výhody:
- Imunita voči vysokým teplotám (oheň, láva, magma spôsobujú minimálne poškodenie).
- Zapaľuje nepriateľov svojimi útokmi počas vlastného horenia.
- Bezpečný spánok v dimenzii Nether.
- Neutralita niektorých démonov.
- Mechanika zberu duší: liečenie seba/okolia, samovzkriesenie pri maximálnom nabití, vzkriesenie iných (v režime Hardcore), zapečatenie mobov do zvitkov duší.
  
Nevýhody:
- Prísna pekelná diéta (jedlo len z dimenzie Nether).
- Nemožnosť spať na posteliach v Overworlde.
- Kritická zraniteľnosť voči chladu.
- Zákaz obchodovania s dedinčanmi-kňazmi (duchovnými).
- Zasekávanie sa v priechodoch so šírkou menšou ako 1.5 bloku kvôli rohom (zachraňuje ho čelenka/obväz).
  
Vlastnosti a vizuál: Štartovacie objavenie sa (spawn) v dimenzii Nether. Pekelný ukazovateľ: vyčerpáva sa v obyčajnom svete, pri úplnom vyčerpaní nútene teleportuje hráča do dimenzie Nether (dopĺňa sa jedlom z Netheru). Duše sa uchovávajú v inventári (limit 10 - 20). Štandardný vzrast. Rohy a malý chvost. Zriedkavé častice tlejúceho popola z tela.
Súvisiace predmety/bloky: Obväz/čelenka (skryje rohy pre prechod v úzkych miestach). Entita Duša (možno ju chytiť do ampulky/fľaše). Zvitky. Zvitok duše (dostupný len pre Démona, zapečaťuje mobov, ostatným rasám spôsobuje poškodenie). Strieborné ampulky (uchovávanie duší, hromadné liečenie). Okrídlení nether-démoni (aplikujú Prekliatie). Zub démona (výroba vidiel/elixíru Prekliatia). Pekelné vidly (obojručná zbraň s krvácaním).

10. Nemŕtvy (Povstalec) - Plánované
Možnosti tela: Základné štatistiky nie sú zmenené.

Výhody:
- Úplná imunita voči negatívnym efektom (vrátane Moru).
- Duplikovanie negatívnych efektov uložených na hráča na nepriateľov počas útoku.
- Pôvodní (vanilla) nemŕtvi sú neutrálni (do prvého úderu hráča).
- Pri smrti, ak je nahromadený ukazovateľ hniloby, postava vstane z mŕtvych (hniloba sa konvertuje na zdravie, negatívne efekty sa odstránia).

Nevýhody:
- Zhnitá diéta (zhnité mäso a pokazené jedlo).
- Agresia golemov a odmietanie obchodu zo strany dedinčanov.
- Zvýšené poškodenie zo strieborných zbraní.
- Útoky ghulov a vlkolakov rýchlo vyčerpávajú ukazovateľ hniloby hráča.

Vlastnosti a vizuál: Ukazovateľ hniloby: zvyšuje obranu a nosnosť, v bojoch sa rýchlo míňa, dopĺňa sa zhnitým jedlom. Zabitie dedinčana ho premení na zombíka. Invertovaná alchýmia (elixíry zranenia/otryavy liečia a elixíry liečenia/regenerácie spôsobujú poškodenie). Spánok len v rakvách. Štandardný vzrast. Model so zhnitými časťami tela. Odpudzujúce, mľaskavé zvuky pri konzumácii jedla.
Súvisiace predmety/bloky: Ľudský amulet (dočasne skryje podobu nemŕtveho na obchodovanie, pri kontakte s nemŕtvym sa rozbije). Štruktúry: opustené cintoríny. Náhrobky (dekoratívne tabuľky). Profesia Hrobára pre dedinčanov. Kaďa rozkladu (vytvára "Hnilobu" z mäsa/tkaniva). Hniloba (hnojivo a jedlo pre Nemŕtveho). Strieborné spomienkové sviečky.

11. Mimozemšťan (Xenos, Radian, Astronit) - Plánované (vyžaduje Alex's Caves)
Možnosti tela: Základné štatistiky nie sú zmenené.

Výhody:
- Úplná imunita voči otrave, radiácii a kyseline.
- Nadľudská strava (živenie sa uránom, radónom a rádioaktívnymi sladkosťami).
- Obchodovanie/odmeny od božstva za plnenie kvót.
- Exkluzívne čítanie nákresov z meteoritov v mimozemskom jazyku (odomyká technológie ako Jetpack).
- Bezpečné používanie Teleportačnej stanice.
- Dočasná kontrola počasia prostredníctvom uctievania.

Nevýhody:
- Sladká/rádioaktívna diéta (zákaz obyčajného jedla).
- Závislosť od ukazovateľa priazne Boha (postihy a pokles reputácie pri nesplnení denných stupňujúcich sa zberných kvót).

Vlastnosti a vizuál: Ostatné rasy utrpia poškodenie pri použití Teleportačnej stanice a nemôžu komunikovať s Bohom. Pri smrti telo hráča vybuchne (spôsobí kolosálne poškodenie v okolí, no neničí terén, objaví sa jadrový hríb, 100 % vypadnutie koristi a bezpečnosť pre vypadnuté veci). Štandardný vzrast. Tykadlo na čele. Vizuálne efekty radiácie, vyparovanie predmetov pri obetovaní.

---