package interfacce;

public interface Inserimenti {
	
	/**
	 * Inserisce nuovo utente
	 * @param username identificativo dell'utente
	 * @param password
	 * @return 1 se l'inserimento ha avuto successo, 0 altrimenti
	 */
	public int utente(String username, String password);
	
	/**
	 * Inserisce nuovo luogo
	 * @param nome del luogo
	 * @param latitudine
	 * @param longitudine
	 * @param citta
	 * @param indirizzo
	 * @param tipo 
	 * @param tempoVisita media tempo di visita in secondi
	 * @param immagine url dell'immagine del luogo
	 * @return 1 se l'inserimento ha avuto successo, 0 altrimenti
	 */
	public int luogo(String nome, double latitudine, double longitudine,
			String citta, String indirizzo, String tipo, int tempoVisita, String immagine);
	
	/**
	 * Inserisce nuova mappa
	 * @param nomeMappa identifica la mappa
	 * @param nomeUtente che possiede la mappa
	 * @param giorno
	 * @param stringaNome è elenco dei nomi dei luoghi 
	 * @param stringaLatitudine è elenco delle latitudini dei luoghi 
	 * @param stringaLongitudine è elenco delle longitudini dei luoghi 
	 * @param stringaTempoVisita è elenco dei tempi di visita dei luoghi 
	 * @param stringaOraArrivo è elenco degli orari di arrivo dei luoghi 
	 * @return 1 se l'inserimento ha avuto successo, 0 altrimenti
	 */
	public int mappa(String nomeMappa, String nomeUtente, int giorno, String stringaNome, String stringaLatitudine, 
			String stringaLongitudine, String stringaTempoVisita, String stringaOraArrivo);

}
