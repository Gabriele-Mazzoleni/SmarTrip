package interfacce;

import java.util.List;

import modelli.Utente;

public interface GestoreUtente {
	
	
	/**
	 * Salva il nuovo utente 
	 * @param u
	 * @return utente
	 */
	public Utente salvaUtente(Utente u);
	
	/**
	 * Restituisce utente dato l'username
	 * @param username
	 * @return utente 
	 */
	public Utente cercaPerUsername(String username);
	
	/**
	 * Visualizza tutti gli utenti
	 * @return lista utenti salvati
	 */
	public List<Utente> getAll();

}
