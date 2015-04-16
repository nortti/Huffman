# Testausdokumentti

### Mitä on testattu, miten tämä tehtiin

Kaikkia luokkia aiotaan (suurin osa tehty) testata jUnitilla yksikkötesteillä. Rajapintoja ja stubeja on käytetty riippuvuuksien vähentämiseksi.

### Minkälaisilla syötteillä testaus tehtiin

Yksinkertaisilla perustapauksilla, työ ei ole kovin vertailupainoitteinen, ja se joko toimii tai se ei toimi.

### Miten testit voidaan toistaa

Suorittamalla jUnit-testit.

### Testaus graafisessa muodossa

gradle clean test jacoco; open build/jacocoHtml/index.html


