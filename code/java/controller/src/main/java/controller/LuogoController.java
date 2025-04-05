package controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import interfacce.FunzioniLuogo;
import services.LuogoServices;


@RestController
@RequestMapping("/luoghi")
public class LuogoController implements FunzioniLuogo{

	@Autowired
	private LuogoServices service;
	
	/**
	 * SignIn utente
	 * @param u utente
	 * @return utente se registrato, altrimenti error
	 */
	@Override
	@PostMapping("/citta")
	public ResponseEntity<?> getAllCitta() { 
		List<String> citta = service.ritornaCitta();
	    if (citta.size() != 0) {
	        System.out.println("Città ottenute");
	        return ResponseEntity.ok(citta);
	    } else {
	        System.out.println("Città non ottenute");
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(Collections.singletonMap("errore", false));
	    }
	}
	

}
