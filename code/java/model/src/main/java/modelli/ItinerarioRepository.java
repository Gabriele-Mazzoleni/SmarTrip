package modelli;

import java.util.*;


public class ItinerarioRepository {
	Map<Integer, List<String>> tabelleDiMarcia = new HashMap<>();

	public Map<Integer, List<String>> salva(Map<Integer, List<String>> m){
		this.tabelleDiMarcia=m;
		return tabelleDiMarcia;
	}

	public Map<Integer, List<String>> getAll() {
		return tabelleDiMarcia;
	}
	
}
