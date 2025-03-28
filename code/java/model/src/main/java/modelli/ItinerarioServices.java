package modelli;

import java.util.*;

public class ItinerarioServices {

	private ItinerarioRepository repo = new ItinerarioRepository();
	
	public Map<Integer, List<String>> creaTabelleDiMarcia(Map<Integer, List<String>> m) {
        return repo.salva(m);
	}

	public Map<Integer, List<String>> getAllItinerari() {
		return repo.getAll();
	}
}
