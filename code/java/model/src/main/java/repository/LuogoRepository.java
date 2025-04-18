package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import gestore_db.DatabaseManager;
import modelIF.GestoreLuogo;
import modelli.Luogo;
import modelli.LuogoConDistanza;

@Repository
public class LuogoRepository implements GestoreLuogo {
	
	/**
	 * Restituisce lista città passate dal database
	 */
	@Override
	public List<String> listaCitta() {
		List<String> citta = DatabaseManager.getIstanza().getQueryLuogo().ritornaCitta();
		if (citta.size() == 0) {
			return null;
		}
		else return citta;
	}
	
	/**
	 * Restituisce lista luoghi di una città passati dal database
	 */
	@Override
	public List<Luogo> listaLuoghiDiCitta(String cittaInserita) {
		List<Map<String, Object>> risultati = DatabaseManager.getIstanza().getQueryLuogo().ritornaLuoghiCitta(cittaInserita);
		if (risultati.size() == 0) {
			return null;
		}
		else {
			List<Luogo> luoghi = new ArrayList<>(); 
			for(Map<String, Object> mappa: risultati) {
				String nome = (String) mappa.get("nome");
				double latitudine = (Double) mappa.get("latitudine");
				double longitudine = (Double) mappa.get("longitudine");
				String citta = (String) mappa.get("citta");
				String indirizzo = (String) mappa.get("indirizzo");
				String tipo = (String) mappa.get("tipo");
				int tempoDiVisita = (Integer) mappa.get("tempoDiVisita");
				String immagine = (String) mappa.get("immagine");
				Luogo luogo = new Luogo(nome, latitudine, longitudine, citta, indirizzo, tipo, tempoDiVisita, immagine);
				luoghi.add(luogo);
			}
			
			return luoghi;
		}
	}
	
	/**
	 * Restituisce lista dei ristoranti del database
	 */
	@Override
	public List<LuogoConDistanza> listaRistorantiVicini(double lon, double lat, int n) {
	    List<Map<String, Object>> risultati = DatabaseManager.getIstanza().getQueryLuogo().ritornaRistorantiVicini(lon, lat, n);
	    
	    if (risultati.isEmpty()) {
	        return null;
	    } else {
	        List<LuogoConDistanza> luoghi = new ArrayList<>();
	        
	        for (Map<String, Object> mappa : risultati) {
	            String nome = (String) mappa.get("nome");
	            double latitudine = (Double) mappa.get("latitudine");
	            double longitudine = (Double) mappa.get("longitudine");
	            String citta = (String) mappa.get("citta");
	            String indirizzo = (String) mappa.get("indirizzo");
	            String tipo = (String) mappa.get("tipo");
	            int tempoDiVisita = (Integer) mappa.get("tempoDiVisita");
	            String immagine = (String) mappa.get("immagine");
	            int distanza = ((Double) mappa.get("distanza")).intValue();

	            Luogo luogo = new Luogo(nome, latitudine, longitudine, citta, indirizzo, tipo, tempoDiVisita, immagine);
	            LuogoConDistanza luogoConDistanza = new LuogoConDistanza(luogo, distanza);
	            luoghi.add(luogoConDistanza);
	        }

	        return luoghi;
	    }
	}

}
