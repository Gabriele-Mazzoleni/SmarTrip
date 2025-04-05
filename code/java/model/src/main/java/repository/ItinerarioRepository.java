package repository;

import java.util.*;

import org.springframework.stereotype.Repository;

import modelli.LuogoEsteso;

@Repository
public class ItinerarioRepository {
	
	Map<Integer, List<LuogoEsteso>> tabelleDiMarcia = new HashMap<>();

	public Map<Integer, List<LuogoEsteso>> salva(String utente, Map<Integer, List<LuogoEsteso>> m){
		
		for (Map.Entry<Integer, List<LuogoEsteso>> entry : m.entrySet()) {
		    Integer giorno = entry.getKey();
		    StringJoiner nomi = new StringJoiner(";");  // StringJoiner evita l'ultimo ";"
		    
		    for (LuogoEsteso luogoEsteso : entry.getValue()) {
		        nomi.add(luogoEsteso.getLuogo().getNome());
		    }
		    
		    System.out.println("Risultato: " + giorno + ": " + nomi);
		}

		
		this.tabelleDiMarcia=m;
		return tabelleDiMarcia;
	}

	public Map<Integer, List<LuogoEsteso>> getAll() {
		return tabelleDiMarcia;
	}
	
}
