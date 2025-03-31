package api;

import java.util.List;

import org.springframework.stereotype.Service;

import interfacce.FunzioniUtente;
import modelli.Utente;
import modelli.UtenteRepository;

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
	 * @return
	 */
	public Utente registraUtente(String username, String password) {
		return repo.salvaUtente(new Utente(username, password));
	}

	/**
	 * Ritorna utente chiamando funzione del model
	 * @param username
	 * @return
	 */
	public Utente getUtente(String username) {
		return repo.cercaPerUsername(username);
	}

	/**
	 * Ritorna tutti gli utenti chiamando funzione del model
	 * @return
	 */
	public List<Utente> getAllUtenti() {
		return repo.getAll();
	}

}


