package api;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import interfacce.FunzioniItinerario;
import modelli.Itinerario;
import modelli.Luogo;
import modelli.LuogoEsteso;

@RestController
@RequestMapping("/itinerari")
public class ControllerItinerario implements FunzioniItinerario{
	
	@Autowired
	private ItinerarioServices service;
	
	/**
	 * Ritorna l'itinerario di viaggio personalizzato all'utente
	 * @param i utente
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

}
