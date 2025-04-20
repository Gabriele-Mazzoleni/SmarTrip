
# Funzione `creaTabelleDiMarcia(itinerario)`

## Descrizione generale

Questa funzione genera le tabelle di marcia per un itinerario turistico, creando un percorso ottimizzato giorno per giorno, tenendo conto dei tempi di visita, orari dei pasti e distanza tra i luoghi.

---

## Pseudocodice dettagliato

```pseudocode
Funzione creaTabelleDiMarcia(itinerario):

    if (itinerario è nullo oppure itinerario.luoghi è vuoto):
        viene restituita una mappa vuota

    grafo = nuovo grafo pesato

    nodoAlloggio = nuovo luogo con coordinate dell’alloggio
    nodoAlloggio viene aggiunto al grafo

    for each luogo in itinerario.luoghi:
        luogo viene aggiunto al grafo

    for i da 0 a numero dei luoghi - 1:
        for j da i+1 a numero dei luoghi:
            luogo1 = itinerario.luoghi[i]
            luogo2 = itinerario.luoghi[j]

            distanza = viene calcolata la distanza tra luogo1 e luogo2
            tempoPercorrenza = viene calcolato il tempo per percorrerla a piedi

            if (non esiste ancora un arco tra luogo1 e luogo2):
                un arco viene aggiunto al grafo tra i due

            il peso dell’arco viene impostato a tempoPercorrenza

    tabelle = nuova mappa vuota

    for giornoIndex da 0 a itinerario.giorni.size - 1:

        giorno = viene preso il giorno corrispondente dell’itinerario

        tempoInizio = l’orario di inizio del giorno viene convertito in secondi
        orarioPranzo = l’orario previsto per il pranzo viene convertito in secondi
        tempoCorrente = tempoInizio

        percorso = nuova lista vuota
        al percorso viene aggiunta una tappa che rappresenta l’alloggio con l’orario di partenza
        nodoCorrente = nodoAlloggio

        luoghiVisitati = nuova lista vuota
        devePranzare = viene preso dal giorno se il pranzo è previsto
        pranzoTroppoLungo = false

        while (ci sono luoghi visitabili nel grafo):

            migliorLuogo = null
            tempoMinimo = infinito

            for each luogo in grafo.nodi:
                if (luogo è già nel percorso):
                    continua

                tempoSpostamento = viene calcolato il tempo da nodoCorrente a luogo
                tempoArrivo = tempoCorrente + tempoSpostamento
                tempoFineVisita = tempoArrivo + durata della visita al luogo

                if (tempoFineVisita < tempoMinimo):
                    migliorLuogo = luogo
                    tempoMinimo = tempoFineVisita

            if (migliorLuogo è null):
                esci dal ciclo

            if (devePranzare e tempoCorrente >= orarioPranzo):
                if (tempoCorrente + 3600 <= giorno.orarioFine convertito in secondi):
                    al percorso viene aggiunta una tappa fittizia "pranzo"
                    tempoCorrente = tempoCorrente + 3600
                    devePranzare = false
                else:
                    pranzoTroppoLungo = true

            tempoRitorno = tempo da migliorLuogo all’alloggio
            if (tempoMinimo + tempoRitorno > giorno.orarioFine convertito in secondi oppure pranzoTroppoLungo):
                esci dal ciclo

            tempoCorrente = tempoCorrente + tempo da nodoCorrente a migliorLuogo
            al percorso viene aggiunta una tappa con migliorLuogo e tempoCorrente
            tempoCorrente = tempoCorrente + durata della visita
            nodoCorrente = migliorLuogo
            luoghiVisitati.add(migliorLuogo)

        tempoRitorno = tempo da nodoCorrente all’alloggio
        tempoCorrente = tempoCorrente + tempoRitorno
        al percorso viene aggiunta una tappa con l’alloggio e tempoCorrente

        tabelle["Giorno " + (giornoIndex + 1)] = percorso

        for each luogo in luoghiVisitati:
            il luogo viene rimosso dal grafo

    repository.salvaMappa(nome dell’itinerario, utente, tabelle)

    restituisci tabelle
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
