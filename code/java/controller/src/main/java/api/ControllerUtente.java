package api;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import interfacce.FunzioniUtente;
import modelli.Utente;


@RestController
@RequestMapping("/utenti")
public class ControllerUtente implements FunzioniUtente{

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
	    Utente utente = service.registraUtente(u.getUsername(), u.getPassword());
	    
	    if (utente != null) {
	        System.out.println("Utente inserito");
	        return ResponseEntity.ok(utente);
	    } else {
	        System.out.println("Utente non inserito");
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(Collections.singletonMap("errore", "Registrazione fallita"));
	    }
	}
	
	/**
	 * Restituisce utente dato username
	 * @param  username
	 * @return ResponseEntity con l'utente se registrato, altrimenti errore
	 */
	@Override
	@GetMapping("/{username}")
	public ResponseEntity<?> getUtente(@PathVariable String username) {
	    Utente u = service.getUtente(username);
	    if (u != null) {
	        System.out.println("Utente trovato");
	        return ResponseEntity.ok(u);
	    } else {
	        System.out.println("Utente non trovato");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(Collections.singletonMap("errore", "Utente non trovato"));
	    }
	}

	/**
	 * Restituisce gli utenti registrati
	 * @return ResponseEntity con la lista utenti se presenti, altrimenti errore
	 */
	@Override
	@GetMapping
	public ResponseEntity<?> getAllUtenti() {
	    List<Utente> lista = service.getAllUtenti();
	    if (!lista.isEmpty()) {
	        System.out.println("Utenti trovati");
	        return ResponseEntity.ok(lista);
	    } else {
	        System.out.println("Utenti non trovati");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(Collections.singletonMap("errore", "Nessun utente registrato"));
	    }
	}

}

