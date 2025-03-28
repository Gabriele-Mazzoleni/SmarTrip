package server;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import modelli.Itinerario;
import modelli.ItinerarioServices;
import modelli.Luogo;
import modelli.Utente;

@RestController
@RequestMapping("/itinerari")
public class ControllerItinerario {
	
	@Autowired
	private ItinerarioServices service;
	
	@PostMapping("/add")
	public Map<Integer, List<String>> addItinerario(@RequestBody Itinerario i) { 
		// Creazione del grafo
        Graph<String, DefaultWeightedEdge> grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        // Aggiungi nodo iniziale (A)
        String nodoA = "A";
        grafo.addVertex(nodoA);

        // Mappa per mantenere le coordinate dei luoghi
        Map<String, double[]> coordinate = new HashMap<>();
        coordinate.put(nodoA, new double[]{i.getLatA(), i.getLonA()});
        Map<String, Double> tempi_di_visita = new HashMap<>();
        tempi_di_visita.put(nodoA, 0.0);

        // Aggiungi i luoghi come nodi nel grafo
        for (Luogo luogo : i.getLuoghi()) {
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
                    int tempoPercorrenza = (int)(distanza / i.getVelocitaMedia());

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
        for (int giorno = 1; giorno <= i.getGiorni(); giorno++) {
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

                if (tempoTotale + tempoPercorrenza + tempi_di_visita.get(nodoScelto)> i.getTempoVisita()) break;
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

		Map<Integer, List<String>> mappa = service.creaTabelleDiMarcia(tabelleDiMarcia);
		if(mappa != null)
			System.out.println("Utente inserito");
		else
			System.out.println("Utente non inserito");
		return mappa;
	}
	
	@GetMapping
	public Map<Integer, List<String>> getAllItinarari() {
		Map<Integer, List<String>> mappa = service.getAllItinerari();
		if(mappa.size() != 0)
			System.out.println("Utenti trovati");
		else
			System.out.println("Utenti non trovati");
	    return service.getAllItinerari();
	}
	
	public static String convertSecondsToTime(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int remainingSeconds = seconds % 60;
        
        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
    }

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
}
