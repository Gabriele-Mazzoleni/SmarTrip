package databaseIF;

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
	 * Controlla se la password inserita è corretta
	 * @param username 
	 * @param password valore da controllare
	 * @return 1 se corretta, 0 altrimenti
	 */
	public int controllaPassword(String username, String password);
	
	/**
	 * Elimina utente
	 * @param username
	 * @param password
	 * @return 1 se la rimozione ha avuto successo, 0 altrimenti
	 */
	public int eliminaUtente(String username);
	
}
