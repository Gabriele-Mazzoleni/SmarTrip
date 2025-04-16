package repository;

import java.util.*;

import org.springframework.stereotype.Repository;
import gestore_db.DatabaseManager;
import interfacce.GestoreItinerario;
import modelli.Itinerario;
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
	        
	        System.out.println("Nome mappa: " + nomeMappa);
	        System.out.println("Giorno: " + giorno);
	        System.out.println("Utente: " + utente);
	        System.out.println("Nomi: " + nomi);
	        System.out.println("Latitudini: " + latitudini);
	        System.out.println("Longitudini: " + longitudini);
	        System.out.println("Città: " + citta);
	        System.out.println("Indirizzi: " + indirizzi);
	        System.out.println("Tipi: " + tipi);
	        System.out.println("Tempi di visita: " + tempiDiVisita);
	        System.out.println("Immagini: " + immagini);
	        System.out.println("Orari di arrivo: " + orariDiArrivo);

	        if(DatabaseManager.getIstanza().getQueryMappa().inserisciMappa(nomeMappa, utente, giorno, nomi, latitudini, longitudini, 
	        		citta, indirizzi, tipi, tempiDiVisita, immagini, orariDiArrivo) == 0) {
	        	return null;
			}
	    }
	    
	    this.tabelleDiMarcia = m;
	    return tabelleDiMarcia;
	}
	
	/*
	 * Restituisce mappa vecchia dal database dato utente
	 */
	@Override
	public void listaMappeDiUtente() {
		// TODO Auto-generated method stub
		
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
