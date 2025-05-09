package databaseIF;

import java.util.List;
import java.util.Map;

public interface LuogoDB {
	
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
	public int inserisciLuogo(String nome, double latitudine, double longitudine, String citta, 
			String indirizzo, String tipo, int tempoVisita, String immagine);
	
	/**
	 * Restituisce tutte le città associate a un determinato luogo
	 * @param nome del luogo
	 * @param latitudine
	 * @param longitudine
	 * @return lista di città, se vuota errore
	 */
	public List<String> ritornaCitta();
	
	/**
	 * Restituisce tutti i luoghi in una città
	 * @param citta
	 * @return lista di luoghi con attributi, se vuota errore
	 */
	public List<Map<String, Object>> ritornaLuoghiCitta(String citta);

	/**
	 * Elimina luogo
	 * @param nome del luogo
	 * @param latitudine
	 * @param longitudine
	 * @return 1 se la rimozione ha avuto successo, 0 altrimenti
	 */
	public int eliminaLuogo(String nome, double latitudine, double longitudine);

}
