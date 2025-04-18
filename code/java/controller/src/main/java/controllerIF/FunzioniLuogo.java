package controllerIF;

import org.springframework.http.ResponseEntity;

public interface FunzioniLuogo {

	/**
	 * Ritorna la lista di città disponibili
	 * @return citta se presenti, altrimenti errore
	 */
	public ResponseEntity<?> getAllCitta();
	
	/**
	 * Ritorna la lista di luoghi data la città
	 * @param citta
	 * @return luoghi se presenti, altrimenti errore
	 */
	public ResponseEntity<?> getLuoghiByCitta(String citta);
	
	/**
	 * Ritorna la lista dei n ristoranti più vicini date le coordinate
	 * @param latitudine, longitudine, n
	 * @return lista dei n ristoranti più vicini se presenti, altrimenti errore
	 */
	public ResponseEntity<?> getRistorantiByCoordinate(double latitudine, double longitudine, int n);
	
}
