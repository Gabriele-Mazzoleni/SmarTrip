package controller;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import java.util.*;

public class GrafoAPI {

    // Velocità media a piedi (in m/s)
    private static final double VELOCITA_MEDIA = 1.4; // circa 5 km/h

    // Calcola la distanza in linea d'aria tra due punti geografici (Haversine formula)
    private static double calcolaDistanza(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371000; // Raggio della Terra in metri
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // distanza in metri
    }
    
    public static String convertSecondsToTime(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int remainingSeconds = seconds % 60;
        
        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
    }

    public static Map<Integer, List<String>> calcolaTabelleDiMarcia(
            double latA, double lonA, double pausa, double tempoVisita, int giorni, List<Luogo> luoghi) {

        // Creazione del grafo
        Graph<String, DefaultWeightedEdge> grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        // Aggiungi nodo iniziale (A)
        String nodoA = "A";
        grafo.addVertex(nodoA);

        // Mappa per mantenere le coordinate dei luoghi
        Map<String, double[]> coordinate = new HashMap<>();
        coordinate.put(nodoA, new double[]{latA, lonA});
        Map<String, Double> tempi_di_visita = new HashMap<>();
        tempi_di_visita.put(nodoA, 0.0);

        // Aggiungi i luoghi come nodi nel grafo
        for (Luogo luogo : luoghi) {
            grafo.addVertex(luogo.getNome());
            coordinate.put(luogo.getNome(), new double[]{luogo.getLatitudine(), luogo.getLongitudine()});
            tempi_di_visita.put(luogo.getNome(), luogo.getTempoDiVisita());
        }

        // Aggiungi archi con pesi calcolati in base al tempo di percorrenza
        for (String nodo1 : grafo.vertexSet()) {
            for (String nodo2 : grafo.vertexSet()) {
                if (!nodo1.equals(nodo2)) {
                    double[] coord1 = coordinate.get(nodo1);
                    double[] coord2 = coordinate.get(nodo2);
                    double distanza = calcolaDistanza(coord1[0], coord1[1], coord2[0], coord2[1]);
                    int tempoPercorrenza = (int)(distanza / VELOCITA_MEDIA);

                    // Verifica se l'arco esiste già
                    DefaultWeightedEdge edge = grafo.getEdge(nodo1, nodo2);
                    if (edge == null) {
                        // Se l'arco non esiste, crea un nuovo arco
                        edge = grafo.addEdge(nodo1, nodo2);
                    }

                    // Imposta il peso dell'arco
                    grafo.setEdgeWeight(edge, tempoPercorrenza);
                }
            }
        }

        // Output: Tabelle di marcia per ogni giorno
        Map<Integer, List<String>> tabelleDiMarcia = new HashMap<>();

        // Algoritmo per calcolare il percorso ottimale
        for (int giorno = 1; giorno <= giorni; giorno++) {
            if (grafo.vertexSet().size() <= 1) break; // Solo il nodo A rimasto

            // TSP approssimato con il Nearest Neighbor
            List<String> percorso = new ArrayList<>();
            percorso.add(nodoA);
            double tempoTotale = 0;
            String[] nodoCorrente = {nodoA};

            while (true) {
                // Trova il nodo più vicino rispettando i vincoli
                Optional<String> prossimoNodo = grafo.vertexSet().stream()
                        .filter(nodo -> !percorso.contains(nodo))
                        .min(Comparator.comparingDouble(nodo -> grafo.getEdgeWeight(grafo.getEdge(nodoCorrente[0], nodo))+tempi_di_visita.get(nodo)));

                if (prossimoNodo.isEmpty()) break;

                String nodoScelto = prossimoNodo.get();
                double tempoPercorrenza = grafo.getEdgeWeight(grafo.getEdge(nodoCorrente[0], nodoScelto));

                if (tempoTotale + tempoPercorrenza + tempi_di_visita.get(nodoScelto)> tempoVisita) break;
                System.out.println("tempoTotale: " + convertSecondsToTime((int)tempoTotale) + " tempoPercorrenza: " + convertSecondsToTime((int)tempoPercorrenza));
                percorso.add(nodoScelto);
                tempoTotale += tempoPercorrenza + tempi_di_visita.get(nodoScelto);
                nodoCorrente[0] = nodoScelto; // Aggiornamento del riferimento
            }

            // Completa il percorso tornando al nodo A
            percorso.add(nodoA);

            // Salva il percorso nella tabella di marcia
            tabelleDiMarcia.put(giorno, percorso);

            // Rimuovi i nodi visitati (eccetto A)
            for (String nodo : percorso) {
                if (!nodo.equals(nodoA)) {
                    grafo.removeVertex(nodo);
                }
            }
        }

        return tabelleDiMarcia;
    }

    //classe perchè non si riesce a linkare
    public static class Luogo {
        private final String nome;
        private final double latitudine;
        private final double longitudine;
        private final double tempoDiVisita;

        public Luogo(String nome, double latitudine, double longitudine, double tempoDiVisita) {
            this.nome = nome;
            this.latitudine = latitudine;
            this.longitudine = longitudine;
            this.tempoDiVisita = tempoDiVisita;
        }

        public String getNome() {
            return nome;
        }

        public double getLatitudine() {
            return latitudine;
        }

        public double getLongitudine() {
            return longitudine;
        }

        public double getTempoDiVisita() {
            return tempoDiVisita;
        }
    }
    
    //test sulla classe al volo
    public static void main(String[] args) {
        // Punto di partenza (A)
        double latA = 45.4642; // Milano
        double lonA = 9.19;

        // Parametri
        double pausa = 1800; // 30 minuti in secondi
        double tempoVisita = 10000; // 1,5 ore in secondi
        int giorni = 1;

        // Lista dei luoghi
        List<Luogo> luoghi = new ArrayList<>();
        luoghi.add(new Luogo("Duomo di Milano", 45.4641, 9.1919, 3600));
        luoghi.add(new Luogo("Castello Sforzesco", 45.4705, 9.1796, 1000));
        luoghi.add(new Luogo("Teatro alla Scala", 45.4672, 9.1895, 5600));
        luoghi.add(new Luogo("Parco Sempione", 45.4722, 9.1732, 1800));

        // Calcola le tabelle di marcia
        Map<Integer, List<String>> tabelleDiMarcia = calcolaTabelleDiMarcia(latA, lonA, pausa, tempoVisita, giorni, luoghi);

        // Stampa il risultato
        for (Map.Entry<Integer, List<String>> entry : tabelleDiMarcia.entrySet()) {
            System.out.println("Giorno " + entry.getKey() + ": " + entry.getValue());
        }
    }
}
