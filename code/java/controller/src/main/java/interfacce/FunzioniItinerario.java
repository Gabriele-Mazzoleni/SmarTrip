package interfacce;

import org.springframework.http.ResponseEntity;

import modelli.Itinerario;

public interface FunzioniItinerario {
	
	/**
	 * Ritorna l'itinerario di viaggio personalizzato all'utente
	 * @param i utente
	 * @return itinerario di viaggio, altrimenti errore
	 */
	public ResponseEntity<?> addItinerario(Itinerario i);
	

}
