# Määrittelydokumentti

#### Algoritmit ja tietorakenteet

<<<<<<< HEAD
Toteutan **(Huffmanin koodausalgoritmin** ohjelmaan jolla pakataan ja puretaan tiedostoja. Algoritmissä
käytetyt tietorakenteet ovat **binääripuu** ja **keko**.
=======
Toteutan *Huffmanin koodausalgoritmin* ohjelmaan jolla pakataan ja puretaan tiedostoja. Algoritmissä
käytetyt tietorakenteet ovat *binääripuu* ja *keko*.
>>>>>>> 2b168e5fcb19c8689d751408e144c3fddfa94430

#### Minkä ongelman ratkaisen ja miksi

Ongelma, jonka jonka ratkaisen on: kuinka pakata tekstitiedosto optimaalisesti. Valitsin sen koska
se vaikuttaa mielenkiintoiselta ongelmalta. Alkutapaamisessa mainittiin Huffmanin koodaus yhtenä
mahdollisuutena, ja tutustuttuani algoritmiin totesin sen olevan sopiva.

#### Mitä syötteitä ohjelma saa ja miten näitä käytetään

Ohjelma saa syötteenä nimen pakattavalle tiedostolle joka pitää purkaa, tai nimen puretulle tiedostolle,
joka pitää pakata.

#### Tavoitteena olevat aika-ja tilavaativuudet

<<<<<<< HEAD
*n* = merkkien määrä alkuperäisessä tiedostossa

Aikavaativuus: O(*n* log *n*). Perustelu: Pinoon lisäämisen aikavaativuus on O(log *n*), ja
operaatio tehdään pahimmassa tapauksessa *n* kertaa.

Tilavaativuus: O(_n_). Perustelu: Algoritmi luo puun jossa on maksimissaan 2*n* - 1 alkiota.

#### Lähteet

http://en.wikipedia.org/wiki/Huffman_coding
