package server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import modelli.Utente;
import modelli.UtenteServices;

@RestController
@RequestMapping("/utenti")
public class ControllerUtente {

	@Autowired
	private UtenteServices service;
	
	@GetMapping("/utenti/{codice}")
	public Utente getUtente(@PathVariable String codice) {
		return service.getUtente(codice);
	}
	
	@GetMapping
	public List<Utente> getAllUtenti() {
	    return service.getAllUtenti();
	}
	
	@PostMapping("/add")
	public Utente addUtente(@RequestBody Utente u) { 
		return service.creaUtente(u.getCodice(), u.getUsername(), u.getPassword());
	}
}
