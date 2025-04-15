package interfacce;

public interface MappaDB {

	/**
	 * Inserisce nuova mappa
	 * @param nomeMappa identifica la mappa
	 * @param nomeUtente che possiede la mappa
	 * @param giorno
	 * @param stringaNome è elenco dei nomi dei luoghi 
	 * @param stringaLatitudine è elenco delle latitudini dei luoghi 
	 * @param stringaLongitudine è elenco delle longitudini dei luoghi 
	 * @param stringaCitta è elenco delle città dove si trovano i luoghi
	 * @param stringaIndirizzo è indirizzo dei luoghi
	 * @param stringaTipo indica se il luogo è visitabile o ristoro
	 * @param stringaTempoVisita è elenco dei tempi di visita dei luoghi
	 * @param stringaImmagine è elenco dei link delle immagini 
	 * @param stringaOraArrivo è elenco degli orari di arrivo dei luoghi 
	 * @return 1 se l'inserimento ha avuto successo, 0 altrimenti
	 */
	public int inserisciMappa(String nomeMappa, String nomeUtente, int giorno, String stringaNome, String stringaLatitudine, 
			String stringaLongitudine, String stringaCitta, String stringaIndirizzo, String stringaTipo, String stringaTempoVisita, 
			String stringImmagine, String stringaOraArrivo);
	
	/**
	 * Elimina mappa
	 * @param nomeMappa identifica la mappa
	 * @param nomeUtente che possiede la mappa
	 * @return 1 se la rimozione ha avuto successo, 0 altrimenti
	 */
	public int eliminaMappa(String nomeMappa, String nomeUtente);
	
}
