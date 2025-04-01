package interfacce;

import org.springframework.http.ResponseEntity;

import modelli.Utente;

public interface FunzioniUtente {
	
	/**
	 * SignIn utente
	 * @param u utente
	 * @return utente se registrato, altrimenti errore
	 */
	public ResponseEntity<?> signInUtente(Utente u);
	
	/**
	 * LogIn utente
	 * @param u utente
	 * @return true se password corretta, altrimenti errore
	 */
	public ResponseEntity<?> logInUtente(Utente u);
	
}
