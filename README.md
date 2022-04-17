# Kötelező Program Java (Heroes)
Heroes of Magic &amp; Might Duel

--------
Futtatás
-

A futtatáshoz kelleni fog a Maven, amit [itt](https://maven.apache.org/index.html) le tudsz tölteni.
A Maven telepítése után beírjuk a `mvn clean javafx:run` parancsot a projekt mappában, és reménykedünk, hogy sikerüljön
a build. Amennyiben nem sikerül, ajánlatos újratölteni a projektet, mivel esetlegesen el tudja
rontani még egy IDE-n belül is a futtathatóságot (tapasztalat). (Vagy csak IntelliJ-n belül rebuildeljük a projektet, amíg nem indul el).
Sikeres build esetén egy egyszerű `java -jar` paranccsal futtatni tudjuk a programot.
[(További Maven anyag)](https://openjfx.io/openjfx-docs/#maven)

...a projekt futtatása még megtörténhet IntelliJ IDEA-ból, ahol az egyetemi órákon tanult módon megnyitjuk
a projekt fájlt a fejlesztői környezetben.

**Ez javasolt a program teszteléséhez, hiszen itt biztosan lefut, illetve lehet logolni is.**

---




Tudnivalók
-
A játék nem tartalmazza a Player VS AI módot. Csak a Multiplayer mód teljes értékű.
A BOT megtartja a nagybevásárlást, le is rakja az egységeit, viszont lépkedni már nem tud.

(kár hogy a webtervet is 04.17.-én kell beadni am i right)

A Multiplayer is különböző arany mennyiséggel játszható, úgy gondoltam, hogy ez így színesebbé teszi
a játékélményt.

A játék tartalmaz 2 extra varázslatot, illetve 2 extra egységet.

A plusz varázslatok, magyarázattal:

- Kötélbilincs: Egy kör erejéig tehetetlenné tesz egy ellenséges egységet, azaz a célpont kimarad 1 körből.
- Harci mámor (P Skill Nagymester): Minden szövetséges egység támadóerejét megnöveli varázserő * 2-vel

A két extra egység:

- Mágus: a mágus támadások után gyógyítja magát legfeljebb varázserő * 5 életerővel, viszont ha max. életerőn van,
akkor nem gyógyul tovább
- Szupercsillagharcos: a legendás Szupercsillagharcosok 50% eséllyel kivédik a beérkező támadást.
Drága, de jó használat mellett igazán overpowered.

**Jó tesztelést!**
