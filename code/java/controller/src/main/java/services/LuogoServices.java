package services;

import java.util.List;

import org.springframework.stereotype.Service;

import repository.LuogoRepository;

/**
 * Logica delle api di luogo, utilizza le funzioni del model
 */
@Service
public class LuogoServices {

	private LuogoRepository repo = new LuogoRepository();
	
	/**
	 * Ritorna lista di tutte le citta chiamando funzione dal model
	 * @return citta
	 */
	public List<String> ritornaCitta() {
		return repo.listaCitta();
	}

}
