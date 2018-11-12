# Galgeleg

Release: 2. iteration

Dato: 12/11 - 2018

Lavet af: Thomas Mattsson, s175206

# Realase notes:
Dette er 2. iteration af mit galgespil, hvori jeg primært har implementeret mere funktionalitet som f.eks. highscore og ordliste. Dertil har jeg lavet et custom tastatur, som gør appen mere brugbar. Jeg er gået fra at bruge en txt-fil til at opbevare ordene, men bruger JSON istedet. I ordlisten henter jeg asynkront ord fra myjson.com via en api.
Features overblik:
- Endgame med "Prøv igen" og "Menu"
- Highscore liste gemt i Shared preferences. Som brugeren også kan gemme sit navn i.
- Henter asynkront ord fra nettet med definitioner og viser dem i en ordliste.
- Nyt custom tastatur som gør det nemmere at spille spillet, da man slipper for at bruge Android tastatur.

# TODO:
- Find på algoritme til highscore.
- Animation på starskærm og i spillet.
- Implementer mulighed for at tilføje bruger-bestemte ord.
