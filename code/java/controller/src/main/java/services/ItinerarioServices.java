package services;

import java.util.*;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.springframework.stereotype.Service;

import modelli.GiornoVisita;
import modelli.Itinerario;
import modelli.Luogo;
import modelli.LuogoEsteso;
import repository.ItinerarioRepository;

@Service
public class ItinerarioServices {

	private ItinerarioRepository repo = new ItinerarioRepository();
	
	public Map<Integer, List<LuogoEsteso>> creaTabelleDiMarcia(Itinerario i) {
		// Creazione del grafo
	      Graph<Luogo, DefaultWeightedEdge> grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
	  
	      // Nodo iniziale (A)
	      Luogo nodoA = new Luogo("Soggiorno", i.getLatA(), i.getLonA(), "", "", "", 0, "");
	      grafo.addVertex(nodoA);
	  
	      // Aggiungi i luoghi come nodi nel grafo
	      for (Luogo luogo : i.getLuoghi()) {
	          grafo.addVertex(luogo);
	      }
	  
	      // Aggiungi archi con pesi basati sulla distanza e il tempo di percorrenza
	      for (Luogo luogo1 : grafo.vertexSet()) {
	          for (Luogo luogo2 : grafo.vertexSet()) {
	              if (!luogo1.equals(luogo2)) {
	                  double distanza = calcolaDistanza(luogo1.getLatitudine(), luogo1.getLongitudine(), 
	                                                    luogo2.getLatitudine(), luogo2.getLongitudine());
	                  int tempoPercorrenza = (int) (distanza / i.getVelocitaMedia());
	  
	                  DefaultWeightedEdge edge = grafo.getEdge(luogo1, luogo2);
	                  if (edge == null) {
	                      edge = grafo.addEdge(luogo1, luogo2);
	                  }
	  
	                  grafo.setEdgeWeight(edge, tempoPercorrenza);
	              }
	          }
	      }
	  
	      // Output: Tabelle di marcia per ogni giorno
	      Map<Integer, List<LuogoEsteso>> tabelleDiMarcia = new HashMap<>();
	  
	      // Algoritmo per calcolare il percorso ottimale
	      for (int giorno = 1; giorno <= i.getNumeroGiorni(); giorno++) {
	    	  GiornoVisita giornoAttuale = i.getGiorno(giorno-1);
	    	  boolean devoPranzare = giornoAttuale.getDevoPranzare();
	    	  int tempoPranzo = giornoAttuale.getTempoPranzo();
		      // Converti ora di inizio in secondi
		      int tempoInizio = convertTimeToSeconds(giornoAttuale.getOrarioDiInizioVisita());
	          if (grafo.vertexSet().size() <= 1) break; // Solo il nodo A rimasto
	  
	          List<LuogoEsteso> percorso = new ArrayList<>();
	          int tempoTotale = tempoInizio;
	          Luogo[] nodoCorrente = {nodoA};
	  
	          percorso.add(new LuogoEsteso(nodoA, convertSecondsToTime(tempoTotale))); // Partenza
	  
	          while (true) {
	              // Trova il nodo più vicino rispettando i vincoli
	              Optional<Luogo> prossimoLuogo = grafo.vertexSet().stream()
	                      .filter(luogo -> !percorso.stream().anyMatch(le -> le.getLuogo().equals(luogo)))
	                      .min(Comparator.comparingDouble(luogo -> grafo.getEdgeWeight(grafo.getEdge(nodoCorrente[0], luogo)) + luogo.getTempoDiVisita()));
	  
	              if (prossimoLuogo.isEmpty()) break;
	  
	              Luogo luogoScelto = prossimoLuogo.get();
	              double tempoPercorrenza = grafo.getEdgeWeight(grafo.getEdge(nodoCorrente[0], luogoScelto));
	              if(tempoTotale >= convertTimeToSeconds(giornoAttuale.getOrarioPranzo()) && devoPranzare) {
	            	  devoPranzare = false;
	            	  percorso.add(new LuogoEsteso(new Luogo("Ricerca ristorante",nodoCorrente[0].getLatitudine(),nodoCorrente[0].getLongitudine(),
	            			  nodoCorrente[0].getCitta(), nodoCorrente[0].getIndirizzo(), "Ristorante",
	            			  tempoPranzo, nodoCorrente[0].getImmagine()), convertSecondsToTime(tempoTotale)));
	            	  tempoTotale+= tempoPranzo;
	              }
	              if (tempoTotale + tempoPercorrenza + luogoScelto.getTempoDiVisita() > (giornoAttuale.getTempoVisita()+tempoInizio)) break;
	              tempoTotale += tempoPercorrenza;
	              percorso.add(new LuogoEsteso(luogoScelto, convertSecondsToTime(tempoTotale)));
	              tempoTotale += luogoScelto.getTempoDiVisita();
	              nodoCorrente[0] = luogoScelto; // Aggiornamento del riferimento
	          }
	  
	          // Completa il percorso tornando al nodo A
	          if (!nodoCorrente[0].equals(nodoA)) {
	        	  tempoTotale += grafo.getEdgeWeight(grafo.getEdge(nodoCorrente[0], nodoA));  
	          }
	          percorso.add(new LuogoEsteso(nodoA, convertSecondsToTime(tempoTotale)));
	  
	          // Salva il percorso nella tabella di marcia
	          tabelleDiMarcia.put(giorno, percorso);
	  
	          // Rimuovi i nodi visitati (eccetto A)
	          for (LuogoEsteso luogoEsteso : percorso) {
	              if (!luogoEsteso.getLuogo().equals(nodoA)) {
	                  grafo.removeVertex(luogoEsteso.getLuogo());
	              }
	          }
	      }
	  
	      return repo.salva(i.getUtente(), tabelleDiMarcia);
	  }
	  
	  private int convertTimeToSeconds(String time) {
	      String[] parts = time.split(":");
	      int hours = Integer.parseInt(parts[0]);
	      int minutes = Integer.parseInt(parts[1]);
	      return (hours * 3600) + (minutes * 60);
	  }
	  
	  private String convertSecondsToTime(int seconds) {
	      int hours = (seconds / 3600) % 24;
	      int minutes = (seconds % 3600) / 60;
	      return String.format("%02d:%02d", hours, minutes);
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
