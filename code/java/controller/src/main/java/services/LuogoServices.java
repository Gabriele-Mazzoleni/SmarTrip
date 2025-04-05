package services;

import java.util.List;

import org.springframework.stereotype.Service;

import modelli.Luogo;
import repository.LuogoRepository;

/**
 * Logica delle api di luogo, utilizza le funzioni del model
 */
@Service
public class LuogoServices {

	private LuogoRepository repo = new LuogoRepository();
	
	/**
	 * Ritorna lista di tutte le citta chiamando funzione dal model
	 * @return lista citta
	 */
	public List<String> ritornaCitta() {
		return repo.listaCitta();
	}
	
	/**
	 * Ritorna lista dei luoghi di una città chiamando funzione dal model
	 * @return lista luoghi
	 */
	public List<Luogo> ritornaLuoghiDataCitta(String citta) {
		return repo.listaLuoghiDiCitta(citta);
	}

}
