package modelIF;

import java.util.List;
import java.util.Map;
import modelli.LuogoEsteso;

public interface GestoreItinerario {

	/*
	 * Converte itinerario calcolato e lo salva nel database come mappa
	 * @param nomeMappa
	 * @param nomeUtente
	 * @param m
	 * @return tabella di marcia, altrimenti null
	 */
	public Map<Integer, List<LuogoEsteso>> salva(String nomeMappa, String nomeUtente, Map<Integer, List<LuogoEsteso>> m);
	
	/*
	 * Restituisce nomi mappe dal database dato utente
	 * @param nomeUtente
	 * @return lista nomi, altrimenti null
	 */
	public List<String> listaNomiMappeDiUtente(String nomeUtente);
	
	/*
	 * Restituisce mappa dal database dato nome mappa e utente
	 * @param nomeMappa
	 * @param nomeUtente
	 * @return mappa, altrimenti null
	 */
	public Map<Integer, List<LuogoEsteso>> listaMappeDiUtente(String nomeMappa, String nomeUtente);
	
	/**
	 * Elimina mappa dal database
	 * @param nomeMappa
	 * @param nomeUtente
	 * @return true se successo, altrimenti errore
	 */
	public boolean cancellaMappa(String nomeMappa, String nomeUtente);
	
}
