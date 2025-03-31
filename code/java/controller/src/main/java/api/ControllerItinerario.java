package api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import modelli.Itinerario;
import modelli.Luogo;
import modelli.LuogoEsteso;

@RestController
@RequestMapping("/itinerari")
public class ControllerItinerario {
	
	@Autowired
	private ItinerarioServices service;
	
	@PostMapping("/add")
	public Map<Integer, List<LuogoEsteso>> addItinerario(@RequestBody Itinerario i) { 
		Map<Integer, List<LuogoEsteso>> mappa = service.creaTabelleDiMarcia(i);
		if(mappa != null)
			System.out.println("Itinerario inserito");
		else
			System.out.println("Itinerario non inserito");
		return mappa;
	}
	
	@GetMapping
	public Map<Integer, List<LuogoEsteso>> getAllItinarari() {
		Map<Integer, List<LuogoEsteso>> mappa = service.getAllItinerari();
		if(mappa.size() != 0)
			System.out.println("Itinerari trovati");
		else
			System.out.println("Itinierari non trovati");
	    return service.getAllItinerari();
	}
}
