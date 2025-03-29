package modelli;

import java.util.*;


public class ItinerarioRepository {
	Map<Integer, List<LuogoEsteso>> tabelleDiMarcia = new HashMap<>();

	public Map<Integer, List<LuogoEsteso>> salva(Map<Integer, List<LuogoEsteso>> m){
		this.tabelleDiMarcia=m;
		return tabelleDiMarcia;
	}

	public Map<Integer, List<LuogoEsteso>> getAll() {
		return tabelleDiMarcia;
	}
	
}
