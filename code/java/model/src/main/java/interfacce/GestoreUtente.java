package interfacce;

import java.util.List;

import modelli.Utente;

public interface GestoreUtente {
	
	public Utente salva(Utente u);
	public Utente cercaPerCodice(String codice);
	public List<Utente> getAll();

}
