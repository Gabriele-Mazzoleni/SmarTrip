package modelli;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe modello dell'itinerario
 */
public class Itinerario {
	
	private String nomeMappa;
	private String utente;
	private double latA;
    private double lonA;
    private int nGiorni;
    private List<GiornoVisita> giorni;
    private List<Luogo> luoghi;
    private double velocitaMedia;
	
	/*
	 * Esempio di jason da inviare: 
	 * {
    "nomeMappa": "MiaMappaBergamo",
    "utente": "Daniele",
    "latA": 45.4642,
  	"lonA": 9.1900,
  	"nGiorni": 2,
  	"velocitaMedia": 1.4,
  	"giorni": [
	    		{
	      			"orarioDiInizioVisita": "11:40",
  					"devoPranzare" : true,
  					"orarioPranzo": "12:00",
  					"tempoPranzo": 522000,
	      			"pausa": 1800,
	      			"tempoVisita": 3600
	    		},
	    		{
	      			"orarioDiInizioVisita": "18:00",
  					"devoPranzare" : false,
  					"orarioPranzo": "00:00",
  					"tempoPranzo": 0,
	      			"pausa": 1800,
	      			"tempoVisita": 3600
	    		}
	  		],
  	"luoghi": [
	    		{
	      			"nome": "Duomo di Milano",
	      			"latitudine": 45.4641,
	      			"longitudine": 9.1919,
	      			"citta": "Milano",
	      			"indirizzo": "Via Duomo",
	      			"tipo": "Visitabile",
	      			"tempoDiVisita": 2600,
	      			"immagine": "abc"
	    		},
	    		{
	      			"nome": "Castello Sforzesco",
	      			"latitudine": 45.4672,
	      			"longitudine": 9.1798,
	      			"citta": "Milano",
	      			"indirizzo": "Via Sforza",
	      			"tipo": "Visitabile",
	      			"tempoDiVisita": 1000,
	      			"immagine": "def"
	    		}
	  		]
		}
	 */
	
	@JsonCreator
    public Itinerario(
    		@JsonProperty("nomeMappa") String nomeMappa,
    		@JsonProperty("utente") String utente,
            @JsonProperty("latA") double latA,
            @JsonProperty("lonA") double lonA,
            @JsonProperty("nGiorni") int nGiorni,
            @JsonProperty("velocitaMedia") double velocitaMedia,
            @JsonProperty("giorni") List<GiornoVisita> giorni,
            @JsonProperty("luoghi") List<Luogo> luoghi) {
		this.nomeMappa = nomeMappa;
		this.utente = utente;
		this.latA = latA;
        this.lonA = lonA;
        this.nGiorni = nGiorni;
        this.velocitaMedia = velocitaMedia;
        this.giorni = giorni;
        this.luoghi = luoghi;
    }
	
	public String getNomeMappa() {
		return nomeMappa;
	}
	
	public String getUtente() {
		return utente;
	}

	public GiornoVisita getGiorno(int numeroGiorno) { 
		return giorni.get(numeroGiorno); 
	}
	
    public double getLatA() { 
    	return latA; 
    }
    
    public double getLonA() { 
    	return lonA; 
    }
    
    public int getNumeroGiorni() { 
    	return nGiorni; 
    }
    
    public List<Luogo> getLuoghi() { 
    	return luoghi; 
    }
    
    public double getVelocitaMedia() { 
    	return velocitaMedia; 
    }
    
}