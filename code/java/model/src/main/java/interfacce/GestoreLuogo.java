package interfacce;

import java.util.List;
import java.util.Map;

import modelli.Luogo;



public interface GestoreLuogo {

	/**
	 * Restituisce lista città passate dal database
	 */
	public List<String> listaCitta();
	
	/**
	 * Restituisce lista luoghi di una città passati dal database
	 */
	public List<Luogo> listaLuoghiDiCitta(String cittaInserita);
	
}
