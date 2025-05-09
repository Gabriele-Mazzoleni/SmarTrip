package controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import controllerIF.FunzioniLuogo;
import modelli.Luogo;
import modelli.LuogoConDistanza;
import services.LuogoServices;


@RestController
@RequestMapping("/luoghi")
public class LuogoController implements FunzioniLuogo{

	@Autowired
	private LuogoServices service;
	
	/**
	 * Ritorna la lista di città disponibili
	 * @return citta se presenti, altrimenti errore
	 */
	@Override
	@PostMapping("/citta")
	public ResponseEntity<?> getAllCitta() { 
		List<String> citta = service.ritornaCitta();
	    if (citta.size() != 0) {
	        System.out.println("Citta' ottenute");
	        return ResponseEntity.ok(citta);
	    } else {
	        System.out.println("Citta' non ottenute");
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(Collections.singletonMap("errore", false));
	    }
	}
	
	/**
	 * Ritorna la lista di luoghi data la città
	 * @param citta
	 * @return luoghi se presenti, altrimenti errore
	 */
	@Override
	@PostMapping("/{citta}")
	public ResponseEntity<?> getLuoghiByCitta(@PathVariable String citta) { 
		List<Luogo> luoghi = service.ritornaLuoghiDataCitta(citta);
	    if (luoghi.size() != 0) {
	        System.out.println("Luoghi ottenuti");
	        return ResponseEntity.ok(luoghi);
	    } else {
	        System.out.println("Luoghi non ottenuti");
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(Collections.singletonMap("errore", false));
	    }
	}
	
	/**
	 * Ritorna la lista dei ristoranti più vicini date le coordinate
	 * @param latitudine, longitudine
	 * @return lista dei ristoranti più vicini se presenti, altrimenti errore
	 */
	@Override
	@PostMapping("/{latitudine}/{longitudine}/{n}")
	public ResponseEntity<?> getRistorantiByCoordinate(
	        @PathVariable double latitudine,
	        @PathVariable double longitudine,
	        @PathVariable int n) {
	    
	    List<LuogoConDistanza> luoghi = service.ritornaRistorantiDateCoordinate(longitudine, latitudine, n);
	    
	    if (!luoghi.isEmpty()) {
	    	System.out.println("Ristoranti ottenuti");
	        return ResponseEntity.ok(luoghi);
	    } else {
	        System.out.println("Ristoranti non ottenuti");
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(Collections.singletonMap("errore", false));
	    }
	}

	

}
