package inizializzazione;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import modelli.UtenteServices;

@Component
public class ProvaServer {
	
	@Autowired
	 private UtenteServices service;
	 
	 public void run(String[] args) {
	    service.creaUtente("a", "Daniele", "1234");
	 }
}
