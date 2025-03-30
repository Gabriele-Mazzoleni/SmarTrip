package interfacce;

public interface Rimozioni {

	/**
	 * Elimina utente
	 * @param username
	 * @param password
	 * @return 1 se la rimozione ha avuto successo, 0 altrimenti
	 */
	public int utente(String codice);
	
	/**
	 * Elimina luogo
	 * @param nome del luogo
	 * @param latitudine
	 * @param longitudine
	 * @return 1 se la rimozione ha avuto successo, 0 altrimenti
	 */
	public int luogo(String nome, double latitudine, double longitudine);
	
	/**
	 * Elimina mappa
	 * @param nomeMappa identifica la mappa
	 * @param nomeUtente che possiede la mappa
	 * @return 1 se la rimozione ha avuto successo, 0 altrimenti
	 */
	public int mappa(String nomeMappa, String nomeUtente);
	
}
