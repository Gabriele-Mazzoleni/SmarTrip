package modelIF;

import java.util.List;

import modelli.Luogo;
import modelli.LuogoConDistanza;

public interface GestoreLuogo {

	/**
	 * Restituisce lista città passate dal database
	 */
	public List<String> listaCitta();
	
	/**
	 * Restituisce lista luoghi di una città passati dal database
	 */
	public List<Luogo> listaLuoghiDiCitta(String cittaInserita);
	
	/**
	 * Restituisce lista dei ristoranti del database
	 */
	public List<LuogoConDistanza> listaRistorantiVicini(double longitudine, double latitudine, int n);
	
}
