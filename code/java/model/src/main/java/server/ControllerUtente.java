package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import modelli.Utente;
import modelli.UtenteServices;

@RestController
public class ControllerUtente {

	@Autowired
	private UtenteServices service;
	
	@GetMapping("/utenti/{codice}")
	public Utente getUtente(@PathVariable String codice) {
		return service.getUtente(codice);
	}
	
	@PostMapping
	public Utente addUtente(@RequestParam(defaultValue = "") 
			String codice, String username, String password) {
		return service.creaUtente(codice, username, password);
	}
}
