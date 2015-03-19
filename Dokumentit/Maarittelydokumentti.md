# Määrittelydokumentti

#### Algoritmit ja tietorakenteet

Toteutan **Huffmanin koodausalgoritmin** ohjelmaan jolla pakataan ja puretaan tiedostoja. Algoritmissä
käytetyt tietorakenteet ovat **binääripuu** ja **keko**.

#### Minkä ongelman ratkaisen ja miksi

Ongelma, jonka jonka ratkaisen on: kuinka pakata tekstitiedosto optimaalisesti. Valitsin sen koska
se vaikuttaa mielenkiintoiselta ongelmalta. Alkutapaamisessa mainittiin Huffmanin koodaus yhtenä
mahdollisuutena, ja tutustuttuani algoritmiin totesin sen olevan sopiva.

#### Mitä syötteitä ohjelma saa ja miten näitä käytetään

Ohjelma saa syötteenä nimen pakattavalle tiedostolle joka pitää purkaa, tai nimen puretulle tiedostolle,
joka pitää pakata.

#### Tavoitteena olevat aika-ja tilavaativuudet

*n* = merkkien määrä alkuperäisessä tiedostossa

Aikavaativuus: O(*n* log *n*). Perustelu: Pinoon lisäämisen aikavaativuus on O(log *n*), ja
operaatio tehdään pahimmassa tapauksessa *n* kertaa.

Tilavaativuus: O(*n*). Perustelu: Algoritmi luo puun jossa on maksimissaan 2*n* - 1 alkiota.

#### Lähteet

http://en.wikipedia.org/wiki/Huffman\_coding
