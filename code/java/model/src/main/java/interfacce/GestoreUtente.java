package interfacce;

import modelli.Utente;

public interface GestoreUtente {
	
	/**
	 * Salva il nuovo utente 
	 * @param u
	 * @return true se successo
	 */
	public boolean salvaUtente(Utente u);
	
	/**
	 * Controlla la password inserita dall'utente 
	 * @param username
	 * @param password
	 * @return true se successo
	 */
	public boolean controllaPassword(String username, String password);

}
