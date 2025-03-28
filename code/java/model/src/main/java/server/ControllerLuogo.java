package server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import modelli.Luogo;
import modelli.LuogoServices;

@RestController
@RequestMapping("/luoghi")
public class ControllerLuogo {

	@Autowired
	private LuogoServices service;
	
	
	@GetMapping
	public List<Luogo> getAllLuoghi() {
		List<Luogo> lista = service.getAllLuoghi();
		if(lista.size() != 0)
			System.out.println("Luoghi trovati");
		else
			System.out.println("Luoghi non trovati");
	    return service.getAllLuoghi();
	}
	
	@PostMapping("/add")
	public Luogo addLuogo(@RequestBody Luogo l) { 
		Luogo luogo = service.creaLuogo(l.getNome(),l.getLatitudine(),l.getLongitudine(),l.getTempoDiVisita());
		if(luogo != null)
			System.out.println("Luogo inserito");
		else
			System.out.println("Luogo non inserito");
		return luogo;
	}
}
