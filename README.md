# Netflix

This repository contains the source code for the Netflix API.

### Extra

If you see this repo open an issue containing a title "Il tornado è tornato".

# Learnings (in Italian)

### Always Valid state

Quando applicato garantisce che una istanza sia in uno stato considerato "sempre valido".

Questo vuol dire che un oggetto, grazie al design della sua classe, ingloba regole di validazione al suo interno
e nel caso un metodo vada a cambiarne lo stato, lo stesso garantisce che le invarianti sia garantite.      

Importante è lanciare una eccezione (più esplicita possibile) ogni volta si tenti di creare un oggetto, o di mutarlo, in uno stato invalido. 
 
Esempio: vedere un VO o un entità del package com.antondoddo.netflix.production
 
### Value Object

Un VO è un oggetto immutabile, quindi una volta istanziato le sue proprietà non possono cambiare;
Due VO sono considerati uguali tra di loro se tutte le loro proprietà sono uguali.

Esempio: vedere un classe nel package com.antondoddo.netflix.production.valueobject
Esempio nella standard library: java.time.Duration
 
### Entity

Un entità è un oggetto mutabile, deve avere un identità (ergo un ID);
due entità sono considerate uguali tra di loro se hanno lo stesso ID.

Esempio: com.antondoddo.netflix.production.Production

### Static Factory Methods 

Design pattern che tramite mira a sostituire un semplice override del costruttore dando un nome esplicito e chiaro di quando usarlo.  
Usato in concomitanza con un costruttore privato, si può garantire l'efficacia del naming del metodo.
Essendo un metodo statico è legato alla classe, applicando questo pattern facilita la creazione di un oggetto in uno stato sempre valido (vedi Always Valid State).  

Esempio: costruttori `ofMovie` e `ofEpisode` nella classe com.antondoddo.netflix.production.Production
Esempio nella standard library: costruttori `ofSeconds` nella classe java.time.Duration

### Null Object Pattern

Design pattern che, grazie alla creazione di un "null object",
gestisce i comportamenti di un oggetto che potrebbe essere referenziato come `null`. 
 
Esempio: com.antondoddo.netflix.production.valueobject.NullSeason
 