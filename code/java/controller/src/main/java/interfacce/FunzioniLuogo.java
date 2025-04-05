package interfacce;

import org.springframework.http.ResponseEntity;

public interface FunzioniLuogo {

	/**
	 * Ritorna la lista di città disponibili
	 * @return citta se presenti, altrimenti errore
	 */
	public ResponseEntity<?> getAllCitta();
}
