package modelli;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Itinerario {
	
	private double latA;
    private double lonA;
    private double pausa;
    private double tempoVisita;
    private int nGiorni;
    private String orarioDiInizioVisita;
    private List<GiornoVisita> giorni;
    private List<Luogo> luoghi;
    private double velocitaMedia;
	
	/*
	 * esempio di jason da inviare: 
	 * {
  		"latA": 45.4642,
  		"lonA": 9.1900,
  		"pausa": 1800,
  		"tempoVisita": 3600,
  		"nGiorni": 2,
  		"orarioDiInizioVisita": "08:00",
  		"velocitaMedia": 1.4,
  		"giorni": [
	    		{
	      			"orarioDiInizioVisita": "08:00",
	      			"pausa": 1800,
	      			"tempoVisita": 3600
	    		},
	    		{
	      			"orarioDiInizioVisita": "19:00",
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
            @JsonProperty("pausa") double pausa,
            @JsonProperty("tempoVisita") double tempoVisita,
            @JsonProperty("nGiorni") int nGiorni,
            @JsonProperty("orarioDiInizioVisita") String orarioDiInizioVisita,
            @JsonProperty("velocitaMedia") double velocitaMedia,
            @JsonProperty("giorni") List<GiornoVisita> giorni,
            @JsonProperty("luoghi") List<Luogo> luoghi) {
		this.latA = latA;
        this.lonA = lonA;
        this.pausa = pausa;
        this.tempoVisita = tempoVisita;
        this.nGiorni = nGiorni;
        this.orarioDiInizioVisita = orarioDiInizioVisita;
        this.velocitaMedia = velocitaMedia;
        this.giorni = giorni;
        this.luoghi = luoghi;
    }

    // Getters
	public GiornoVisita getGiorno(int numeroGiorno) { return giorni.get(numeroGiorno); }
	
    public double getLatA() { return latA; }
    
    public String getorarioDiInizioVisita() { return orarioDiInizioVisita; }
    
    public double getLonA() { return lonA; }
    
    public double getPausa() { return pausa; }
    
    public double getTempoVisita() { return tempoVisita; }
    
    public int getGiorni() { return nGiorni; }
    
    public List<Luogo> getLuoghi() { return luoghi; }
    
    public double getVelocitaMedia() { return velocitaMedia; }
}