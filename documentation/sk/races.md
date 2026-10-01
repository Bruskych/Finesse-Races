<div align="center">

# Rasy (Pôvod hráča)
### Zbierka všetkých `nápadov autora` a `iných účastníkov` o rasách, ich schopnostiach, vlastnostiach, obmedzeniach, mechanikách a vizuálnych prvkoch. Toto nie je plán vývoja ani zoznam povinných úloh. Sú tu zhromaždené všetky navrhované nápady a koncepty, ktoré by mohli byť implementované v móde. V ideálnom prípade sa zrealizujú všetky tu opísané mechaniky, avšak konkrétna implementácia jednotlivých nápadov sa môže počas procesu vývoja zmeniť.

</div>

---

## Základné módy (závislosti potrebné pre fungovanie Finesse Races)

- Remedy Core - API autora
- [Cold Sweat](https://www.curseforge.com/minecraft/mc-mods/cold-sweat)
- [Caverns & Chasms](https://www.curseforge.com/minecraft/mc-mods/caverns-and-chasms)

## Módy v pozadí (závislosti, ktoré sú žiaduce pre fungovanie Finesse Races)

- [Serene Seasons](https://www.curseforge.com/minecraft/mc-mods/serene-seasons)
- [Brewin' And Chewin'](https://www.curseforge.com/minecraft/mc-mods/brewin-and-chewin)
- [Species](https://www.curseforge.com/minecraft/mc-mods/species)
- [Create](https://www.curseforge.com/minecraft/mc-mods/create)

---

## Globálne zmeny pre každú rasu
*(Atribúty, odolnosť voči teplote atď.)*

- `Rýchlosť chôdze`
- `Rýchlosť plávania`
- `Rýchlosť ponárania`

- `Teplota` a `vlhkosť` (Cold Sweat)
  - Každá rasa bude mať potenciálne vlastnú toleranciu voči teplote a vlhkosti, alebo naopak, slabosť voči nim.

- `Nosnosť` (vlastný systém hmotnosti brnenia)
  - Každá rasa bude mať svoj vlastný ukazovateľ nosnosti. Od neho bude závisieť vzdialenosť a rýchlosť obnovy kotúľov, ako aj spotreba výdrže.

- `Množstvo a kvalita výdrže` (vlastný systém výdrže)
  - Rýchlosť spotreby výdrže, vzdialenosť a rýchlosť obnovy kotúľov.

- `Systém stravovania (diéta)`
  - Rasa získa menej nasýtenia z jedla, ktoré nepatrí do jej jedálnička, s výnimkou sladkostí.

- `Zvukové efekty`
  - Každá rasa bude mať vlastné zvuky, ako aj jedinečné zvuky pre určité činnosti.

- `Vzhľad`
  - Každá rasa bude mať jedinečný vzhľad v podobe dodatočných prvkov: uši, brada, rohy atď.

- `Výška kroku`
  - Každá rasa má svoju vlastnú výšku kroku, čím je rasa vyššia a silnejšia, tým vyššia je výška jej kroku (umožňuje hráčovi nevyskakovať pri výstupe na 1 blok).

- `Výška postavy`
  - Rasa bude mať vlastnú výšku postavy, ktorá bude ovplyvňovať hrateľnosť: rýchlosť chôdze, výšku výstupu na blok a potrebnú výšku stropu.
  - Výška postavy nebude určená jednoduchým škálovaním celého modelu hráča, ale veľkosťou jednotlivých častí tela. To umožní zachovať "vanilla" vzhľad postavy a vyhnúť sa efektu rovnomerného zväčšenia/zmenšenia celého modelu.

- `Štartovací balíček predmetov`
  - Každá rasa má svoj štartovací balíček, ktorý sa objaví v inventári hráča po výbere rasy.

---

## Globálne predmety / mechaniky, ktoré pridá mód
*(Tieto predmety sa nevzťahujú ku konkrétnym rasám.)*

- `Sféra znovuzrodenia`
  - Umožní hráčovi v strednej alebo neskorej fáze hry zmeniť svoju rasu a znovuzrodiť sa do novej.
- `Elektrošok` (jedinečné častice)
  - Efekt, ktorý z času na čas (raz za pár sekúnd) privedie hráča do strnulosti; hráč sa krátky čas nemôže hýbať a nemôže používať žiadne predmety v rukách. Efekt sa aplikuje pri zásahu bleskom a pri popŕhlení elektrickou medúzou. Lektvar s týmto efektom je možné uvariť s použitím elektro-gélu z medúzy.

- `Očarovanie (Enchanty) / Prekliatia`
  - ...

- `Efekty`
  - Mastnota (Bez lektvarov) - efekt získaný po konzumácii rybieho tuku. Umožňuje rýchlo vyskočiť z vody bez stlačenia klávesu Shift. Núti hráča pasívne vyplávať na hladinu vody. Počas dažďa umožňuje veľmi pomalý vzlet do vzduchu. Rasy, ktoré utrpia poškodenie z tekutín, ním prestanú trpieť.
  - Mokrota - efekt, ktorý umožňuje hráčovi nestrácať vlhkosť.
  - Krvavý smäd - efekt, ktorý hráčovi dáva schopnosti vampirizmu.
  - Krvácanie (Bez lektvarov, krvavé častice) - efekt, ktorý spôsobuje poškodenie, ak sa hráč hýbe.
  - Prekliatie - efekt, ktorý zakazuje nositeľovi akokoľvek si obnovovať zdravie (nepomôže jedlo, lektvary, jednoducho nič).
  - Mor / Nákaza / Čierna smrť (Bez lektvarov, jedinečné častice) - efekt, ktorý možno získať s malou pravdepodobnosťou pri konzumácii zhnitého mäsa alebo jeho alternatív. S malou pravdepodobnosťou sa s týmto efektom budú spawnovať aj zombíci a krysy. Prenáša sa na všetky entity v blízkosti nositeľa, konkrétne - na hráčov, dobytok, zombíkov a ich alternatívy, krysy a dedinčanov. Efekt nemá trvanie (prebieha v 3 štádiách), nedá sa odstrániť mliekom / smrťou / spánkom, dá sa len vyliečiť.

- `Vylepšený systém vlhkosti z módu Cold Sweat`
  - Postih klzkých rúk (Vypadávanie predmetov):
    - Ak je hráč vystavený vysokej úrovni vlhkosti (alebo pod vplyvom efektu Mastnoty), môže s určitou pravdepodobnosťou pri kopaní blokov, používaní nástrojov alebo zasahovaní cieľov upustiť aktuálny predmet z hlavnej ruky na zem.
    - Vo vode alebo v daždi sa šanca na upustenie nástroja výrazne zvyšuje.
    - Pri prijatí poškodenia sa šanca na upustenie držaného predmetu stáva podstatne vyššou.

  - Výnimky a imunity rás:
    - Rasa Obojživelník má absolútnu imunitu voči tejto mechanike — z ich rúk je nemožné vyraziť alebo upustiť predmety kvôli vlhkosti.
    - Špeciálne vrhacie a podvodné zbrane (Trojzubec a Harpúna) majú zabudovanú imunitu: nie je možné ich za žiadnych okolností vyraziť alebo upustiť z rúk.

  - Ochrana a úpravy predmetov:
    - Lepidlo (Spotrebný materiál): Umožňuje naniesť na nástroj alebo zbraň ochrannú vrstvu, ktorá trikrát zabráni náhodnému vypadnutiu predmetu pri práci vo vlhkom prostredí.
    - Očarovanie „Zovretie“ / „Mŕtvy stisk“: Kúzla na zbrane/nástroje, ktoré úplne zabraňujú ich vypadnutiu z rúk.
    - Očarovanie „Dehydratácia“ / „Odzbrojenie“: Agresívne kúzla na zbrane, ktoré pri útoku na cieľ so zvýšenou pravdepodobnosťou vyrazia predmet z jeho rúk.

  - Interakcia s mobmi:
    - Utopenci (Drowned): Pri udelení poškodenia hráčovi aplikujú efekt zvýšenej vlhkosti a majú zvýšenú šancu vyraziť aktuálnu zbraň z rúk hráča svojím útokom.

- `Skazený kvet`
  - Nový druh liečivej rastliny, ktorá rastie v močiaroch (pod vodou).
  - Možno z neho vyrobiť liek proti Moru (jednorazovo odstráni efekt Moru).
  - Možnosť využiť upírsky injektor na výrobu vakcíny.

- `Extrakt z liečivej plesne`
  - Lieči Mor a poskytuje imunitu na niekoľko herných dní.
  - Spôsoby získania: mach z módu Species. Zatiaľ je málo nápadov.

- `Dáždniky (ako predmet)`
  - Dáždnik bude možné vyrobiť vlastnoručne a bude mať svoju odolnosť.
  - Každý dáždnik bude možné zafarbiť na akúkoľvek farbu.
  - Dáždnik zníži alebo úplne eliminuje vplyv slnka na určité rasy.
  - Ak držíte dáždnik v ruke, bude chrániť hráča pred vlhkosťou, dažďom a rozprašovačom z módu Create.

- `Dáždniky (použitie vo svete)`
  - Dedinčania si počas dažďa otvoria dáždniky.

- `Dáždniky (za predpokladu, že klzáky nebudú k dispozícii)`
  - Dáždnik bude možné použiť na plachtenie, pričom sa však bude spotrebúvať jeho odolnosť.
  - Bude tu kompatibilita s hmotnosťou hráča; pri veľkej hmotnosti sa dáždnik pri otvorení z výšky zlomí.
  - Počas dažďa sa lietanie na dáždnikoch sťažuje a šanca na zásah bleskom je väčšia.
  - Medené vylepšenie proti blesku, ktoré umožní menej trpieť bleskami, a ak vás blesk zasiahne, nedostanete poškodenie.
  - Očarovanie, ktoré umožňuje veľmi vysoko vzlietnuť nad ohniskami, pričom každý typ ohniska bude hráča vynášať inak.
  - Možnosť vyrobiť lietajúci dáždnik, ktorý pri podržaní klávesu Shift pomaly vynáša hráča hore a takmer necíti jeho váhu.
  - Dáždniky v Nethere horia, treba pre ne vytvoriť vylepšenie.

- `Klobúk s dáždnikom`
  - Variant pokrývky hlavy s funkciami dáždnika.
  - Konkrétne mechaniky ešte nie sú určené.

- `Rybí tuk`
  - Bude možné ho aplikovať na brnenie / konzumovať / aplikovať na mobov.
  - Po konzumácii hráč získa efekt Mastnota.

- `Uterák`
  - Bude ho možné vytvoriť z látky, umožní utierať sa, a tým znižovať alebo úplne odstraňovať vlhkosť.
  - Uterák bude mať svoju odolnosť.
  - Možno bude možné uterákom utierať aj iných hráčov.

- `Prilba so sviečkou`
  - Poskytne hráčovi pasívne osvetlenie okolo neho, čím odstráni nutnosť neustále držať v ruke fakľu.
  - Sviečka sa počas používania bude postupne spotrebovávať.

- `Štruktúry`
  - Meteority, v ktorých sa budú nachádzať užitočné materiály pre trpaslíka a robota.

---

## Globálne systémy, ktoré zlepšia herný zážitok rás
*(Na implementáciu týchto systémov bude potrebné vytvoriť samostatné módy.)*

- `Systém hmotnosti brnenia`
  - Každá časť brnenia bude mať vlastnú hmotnosť, ktorá bude ovplyvňovať hrateľnosť.

- `Kotúle a systém výdrže`
  - Hráč bude môcť vykonávať kotúle (úhyby), ktoré spotrebúvajú výdrž.
  - Energetické nápoje - určené na zlepšenie systému výdrže. Budú dočasne alebo trvalo zvyšovať zásobu výdrže hráča.

- `Okno atribútov`
  - Samostatné okno v hre zobrazujúce aktuálne atribúty hráča.

- `Nerf a vyváženie Elytry`
  - Mód, ktorý bude podobný Aileronu.
  - Nový systém ohňostrojov - ich výroba a ďalšie veci.

- `Okno prsteňov a užitočných predmetov`
  - Samostatné okno pre vybavenie prsteňov a rôznych užitočných predmetov, napríklad kompasu, hĺbkomeru atď.

- `Liečiteľ`
  - Nový typ potulného obchodníka.
  - Bude predávať rôzne lieky a predmety spojené s liečením. Presný zoznam predmetov zatiaľ nie je určený.

- `Liatina (Ako bonus oceľ a bronz)`
  - Liatina - tvrdší materiál v porovnaní so železom.
  - Bloky liatiny majú fyziku a padajú ako gravitačné bloky (podobne ako piesok).
  - Padajúci blok liatiny spôsobuje väčšie poškodenie ako padajúca nákova.
  - Z liatiny je možné vyrábať závažia, kladivá, vrtáky.
  - V budúcnosti sa bude dať liatina taviť cez Create a vyhňu.

- `Systém hnojív a záhonov`
  - Hnoj
    - Blok: Blok hnoja
    - Pôda: Dusičnanová pôda
    - Zoraná pôda: Dusičnanová zoraná pôda
    - Plodiny: Pšenica, zemiaky, mrkva, kukurica

  - Drevný prach
    - Blok: Blok drevného prachu
    - Pôda: Mulčovaná pôda
    - Zoraná pôda: Mulčovaná zoraná pôda
    - Plodiny: Sadenice stromov, bobule, kríky

  - Guáno
    - Blok: Blok guána
    - Pôda: Fosforová pôda
    - Zoraná pôda: Fosforová zoraná pôda
    - Plodiny: Tekvice, melóny, trstina

  - Hniloba
    - Blok: Blok hniloby
    - Pôda: Zhnitá pôda
    - Zoraná pôda: Zhnitá zoraná pôda
    - Plodiny: Rastliny z Netheru a huby

  - Kompost
    - Blok: Blok kompostu
    - Pôda: Humusová pôda
    - Zoraná pôda: Humusová zoraná pôda
    - Plodiny: Bežné potravinové plodiny

  - Kostná múčka
    - Blok: Blok kostnej múčky
    - Pôda: Vápencová pôda
    - Zoraná pôda: Vápencová zoraná pôda
    - Plodiny: Cvikla, cibuľa a iné koreňové plodiny obľubujúce vápnik

  - Drvený koral
    - Blok: Blok drveného koralu
    - Pôda: Koralová pôda
    - Zoraná pôda: Koralová zoraná pôda
    - Plodiny: Ryža, morské hrozno a iné vodné/tropické rastliny

  - Liadok
    - Hnojí akúkoľvek plodinu, ale oveľa slabšie ako iné typy hnojív.

  - Bežná zoraná pôda
    - Teraz na nej budú všetky plodiny rásť veľmi pomaly a na takomto záhone nebude možné použiť žiadne hnojivo.

---

## 1. Trpaslík (Dverg, Podhoral)

### Telesné schopnosti:
- Vanilla:
  - `Poškodenie bez zbrane` - Silné
  - `Výška kroku` - Bez zmien
  - `Rýchlosť chôdze` - Slabé
  - `Rýchlosť plávania` - Slabé
  - `Rýchlosť ponárania` - Silné

- Cold Sweat:
  - `Odolnosť voči chladu` - Silné
  - `Odolnosť voči teplu` - Slabé

- Custom mods (do budúcnosti):
  - `Nosnosť` - Silné
  - `Výdrž` - Silné

### Plusy:
- Zvýšená rýchlosť kopania krompáčom a zabudovaná ťažba.
- Znížená strata hladu pri kopaní.
- Vyššia odolnosť voči účinkom opitosti (od alkoholu z iných módov).
- Znížené náklady skúseností (XP) na opravu a zvýšená odolnosť nákovy pri práci.
- Možnosť vyrábať alebo nachádzať artefakty (prstene pre sloty Curios, fungujúce podobne ako Wayfarer, s limitom na dva sloty pre prstene, ktoré poskytujú pasívne atribúty: šancu na kritický zásah, šancu na extra ťažbu, zabudované tŕne, rýchlosť útoku, poškodenie, zníženie vlastného odhodenia a zosilnenie odhodenia nepriateľov).

### Mínusy:
- Jaskynná diéta (konzumácia húb, machu, pavúkov a iného jaskynného jedla).
- Obmedzenie spánku: trpaslík môže spať len hlboko v podzemí.
- Je slabý v plávaní.
- Znížený dosah (dosah umiestňovania a kopania blokov).

### Neutrálne (vlastnosť rasy, vizuál atď.):
- Má v sebe zabudovaný pracovný stôl (Crafting table).
- Stupnica alkoholu (je potrebné piť alkohol).
- Rast brady, ktorú je možné ostrihať na získanie nití alebo iných materiálov; brada poskytuje ochranu pred chladom.

### Výška postavy a model:
- Nízky rast (1 - 1,5 bloku na výšku).
- Brada.

### Vizuálna a zvuková časť (pre lepšie ponorenie do hry):
- Pri ťažení rudy alebo kameňa sa okolo hráča objavia malé vizuálne častice prachu a kamennej drte.

### Predmety a bloky:
- `Nové drahokamy` - ignit, rubín, zafír a ďalšie.
- `Stôl drahokamov` - umožňuje dočasne vylepšiť zbrane pomocou drahokamov.
- `Prstene-artefakty` - jedinečné prstene pre sloty Curios.
- `Opustené podzemné vyhne` - nové štruktúry s korisťou a prvkami spojenými s kováčstvom.
- `Vrták` - nový nástroj na kopanie, vyrobený napríklad z liatiny.
- `Píla` - nový nástroj na rúbanie stromov, vyrobený napríklad z liatiny.
- `Trpasličí šrot` - nový "kov", ktorý bude možné nájsť v štruktúrach, meteoritoch, alebo si ho vyrobiť sám roztavením v móde Create.

---

## 2. Obojživelník (Merlin, Vodník)

### Telesné schopnosti:
- Vanilla:
  - `Poškodenie bez zbrane` - Bez zmien
  - `Výška kroku` - Bez zmien
  - `Rýchlosť chôdze` - Mierne zvýšené (Slabé +)
  - `Rýchlosť plávania` - Silné
  - `Rýchlosť ponárania` - Slabé

- Cold Sweat:
  - `Odolnosť voči chladu` - Silné
  - `Odolnosť voči teplu` - Slabé

- Custom mods (do budúcnosti):
  - `Nosnosť` - Slabé
  - `Výdrž` - Silné

### Plusy:
- Ťaženie blokov vo vode je rovnako rýchle ako na súši.
- Lepšia viditeľnosť pod vodou.
- Zvýšená rýchlosť behu počas dažďa.
- Úplná imunita voči vlhkosti (z módu Cold Sweat).

### Mínusy:
- Rybia diéta (morské plody, chaluha a iné).
- Vyššie poškodenie od akýchkoľvek zdrojov tepla (oheň, magma a iné).

### Neutrálne (vlastnosť rasy, vizuál atď.):
- Úplné vypnutie stupnice vzduchu a nemožnosť udusiť sa (žiadne bublinky kyslíka).
- Obmedzenie pre dýchacie prístroje: ani korytnačí pancier, ani dýchacie prístroje neposkytujú žiadne bonusy k dýchaniu.
- Prítomnosť stupnice vlhkosti: je potrebné ju dopĺňať pobytom vo vode, státím v daždi, pitím vody, lektvarov, piva alebo iných nápojov; v teple (podľa mechaniky Cold Sweat) sa stupnica spotrebúva rýchlejšie.

### Výška postavy a model:
- Výška je "vanilla".
- Žiabre a plutvy.

### Vizuálna a zvuková časť (pre lepšie ponorenie do hry):
- Pri pobyte pod vodou sa okolo hráča budú pravidelne objavovať malé bublinky a vodné častice.
- Pri rýchlom plávaní za hráčom zostane malá stopa bublín.

### Predmety a bloky:
- `Lektvary na zadržiavanie vlhkosti` - spomaľujú odparovanie vlhkosti u hráča.
- `Prostriedky na potiahnutie brnenia` - aloa, tuk a vosk, ktoré spomaľujú stratu vlhkosti.
- `Podvodné plodiny` - jedlé rastliny a plodiny, ktoré je možné pestovať pod vodou.
- `Morské mušle` - podlaha z mušlí, ktorá pozostáva zo 4 sekcií. Dá sa spracovať na kostnú múčku.
- `Harpúny` - zbrane na podmorský lov, schopné strieľať pod vodou, priťahovať nepriateľov alebo pritiahnuť samotného hráča k nepriateľom a blokom.
- `Sumec`
- `Homár`
- `Medúza` - nový priateľský mob. Má tri varianty - obyčajná, elektrická, svietiaca. Výskyt každého druhu závisí od biómu. Každý typ medúzy po smrti upustí svoj typ gélu.
  - Gél z medúzy - najbežnejšie želé, z ktorého sa dá vytvoriť blok (dá sa cezeň prechádzať ako cez pavučinu). Hlavným účelom je zvlhčovanie záhonov. Ak kliknete pravým tlačidlom myši na záhon a držíte gél v ruke, záhon zostane mokrý už navždy (vizuálne bude pokrytý tenkou priehľadnou vrstvou gélu). Pri zničení záhona gél nevypadne späť. To umožní pestovať plodiny aj v dimenzii Nether.
  - Elektro-gél z medúzy - z neho sa dá tiež urobiť blok, no pri kontakte s ním sa aplikuje efekt „Elektrošok“. Tento gél je tiež hlavnou zložkou na varenie lektvaru Elektrošoku.
  - Lumi-gél z medúzy - z neho sa dá tiež vytvoriť blok, bude vyžarovať silné svetlo. Funguje rovnako ako bežný gél, a dá sa ním zvlhčiť záhon, ale okrem toho na takýchto záhonoch bude možné pestovať plodiny aj v úplnej tme. Štandardne pri úrovni osvetlenia 9 a nižšie žiadne plodiny nerastú, ale s lumi-gélom to bude možné. Vizuálne bude blok pokrytý rovnakou polopriehľadnou vrstvou gélu, no okrem toho bude vyžarovať slabé svetlo.
- `Morské hrozno` - nová podvodná rastlina (jedlá plodina). Stredový blok slúži ako kotva, a z neho sa morské hrozno bude rozrastať do rôznych smerov ako tekvica. V 1 bloku hrozna budú 4 tyčinky hrozna, ktoré rastú nahor podobne ako morské uhorky (maximálne 2 bloky do výšky).
- `Obrovská chaluha (Kelp)` - nový druh chaluhy, ktorý rastie na kameni v zhlukoch s veľkosťou od 10x10 do 25x25 blokov. Je veľmi hustá, mohutná a dosahuje až na hladinu vody, čím lode budú ponad ňu preplávať pomaly. Hlavný účel - je možné z nej vyrobiť vlastný typ palíc a dosiek.

---

## 3. Upír (Krvožiznivec, Gúl, Dhampír)

### Telesné schopnosti:
- Vanilla:
  - `Poškodenie bez zbrane` - Silné
  - `Výška kroku` - Bez zmien
  - `Rýchlosť chôdze` - Silné
  - `Rýchlosť plávania` - Bez zmien
  - `Rýchlosť ponárania` - Bez zmien

- Cold Sweat:
  - `Odolnosť voči chladu` - Silné
  - `Odolnosť voči teplu` - Slabé

- Custom mods (do budúcnosti):
  - `Nosnosť` - Bez zmien
  - `Výdrž` - Silné

### Plusy:
- Výrazné zosilnenie vampirizmu a mierne zvýšenie rýchlosti pohybu v tme.

### Mínusy:
- Agresivita zo strany dedinčanov a železných golemov (dedinčania odmietajú obchodovať, kým upír neskryje svoju tvár tekvicou alebo iným predmetom, napríklad maskou).
- Zraniteľnosť voči slnečnému žiareniu (upír na slnku tlie/horí). Pomôže špeciálne vybavenie alebo lektvar ohňovzdornosti.
- Extrémne silný strach zo striebra.
- Spôsobuje menšie poškodenie bytostiam v striebornom brnení.

### Neutrálne (vlastnosť rasy, vizuál atď.):
- Prítomnosť stupnice krvi, ktorú je potrebné neustále dopĺňať útokmi alebo pitím krvi.
- Zabudovaný vampirizmus: dopĺňa zásoby krvi pri útokoch, kritických zásahoch a zabitiach (efekt závisí od spôsobeného poškodenia).
- Spánok výlučne v rakve, postele nebudú fungovať (s integráciou módu Sleep Tight).

### Výška postavy a model:
- Výška je "vanilla" (alebo o niečo viac ako 2 bloky).
- Špicaté bledé uši.

### Vizuálna a zvuková časť (pre lepšie ponorenie do hry):
- Pri útoku na entitu sa objavia malé častice krvi a charakteristický zvuk uhryznutia.
- Ak sa nachádza v blízkosti živých bytostí, pravidelne bude počuť tlmené zvuky tlkotu srdca.
- V noci sa okolo hráča budú objavovať slabé temné častice.

### Predmety a bloky:
- `Jedlo` - krvavé pivo, krvavý koktail, krvavá zmrzlina a krvavá torta.
- `Špeciálne brnenie` - chráni pred slnečným svetlom, ale postupne stráca svoju odolnosť.
- `Rakva` - umožňuje upírom spať. Kompatibilita so Sleep Tight.
- `Kapsule a súprava na krv` - kapsule zo Sully's a súprava na uchovávanie krvi.
- `Strieborný injektor` - umožňuje odoberať krv z entít a spôsobovať im pri tom poškodenie.
- `Strieborné šípy` - spôsobujú zvýšené poškodenie upírom, dočasne oslabujú ich schopnosti a blokujú regeneráciu zdravia akejkoľvek bytosti.
- `Krv` - fľaštičky a vedrá s krvou.
- `Očarovania a lektvary vampirizmu` - poskytujú rôzne schopnosti a efekty spojené s vampirizmom.
- `Upíri` - noví upírski mobovia, s ktorými sa dá obchodovať. Štruktúry upírskych hradov a veží. Profesie: hrobár, rituálnik, mäsiar, monštrológ.
- `Lovci` - noví illageri, vyzbrojení striebornými šípmi. Lovia nemŕtvych, vlkolakov a upírov. Sú neutrálni voči ostatným rasám, ale spôsobujú im výrazne menšie poškodenie. Štruktúry táborov a základní.

---

## 4. Enderan (Enderian)

### Telesné schopnosti:
- Vanilla:
  - `Poškodenie bez zbrane` - Silné
  - `Výška kroku` - Silné
  - `Rýchlosť chôdze` - Silné
  - `Rýchlosť plávania` - Slabé
  - `Rýchlosť ponárania` - Silné

- Cold Sweat:
  - `Odolnosť voči chladu` - Bez zmien
  - `Odolnosť voči teplu` - Silné

- Custom mods (do budúcnosti):
  - `Nosnosť` - Silné
  - `Výdrž` - Silné

### Plusy:
- Neutralita endermanov pri očnom kontakte (nereagujú, ak sa im hráč pozrie do očí).
- Vďaka svojim očiam a jedinečnej štruktúre mäsa má vyššiu ochranu pred ohňom a lávou. Má tiež o niečo lepšiu viditeľnosť pod lávou.
- Zvýšený dosah pre umiestňovanie a rozbíjanie blokov.
- Zvýšená vzdialenosť hodu ender perál a žiadne poškodenie z teleportácie ich pomocou.
- Úplná absencia vyčerpania stupnice hladu (konzumácia potravy je možná len kvôli efektom a obnove sŕdc).
- Umožňuje cez atlas (Antique Atlas) teleportovať sa k inému hráčovi alebo na akékoľvek miesto na svete. Cena závisí od vzdialenosti a dimenzie: spotrebúvajú sa ender perly a zdravie. Pri diaľkových teleportoch môže hráč takmer zomrieť, no totem nesmrteľnosti ho dokáže zachrániť. Teleportácia trvá istý čas.

### Mínusy:
- Nemôže spať v posteliach. Môže zaspať postojačky kdekoľvek v Overworlde. V Nethere a v Ende pri pokuse o spánok dostane poškodenie. Klasickým spôsobom si teda nemôže nastaviť bod oživenia (spawn point) - je to možné len v Nethere.
- Hydrofóbia (strach z vody, zranenia pri kontakte s vodou, iné kvapaliny podobné vode mu takisto škodia). Pomôže špeciálne vybavenie alebo iné predmety (dáždnik, dýchací prístroj, rybí tuk).
- Zraniteľnosť voči tekutinám: malé poškodenie pri pití vody/lektvarov alebo pri zásahu vrhacou fľaštičkou s vodou.
- Nemôže akokoľvek interagovať s tekvicami (rozbíjať ich, jesť, nasadiť si ich na hlavu atď.).

### Neutrálne (vlastnosť rasy, vizuál atď.):
- S malou pravdepodobnosťou (10 %) sa môže náhodne teleportovať na krátku vzdialenosť, ak utrpí poškodenie z vody.
- Zraniteľnosť voči maskovaniu: všetci mobovia a hráči, ktorí si nasadia tekvicu na hlavu, sa stanú pre enderana neviditeľnými (on sám si tekvicu nasadiť nemôže).

### Výška postavy a model:
- Vysoký vzrast (2.5 - 3 bloky).
- Častice.

### Vizuálna a zvuková časť (pre lepšie ponorenie do hry):
- Pri očnom kontakte s niekým sa bude kamera hráča triasť a bude počuť zvuky podráždenia.
- Pri teleportácii alebo poškodení zanechá za sebou svojho avatara (stopu).

### Predmety a bloky:
- `Vodná pištoľ` - projektily (voda, kyselina, sóda). Odhadzuje, spomaľuje. Kyselina rozleptáva brnenie.

---

## 5. Mechar (Robot, Ozubenec)

### Telesné schopnosti:
- Vanilla:
  - `Poškodenie bez zbrane` - Silné
  - `Výška kroku` - Bez zmien
  - `Rýchlosť chôdze` - Silné
  - `Rýchlosť plávania` - Slabé
  - `Rýchlosť ponárania` - Silné

- Cold Sweat:
  - `Odolnosť voči chladu` - Slabé
  - `Odolnosť voči teplu` - Silné

- Custom mods (do budúcnosti):
  - `Nosnosť` - Silné
  - `Výdrž` - Silné

### Plusy:
- Prítomnosť väčšieho počtu slotov na vlastné moduly v systéme Curios pre inštaláciu vylepšení nájdených vo svete alebo vytvorených výrobou (rôzne moduly dávajú jedinečné pasívne schopnosti a vlastnosti).

### Mínusy:
- Pri páde do vody bez vylepšení dôjde ku skratu a pravidelne dostáva poškodenie (aplikuje sa efekt Elektrošok).
- Chladné podmienky urýchľujú vybíjanie batérie.
- Ako „potravu“ je nútený konzumovať ozubené kolesá a kovový šrot.

### Neutrálne (vlastnosť rasy, vizuál atď.):
- Úplná absencia stupnice hladu, nahrádza ju stupnica nabitia (pri jej vyčerpaní bude robot slabý a pomalý, ale zachová si schopnosť fungovať).
- Možnosť nabíjať sa z bleskov: robot priťahuje blesky podobne ako medené brnenie a je plne chránený pred ich poškodením, pričom pri zásahu získava nabitie.

### Výška postavy a model:
- Výška je "vanilla".
- Mechanická ruka.

### Vizuálna a zvuková časť (pre lepšie ponorenie do hry):
- Pri prijatí poškodenia sa objavia malé iskry a charakteristický kovový zvuk.
- Robot bude vydávať zvuky mechanických pohonov a pravidelne vypúšťať malé iskry.
- Pri získaní nabitia z blesku sa okolo robota objavia elektrické častice a bude počuť zvuk výboja.
- Po inštalácii alebo výmene modulu sa ozvú zvuky mechanického inštalovania dielov.

### Predmety a bloky:
- `Ozubené kolesá a kovový šrot` - nové predmety, s kompatibilitou so šrotom z módu Alex's Caves.
- `Nabíjacia stanica` - blok na nabíjanie batérií, kombinovaný s miestom na spánok.
- `Batérie` - predstavené ako predmety a bloky.
- `Cievka` - nový blok pre Create, ktorý pri otáčaní spôsobuje elektrické poškodenie mobom.
- `Moduly vylepšení (prázdne a naplnené informáciami)` - získavajú sa pomocou bioanalyzátora pri skenovaní zvierat, každé zviera poskytuje iný modul.
- `Štruktúry` - meteority, budovy a skládky obsahujúce rôzne suroviny.
- `Noví mobovia` - padá z nich šrot a ozubené kolesá.
- `Rôzne vylepšenia`:
  - spracovanie potravy na palivo (umožňuje jesť jedlo po nainštalovaní modulu),
  - plávanie vo vode (bez vylepšenia sa robot topí, je pomalý, skratuje a dostáva poškodenie),
  - integrované osvetlenie (zapína sa priamo v inventári podržaním klávesu za cenu energie),
  - ochrana pred teplotou,
  - zvýšená regenerácia,
  - dvojitý skok (double jump),
  - pružiny (efekt slizu),
  - pomalé plazenie po stenách,
  - rôzna nosnosť,
  - luminiscenčné nočné videnie.

---

## 6. Vták (Avian, Neban)

### Telesné schopnosti:
- Vanilla:
  - `Poškodenie bez zbrane` - Bez zmien
  - `Výška kroku` - Silné
  - `Rýchlosť chôdze` - Silné
  - `Rýchlosť plávania` - Slabé
  - `Rýchlosť ponárania` - Slabé

- Cold Sweat:
  - `Odolnosť voči chladu` - Slabé
  - `Odolnosť voči teplu` - Silné

- Custom mods (do budúcnosti):
  - `Nosnosť` - Slabé
  - `Výdrž` - Silné

### Plusy:
- Zabudované krídla s možnosťou plachtenia (podobné ako „vanilla“ Elytry, ale lepšie).
- Veľmi vysoká mobilita a rýchlosť pohybu vo vzduchu.

### Mínusy:
- Ak hráč vo vode alebo v daždi zmokne, letí oveľa horšie.
- Krídla sa môžu natrhnúť, ak utrpí špecifické množstvo poškodenia zozadu. Je možné ich obnoviť s určitou šancou počas jedenia.
- Obmedzenie spánku: rasa môže spať výhradne vysoko vo vzduchu a (pravdepodobne) len v hniezde.
- Nemôže jesť iné vtáky a nič, čo s tým súvisí.
- Výrazne zvýšené poškodenie z pádu.

### Neutrálne (vlastnosť rasy, vizuál atď.):
- Schopnosť pravidelne znášať vajcia a zhadzovať perie.
- Nemôže si na seba vziať "vanilla" Elytry, keďže má vlastné.

### Výška postavy a model:
- Výška je "vanilla".
- Krídla na chrbte. Operenie na ramenách.

### Vizuálna a zvuková časť (pre lepšie ponorenie do hry):
- Pri pobyte pod otvoreným nebom sa hráč cíti prirodzene, ale v podzemí dostáva vizuálne a zvukové efekty stiesneného priestoru (klaustrofóbia).
- Pri plachtení sa okolo hráča objavujú malé častice peria.
- Počas vypadnutia vajíčka zaznie krátky zvukový efekt.

### Predmety a bloky:
- `Nový typ ohňostrojov` - na väčšie zrýchlenie za letu.
- `Perie a vajcia` - nové druhy, ktoré vták znáša.
- `Dekoratívne bloky` - dosť možno budú potrebné. Napríklad blok palíc na stavbu hniezd.
- `Štruktúry` - horské hniezda (ľadové a bežné).

---

## 7. Vlkolak (Lykantrop)

### Telesné schopnosti:

- Vanilla:
  - `Poškodenie bez zbrane` - Bez zmien
  - `Výška kroku` - Silné
  - `Rýchlosť chôdze` - Bez zmien
  - `Rýchlosť plávania` - Bez zmien
  - `Rýchlosť ponárania` - Bez zmien

- Cold Sweat:
  - `Odolnosť voči chladu` - Silné
  - `Odolnosť voči teplu` - Slabé

- Custom mods (do budúcnosti):
  - `Nosnosť` - Bez zmien
  - `Výdrž` - Silné

### Plusy:
- Vo vlkolačej forme sa výrazne zvyšuje poškodenie zblízka, rýchlosť útoku a rýchlosť pohybu.
- Silné výpady umožňujú rýchlo skrátiť vzdialenosť k nepriateľovi.
- Výdrž vo vlkolačej forme sa spotrebúva oveľa pomalšie alebo takmer vôbec.
- Úplná imunita voči chladu a teplu vo vlkolačej forme.
- Získava schopnosť zahnať hlad útočením a zabíjaním nepriateľov.
- V zimnom období sa pokryje dodatočnou srsťou, čo zvyšuje ochranu pred chladom.
- Vo vlkolačej forme sa postupne hromadí zúrivosť. Pri vysokej zúrivosti sa zvyšujú základné vlastnosti vlkolaka.
- Zúrivosť sa dá udržiavať udeľovaním poškodenia a zabíjaním nepriateľov.

### Mínusy:
- Vo vlkolačej forme sa brnenie odloží/zloží z postavy.
- Vo vlkolačej forme sa môže vlkolak živiť len mäsom a kosťami.
- Veľká pažravosť a rýchla spotreba jedla.
- Pri nízkej úrovni zúrivosti sa hlad ešte viac zintenzívni.
- Nemôže v noci spať, kým nie je lykantropia vyliečená.
- Vo vlkolačej forme môže pomalšie ťažiť bloky, kvôli chýbajúcej vhodnej anatómii na prácu s nástrojmi.
- Dedinčania (a ďalšie prípadné inteligentné entity) sa pri priblížení vlkolaka vo vlčej podobe rozutekajú.
- Železní golemovia (a iné podobné bytosti) budú na vlkolaka vo vlčej podobe útočiť.
- Striebro spôsobuje zvýšené poškodenie a môže byť použité ako prostriedok obrany proti vlkolakovi.

### Neutrálne (vlastnosť rasy, vizuál atď.):
- Každú noc sa vlkolak premení do vlčej podoby. Sila tejto podoby závisí od fázy mesiaca: počas splnu je vlkolak výrazne silnejší.

### Výška postavy a model:
- Výška je "vanilla". Dve podoby postavy: bežná a vlčia.
- Vo vlkolačej forme sa používa samostatný model so srsťou, zväčšenými končatinami a výraznými tesákmi.
- Počas premeny sa model hráča postupne mení s vizuálnym efektom rastu srsti a zväčšenia tela.

### Vizuálna a zvuková časť (pre lepšie ponorenie do hry):
- Pri príchode noci, pred samotnou premenou, je počuť silnejúce zavýjanie a charakteristické zvuky pukania kostí a srsti.
- Vo vlkolačej forme je počuť ťažké dýchanie, vrčanie a zvuky krokov šelmy.
- Pri nízkej zúrivosti je počuť ťažšie dýchanie a podráždené vrčanie.
- Pri úspešnom útoku alebo zabití nepriateľa je zúrivosť sprevádzaná krátkym zvukovým efektom.
- Okolité prostredie získa počas premeny výraznejší vizuálny efekt.

### Predmety a bloky:
- `Strieborný amulet` - zabraňuje premene na niekoľko nocí, po čom sa zničí (zlomí). Počas splnu amulet nefunguje.
- `Kvet lykantropie` - vzácny kvet zo zasnežených hôr, používaný na výrobu prostriedkov na kontrolu premeny. Nedá sa rozmnožiť kostnou múčkou. Dá sa kúpiť u liečiteľa.
- `Žihľava` - prísada do tinktúr proti lykantropii. Pri kontakte spôsobuje poškodenie podobne ako krík bobúľ, ale vyššie.
- `Lektvary lykantropie` - dočasne vyvolávajú alebo zosilňujú vlčiu podobu.
- `Strieborné predmety` - zbrane alebo iné predmety, ktoré sú mimoriadne efektívne proti vlkolakom.

---

## 8. Lešij (Ent, Verdian)
*(Túto rasu bude možné vidieť v zozname len s nainštalovaným módom Serene Seasons)*

### Telesné schopnosti:
- Vanilla:
  - `Poškodenie bez zbrane` - Bez zmien
  - `Výška kroku` - Bez zmien
  - `Rýchlosť chôdze` - Bez zmien
  - `Rýchlosť plávania` - Bez zmien
  - `Rýchlosť ponárania` - Slabé

- Cold Sweat:
  - `Odolnosť voči chladu` - Bez zmien
  - `Odolnosť voči teplu` - Bez zmien

- Custom mods (do budúcnosti):
  - `Nosnosť` - Bez zmien
  - `Výdrž` - Bez zmien

### Plusy:
- Takmer voľný priechod cez lístie (vo vnútri lístia mierne spomaľuje hráča ako pavučina).
- Možnosť odrezať zo seba rôzne kvety a kôru (časom opäť dorastú).
- Takmer všetky zvieratá je možné skrotiť na prvýkrát.
- Nedostáva poškodenie od žihľavy, sladkých bobúľ, kaktusov, aloy a žiadnych iných rastlín.
- Úplná pasivita včiel pri zbere medu a surovín (neprejavujú agresiu).
- Získavanie jedinečných materiálov od včiel (svojrázna „farma“ dostupná len pre neho).

### Mínusy:
- Prísna vegetariánska diéta.
- Zimné obdobie prežíva veľmi ťažko (vlastnosti sa zhoršujú).
- Úplná neschopnosť uhasiť sa samostatne pri vznietení počas jari a leta; musí použiť vodu alebo iné metódy hasenia.

### Neutrálne (vlastnosť rasy, vizuál atď.):
- Počas jedenia vytvára kompost. Bude môcť konzumovať kompost ako potravu?
- Schopnosť kvitnúť a meniť svoje vlastnosti v závislosti od ročného obdobia v móde Serene Seasons.
  - V lete: Telo hráča je pokryté tŕňmi, každý, kto doň udrie, dostane spätné poškodenie.
  - Na jar: Pasívna regenerácia zo slnečných lúčov. Urýchľuje rast plodín. Včely v blízkosti sa o hráča budú opyľovať.
  - Na jeseň: Zvýšená rýchlosť pohybu a vyššia výdrž.

### Výška postavy a model:
- Výška je "vanilla".
- Kvety / vetvičky / kôra na tele (v závislosti od ročného obdobia).

### Vizuálna a zvuková časť (pre lepšie ponorenie do hry):
*(Zatiaľ nie je špecifikované)*

### Predmety a bloky:
- `Receptúra rastu` (na kontrolu sezónnych atribútov).
- `Kvety a kôra`, odrezané zo samotného lešija.
- `Jedinečné materiály`, získané od včiel (zatiaľ je málo nápadov).
- `Štruktúry` - včelnice v dedinách, divoké včelnice, opustené zemľanky.
- `Rôzne typy kompostu` - (diskutabilný nápad).

---

## 9. Démon (Ohnivec)

### Telesné schopnosti:
- Vanilla:
  - `Poškodenie bez zbrane` - Bez zmien
  - `Výška kroku` - Bez zmien
  - `Rýchlosť chôdze` - Bez zmien
  - `Rýchlosť plávania` - Bez zmien
  - `Rýchlosť ponárania` - Bez zmien

- Cold Sweat:
  - `Odolnosť voči chladu` - Slabé
  - `Odolnosť voči teplu` - Silné

- Custom mods (do budúcnosti):
  - `Nosnosť` - Bez zmien
  - `Výdrž` - Bez zmien

### Plusy:
- Takmer úplná imunita voči vysokým teplotám (dostáva veľmi slabé poškodenie od ohňa, lávy, magmy atď.).
- Počas horenia môže svojimi útokmi zapaľovať ostatné entity.
- Môže bezpečne spať v dimenzii Nether.
- Niektorí démoni sú voči hráčovi neutrálni.
- Nazbierané duše entít môže pohlcovať, čím si obnovuje zdravie. Dušami môže liečiť aj iné entity alebo hráčov.
- Môže oživiť nedávno zabitého hráča obetovaním všetkých svojich duší (v režime Hardcore).
- Môže vstať z mŕtvych po smrti na tom istom mieste, ak použije svoje maximum duší.
- Hráč môže vytvárať alebo vylepšovať bežné zvitky na zvitky duší utrácaním nazbieraných duší. Tieto zvitky umožňujú uväzniť určitých netvorov.

### Mínusy:
- Pekelná diéta (konzumácia výlučne potravín a produktov z Netheru).
- Nemôže si nastaviť bod oživenia pomocou postele.
- Extrémne vysoká zraniteľnosť a náchylnosť na chlad.
- Hráč nemôže obchodovať s dedinčanmi-kňazmi (Cleric).
- Kvôli svojim vyčnievajúcim rohom hráč nemôže prechádzať priestorom s menšou šírkou ako 1,5 bloku. Môže ísť iba bokom, alebo použiť pásku na rohy.

### Neutrálne (vlastnosť rasy, vizuál atď.):
- Predvolený bod zrodenia (spawn) je v Nethere.
- Pekelná stupnica. Ak sa hráč nachádza mimo dimenzie Nether, stupnica pomaly klesá, a pri jej úplnom vyčerpaní sa hráč teleportuje späť do Netheru. Stupnica sa dá dopĺňať jedlom z Netheru (alebo pomocou hodín, ešte treba premyslieť).
- Zabitím nepriateľov zbiera do seba ich duše (budú sa zobrazovať v inventári, limit duší je 10-20).

### Výška postavy a model:
- Výška je "vanilla".
- Rohy na hlave, malý chvost vzadu.

### Vizuálna a zvuková časť (pre lepšie ponorenie do hry):
- Vzácne častice tlejúceho popola, ktoré stúpajú nahor z tela hráča.

### Predmety a bloky:
- `Páska` - nová súčasť výbavy na hlavu, ktorá hráčovi umožňuje skryť rohy a tým mu dáva schopnosť prechádzať úzkymi miestami.
- `Duša` - nová entita, ktorá vyletí z iných zabitých tvorov. Chaoticky lieta nahor a po krátkom čase jednoducho zmizne. Dá sa chytiť (Klik pravým tlačidlom myši na dušu) do fialy (ampulky) alebo do fľaštičky.
- `Zvitok` - nový predmet, ktorý sa dá nájsť v štruktúrach alebo kúpiť u dedinčana-kňaza.
- `Zvitok duše` - vylepšená verzia bežného zvitku, ktorá umožňuje zapečatiť (a po čase vypustiť) dovnútra takmer akúkoľvek entitu. Obmedzenie: Zvitky duší môže používať iba rasa Démon. Ak sa zvitok pokúsi aktivovať iná trieda, artefakt sa zničí, postave udelí poškodenie a následne sa vyparí.
- `Strieborné fialy` - nádoba na uloženie duší. Duše je možné uschovať iba dočasne, po dlhšom čase odtiaľ vyletia. Fialu je možné položiť na zem a zapečatiť ju, alebo naopak otvoriť, aby všetky duše vyleteli a vyliečili všetkých naokolo.
- `Entity` - okrídlení démoni, ktorí lietajú po Nethere. Svojimi útokmi aplikujú na hráča krátkodobý efekt prekliatia.
- `Démoní zub` - ostrý zub, ktorý vypadne po zabití démona. Potrebný na výrobu varného stojana (brewing stand) a novej zbrane - pekelných vidlí. Slúži aj na varenie nového lektvaru - Prekliatie.
- `Pekelné vidly` - nová zbraň podobná trojzubcu. Obojručná zbraň s vysokým poškodením, ktorú nie je možné hádzať, dá sa ňou nepriateľ iba prebodnúť. Pri útokoch aplikuje krvácanie.
- `Pekelná verzia dobytka` - ?

---

## 10. Nemŕtvy (Vzkriesený)

### Telesné schopnosti:
- Vanilla:
  - `Poškodenie bez zbrane` - Bez zmien
  - `Výška kroku` - Bez zmien
  - `Rýchlosť chôdze` - Bez zmien
  - `Rýchlosť plávania` - Bez zmien
  - `Rýchlosť ponárania` - Bez zmien

- Cold Sweat:
  - `Odolnosť voči chladu` - Bez zmien
  - `Odolnosť voči teplu` - Bez zmien

- Custom mods (do budúcnosti):
  - `Nosnosť` - Bez zmien
  - `Výdrž` - Bez zmien

### Plusy:
- Na hráča nepôsobia žiadne negatívne efekty (dokonca ani Mor).
- Počas útokov zrkadlí (kopíruje) na nepriateľa všetky negatívne efekty, ktoré na neho boli uvrhnuté.
- Všetky entity typu "Nemŕtvy" (Undead) budú voči hráčovi neutrálne, ale začnú útočiť, ak hráč udrie čo i len jedného z nich (mechanika Zombie Piglinov).
- Ak sa naplní určitá časť stupnice hniloby, po svojej smrti získa možnosť vstať z mŕtvych, pričom zostávajúca časť stupnice sa premení na zdravie a všetky negatívne efekty zmiznú.

### Mínusy:
- Zhnitá diéta (zhnité mäso a ďalšie).
- Dokáže zjesť aj zhnité alebo na konzumáciu inak nevhodné jedlo.
- Dedinčania sa budú hráčovi vyhýbať a golemovia na neho budú útočiť (obchod s dedinčanmi je nemožný).
- Dostáva väčšie poškodenie od strieborných zbraní.
- Gúlovia, vlci, vlkolaci a všetky podobné entity rýchlo vyčerpávajú stupnicu hniloby hráča počas svojich útokov.

### Neutrálne (vlastnosť rasy, vizuál atď.):
- Stupnica hniloby. Čím je vyššia, tým viac ochrany hráč má a tým má aj vyššiu nosnosť. V bojoch sa rýchlo vyčerpáva. Dopĺňa sa požieraním hniloby.
- Ak sa hráč pokúsi zabiť dedinčana, nakazí ho (zmení ho na zombie dedinčana).
- Dostáva poškodenie z lektvarov liečenia (Healing) a obnovuje si zdravie z lektvarov poškodenia (Harming).
- Lektvary regenerácie ho otrávia, zatiaľ čo lektvary otravy ho regenerujú.
- Musí spať v rakve, obyčajné postele nebudú fungovať.

### Výška postavy a model:
- Výška je "vanilla".
- Niektoré časti tela sú zhnité a vyzerajú podobne ako tie u zombíka.

### Vizuálna a zvuková časť (pre lepšie ponorenie do hry):
- Odlišné zvuky jedenia potravy, oveľa odpornejšie, mľaskavé.

### Predmety a bloky:
- `Ľudský amulet` - umožňuje hráčovi získať ľudskú podobu, čo mu umožní obchodovať s dedinčanmi, no amulet rýchlo stráca svoju odolnosť. Úplne sa zničí pri kontakte s nemŕtvymi.
- `Štruktúry` - veľké opustené cintoríny po svete, malé cintoríny v upírskych dedinách.
- `Náhrobok` - dekoratívny blok, ktorý funguje podobne ako "vanilla" ceduľky (tabuľky).
- `Hrobár` - nová profesia pre Upírov/Dedinčanov.
- `Sud rozkladu` - nový funkčný blok, ktorý Upíri/Dedinčania používajú na získanie profesie "Hrobár". Funguje podobne ako kompostér, treba ho plniť mäsom a zhnitými potravinami, aby ste na výstupe získali "Hnilobu". Mäso tam zhnije a vyprodukuje 2-krát viac materiálu ako zhnité mäso zombíkov.
- `Hniloba` - nový druh hnojiva, a zároveň veľmi výživné jedlo, ktoré môže konzumovať výlučne rasa "Nemŕtvy".
- `Smútočné sviečky` - nový typ sviečok, ktoré sa budú vyrábať zo striebra (čisto dekoratívna verzia). Budú umiestnené na opustených hroboch.

---

## 11. Mimozemšťan (Xenos, Radian, Astronit)

### Telesné schopnosti:
- Vanilla:
  - `Poškodenie bez zbrane` - Bez zmien
  - `Výška kroku` - Bez zmien
  - `Rýchlosť chôdze` - Bez zmien
  - `Rýchlosť plávania` - Bez zmien
  - `Rýchlosť ponárania` - Bez zmien

- Cold Sweat:
  - `Odolnosť voči chladu` - Bez zmien
  - `Odolnosť voči teplu` - Bez zmien

- Custom mods (do budúcnosti):
  - `Nosnosť` - Bez zmien
  - `Výdrž` - Bez zmien

### Plusy:
- Úplná imunita voči otrave, radiácii a kyseline.
- Nadľudská strava: môže sa kŕmiť uránom a rádioaktívnymi sladkosťami (uránová zmrzlina, uránová torta, uránový koktail) a piť radón.
- Prístup k jedinečnému božskému obchodovaniu/odmenám od svojho Boha za plnenie kvót.
- Exkluzívna schopnosť čítať nákresy (blueprinty) z meteoritov v neznámom jazyku, čím si otvára jedinečné recepty a technológie (napríklad Jetpack).
- Ovládanie jedinečných technológií (Jetpack funguje maximálne efektívne iba na Mimozemšťanovi; prístup k Teleportačnej stanici).
- Možnosť dočasne meniť počasie prostredníctvom uctievania Boha.

### Mínusy:
- Obmedzenie stravy: bežné jedlo je nahradené „Sladkou diétou“ (sladkosti, zmrzlina, torty, sušienky) a rádioaktívnymi produktmi.
- Závislosť od stupnice priazne Boha: je povinný pravidelne plniť každodenne sa sťažujúce kvóty na zber rôznych predmetov, inak klesá reputácia a stupnica priazne, čo vedie k debuffom (negatívnym efektom).

### Neutrálne (vlastnosť rasy, vizuál atď.):
- Má jedinečnú mechaniku reputácie a stupnice priazne od jeho Boha (získava buffy za vysokú reputáciu, ale aj debuffy za nízku).
- Ostatné rasy nemôžu interagovať/komunikovať s jeho Bohom a čítať mimozemské nákresy.
- Použitie Teleportačnej stanice inými rasami je obmedzené/nebezpečné (spôsobuje im poškodenie a vyžaduje veľa skúseností (XP)).

### Výška postavy a model:
- Výška je "vanilla".
- Tykadlo (tyčinka) na čele.

### Vizuálna a zvuková časť (pre lepšie ponorenie do hry):
- Vizuálny efekt "vyparovania" predmetov pri ich pálení/obetovaní pri soche Boha.
- Vizuálne efekty radiácie a uctievania.

### Predmety a bloky:
- Zatiaľ nie sú nápady.

---

## 12. Prázdne?

### Telesné schopnosti:
- Vanilla:
  - `Poškodenie bez zbrane` -
  - `Výška kroku` -
  - `Rýchlosť chôdze` -
  - `Rýchlosť plávania` -
  - `Rýchlosť ponárania` -

- Cold Sweat:
  - `Odolnosť voči chladu` -
  - `Odolnosť voči teplu` -

- Custom mods (do budúcnosti):
  - `Nosnosť` -
  - `Výdrž` -

### Plusy:
### Mínusy:
### Neutrálne (vlastnosť rasy, vizuál atď.):
### Výška postavy a model:
### Vizuálna a zvuková časť (pre lepšie ponorenie do hry):
### Predmety a bloky:

---

## 13. Prázdne?

### Telesné schopnosti:
- Vanilla:
  - `Poškodenie bez zbrane` -
  - `Výška kroku` -
  - `Rýchlosť chôdze` -
  - `Rýchlosť plávania` -
  - `Rýchlosť ponárania` -

- Cold Sweat:
  - `Odolnosť voči chladu` -
  - `Odolnosť voči teplu` -

- Custom mods (do budúcnosti):
  - `Nosnosť` -
  - `Výdrž` -

### Plusy:
### Mínusy:
### Neutrálne (vlastnosť rasy, vizuál atď.):
### Výška postavy a model:
### Vizuálna a zvuková časť (pre lepšie ponorenie do hry):
### Predmety a bloky:

---