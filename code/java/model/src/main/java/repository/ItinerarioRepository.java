package repository;

import java.util.*;

import org.springframework.stereotype.Repository;
import gestore_db.DatabaseManager;
import modelIF.GestoreItinerario;
import modelli.Luogo;
import modelli.LuogoEsteso;

@Repository
public class ItinerarioRepository implements GestoreItinerario{
	
	private Map<Integer, List<LuogoEsteso>> tabelleDiMarcia = new HashMap<>();

	/*
	 * Converte itinerario calcolato e lo salva nel database come mappa
	 */
	public Map<Integer, List<LuogoEsteso>> salva(String nomeMappa, String utente, Map<Integer, List<LuogoEsteso>> m) {
	    for (Map.Entry<Integer, List<LuogoEsteso>> entry : m.entrySet()) {
	        Integer giorno = entry.getKey();
	        
	        StringJoiner nomiSJ = new StringJoiner(";");
	        StringJoiner latitudiniSJ = new StringJoiner(";");
	        StringJoiner longitudiniSJ = new StringJoiner(";");
	        StringJoiner cittaSJ = new StringJoiner(";");
	        StringJoiner indirizziSJ = new StringJoiner(";");
	        StringJoiner tipiSJ = new StringJoiner(";");
	        StringJoiner tempiDiVisitaSJ = new StringJoiner(";");
	        StringJoiner immaginiSJ = new StringJoiner(";");
	        StringJoiner orariDiArrivoSJ = new StringJoiner(";");

	        for (LuogoEsteso luogoEsteso : entry.getValue()) {
	            Luogo luogo = luogoEsteso.getLuogo();

	            nomiSJ.add(luogo.getNome());
	            latitudiniSJ.add(String.valueOf(luogo.getLatitudine()));
	            longitudiniSJ.add(String.valueOf(luogo.getLongitudine()));
	            cittaSJ.add(luogo.getCitta());
	            indirizziSJ.add(luogo.getIndirizzo());
	            tipiSJ.add(luogo.getTipo());
	            tempiDiVisitaSJ.add(String.valueOf(luogo.getTempoDiVisita()));
	            immaginiSJ.add(luogo.getImmagine());
	            orariDiArrivoSJ.add(luogoEsteso.getOrarioDiArrivo());
	        }

	        // Conversione in String
	        String nomi = nomiSJ.toString();
	        String latitudini = latitudiniSJ.toString();
	        String longitudini = longitudiniSJ.toString();
	        String citta = cittaSJ.toString();
	        String indirizzi = indirizziSJ.toString();
	        String tipi = tipiSJ.toString();
	        String tempiDiVisita = tempiDiVisitaSJ.toString();
	        String immagini = immaginiSJ.toString();
	        String orariDiArrivo = orariDiArrivoSJ.toString();

	        if(DatabaseManager.getIstanza().getQueryMappa().inserisciMappa(nomeMappa, utente, giorno, nomi, latitudini, longitudini, 
	        		citta, indirizzi, tipi, tempiDiVisita, immagini, orariDiArrivo) == 0) {
	        	return null;
			}
	    }
	    
	    this.tabelleDiMarcia = m;
	    return tabelleDiMarcia;
	}
	
	/*
	 * Restituisce nomi mappe dal database dato utente
	 */
	@Override
	public List<String> listaNomiMappeDiUtente(String nomeUtente) {
		List<String> nomiMappe = DatabaseManager.getIstanza().getQueryMappa().ritornaNomiMappeUtente(nomeUtente);
		if (nomiMappe.size() == 0) {
			return null;
		}
		else {
			Set<String> unici = new LinkedHashSet<>(nomiMappe); // elimina duplicati
			return new ArrayList<>(unici);
		}
	}
	
	/*
	 * Restituisce mappa dal database dato nome mappa e utente
	 */
	@Override
	public Map<Integer, List<LuogoEsteso>> listaMappeDiUtente(String nomeMappa, String nomeUtente) {
		List<Map<String, Object>> risultati = DatabaseManager.getIstanza().getQueryMappa().ritornaMappeUtente(nomeMappa, nomeUtente);
		if (risultati.size() == 0) {
			return null;
		}
		else {
			Map<Integer, List<LuogoEsteso>> mappa = new HashMap<>(); 
			
			for (Map<String, Object> risultato : risultati) {
				int giorno = (int) risultato.get("giorno");
				String[] nomi = ((String) risultato.get("nomi")).split(";");
				String[] latitudini = ((String) risultato.get("latitudini")).split(";");
				String[] longitudini = ((String) risultato.get("longitudini")).split(";");
				String[] citta = ((String) risultato.get("citta")).split(";");
				String[] indirizzi = ((String) risultato.get("indirizzi")).split(";");
				String[] tipi = ((String) risultato.get("tipi")).split(";");
				String[] tempiDiVisita = ((String) risultato.get("tempiDiVisita")).split(";");
				String[] immagini = ((String) risultato.get("immagini")).split(";");
				String[] orariDiArrivo = ((String) risultato.get("orariDiArrivo")).split(";");

				for (int i = 0; i < nomi.length; i++) {
					String nome = nomi[i];
					double latitudine = Double.parseDouble(latitudini[i]);
					double longitudine = Double.parseDouble(longitudini[i]);
					String città = citta[i];
					String indirizzo = indirizzi[i];
					String tipo = tipi[i];
					int tempo = Integer.parseInt(tempiDiVisita[i]);
					String immagine = immagini[i];
					String orario = orariDiArrivo[i];

					Luogo luogo = new Luogo(nome, latitudine, longitudine, città, indirizzo, tipo, tempo, immagine);
					LuogoEsteso esteso = new LuogoEsteso(luogo, orario);

					mappa.computeIfAbsent(giorno, k -> new ArrayList<>()); // elimina duplicati
					if (!mappa.get(giorno).contains(esteso)) {
						mappa.get(giorno).add(esteso);
					}
				}
			}
			
			return mappa;
		}
	}

	/**
	 * Elimina mappa dal database
	 */
	@Override
	public boolean cancellaMappa(String nomeMappa, String nomeUtente) {
		if(DatabaseManager.getIstanza().getQueryMappa().eliminaMappa(nomeMappa, nomeUtente) > 0) {
			return true;
		}
		else return false;
	}
	
}
