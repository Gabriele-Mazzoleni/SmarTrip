package interfacce;

public interface UtenteDB {
	
	/**
	 * Inserisce nuovo utente
	 * @param username identificativo dell'utente
	 * @param password
	 * @param dbPath
	 * @return 1 se l'inserimento ha avuto successo, 0 altrimenti
	 */
	public int inserisciUtente(String username, String password);

	/**
	 * Elimina utente
	 * @param username
	 * @param password
	 * @return 1 se la rimozione ha avuto successo, 0 altrimenti
	 */
	public int eliminaUtente(String username);
	
	/**
	 * Permette di cambiare password
	 * @param utente dell'utente a cui fare modifiche
	 * @param newPassword nuovo valore della password, unico attributo modificabile
	 * @return 1 se la modifica ha avuto successo, 0 altrimenti
	 */
	public int cambiaPassword(String utente, String newPassword);
	
}
