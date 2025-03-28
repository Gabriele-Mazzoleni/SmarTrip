package modelli;

import java.util.List;

import interfacce.ServiziLuogo;

public class LuogoServices implements ServiziLuogo {


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
