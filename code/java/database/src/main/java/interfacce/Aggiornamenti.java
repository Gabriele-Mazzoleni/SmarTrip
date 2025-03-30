package interfacce;

public interface Aggiornamenti {

	/**
	 * Permette di cambiare password
	 * @param utente dell'utente a cui fare modifiche
	 * @param newPassword nuovo valore della password, unico attributo modificabile
	 * @return 1 se la modifica ha avuto successo, 0 altrimenti
	 */
	public int cambiaPassword(String utente, String newPassword);
	
}
