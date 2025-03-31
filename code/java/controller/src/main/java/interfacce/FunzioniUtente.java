package interfacce;

import org.springframework.http.ResponseEntity;

import modelli.Utente;

public interface FunzioniUtente {
	
	/**
	 * SignIn utente
	 * @param u 
	 * @return utente se registrato, altrimenti errore
	 */
	public ResponseEntity<?> signInUtente(Utente u);
	
	/**
	 * Restituisce utente dato username
	 * @param  username
	 * @return utente se registrato, altrimenti errore
	 */
	public ResponseEntity<?> getUtente(String username);
	
	/**
	 * Restituisce gli utenti registrati
	 * @return utente se registrato, altrimenti errore
	 */
	public ResponseEntity<?> getAllUtenti();
	
}
