package repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import gestore_db.DatabaseManager;
import interfacce.GestoreLuogo;

@Repository
public class LuogoRepository implements GestoreLuogo {
	
	/**
	 * Restituisce lista città passate dal database
	 */
	@Override
	public List<String> listaCitta() {
		List<String> citta = new ArrayList<>();
		citta = DatabaseManager.getIstanza().getQueryLuogo().ritornaCitta();
		return citta;
	}

}
