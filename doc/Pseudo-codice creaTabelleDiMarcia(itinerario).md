
# Funzione `creaTabelleDiMarcia(itinerario)`

## Descrizione generale

Questa funzione genera le tabelle di marcia per un itinerario turistico, creando un percorso ottimizzato giorno per giorno, tenendo conto dei tempi di visita, orari dei pasti e distanza tra i luoghi.

---

## Pseudocodice dettagliato

```pseudocode
Funzione creaTabelleDiMarcia(itinerario):

    if (itinerario è nullo oppure itinerario.luoghi è vuoto):
        return (mappa vuota);

    grafo = nuovo grafo pesato;

    nodoAlloggio = nuovo luogo con coordinate dell’alloggio;
    grafo.add(nodoAlloggio);

    for each luogo in itinerario.luoghi:
       grafo.add(luogo);

    for (i da 0 a numero dei luoghi - 1){
        for j da i+1 a numero dei luoghi:
            luogo1 = itinerario.luoghi[i]
            luogo2 = itinerario.luoghi[j]

            distanza = calcolaDistanza(luogo1, luogo2);
            tempoPercorrenza = calcolaTempoPercorrenza(distanza,velocità);

            if (non esiste ancora un arco tra luogo1 e luogo2):
                grafo.addArco(luogo1,luogo2);

            grafo.setPeso(arco tra luogo1 e luogo2, tempoPercorrenza);
    }
    tabellaDiMarcia = nuova mappa vuota

    for (nGiorno da 0 a itinerario.giorni.size - 1) {

        giornoAttuale = itinerario.getGiorno(nGiorno-1);
        tempoInizio = convertiInSecondi(giornoAttuale.Orario_di_inizio_visita);
        devePranzare = giornoAttuale.deve_pranzare;
        pranzoTroppoLungo = false;
        orarioPranzo = convertiInSecondi(giornoAttuale.Orario_di_pranzo);
        tempoCorrente = tempoInizio;
        percorso = new lista di luoghi;
        percorso.add(nodoAlloggio,tempoCorrente);
        nodoCorrente = nodoAlloggio;

        if(rimasto solo il nodoAlloggio nel grafo):
            break;

        while (true){
            
            prossimoLuogo = nodo più vicino a nodoCorrente non ancora visitato e presente nel grafo che minimizza (tempoPercorrenza + tempoDiVisita);
            if (prossimoLuogo.isEmpty()):
                break;

            luogoScelto = prossimoLuogo;
            tempoPercorrenza = grafo.getPeso(nodoCorrente,luogoScelto);

            if (devePranzare && tempoCorrente >= orarioPranzo):
                if (tempoCorrente + durataPranzo < mezzanotte): 
                    percorso.add(ricercaRistorante,tempoCorrente);
                    tempoCorrente += durataPranzo;
                    devePranzare = false;
                else:
                    pranzoTroppoLungo = true;
             if (tempoCorrente + tempoPercorrenza + luogoScelto.TempoDiVisita > giornoAttuale.orarioFineVisite || pranzoTroppoLungo):
                break;

            tempoCorrente += tempoPercorrenza;
            percorso.add(luogoScelto,tempoCorrente);
            tempoCorrente += luogoScelto.TempoPerVisitare;
            nodoCorrente = luogoScelto;
        }
        if (nodoCorrente diverso da nodoAlloggio):
            tempoCorrente += grafo.getPeso(nodoCorrente,nodoAlloggio);
        percorso.add(nodoAlloggio,tempoCorrente);

        tabellaDiMarcia.put(nGiorno,percorso);

        for (luogo in percorso) {
            if (luogo diverso da nodoAlloggio):
                grafo.rimuoviNodo(luogo);
        }
    }

    return repository.salva(itinerario.NomeMappa, itinerario.Utente, tabellaDiMarcia);
```

---

## Funzioni di supporto

```pseudocode
Funzione calcolaDistanza(luogo1, luogo2):
    Viene applicata la formula della distanza tra coordinate geografiche
    La distanza viene restituita in metri

Funzione convertiOrarioInSecondi(orario):
    L’orario nel formato "HH:MM" viene convertito in secondi dall’inizio della giornata
    Il valore viene restituito

Funzione nuovaTappa(luogo, tempoSecondi):
    Viene creato un oggetto contenente:
        - il nome del luogo
        - l’orario leggibile (convertito da secondi)
    L’oggetto viene restituito
```
