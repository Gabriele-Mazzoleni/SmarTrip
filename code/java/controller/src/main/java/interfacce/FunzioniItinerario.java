package interfacce;

import org.springframework.http.ResponseEntity;
import modelli.Itinerario;

public interface FunzioniItinerario {
	
	/**
	 * Ritorna l'itinerario di viaggio personalizzato all'utente
	 * @param i itinerario
	 * @return itinerario di viaggio, altrimenti errore
	 */
	public ResponseEntity<?> addItinerario(Itinerario i);
	
	/**
	 * Ritorna la lista di mappe dato l'username dell'utente
	 * @param u username
	 * @return mappe se presenti, altrimenti errore
	 */
	public ResponseEntity<?> getItinerarioByUsername();

	/**
	 * Elimina l'itinerario di viaggio 
	 * @param nomeMappa
	 * @param nomeUtente
	 * @return true, altrimenti errore
	 */
	public ResponseEntity<?> deleteItinerario(String nomeMappa, String nomeUtente);

}
