package modelli;

import java.util.ArrayList;
import java.util.List;

import gestore_db.InserimentiJooq;
import interfacce.GestoreUtente;

/**
 * Permette di usare le funzioni nel progetto database
 */
public class UtenteRepository implements GestoreUtente {

	List<Utente> lista = new ArrayList<>();

	/**
	 * Salva nel database utente
	 */
	@Override
	public Utente salvaUtente(Utente u) {
		if(InserimentiJooq.getIstanza().utente(u.getUsername(), u.getPassword()) == 1) {
			return u;
		}
		else return null;
	}

	/**
	 * Cerca nel database l'utente
	 */
	@Override
	public Utente cercaPerUsername(String username) {
		for(Utente u : lista)
			if (u.getUsername().equals(username)) return u;
		return null;
	}

	/**
	 * Restituisce tutti gli utenti del database
	 */
	@Override
	public List<Utente> getAll() {
		return lista;
	}
	
}
