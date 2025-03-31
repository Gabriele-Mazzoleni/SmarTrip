package modelli;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Itinerario {
	
	private double latA;
    private double lonA;
    private int nGiorni;
    private List<GiornoVisita> giorni;
    private List<Luogo> luoghi;
    private double velocitaMedia;
	
	/*
	 * esempio di jason da inviare: 
	 * {
  		"latA": 45.4642,
  		"lonA": 9.1900,
  		"nGiorni": 2,
  		"velocitaMedia": 1.4,
  		"giorni": [
	    		{
	      			"orarioDiInizioVisita": "09:00",
  					"devoPranzare" : true,
  					"orarioPranzo": "12:00",
	      			"pausa": 1800,
	      			"tempoVisita": 3600
	    		},
	    		{
	      			"orarioDiInizioVisita": "18:00",
  					"devoPranzare" : false,
  					"orarioPranzo": "00:00",
	      			"pausa": 1800,
	      			"tempoVisita": 3600
	    		}
	  		],
  		"luoghi": [
	    		{
	      			"nome": "Duomo di Milano",
	      			"latitudine": 45.4641,
	      			"longitudine": 9.1919,
	      			"tempoDiVisita": 2600
	    		},
	    		{
	      			"nome": "Castello Sforzesco",
	      			"latitudine": 45.4672,
	      			"longitudine": 9.1798,
	      			"tempoDiVisita": 1000
	    		}
	  		]
		}
	 */
	
	@JsonCreator
    public Itinerario(
            @JsonProperty("latA") double latA,
            @JsonProperty("lonA") double lonA,
            @JsonProperty("nGiorni") int nGiorni,
            @JsonProperty("velocitaMedia") double velocitaMedia,
            @JsonProperty("giorni") List<GiornoVisita> giorni,
            @JsonProperty("luoghi") List<Luogo> luoghi) {
		this.latA = latA;
        this.lonA = lonA;
        this.nGiorni = nGiorni;
        this.velocitaMedia = velocitaMedia;
        this.giorni = giorni;
        this.luoghi = luoghi;
    }

    // Getters
	
	public GiornoVisita getGiorno(int numeroGiorno) { return giorni.get(numeroGiorno); }
	
    public double getLatA() { return latA; }
    
    public double getLonA() { return lonA; }
    
    public int getNumeroGiorni() { return nGiorni; }
    
    public List<Luogo> getLuoghi() { return luoghi; }
    
    public double getVelocitaMedia() { return velocitaMedia; }
}