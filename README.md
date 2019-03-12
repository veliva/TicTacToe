# TicTacToe
## Projekti seadistamine
1. Lae alla projekt githubist.
2. Android Studios vali **File > New > Import Project.**
3. Vali projekti kaust ja vajuta OK. Valitud projekt avaneb Android Studios.

[Projekti avamise juhend inglise keeles](https://developer.android.com/studio/intro/migrate).
### Juhend
Projekti funktsionaalsus ja algne disain on tehtud [Youtubes oleva õpetuse järgi](https://www.youtube.com/playlist?list=PLrnPJCHvNZuA0ykzsHIdQUC5yKxArSM2V).
Juhendi järgi sai mängu põhiline funktsionaalsus tehtud. Kood, mida kirjutatakse videos on natuke aegunud, mõningaid probleeme ja lahendusi tuli otsida internetist. Vajadusel on iga video kirjelduses ka link lähtekoodile.

Video alguses ütleb koodikirjutaja kohe, et tegemist ei ole kõige parema lähenemisega, kuid tegemist on tema arvates kõige lihtsamaga. Mitmes kohas muutsin hiljem koodi, kuna leidsin tunduvalt lühema ja kergema lahenduse, näiteks mängu seisundi salvestamine telefoni orientatsiooni muutmisel. Midagi otseselt ette heita ei ole, video oli piisavalt selgitav.
### Juhendi muutused
Juhendi järgi sai tehtud ainult funktsionaalsuse ja reset nupu. Juurde lisasin Menu activity ja muutsin selle rakenduse avamisel esimeseks ekraaniks. Menu activitys on Play nupp, mille vajutamisel viiakse Main activitysse, kus on mäng. Menus on ka tekst ja pilt, mille positsioon ekraanil sõltub seadme ekraani suurusest.Main activitys, kus on mäng lisasin *Custom Toolbari*, mille peal on vasakult poolt tagasi mineku nupp, mis viib Menusse, tekst TicTacToe ja paremas ääres Reset nupp, mis alustab mängu algusest ja resetib teenitud punktid.

Disaini poole pealt muutsin rakenduse Primary ja PrimaryDark värve, tegin mängus kasutatavatele nuppudele eraldi XML failis kujunduse. Menu ehk mängu activitys paigutasin mängijate nimed ja skoorid kindlatele asukohtadele, mis jäävad samaks ka erinevate ekraanisuuruste korral. Mängija nimi on rohelist värvi, kui on parajasti tema kord käia. Mängijate nimed on vastavalt Noughts ehk ringid ja Crosses ehk ristid.

## Demo
Rakenduse demo on vaadatav [Streamable keskkonnas](https://streamable.com/1ua91).