package interfacce;

import modelli.Utente;

public interface GestoreUtente {
	
	public Utente salva(Utente u);
	public Utente cercaPerCodice(String codice);

}
