package modelIF;

import java.util.List;
import java.util.Map;

import modelli.Itinerario;
import modelli.LuogoEsteso;

public interface GestoreItinerario {

	/*
	 * Converte itinerario calcolato e lo salva nel database come mappa
	 * @param nomeMappa
	 * @param utente
	 * @param m
	 * @return tabella di marcia, altrimenti null
	 */
	public Map<Integer, List<LuogoEsteso>> salva(String nomeMappa, String utente, Map<Integer, List<LuogoEsteso>> m);
	
	/**
	 * Restituisce mappa vecchia dal database dato utente
	 * 
	 * 
	 * 
	 */
	public void listaMappeDiUtente();
	
	/**
	 * Elimina mappa dal database
	 * @param nomeMappa
	 * @param nomeUtente
	 * @return true se successo, altrimenti errore
	 */
	public boolean cancellaMappa(String nomeMappa, String nomeUtente);
	
}
