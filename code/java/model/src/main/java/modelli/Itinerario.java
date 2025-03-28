package modelli;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Itinerario {
	
	private double latA;
    private double lonA;
    private double pausa;
    private double tempoVisita;
    private int giorni;
    private List<Luogo> luoghi;
    private double velocitaMedia;
	
	/*
	 * esempio di jason da inviare: 
	 * {
  		"latA": 45.4642,
  		"lonA": 9.1900,
  		"pausa": 1.5,
  		"tempoVisita": 2.0,
  		"giorni": 3,
  		"velocitaMedia": 1,
  		"luoghi": [
	    		{
	      			"nome": "Duomo di Milano",
	      			"latitudine": 45.4641,
	      			"longitudine": 9.1919,
	      			"tempoDiVisita": 2.5
	    		},
	    		{
	      			"nome": "Castello Sforzesco",
	      			"latitudine": 45.4672,
	      			"longitudine": 9.1798,
	      			"tempoDiVisita": 1.5
	    		}
	  		]
		}
	 */
	
	@JsonCreator
    public Itinerario(
            @JsonProperty("latA") double latA,
            @JsonProperty("lonA") double lonA,
            @JsonProperty("pausa") double pausa,
            @JsonProperty("tempoVisita") double tempoVisita,
            @JsonProperty("giorni") int giorni,
            @JsonProperty("velocitaMedia") double velocitaMedia,
            @JsonProperty("luoghi") List<Luogo> luoghi) {
		this.latA = latA;
        this.lonA = lonA;
        this.pausa = pausa;
        this.tempoVisita = tempoVisita;
        this.giorni = giorni;
        this.velocitaMedia = velocitaMedia;
        this.luoghi = luoghi;
    }

    // Getters
    public double getLatA() { return latA; }
    
    public double getLonA() { return lonA; }
    
    public double getPausa() { return pausa; }
    
    public double getTempoVisita() { return tempoVisita; }
    
    public int getGiorni() { return giorni; }
    
    public List<Luogo> getLuoghi() { return luoghi; }
    
    public double getVelocitaMedia() { return velocitaMedia; }
}
