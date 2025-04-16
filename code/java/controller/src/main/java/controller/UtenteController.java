package controller;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import controllerIF.FunzioniUtente;
import modelli.Utente;
import services.UtenteServices;


@RestController
@RequestMapping("/utenti")
public class UtenteController implements FunzioniUtente{

	@Autowired
	private UtenteServices service;
	
	/**
	 * SignIn utente
	 * @param u utente
	 * @return utente se registrato, altrimenti error
	 */
	@Override
	@PostMapping("/signin")
	public ResponseEntity<?> signInUtente(@RequestBody Utente u) { 
	    if (service.registraUtente(u.getUsername(), u.getPassword())) {
	        System.out.println("Utente inserito");
	        return ResponseEntity.ok(u);
	    } else {
	        System.out.println("Utente non inserito");
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(Collections.singletonMap("errore", false));
	    }
	}
	
	/**
	 * Controlla password inserita dall'utente
	 * @param u utente
	 * @return true se password corretta, altrimenti errore
	 */
	@Override
	@PostMapping("/login")
	public ResponseEntity<?> logInUtente(@RequestBody Utente u) {
		if (service.accediUtente(u.getUsername(), u.getPassword())) {
	        System.out.println("Password corretta");
	        return ResponseEntity.ok(Collections.singletonMap("esito", true));
	    } else {
	        System.out.println("Password errata o utente non trovato");
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(Collections.singletonMap("esito", false));
	    }
	}

}

