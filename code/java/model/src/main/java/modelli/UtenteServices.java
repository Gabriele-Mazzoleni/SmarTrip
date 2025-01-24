package modelli;

import java.util.List;

import org.springframework.stereotype.Service;

import interfacce.ServiziUtente;

@Service
public class UtenteServices implements ServiziUtente {
	
	private UtenteRepository repo = new UtenteRepository();

	@Override
	public Utente creaUtente(String codice, String username, String password) {
		return repo.salva(new Utente(codice, username, password));
	}

	@Override
	public Utente getUtente(String codice) {
		return repo.cercaPerCodice(codice);
	}

	@Override
	public List<Utente> getAllUtenti() {
		return repo.getAll();
	}

}
