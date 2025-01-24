package modelli;

import java.util.ArrayList;
import java.util.List;

import interfacce.GestoreUtente;

/**
 * INVECE CHE LISTA ATTACCARE QUI DB!!!
 */
public class UtenteRepository implements GestoreUtente {

	List<Utente> lista = new ArrayList<>();

	@Override
	public Utente salva(Utente u) {
		lista.add(u);
		return u;
	}

	@Override
	public Utente cercaPerCodice(String codice) {
		for(Utente u : lista)
		if (u.getCodice().equals(codice)) return u;
		return null;
	}

	@Override
	public List<Utente> getAll() {
		return lista;
	}
	
}
