package server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import modelli.Utente;
import modelli.UtenteServices;

@RestController
@RequestMapping("/utenti")
public class ControllerUtente {

	@Autowired
	private UtenteServices service;
	
	@GetMapping("/{codice}")
	public Utente getUtente(@PathVariable String codice) {
		Utente u = service.getUtente(codice);
		if(u != null)
			System.out.println("Utente trovato");
		else
			System.out.println("Utente non trovato");
		return u;
	}
	
	@GetMapping
	public List<Utente> getAllUtenti() {
		List<Utente> lista = service.getAllUtenti();
		if(lista.size() != 0)
			System.out.println("Utenti trovati");
		else
			System.out.println("Utenti non trovati");
	    return service.getAllUtenti();
	}
	
	@PostMapping("/add")
	public Utente addUtente(@RequestBody Utente u) { 
		Utente utente = service.creaUtente(u.getCodice(), u.getUsername(), u.getPassword());
		if(utente != null)
			System.out.println("Utente inserito");
		else
			System.out.println("Utente non inserito");
		return utente;
	}
}
