package modelli;

import java.util.ArrayList;
import java.util.List;

import interfacce.GestoreLuogo;

public class LuogoRepository implements GestoreLuogo {
	List<Luogo> lista = new ArrayList<>();

	@Override
	public Luogo salva(Luogo l) {
		lista.add(l);
		return l;
	}

	@Override
	public List<Luogo> getAll() {
		return lista;
}
