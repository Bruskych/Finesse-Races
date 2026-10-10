<div align="center">

# Rasy (Pôvod hráča)
### Zbierka všetkých `nápadov autora` a `iných účastníkov` o rasách, ich schopnostiach, vlastnostiach, obmedzeniach, mechanikách a vizuálnych prvkoch. Toto nie je plán vývoja ani zoznam povinných úloh. Sú tu zhromaždené všetky navrhované nápady a koncepty, ktoré môžu byť implementované v móde. Ideálne by bolo realizovať všetky tu opísané mechaniky, avšak konkrétna implementácia jednotlivých nápadov sa môže počas vývoja meniť.

</div>

---

## Hlavné módy (závislosti potrebné pre fungovanie Finesse Races)

- Remedy Core - API autora
- [Cold Sweat](https://www.curseforge.com/minecraft/mc-mods/cold-sweat)
- [Caverns & Chasms](https://www.curseforge.com/minecraft/mc-mods/caverns-and-chasms)

## Módy v pozadí (závislosti, ktoré sú žiaduce pre fungovanie Finesse Races)

- [Serene Seasons](https://www.curseforge.com/minecraft/mc-mods/serene-seasons)
- [Brewin' And Chewin'](https://www.curseforge.com/minecraft/mc-mods/brewin-and-chewin)
- [Species](https://www.curseforge.com/minecraft/mc-mods/species)
- [Create](https://www.curseforge.com/minecraft/mc-mods/create)
- [Sully`s Mod](https://www.curseforge.com/minecraft/mc-mods/sullys-mod)

---

## Globálne zmeny pre každú rasu
*(Atribúty, odolnosť voči teplote a tak ďalej)*

- `Rýchlosť chôdze`
- `Rýchlosť plávania`
- `Rýchlosť potápania`

- `Teplota` a `vlhkosť` (Cold Sweat)
  - Každá rasa bude mať potenciálne vlastnú odolnosť voči teplote a vlhkosti alebo, naopak, slabosť voči nim.

- `Nosnosť` (vlastný systém hmotnosti brnenia)
  - Každá rasa bude mať svoj ukazovateľ nosnosti. Od neho bude závisieť vzdialenosť a rýchlosť dobíjania úskokov, ako aj spotreba výdrže.

- `Množstvo a kvalita výdrže` (vlastný systém výdrže)
  - Rýchlosť spotreby výdrže, vzdialenosť a rýchlosť dobíjania úskokov.

- `Systém stravovania`
  - Rasa získa menej nasýtenia z jedla, ktoré nepatrí do jej jedálnička, okrem sladkostí.

- `Zvukový sprievod`
  - Každá rasa bude mať vlastné zvuky, ako aj unikátne zvuky pre určité akcie.

- `Vzhľad`
  - Každá rasa bude mať unikátny vzhľad v podobe dodatočných prvkov: uši, brada, rohy atď.

- `Výška kroku`
  - Každá rasa má svoju výšku kroku; čím je rasa vyššia a silnejšia, tým vyššia je výška jej kroku (umožňuje hráčovi nevyskakovať, aby vystúpil na 1 blok).

- `Výška (Vzrast)`
  - Rasa bude mať vlastnú výšku, ktorá bude ovplyvňovať hrateľnosť: rýchlosť chôdze, výšku výstupu na blok a potrebnú výšku stropu.
  - Výška sa nebude určovať jednoduchým škálovaním celého modelu hráča, ale veľkosťou jednotlivých častí tela. To umožní zachovať "vanilla" vzhľad postavy a vyhnúť sa efektu rovnomerného škálovania celého modelu.

- `Štartovací balíček predmetov`
  - Každá rasa má svoj štartovací balíček, ktorý sa objaví v inventári hráča po výbere rasy.

- `Vlastná textúra ikony teploty`
  - V móde Cold Sweat je ikona hráča, kde sa ukazuje teplota. Predvolene je na nej zobrazený človek. Každá rasa bude mať svoj vlastný vzhľad ikony.

---

## Globálne predmety / mechaniky, ktoré pridá mód
*(Tieto predmety nepatria konkrétnym rasám.)*

- `Sféra znovuzrodenia`
  - Umožní hráčovi v strednej alebo neskorej fáze hry zmeniť svoju rasu a znovuzrodiť sa do novej. Elektrošok (unikátne častice) - efekt, ktorý z času na čas (raz za pár sekúnd) privedie hráča do stuporu, hráč sa nemôže krátky čas hýbať a ani používať žiadne predmety v rukách. Efekt sa aplikuje pri zásahu bleskom a pri útoku elektrickej medúzy. Z elektro-želé medúzy sa dá uvariť elixír s týmto efektom.

- `Očarovania / kliatby`
  - ...

- `Efekty`
  - Mastnota (Bez elixírov) - efekt získaný po konzumácii rybieho oleja. Umožňuje rýchlo vyletieť z vody bez stlačenia Shiftu. Núti hráča pasívne vyplávať na hladinu vody. Počas dažďa umožňuje veľmi pomaly stúpať do vzduchu. Rasy, ktoré dostávajú poškodenie od tekutín, ním prestanú trpieť.
  - Mokrina - efekt, ktorý umožňuje hráčovi nestrácať vlhkosť.
  - Krvilačnosť - efekt, ktorý dáva hráčovi schopnosti vampirizmu.
  - Krvácanie (Bez elixírov, častice krvi) - efekt, ktorý spôsobuje poškodenie, ak sa hráč hýbe.
  - Kliatba - efekt, ktorý zakazuje nositeľovi akokoľvek si obnovovať zdravie (nepomôže jedlo, elixíry, nič).
  - Mor/Nákaza/Čierna smrť (Bez elixírov, unikátne častice) - efekt, ktorý možno získať s malou šancou pri jedení zhnitého mäsa alebo jeho obdôb. S malou šancou sa s týmto efektom budú spawnovať aj zombie a potkany. Prenáša sa na všetky entity v blízkosti nositeľa, konkrétne na hráčov, dobytok, zombie a ich obdoby, potkany, dedinčanov. Efekt nemá trvanie (prebieha v 3 štádiách), nedá sa odstrániť mliekom / smrťou / spánkom, dá sa len vyliečiť.
  -

- `Vylepšený systém vlhkosti z módu Cold Sweat`
  - Postih klzkých rúk (Vypadávanie predmetov):
    - Pri vysokej úrovni vlhkosti (a tiež pod efektom Mastnoty) môže hráč s určitou šancou upustiť aktuálny predmet z hlavnej ruky na zem pri ťažení blokov, používaní nástrojov alebo útočení.
    - Vo vode alebo v daždi sa šanca na upustenie nástroja výrazne zvyšuje.
    - Pri obdržaní poškodenia je šanca na upustenie držaného predmetu podstatne vyššia.

  - Výnimky a imunity rás:
    - Rasa Obojživelník má absolútnu imunitu voči tejto mechanike - z ich rúk je nemožné vyraziť alebo upustiť predmety kvôli vlhkosti.
    - Špeciálne vrhacie a podvodné zbrane (Trojzubec a Harpúna) majú zabudovanú imunitu: za žiadnych podmienok ich nemožno vyraziť ani upustiť z rúk.

  - Ochrana a modifikácie predmetov:
    - Lepidlo (Spotrebný materiál): Umožňuje naniesť ochrannú vrstvu na nástroj alebo zbraň, ktorá trikrát zabráni náhodnému vypadnutiu predmetu pri práci vo vlhkom prostredí.
    - Očarovanie «Zovretie» / «Mŕtvy stisk»: Očarovanie pre zbrane/nástroje, ktoré úplne zabraňuje ich vypadnutiu z rúk.
    - Očarovanie «Odvodnenie» / «Odzbrojenie»: Agresívne očarovanie pre zbrane, ktoré pri útoku na cieľ so zvýšenou pravdepodobnosťou vyrazia predmet z jeho rúk.

  - Interakcia s mobmi:
    - Utopenci (Drowned): Pri spôsobení poškodenia hráčovi aplikujú efekt zvýšenej vlhkosti a majú zvýšenú šancu vyraziť hráčovi aktuálnu zbraň z rúk svojím útokom.

- `Kazokvet`
  - Nový druh liečivej rastliny, ktorá rastie v močiaroch (pod vodou).
  - Dá sa z neho vyrobiť liek proti moru (jednorazovo odstraňuje efekt Moru).
  - Možné využitie injektora Upíra pre vakcínu.

- `Výťažok z liečivej plesne`
  - Lieči Mor a poskytuje imunitu na niekoľko herných dní.
  - Spôsoby získania: mach z módu Species. Zatiaľ je málo nápadov.

- `Dáždniky (ako predmet)`
  - Dáždnik sa bude dať vyrobiť samostatne a bude mať odolnosť.
  - Každý dáždnik bude možné zafarbiť na akúkoľvek farbu.
  - Dáždnik bude znižovať alebo úplne eliminovať vplyv slnka na niektoré rasy.
  - Ak hráč drží dáždnik v ruke, bude ho chrániť pred vlhkosťou, dažďom a rozprašovačom z módu Create.

- `Dáždniky (použitie vo svete)`
  - Dedinčania si počas dažďa otvoria dáždniky.

- `Dáždniky (za predpokladu, že nebudú klzáky)`
  - Dáždnik sa bude dať použiť na plachtenie, pričom sa však bude spotrebúvať jeho výdrž.
  - Bude tu kompatibilita s hmotnosťou hráča, pri veľkej hmotnosti sa dáždnik po otvorení z výšky zlomí.
  - Počas dažďa je let na dáždnikoch ťažší a šanca na zásah bleskom je väčšia.
  - Medené vylepšenie s bleskozvodom, vďaka ktorému hráč zriedkavejšie trpí bleskami, a ak ho zasiahne, nedostane poškodenie.
  - Očarovanie, ktoré umožňuje veľmi vysoko vzlietnuť nad táborákmi; každý typ táboráku bude hráča dvíhať inak.
  - Možnosť urobiť lietajúci dáždnik, ktorý pri podržaní Shiftu bude pomaly stúpať a hráč s ním takmer nebude cítiť váhu.
  - Dáždniky horia v Nethere, treba pre ne vytvoriť vylepšenie.

- `Klobúk s dáždnikom`
  - Variant pokrývky hlavy s funkciami dáždnika.
  - Konkrétne mechaniky zatiaľ nie sú určené.

- `Rybí olej`
  - Bude ho možné nanášať na brnenie / konzumovať / nanášať na mobov.
  - Po konzumácii získa hráč efekt Mastnota.

- `Uterák`
  - Bude možné vyrobiť z látky, umožní utrieť sa, a tým znížiť alebo úplne odstrániť vlhkosť.
  - Uterák bude mať výdrž.
  - Možno sa bude dať uterákom utierať aj iných hráčov.

- `Prilba so sviečkou`
  - Bude poskytovať pasívne osvetlenie okolo hráča, čím ho zbaví potreby neustále držať v ruke fakľu.
  - Sviečka sa bude počas používania postupne spotrebúvať.

- `Štruktúry`
  - Meteority, v ktorých budú užitočné materiály pre trpaslíka a robota.

---

## 1. Trpaslík (Dverg, Podhorník)

### Možnosti tela:
- Vanilla:
  - `Poškodenie úderom` - Silne
  - `Výška kroku` - Bez zmien
  - `Rýchlosť chôdze` - Slabo
  - `Rýchlosť plávania` - Slabo
  - `Rýchlosť potápania` - Silne

- Cold Sweat:
  - `Odolnosť voči chladu` - Silne
  - `Odolnosť voči teplu` - Slabo

- Custom mods (for future):
  - `Nosnosť` - Silne
  - `Výdrž` - Silne

### Plusy:
- Zvýšená rýchlosť ťažby krompáčom a vrodené ťaženie.
- Znížená strata hladu pri ťažení.
- Odolnejší voči účinkom opitosti (od alkoholu z iných módov).
- Znížené náklady na skúsenosti pri oprave a zvýšená výdrž nákovy pri práci.
- Možnosť vyrábať alebo nachádzať artefakty (prstene pre sloty Curios, fungujúce ako Wayfarer, s obmedzením na dva sloty pre prstene, ktoré dávajú pasívne atribúty: šanca na kritický zásah, šanca na korisť, zabudované tŕne, rýchlosť útoku, poškodenie, zníženie vlastného odhodenia a zosilnenie odhodenia nepriateľov).

### Mínusy:
- Jaskynná diéta (konzumácia húb, machu, pavúkov a iného jaskynného jedla).
- Obmedzenie spánku: trpaslík môže spať iba hlboko pod zemou.
- Slabý v plávaní.
- Zmenšený dosah ukladania a ťaženia blokov.

### Neutrálne (vlastnosť rasy, vizuál atď.):
- Má v sebe zabudovaný pracovný stôl.
- Ukazovateľ alkoholu (potreba piť alkohol).
- Rast brady, ktorú možno strihať na získanie nití alebo iných materiálov; brada poskytuje ochranu pred chladom.

### Výška a model postavy:
- Nízky vzrast (1 - 1.5 bloku na výšku).
- Brada.

### Vizuálna a zvuková časť (pre väčšie ponorenie):
- Pri ťažení rudy alebo kameňa sa okolo hráča budú objavovať malé vizuálne častice prachu a úlomkov kameňa.

### Predmety a bloky:
- `Nové drahokamy` - ignit, rubín, zafír a iné.
- `Stôl na drahokamy` - umožňuje dočasne posilňovať zbrane pomocou drahokamov.
- `Prstene-artefakty` - unikátne prstene pre sloty Curios.
- `Opustené podzemné vyhne` - nové štruktúry s korisťou a prvkami spojenými s kováčstvom.
- `Vrták` - nový nástroj na kopanie, vyrába sa napríklad z liatiny.
- `Píla` - nový nástroj na rúbanie stromov, vyrába sa napríklad z liatiny.
- `Trpasličí šrot` - nový "kov", ktorý bude možné nájsť v štruktúrach, meteoritoch, alebo vyrobiť cez tavenie z módu Create.

---

## 2. Obojživelník (Merlin, Vodník)

### Možnosti tela:
- Vanilla:
  - `Poškodenie úderom` - Bez zmien
  - `Výška kroku` - Bez zmien
  - `Rýchlosť chôdze` - Slabo +
  - `Rýchlosť plávania` - Silne
  - `Rýchlosť potápania` - Slabo

- Cold Sweat:
  - `Odolnosť voči chladu` - Silne
  - `Odolnosť voči teplu` - Slabo

- Custom mods (for future):
  - `Nosnosť` - Slabo
  - `Výdrž` - Silne

### Plusy:
- Ťažba blokov vo vode je rovnako rýchla ako na súši.
- Lepšia viditeľnosť pod vodou.
- Zvýšená rýchlosť behu počas dažďa.
- Úplná odolnosť voči vlhkosti (z módu Cold Sweat).

### Mínusy:
- Rybia diéta (morské plody, chaluhy a iné).
- Vyššie poškodenie od akýchkoľvek zdrojov tepla (oheň, magma a iné).

### Neutrálne (vlastnosť rasy, vizuál atď.):
- Úplné vypnutie ukazovateľa vzduchu a možnosť zadusiť sa (bublinky dýchania).
- Obmedzenie dýchacieho výstroja: ani korytnačí pancier, ani akvalungy nedávajú žiadne bonusy na dýchanie.
- Prítomnosť ukazovateľa vlhkosti: je potrebné ho dopĺňať pobytom vo vode, státím v daždi, pitím vody, elixírov, piva alebo iných nápojov; v horúčave (podľa mechaniky Cold Sweat) sa ukazovateľ spotrebúva rýchlejšie.

### Výška a model postavy:
- Vzrast vanilla.
- Žiabre a plutvy.

### Vizuálna a zvuková časť (pre väčšie ponorenie):
- Pri pobyte pod vodou sa okolo hráča budú pravidelne objavovať malé bublinky a vodné častice.
- Pri rýchlom plávaní bude za hráčom zostávať malá stopa z bubliniek.

### Predmety a bloky:
- `Elixíry zadržania vlhkosti` - spomaľujú odparovanie vlhkosti u hráča.
- `Prostriedky na pokrytie brnenia` - aloe, tuk a vosk, ktoré umožňujú spomaliť stratu vlhkosti.
- `Podvodné plodiny` - jedlé rastliny a plodiny, ktoré možno pestovať pod vodou.
- `Morské mušle` - koberček z mušlí, ktorý sa skladá zo 4 sekcií. Dá sa spracovať na kostnú múčku.
- `Harpúny` - zbrane pre podvodný lov, schopné strieľať pod vodou, priťahovať nepriateľov alebo priťahovať samotného hráča k nepriateľom a blokom.
- `Sumec`
- `Homár`
- `Medúza` - nový priateľský mob. Má tri druhy - obyčajná, elektrická, svietiaca. Výskyt každého druhu závisí od biomu. Každý typ medúzy pri smrti zanecháva svoj typ želé.
  - Gél medúzy - najbežnejšie želé, z ktorého sa dá vyrobiť blok (dá sa cezeň prechádzať ako cez pavučinu). Hlavné určenie - zvlhčovanie záhonov. Ak kliknete pravým tlačidlom myši na záhon s gélom v ruke, zostane mokrý natrvalo (vizuálne bude záhon pokrytý tenkou priehľadnou vrstvou gélu). Pri zničení záhonu gél nevypadne späť. To umožní pestovať plodiny aj v dimenzii Nether.
  - Elektro-gél medúzy - tiež sa z neho dá urobiť blok, ale pri kontakte s ním sa aplikuje efekt "Elektrošok". Toto želé je zároveň hlavnou ingredienciou pri varení elixíru Elektrošoku.
  - Lumi-gél medúzy - tiež sa z neho dá urobiť blok, bude vyžarovať silné svetlo. Funguje rovnako ako obyčajné želé a dajú sa ním zvlhčiť záhony, ale okrem toho bude možné na takéto záhony sadiť plodiny aj v úplnej tme. Štandardne žiadne plodiny nerastú pri úrovni osvetlenia 9 a nižšej, ale s lumi-gélom to bude možné. Vizuálne bude blok pokrytý rovnakou polopriehľadnou vrstvou gélu, no okrem toho bude vyžarovať slabé svetlo.
- `Morské hrozno` - nová podvodná rastlina (jedlá plodina). Centrálny blok je kotva, od neho sa ako tekvica bude rozrastať do rôznych strán morské hrozno. V 1 bloku hrozna budú 4 konáriky hrozna, ktoré rastú nahor podobne ako morské uhorky (maximálne 2 bloky do výšky).
- `Obrovská chaluha` - nový druh chaluhy, ktorá rastie na kameni v zhlukoch približne od 10x10 do 25x25 blokov. Je veľmi hustá, košatá a siaha až k samej hladine vody, čím sa lode budú nad ňou plaviť spomalene. Hlavné určenie - dajú sa z nej vyrábať vlastné typy palíc a dosiek.

---

## 3. Upír (Krvožrút, Ghúl, Dhampír)

### Možnosti tela:
- Vanilla:
  - `Poškodenie úderom` - Silne
  - `Výška kroku` - Bez zmien
  - `Rýchlosť chôdze` - Silne
  - `Rýchlosť plávania` - Bez zmien
  - `Rýchlosť potápania` - Bez zmien

- Cold Sweat:
  - `Odolnosť voči chladu` - Silne
  - `Odolnosť voči teplu` - Slabo

- Custom mods (for future):
  - `Nosnosť` - Bez zmien
  - `Výdrž` - Silne

### Plusy:
- Silné zosilnenie vampirizmu a mierne zvýšenie rýchlosti pohybu v tme.

### Mínusy:
- Agresia zo strany dedinčanov a železných golemov (dedinčania odmietajú obchodovať, kým si upír nezakryje tvár tekvicou alebo iným predmetom, napríklad maskou).
- Zraniteľnosť voči slnečnému svetlu (upír tleje na slnku). Pomôže špeciálne vybavenie alebo elixír ohňovzdornosti.
- Extrémne silný strach zo striebra.
- Spôsobuje menšie poškodenie bytostiam v striebornej výbave.

### Neutrálne (vlastnosť rasy, vizuál atď.):
- Prítomnosť ukazovateľa krvi, ktorý je potrebné neustále dopĺňať útokmi alebo pitím krvi.
- Vrodený vampirizmus: dopĺňa zásoby krvi pri útokoch, kritických zásahoch a zabitiach (efekt závisí od spôsobeného poškodenia).
- Spánok výlučne v rakve, postele nebudú fungovať (s integráciou módu Sleep Tight).

### Výška a model postavy:
- Vzrast vanilla (alebo o niečo viac ako 2 bloky).
- Ostré biele uši.

### Vizuálna a zvuková časť (pre väčšie ponorenie):
- Pri útoku na entitu sa objavia malé častice krvi a charakteristický zvuk uhryznutia.
- Pri pobyte v blízkosti živých bytostí bude pravidelne počuť tlmený zvuk tlkotu srdca.
- V noci sa okolo hráča budú objavovať slabé temné častice.

### Predmety a bloky:
- `Jedlo` - krvavé pivo, krvavý koktail, krvavá zmrzlina a krvavá torta.
- `Špeciálne brnenie` - chráni pred slnečným svetlom, ale postupne stráca výdrž.
- `Rakva` - umožňuje upírom spať. Kompatibilita s Sleep Tight.
- `Rakva s hrotmi` - obdoba obyčajnej rakvy. Okamžite zabije iné rasy, ak sa do nej pokúsia vliezť.
- `Kapsuly a súprava na krv` - kapsuly zo Sully's a súprava na skladovanie krvi.
- `Strieborný injektor` - umožňuje zbierať krv z entít, pričom im spôsobuje poškodenie. Taktiež umožňuje...
- `Strieborné šípy` - spôsobujú upírom zvýšené poškodenie, dočasne oslabujú ich schopnosti a blokujú regeneráciu zdravia akejkoľvek bytosti.
- `Krv` - fľaštičky a vedrá s krvou.
- `Očarovania a elixíry vampirizmu` - poskytujú rôzne schopnosti a efekty spojené s vampirizmom.
- `Upíri` - noví mobovia-upíri, s ktorými sa dá obchodovať. Štruktúry upírskych hradov a veží. Profesie: hrobár, rituálnik, mäsiar, monštrológ.
- `Lovci` - noví illageri, vyzbrojení striebornými šípmi. Lovia nemŕtvych, vlkolakov a upírov. Sú neutrálni voči ostatným rasám, ale spôsobujú im oveľa menšie poškodenie. Štruktúry táborov a predmostí.

---

## 4. Enderan (Enderian)

### Možnosti tela:
- Vanilla:
  - `Poškodenie úderom` - Silne
  - `Výška kroku` - Silne
  - `Rýchlosť chôdze` - Silne
  - `Rýchlosť plávania` - Slabo
  - `Rýchlosť potápania` - Silne

- Cold Sweat:
  - `Odolnosť voči chladu` - Bez zmien
  - `Odolnosť voči teplu` - Silne

- Custom mods (for future):
  - `Nosnosť` - Silne
  - `Výdrž` - Silne

### Plusy:
- Neutralita endermanov pri očnom kontakte (nereagujú, ak sa im hráč pozrie do očí).
- Vďaka svojim očiam a unikátnej štruktúre mäsa má vyššiu odolnosť voči ohňu a láve. Taktiež má o niečo lepšie videnie pod lávou.
- Zvýšený dosah ukladania a ničenia blokov.
- Zvýšený dosah hodu ender perál a žiadne poškodenie z teleportácie ich pomocou.
- Úplná absencia vyčerpávania ukazovateľa hladu (konzumácia jedla je dostupná len kvôli efektom a liečeniu sŕdc).
- Umožňuje cez atlas (Antique Atlas) teleportovať sa k akémukoľvek hráčovi alebo na akékoľvek miesto vo svete. Cena závisí od vzdialenosti a dimenzie: spotrebúvajú sa ender perly a zdravie. Pri ďalekých teleportáciách možno takmer zomrieť, ale totem nesmrteľnosti dokáže hráča zachrániť. Teleportácia zaberá čas.

### Mínusy:
- Nemôže spať v posteliach. Môže zaspať postojačky kdekoľvek v Overworlde (Horný svet). V Nethere a Ende pri pokuse o spánok dostáva poškodenie. Takže klasickým spôsobom si nemôže nastaviť bod oživenia (spawn point) - to je možné len v Nethere.
- Hydrofóbia (strach z vody, poškodenie pri kontakte s vodou, iné tekutiny podobné vode mu tiež škodia). Tiež mu škodia nápoje a iné jedlo, v ktorom je veľa vody. Pomôže špeciálne vybavenie alebo iné predmety (dáždnik, akvalung, rybí olej).
- Zraniteľnosť voči tekutinám: malé poškodenie pri pití vody/elixírov alebo pri zasiahnutí vrhacou fľaštičkou s vodou.
- Nemôže nijako interagovať s tekvicami (rozbiť, zjesť, obliecť na hlavu a iné).

### Neutrálne (vlastnosť rasy, vizuál atď.):
- S malou šancou 10% sa môže náhodne teleportovať na krátku vzdialenosť, ak dostane poškodenie od vody.
- Zraniteľnosť voči maskovaniu: všetky moby a hráči, ktorí si nasadia tekvicu na hlavu, sa stávajú pre enderana neviditeľnými (on sám si tekvicu nasadiť nemôže).

### Výška a model postavy:
- Vysoký vzrast (2.5 - 3 bloky).
- Častice.

### Vizuálna a zvuková časť (pre väčšie ponorenie):
- Pri očnom kontakte s kýmkoľvek sa bude kamera hráča triasť a bude počuť zvuky podráždenia.
- Pri teleportácii alebo obdržaní poškodenia zanecháva za sebou svojho avatara (stopu).

### Predmety a bloky:
- `Vodná pištoľ` - náboje (voda, kyselina, sódovka). Odhadzuje, spomaľuje. Kyselina rozožiera brnenie.

---

## 5. Mechar (Robot, Ozubenec)

### Možnosti tela:
- Vanilla:
  - `Poškodenie úderom` - Silne
  - `Výška kroku` - Bez zmien
  - `Rýchlosť chôdze` - Silne
  - `Rýchlosť plávania` - Slabo
  - `Rýchlosť potápania` - Silne

- Cold Sweat:
  - `Odolnosť voči chladu` - Slabo
  - `Odolnosť voči teplu` - Silne

- Custom mods (for future):
  - `Nosnosť` - Silne
  - `Výdrž` - Silne

### Plusy:
- Prítomnosť väčšieho počtu slotov pre custom moduly v systéme Curios na inštaláciu vylepšení nájdených vo svete alebo vytvorených pomocou craftingu (rôzne moduly dávajú unikátne pasívne schopnosti a charakteristiky).

### Mínusy:
- Pri ponorení do vody bez vylepšení skratuje a pravidelne dostáva poškodenie (aplikuje sa efekt Elektrošoku).
- Chladné podmienky urýchľujú vybíjanie batérie.
- Ako "potravu" je nútený konzumovať ozubené kolesá a kovový šrot.

### Neutrálne (vlastnosť rasy, vizuál atď.):
- Úplná absencia ukazovateľa hladu, je nahradený ukazovateľom nabitia (pri jeho vyčerpaní sa robot stáva slabým a pomalým, ale zachováva si schopnosť fungovať).
- Možnosť nabíjať sa od bleskov: robot priťahuje blesky podobne ako medené brnenie a je plne chránený pred ich poškodením, pričom získava nabitie.

### Výška a model postavy:
- Vzrast vanilla.
- Mechanická ruka.

### Vizuálna a zvuková časť (pre väčšie ponorenie):
- Pri obdržaní poškodenia sa objavia malé iskry a charakteristický kovový zvuk.
- Robot bude vydávať zvuky mechanických pohonov a pravidelne vypúšťať malé iskry.
- Pri nabití bleskom sa okolo robota objavia elektrické častice a bude počuť zvuk výboja.
- Po inštalácii alebo výmene modulu sa ozvú zvuky mechanickej inštalácie súčiastok.

### Predmety a bloky:
- `Ozubené kolesá a kovový šrot` - nové predmety s kompatibilitou s kovovým šrotom z módu Alex's Caves.
- `Nabíjacia stanica` - blok na nabíjanie batérií, kombinovaný s miestom na spánok.
- `Batérie` - dostupné vo forme predmetov a blokov.
- `Cievka` - nový blok pre Create, ktorý pri rotácii spôsobuje mobom elektrické poškodenie.
- `Moduly vylepšení (prázdne a naplnené informáciami)` - získavajú sa pomocou bioanalyzátora pri skenovaní zvierat, každé zviera dáva iný modul.
- `Štruktúry` - meteority, budovy a smetiská obsahujúce rôzne zdroje.
- `Nové moby` - z nich vypadáva kovový šrot a ozubené kolesá.
- `Rôznorodé vylepšenia`:
  - prepracovanie jedla na palivo (umožňuje jesť jedlo po inštalácii modulu),
  - plávanie vo vode (bez vylepšenia robot klesá ku dnu, je pomalý, skratuje a dostáva poškodenie),
  - zabudované osvetlenie (zapína sa priamo v inventári podržaním klávesy na úkor energie),
  - ochrana pred teplotou,
  - zvýšená regenerácia,
  - dvojitý skok,
  - pružiny (efekt slizu),
  - pomalé lezenie po stenách,
  - rôzna nosnosť,
  - luminiscenčné nočné videnie.

---

## 6. Vták (Avian, Nebeský)

### Možnosti tela:
- Vanilla:
  - `Poškodenie úderom` - Bez zmien
  - `Výška kroku` - Silne
  - `Rýchlosť chôdze` - Silne
  - `Rýchlosť plávania` - Slabo
  - `Rýchlosť potápania` - Slabo

- Cold Sweat:
  - `Odolnosť voči chladu` - Slabo
  - `Odolnosť voči teplu` - Silne

- Custom mods (for future):
  - `Nosnosť` - Slabo
  - `Výdrž` - Silne

### Plusy:
- Zabudované krídla s možnosťou plachtenia (podoba vanilla Elytriek, ale lepšie).
- Veľmi vysoká mobilita a rýchlosť pohybu vo vzduchu.
- Všetci dedinčania znížia ceny pre hráča približne o 20%.

### Mínusy:
- Keď hráč zmokne vo vode/daždi, letí oveľa horšie.
- Krídla sa môžu roztrhnúť, ak hráč dostane konkrétne množstvo poškodenia do chrbta. Obnoviť ich možno so šancou počas jedenia.
- Obmedzenie spánku: rasa môže spať výlučne vysoko vo vzduchu a (možno) len v hniezde.
- Nemôže jesť iné vtáky a všetko s tým spojené.
- Silne zvýšené poškodenie z pádu.

### Neutrálne (vlastnosť rasy, vizuál atď.):
- Schopnosť pravidelne znášať vajcia a zhadzovať perie.
- Nemôže si na seba obliecť vanilla Elytry, keďže má svoje vlastné.

### Výška a model postavy:
- Vzrast vanilla.
- Krídla na chrbte. Perie na ramenách.

### Vizuálna a zvuková časť (pre väčšie ponorenie):
- Pri pobyte pod holým nebom sa hráč cíti prirodzene, ale pod zemou dostáva vizuálne a zvukové efekty stiesneného priestoru.
- Pri plachtení sa okolo hráča objavujú malé častice peria.
- Počas vypadnutia vajíčka zaznie malý zvukový efekt.

### Predmety a bloky:
- `Nový druh ohňostrojov` - na väčšie zrýchlenie v lete.
- `Perie a vajcia` - nové druhy, ktoré znáša vták.
- `Dekoratívne bloky` - celkom isto budú potrebné. Napríklad blok palíc na hniezda.
- `Štruktúry` - horské hniezda (ľadové a obyčajné).

---

## 7. Vlkolak (Vlkodlak, Lykantrop)

### Možnosti tela:

- Vanilla:
  - `Poškodenie úderom` - Bez zmien
  - `Výška kroku` - Silne
  - `Rýchlosť chôdze` - Bez zmien
  - `Rýchlosť plávania` - Bez zmien
  - `Rýchlosť potápania` - Bez zmien

- Cold Sweat:
  - `Odolnosť voči chladu` - Silne
  - `Odolnosť voči teplu` - Slabo

- Custom mods (for future):
  - `Nosnosť` - Bez zmien
  - `Výdrž` - Silne

### Plusy:
- Vo vlčej forme sa výrazne zvyšuje poškodenie úderom, rýchlosť útoku a rýchlosť pohybu.
- Silné výpady umožňujú rýchlo skrátiť vzdialenosť k nepriateľovi.
- Výdrž vo vlčej forme sa spotrebúva podstatne pomalšie alebo sa prakticky nespotrebúva.
- Úplná imunita voči chladu a teplu vo vlčej forme.
- Získava možnosť utíšiť hlad útočením a zabíjaním nepriateľov.
- V zimnom období sa pokryje dodatočnou srsťou, čo zvyšuje ochranu pred chladom.
- Vo vlčej forme sa postupne hromadí zúrivosť. Pri vysokej zúrivosti sa zvyšujú základné vlastnosti vlkolaka.
- Zúrivosť možno udržiavať spôsobovaním poškodenia a zabíjaním nepriateľov.

### Mínusy:
- Vo vlčej forme sa brnenie odloží.
- Vo vlčej forme sa vlkolak môže stravovať iba mäsom a kosťami.
- Veľmi vysoká žravosť a rýchla spotreba potravy.
- Pri nízkej úrovni zúrivosti sa hlad zosilňuje ešte viac.
- Nemôže spať v noci, kým nie je vyliečený z lykantropie.
- Vo vlčej forme môže ťažiť bloky pomalšie kvôli absencii vhodnej formy pre prácu s nástrojmi.
- Dedinčania (a ďalšie možné inteligentné entity) sa pri priblížení vlkolaka vo vlčej forme rozutekajú.
- Železní golemovia (a iné podobné entity) budú útočiť na vlkolaka vo vlčej forme.
- Striebro spôsobuje zvýšené poškodenie a môže sa použiť ako prostriedok ochrany pred vlkolakom.

### Neutrálne (vlastnosť rasy, vizuál atď.):
- Každú noc sa vlkolak premení do vlčej formy. Sila formy závisí od fázy mesiaca: pri splne je vlkolak podstatne silnejší.

### Výška a model postavy:
- Vzrast vanilla. Dve formy postavy: obyčajná a vlčia.
- Vo vlčej forme sa používa samostatný model so srsťou, zväčšenými končatinami a výraznými tesákmi.
- Počas premeny sa model hráča postupne mení s vizuálnym efektom rastu srsti a zväčšovania tela.

### Vizuálna a zvuková časť (pre väčšie ponorenie):
- Pri príchode noci pred premenou počuť silnejúce vytie a charakteristické zvuky kostí a srsti.
- Vo vlčej forme je počuť ťažké dýchanie, vrčanie a zvuky krokov zvieraťa.
- Pri nízkej zúrivosti je počuť ťažšie dýchanie a podráždené vrčanie.
- Pri úspešnom útoku alebo zabití nepriateľa je zúrivosť sprevádzaná krátkym zvukovým efektom.
- Okolitý priestor získa počas premeny výraznejší vizuálny efekt.

### Predmety a bloky:
- `Strieborný amulet` - zabráni premene na niekoľko nocí, po čom sa zlomí. Počas splnu amulet nefunguje.
- `Vlkoboj` - vzácny kvet zo zasnežených hôr, používaný na výrobu prostriedkov na kontrolu formy. Nedá sa rozmnožiť kostnou múčkou. Dá sa kúpiť u liečiteľa (mód na liečiteľa).
- `Elixíry lykantropie` - dočasne vyvolávajú alebo posilňujú vlčiu formu.
- `Strieborné predmety` - zbrane alebo iné predmety, ktoré sú mimoriadne efektívne proti vlkolakom.

---

## 8. Lešij (Stromovec, Verdian)
*(Rasu bude možné vidieť v zozname len s nainštalovaným Serene Seasons)*

### Možnosti tela:
- Vanilla:
  - `Poškodenie úderom` - Bez zmien
  - `Výška kroku` - Bez zmien
  - `Rýchlosť chôdze` - Bez zmien
  - `Rýchlosť plávania` - Bez zmien
  - `Rýchlosť potápania` - Slabá

- Cold Sweat:
  - `Odolnosť voči chladu` - Bez zmien
  - `Odolnosť voči teplu` - Bez zmien

- Custom mods (for future):
  - `Nosnosť` - Bez zmien
  - `Výdrž` - Bez zmien

### Plusy:
- Takmer voľný prechod cez lístie (vo vnútri trochu spomaľuje hráča ako pavučina).
- Možnosť odrezať zo seba rôzne kvety a kôru (časom dorastú späť).
- Prakticky všetky zvieratá sa dajú skrotiť na prvý pokus.
- Nedostáva poškodenie od žihľavy, bobúľ, kaktusu, aloe a akýchkoľvek iných rastlín.
- Úplná pasivita včiel pri zbere medu a surovín (neprejavujú agresiu).
- Získavanie unikátnych materiálov od včiel (svojrázna „farma“ dostupná len jemu).

### Mínusy:
- Prísna vegetariánska diéta.
- Zimné obdobie prežíva veľmi ťažko (vlastnosti sa zhoršujú).
- Úplná neschopnosť uhasiť sa samostatne pri vznietení na jar a v lete, musí použiť vodu alebo iné spôsoby hasenia.

### Neutrálne (vlastnosť rasy, vizuál atď.):
- Počas jedenia vytvára kompost. Môže konzumovať kompost ako jedlo?
- Schopnosť kvitnúť a meniť svoje vlastnosti v závislosti od ročného obdobia módu Serene Seasons.
  - V lete: Telo hráča je pokryté tŕňmi, každý kto ho udrie, dostane poškodenie späť.
  - Na jar: Pasívna regenerácia zo slnečných lúčov. Urýchľuje rast plodín. Včely v blízkosti sa o hráča opelia.
  - Na jeseň: Zvýšená rýchlosť pohybu a zvýšená výdrž.

### Výška a model postavy:
- Vzrast vanilla.
- Kvety / konáre / kôra na tele (závisí od ročného obdobia).

### Vizuálna a zvuková časť (pre väčšie ponorenie):

### Predmety a bloky:
- `Vzorec rastu` - na kontrolu sezónnych vlastností.
- `Kvety a kôra` - materiály odrezávané zo samotného Lešieho.
- `Unikátne materiály` - získavané od včiel (zatiaľ nedostatok nápadov).
- `Štruktúry` - včelíny v dedinách, divoké včelíny, opustené zemľanky.
- `Rôzne druhy kompostu` - (pochybný nápad).

---

## 9. Démon (Ohnivák)

### Možnosti tela:
- Vanilla:
  - `Poškodenie úderom` - Bez zmien
  - `Výška kroku` - Bez zmien
  - `Rýchlosť chôdze` - Bez zmien
  - `Rýchlosť plávania` - Bez zmien
  - `Rýchlosť potápania` - Bez zmien

- Cold Sweat:
  - `Odolnosť voči chladu` - Slabá
  - `Odolnosť voči teplu` - Silná

- Custom mods (for future):
  - `Nosnosť` - Bez zmien
  - `Výdrž` - Bez zmien

### Plusy:
- Prakticky úplná imunita voči vysokým teplotám (dostáva veľmi slabé poškodenie od ohňa, lávy, magmy atď.).
- Počas horenia môže zapaľovať entity svojimi útokmi.
- Môže bezpečne spať v dimenzii Nether.
- Niektorí démoni sú voči hráčovi neutrálni.
- Nazbierané duše entít sa dajú pohltiť na obnovu vlastného zdravia, dušami možno tiež liečiť iné entity alebo hráčov.
- Môže oživiť nedávno zabitého hráča minutím všetkých svojich duší (v režime Hardcore).
- Môže sa znovuzrodiť po smrti na tom istom mieste s použitím svojho maxima duší.
- Hráč môže vylepšovať obyčajné zvitky na zvitky duší minutím nazbieraných duší. Tieto zvitky umožňujú uväzniť určité monštrá.

### Mínusy:
- Pekelná diéta (konzumácia výlučne potravín a predmetov z Netheru/Dolného sveta).
- Nemôže si nastaviť bod oživenia (spawn) pomocou postelí.
- Extrémne vysoká zraniteľnosť a náchylnosť na chlad.
- Hráč nebude môcť obchodovať s dedinčanmi kňazmi.
- Kvôli trčiacim rohom hráč nemá možnosť prechádzať priestorom užším ako 1.5 bloku. Musí ísť bokom alebo použiť pásku.

### Neutrálne (vlastnosť rasy, vizuál atď.):
- Pôvodný bod oživenia v Nethere.
- Pekelný ukazovateľ. Ak bude hráč mimo dimenzie Nether, ukazovateľ bude pomaly hasnúť a pri úplnom vyčerpaní sa hráč teleportuje späť do Netheru. Ukazovateľ sa dá dopĺňať jedlom z Netheru (alebo hodinami, treba premyslieť).
- Zabíjaním nepriateľov do seba zbiera duše (budú sa zobrazovať v inventári, limit duší je 10-20).

### Výška a model postavy:
- Vzrast vanilla.
- Rohy na hlave, malý chvost vzadu.

### Vizuálna a zvuková časť (pre väčšie ponorenie):
- Zriedkavé častice tlejúceho popola, ktoré vzlietajú nahor z tela hráča.

### Predmety a bloky:
- `Páska` - nový prvok vybavenia na hlavu, ktorý umožňuje hráčovi skryť si rohy a tým pádom mať možnosť prechádzať úzkymi miestami.
- `Duša` - nová bytosť, ktorá vyletí z iných zabitých bytostí. Chaoticky letí nahor a po krátkom čase jednoducho zmizne. Možno ju chytiť (pravým kliknutím na dušu) do fialy alebo fľaštičky.
- `Zvitok` - nový predmet, ktorý možno nájsť v štruktúrach alebo kúpiť u dedinčana-kňaza.
- `Zvitok duše` - vylepšená verzia obyčajného zvitku, umožňuje zapečatiť (a časom vyslobodiť) dovnútra takmer akúkoľvek entitu. Obmedzenie: Zvitky duší môže používať len Démon. Ak sa zvitok pokúsi aktivovať iná trieda, artefakt sa zničí, spôsobí postave poškodenie a vzápätí sa vyparí.
- `Strieborné fialy` - nádoba na uchovávanie duší. Skladovať ich možno len dočasne, po dlhom čase odtiaľ vyletia. Dá sa položiť na zem a zapečatiť fialu, alebo naopak otvoriť, aby všetky duše vyleteli a vyliečili všetkých naokolo.
- `Entity` - okrídlení démoni, ktorí lietajú po Nethere. Svojimi útokmi aplikujú na hráča krátkodobý efekt kliatby.
- `Zub démona` - ostrý zub, ktorý vypadne pri zabití démona. Potrebný pri tvorbe varného stojana a novej zbrane - pekelných vidiel. Ako aj na varenie nového elixíru - Kliatba.
- `Pekelné vidly` - nová zbraň podobná trojzubcu. Obojručná zbraň s vysokým poškodením, ktorú nemožno hádzať, dá sa ňou nepriateľ iba prepichnúť. Pri útokoch aplikuje krvácanie.
- `Pekelná verzia dobytka` - ?

---

## 10. Nemŕtvy (Navrátilec)

### Možnosti tela:
- Vanilla:
  - `Poškodenie úderom` - Bez zmien
  - `Výška kroku` - Bez zmien
  - `Rýchlosť chôdze` - Bez zmien
  - `Rýchlosť plávania` - Bez zmien
  - `Rýchlosť potápania` - Bez zmien

- Cold Sweat:
  - `Odolnosť voči chladu` - Bez zmien
  - `Odolnosť voči teplu` - Bez zmien

- Custom mods (for future):
  - `Nosnosť` - Bez zmien
  - `Výdrž` - Bez zmien

### Plusy:
- Na hráča nepôsobia negatívne efekty (dokonca ani Mor).
- Duplikuje na nepriateľa všetky negatívne efekty, ktoré na neho boli aplikované (počas útokov).
- Všetky entity typu "Nemŕtvy" budú voči hráčovi neutrálne, ale začnú útočiť, ak udrie čo i len jedného (mechanika zombie pigmanov).
- Ak má nabitú určitú časť ukazovateľa hniloby, po svojej smrti má možnosť vstať z mŕtvych, pričom zvyšná časť ukazovateľa sa prekonvertuje na zdravie a všetky negatívne efekty zmiznú.

### Mínusy:
- Hnilá diéta (zhnité mäso a iné).
- Má možnosť jesť dokonca aj zhnité jedlo alebo potravu inak nepožívateľnú.
- Dedinčania sa budú hráčovi vyhýbať a golemovia naňho budú útočiť (obchodovanie s dedinčanmi je nemožné).
- Dostáva väčšie poškodenie od strieborných zbraní.
- Ghúlovia, vlci, vlkolaci a všetky podobné entity rýchlo vyčerpávajú ukazovateľ hniloby hráča počas svojich útokov.

### Neutrálne (vlastnosť rasy, vizuál atď.):
- Ukazovateľ hniloby. Čím je vyšší, tým väčšiu má hráč obranu a tým vyššia je jeho nosnosť. Rýchlo sa míňa v bojoch. Dopĺňať ho treba jedením hniloby.
- Ak sa hráč pokúsi zabiť dedinčana, nakazí ho (premení ho na zombie-dedinčana).
- Dostáva poškodenie od elixírov liečenia a dopĺňa si zdravie elixírmi poškodenia.
- Elixíry regenerácie ho otrávia, zatiaľ čo elixíry otravy ho zregenerujú.
- Spať musí v obyčajnej rakve alebo v rakve s hrotmi, obyčajné postele nebudú fungovať.

### Výška a model postavy:
- Vzrast vanilla.
- Niektoré časti tela zhnili a podobajú sa na tie u zombie.

### Vizuálna a zvuková časť (pre väčšie ponorenie):
- Iné zvuky jedenia, viac nechutné, mľaskavé.

### Predmety a bloky:
- `Ľudský amulet` - umožňuje získať ľudský vzhľad, čo dá možnosť obchodovať s dedinčanmi, no rýchlo stráca svoju výdrž. Úplne sa rozbije pri kontakte s nemŕtvymi.
- `Štruktúry` - veľké opustené cintoríny po svete, malé cintoríny v dedinách Upírov.
- `Náhrobok` - dekoratívny blok, ktorý funguje podobne ako vanilla tabuľky.
- `Hrobár` - nová profesia pre Upírov/Dedinčanov.
- `Káď rozkladu` - nový funkčný blok, ktorý využívajú Upíri/Dedinčania na získanie profesie "Hrobár". Funguje podobne ako kompostér, treba ho plniť mäsom a zhnitými produktami, aby z neho vypadla "Hniloba". Mäso sa tam bude rozkladať na hnilobu 2x rýchlejšie ako zhnité mäso.
- `Hniloba` - nový druh hnojiva a zároveň veľmi výživné jedlo, ktoré môže jesť iba rasa "Nemŕtvy".
- `Smútočné sviečky` - nový typ sviečok, ktoré sa budú vyrábať zo striebra (čisto dekoratívna verzia). Budú stáť na opustených hroboch.

---

## 11. Mimozemšťan (Xenos, Radian, Astronit)

### Možnosti tela:
- Vanilla:
  - `Poškodenie úderom` - Bez zmien
  - `Výška kroku` - Bez zmien
  - `Rýchlosť chôdze` - Bez zmien
  - `Rýchlosť plávania` - Bez zmien
  - `Rýchlosť potápania` - Bez zmien

- Cold Sweat:
  - `Odolnosť voči chladu` - Bez zmien
  - `Odolnosť voči teplu` - Bez zmien

- Custom mods (for future):
  - `Nosnosť` - Bez zmien
  - `Výdrž` - Bez zmien

### Plusy:
- Úplná imunita na otravu, radiáciu a kyselinu.
- Nadľudská strava: môže sa stravovať uránom a rádioaktívnymi sladkosťami (uránová zmrzlina, uránová torta, uránový koktail) a piť radón.
- Prístup k unikátnemu božskému obchodovaniu/odmenám od svojho Boha za splnenie kvót.
- Exkluzívna schopnosť čítať nákresy z meteoritov v neznámom jazyku, čím si odomyká unikátne craftovacie recepty a technológie (napríklad Jetpack).
- Ovládanie unikátnych technológií (Jetpack funguje maximálne efektívne len na Mimozemšťanovi; prístup k Teleportačnej stanici).
- Možnosť dočasne zmeniť počasie prostredníctvom uctievania Boha.

### Mínusy:
- Obmedzenie v diéte: bežné jedlo je nahradené "Sladkou diétou" (sladkosti, zmrzlina, torty, sušienky) a rádioaktívnymi potravinami.
- Závislosť od ukazovateľa priazne Boha: má povinnosť pravidelne plniť každodenne sa sťažujúce kvóty na zber rôznych predmetov, inak klesá reputácia a ukazovateľ priazne, čo vedie k debuffom.

### Neutrálne (vlastnosť rasy, vizuál atď.):
- Má unikátnu mechaniku reputácie a ukazovateľa priazne jeho Bohom (získava buffy za vysokú reputáciu aj debuffy za nízku).
- Iné rasy nemôžu interagovať/komunikovať s jeho Bohom a ani čítať mimozemské nákresy.
- Používanie Teleportačnej stanice inými rasami je obmedzené/nebezpečné (spôsobuje im poškodenie a vyžaduje veľa skúseností).

### Výška a model postavy:
- Vzrast vanilla.
- Tykadlo na čele.

### Vizuálna a zvuková časť (pre väčšie ponorenie):
- Vizuál "odparovania" predmetov pri ich spaľovaní/obetovaní pri soche Boha.
- Vizuálne efekty radiácie a uctievania.

### Predmety a bloky:
- Zatiaľ žiadne nápady.

---

## 12. Prázdne?

### Možnosti tela:
- Vanilla:
  - `Poškodenie úderom` -
  - `Výška kroku` -
  - `Rýchlosť chôdze` -
  - `Rýchlosť plávania` -
  - `Rýchlosť potápania` -

- Cold Sweat:
  - `Odolnosť voči chladu` -
  - `Odolnosť voči teplu` -

- Custom mods (for future):
  - `Nosnosť` -
  - `Výdrž` -

### Plusy:
### Mínusy:
### Neutrálne (vlastnosť rasy, vizuál atď.):
### Výška a model postavy:
### Vizuálna a zvuková časť (pre väčšie ponorenie):
### Predmety a bloky:

---

## 13. Prázdne?

### Možnosti tela:
- Vanilla:
  - `Poškodenie úderom` -
  - `Výška kroku` -
  - `Rýchlosť chôdze` -
  - `Rýchlosť plávania` -
  - `Rýchlosť potápania` -

- Cold Sweat:
  - `Odolnosť voči chladu` -
  - `Odolnosť voči teplu` -

- Custom mods (for future):
  - `Nosnosť` -
  - `Výdrž` -

### Plusy:
### Mínusy:
### Neutrálne (vlastnosť rasy, vizuál atď.):
### Výška a model postavy:
### Vizuálna a zvuková časť (pre väčšie ponorenie):
### Predmety a bloky:

---