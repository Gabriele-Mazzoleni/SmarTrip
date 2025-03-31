package interfacce;

import java.util.List;

import modelli.Luogo;


public interface FunzioniLuogo {

	public Luogo creaLuogo(String nome, double latitudine, double longitudine, double tempoDiVisita);
	public List<Luogo> getAllLuoghi();
}
