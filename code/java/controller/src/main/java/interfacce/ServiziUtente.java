package interfacce;

import java.util.List;

import modelli.Utente;

public interface ServiziUtente {
	
	public Utente creaUtente(String codice, String username, String password);
	public Utente getUtente(String codice);
	public List<Utente> getAllUtenti();
	
}
