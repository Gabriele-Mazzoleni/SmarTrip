package services;

import org.springframework.stereotype.Service;
import modelli.Utente;
import repository.UtenteRepository;

/**
 * Logica delle api di utente, utilizza le funzioni del model
 */
@Service
public class UtenteServices {
	
	private UtenteRepository repo = new UtenteRepository();

	/**
	 * Registra utente chiamando funzione del model
	 * @param username
	 * @param password
	 * @return true se è possibile inserire utente, altrimenti errore
	 */
	public boolean registraUtente(String username, String password) {
		return repo.salvaUtente(new Utente(username, password));
	}
	
	/**
	 * Controlla password chiamando funzione del model
	 * @param username
	 * @param password
	 * @return true se password corretta, altrimenti errore
	 */
	public boolean accediUtente(String username, String password) {
		return repo.controllaPassword(username, password);
	}

}


