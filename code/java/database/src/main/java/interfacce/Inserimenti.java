package interfacce;

public interface Inserimenti {
	
	public int utente(String codice, String username, String password);
	public int luogo(String nome, double latitudine, double longitudine,
			String citta, String indirizzo, String tipo, int durata);

}
