# Testausdokumentti

### Mitä on testattu, miten tämä tehtiin

#### Yksikkötestit

Ohjelma on testattu jUnitin yksikkötesteillä. Rajapintoja ja stubeja on käytetty riippuvuuksien vähentämiseksi.

ArgsParserin testit tarkistavat että jos ensimmäinen argumentti ei ole "c" tai "d", ja toinen argumentti ei ole polku epätyhjään kansioon joka ei ole tiedosto, niin parseaminen epäonnistuu ja virheviesti on oikein. Muulloin tarkistetaan että parseaminen onnistuu.

FileConverterissä on tehty testit jotka tarkistavat että onnistuneessa tapauksessa poistuu vanha tiedosto, jonka tilalle tulee uusi tiedosto, jolla on oikea tiedostonimi ja sisältö. Epäonnistuneessa tapauksessa, eli kun data ei koostu tavallisista merkeistä vaan esim. yritetään kääntää jo valmiiksi käännettyä tiedostoa, tiedostoille ei tehdä mitään.

Encoderissa on testi, joka tarkistaa että annettu data käännetään oikein. Luokkaa voisi testata kattavammin, mutta minusta olisi ikävää muuttaa muita metodeja privatesta publiciksi tätä varten. Lisäksi luokan testeillä ei ole riippuvuuttaa huffmanin puun luomiseen sillä HuffmanTreeBuilder on testissä korvattu stubilla, mikä nostaa luotettavuutta. Lisäksi getNewPathia on testattu. Decoder-luokan testaus on hyvin samanlainen kun Encoderin.

HuffmanTree-luokka on yksinkertainen joten sen testauskin on yksinkertaista, tärkein testi tarkistaa jos annetulle puulle generoidut koodit ovat oikeat.

HuffmanTreeBuilderillä on kaksi testiä: kun data ei ole tavallista tekstiä, tarkistetaan että se heittää puuta tehdessä virheen, muulloin tarkistetaan että puu on korrekti huffman-puu datalle. HuffmanTreeRebuilderin testeissä testataan samat asiat, mutta siinä luotava huffmanin puu on determininistinen, joten siinä tarkistetaan että puu luodaan tismalleen oikealla tavalla, vertailemalla sitä puuhun joka on "oikea lopputulos" käyttäen rekusiivista puunvertailualgoritmia.

PriorityQueuestä on testattu että lisääminen ja poistaminen onnistuu (koon perusteella), ja että heapify toimii, lisäämällä tietyssä järjestykessä alkoita ja sitten tarkistamalla että ne tulevat oikeassa järjestyksessä ulos.

#### Suorituskykytestaus

Tiedoston pakkamisessa aikavaativuuden testaus ei ole kovin mielenkiintoista johtuen merkistön koon asettamasta ylärajasta. Vanhan ja uuden tiedoston koon vertaaminen erilaisilla syötteillä sen sijaan on mielenkiinnon kohteena.
Suurilla tiedostoilla on testattu optimaalista, huonoa ja tavallista tapausta, sekä tapausta kun tiedosto on hyvin pieni.

### Minkälaisilla syötteillä testaus tehtiin

#### Yksikkötestit

Seuraavat asiat ovat testattu jokaisella tapauksella mitä keksin:  
-ArgsParser-luokka  
-FileConverter-luokka  
-Encoderin ja Decoderin getNewPath-metodit

Encoderin ja Decoderin convert-metodit on testattu yhdellä perustapauksella, ja lisäksi on testattu että ne heittävät virheen eteenpäin jos huffmanin puun luonti epäonnistuu.  
HuffmanTreeBuilder- ja HuffmanTreeRebuilder-luokkia on myös testattu yhdellä perustapauksella, ja lisäksi on testattu että ne heittävät virheen jos puun luonti epäonnistuu.

HuffmanTree-luokkaa on testattu yhdellä perustapauksella missä tarkistetaan, että koodit asetetaan oikein.

PriorityQueuesta on testattu että add ja poll toimivat (koon perusteella). Heapify-metodi on testattu lisäämällä alkioita sellaisessa järjestyksessä, että kun ne pollataan pois, kaikki heapifyn tapaukset käydään läpi, samalla kun tarkistetaan että ne tulevat oikeassa järjestyksessä ulos.

#### Suorituskykytestaus

##### Optimaalinen tapaus, suuri tiedosto

Tiedosto, joka koostuu 10000 rivinvaihto-merkistä, eli *10000* tavusta, pakkautuu *1253* tavuun. Tässä tapauksessa tarvitaan vain *12.53%* alkuperäisestä tilasta. 

##### Huono tapaus, suuri tiedosto

Tiedosto, joka koostuu 10000 merkistä, missä käytetään ascii-extendedin desimaaliarvoja 32-255 vastaavia kirjaimia tasaisesti jaoteltuna pakkautuu *10000* tavusta *8450* tavuun. Tässä tapauksessa tarvitaan siis peräti *84.5%* alkuperäisestä tilasta.

##### Tavallinen tapaus, suuri tiedosto

Tiedosto, joka koostuu 10000 lorem ipsum merkistä, pakkautuu *10000* tavusta *5415* tavuun. Tässä tapauksessa tarvitaan siis *54.15%* alkuperäisestä tilasta. 

##### Pieni tiedosto 

Tiedosto, joka koostuu vain yhdestä merkistä merkistä (1 tavu), vaatii peräti kolme tavua eli enemmän kun pakkaamaton versio, sillä tiedostoon pitää edelleen lisätä puun bittiesitys.

### Miten testit voidaan toistaa

#### Yksikkötestit

Suorittamalla jUnit-testit, eli komennolla _gradle clean test_

#### Suorituskysytestaus

Esimerkkitiedostot löytyvät kansiosta Dokumentit/Esimerkkitiedostot/. Testit voi toistaa manuaalisesti vertailemalla kokoja ennen ja jälkeen pakkauksen.

### Testaus graafisessa muodossa

JacocoHtml:län avulla testikattavuutta voi tarkistella näin:  
_gradle clean test jacoco; open build/jacocoHtml/index.html_
