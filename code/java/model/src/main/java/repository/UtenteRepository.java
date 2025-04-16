package repository;

import org.springframework.stereotype.Repository;

import gestore_db.DatabaseManager;
import modelIF.GestoreUtente;
import modelli.Utente;

/**
 * Permette di usare le funzioni nel progetto database
 */
@Repository
public class UtenteRepository implements GestoreUtente {

	/**
	 * Salva nel database utente
	 */
	@Override
	public boolean salvaUtente(Utente u) {
		if(DatabaseManager.getIstanza().getQueryUtente().inserisciUtente(u.getUsername(), u.getPassword()) == 1) {
			return true;
		}
		else return false;
	}

	/**
	 * Controlla se la password inserita è corretta
	 */
	@Override
	public boolean controllaPassword(String username, String password) {
		if(DatabaseManager.getIstanza().getQueryUtente().controllaPassword(username, password) == 1) {
			return true;
		}
		else return false;
	}
	
}
