package api;

import java.util.List;

import org.springframework.stereotype.Service;

import interfacce.FunzioniLuogo;
import modelli.Luogo;
import modelli.LuogoRepository;

@Service
public class LuogoServices implements FunzioniLuogo {


	private LuogoRepository repo = new LuogoRepository();
	
	@Override
	public Luogo creaLuogo(String nome, double latitudine, double longitudine, double tempoDiVisita) {
		return repo.salva(new Luogo(nome, latitudine, longitudine,tempoDiVisita));
	}

	@Override
	public List<Luogo> getAllLuoghi() {
		return repo.getAll();
	}

}
