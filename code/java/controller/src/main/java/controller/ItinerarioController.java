package controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import controllerIF.FunzioniItinerario;
import modelli.Itinerario;
import modelli.LuogoEsteso;
import services.ItinerarioServices;

@RestController
@RequestMapping("/itinerari")
public class ItinerarioController implements FunzioniItinerario{
	
	@Autowired
	private ItinerarioServices service;
	
	/**
	 * Ritorna l'itinerario di viaggio personalizzato all'utente
	 * @param i itinerario
	 * @return itinerario di viaggio, altrimenti errore
	 */
	@Override
	@PostMapping("/add")
	public ResponseEntity<?> addItinerario(@RequestBody Itinerario i) { 
	    Map<Integer, List<LuogoEsteso>> mappa = service.creaTabelleDiMarcia(i);
	    if (mappa != null) {
	        System.out.println("Itinerario inserito");
	        return ResponseEntity.ok(mappa);
	    } else {
	        System.out.println("Itinerario non inserito");
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(Collections.singletonMap("errore", false));
	    }
	}
	
	/**
	 * Ritorna la lista di mappe dato l'username dell'utente
	 * @param u username
	 * @return mappe se presenti, altrimenti errore
	 */
	@Override
	@PostMapping("/mappe/{nomeUtente}")
	public ResponseEntity<?> getNomiItinerarioByUtente(@PathVariable String nomeUtente) {
		List<String> nomiMappe = service.ritornaNomiMappeDatoUtente(nomeUtente);
	    if (nomiMappe.size() > 0) {
	        System.out.println("Mappa ottenuta");
	        return ResponseEntity.ok(nomiMappe);
	    } else {
	        System.out.println("Mappa non ottenuta");
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(Collections.singletonMap("errore", false));
	    }
		
	}
	
	/**
	 * Ritorna la mappa dato nome mappa e l'username dell'utente
	 * @param u username
	 * @return mappe se presenti, altrimenti errore
	 */
	@Override
	@PostMapping("/{nomeMappa}/{nomeUtente}")
	public ResponseEntity<?> getItinerarioByNomeAndUtente(@PathVariable String nomeMappa, @PathVariable String nomeUtente) {
		Map<Integer, List<LuogoEsteso>> mappa = service.ritornaMappeDatoUtente(nomeMappa, nomeUtente);
	    if (mappa != null) {
	        System.out.println("Mappa ottenuta");
	        return ResponseEntity.ok(mappa);
	    } else {
	        System.out.println("Mappa non ottenuta");
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(Collections.singletonMap("errore", false));
	    }
	}
	
	/**
	 * Elimina l'itinerario di viaggio 
	 * @param nomeMappa
	 * @param nomeUtente
	 * @return true, altrimenti errore
	 */
	@Override
	@PostMapping("/delete/{nomeMappa}/{nomeUtente}")
	public ResponseEntity<?> deleteItinerario(@PathVariable String nomeMappa, @PathVariable String nomeUtente) { 
		if (service.eliminaItinerario(nomeMappa, nomeUtente)) {
	        System.out.println("Itinerario eliminato");
	        return ResponseEntity.ok(Collections.singletonMap("esito", true));
	    } else {
	        System.out.println("Itinerario non eliminato");
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(Collections.singletonMap("errore", false));
	    }
	}

}
