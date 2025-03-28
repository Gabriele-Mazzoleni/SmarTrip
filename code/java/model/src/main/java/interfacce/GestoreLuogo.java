package interfacce;

import java.util.List;

import modelli.Luogo;

public interface GestoreLuogo {

	public Luogo salva(Luogo l);
	public List<Luogo> getAll();
}
