package modelli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe modello del giorno di visita
 */
public class GiornoVisita {
    private String orarioDiInizioVisita;
    private boolean devoPranzare;
    private String orarioPranzo;
    private double pausa;
    private double tempoVisita;

    @JsonCreator
    public GiornoVisita(
            @JsonProperty("orarioDiInizioVisita") String orarioDiInizioVisita,
            @JsonProperty("devoPranzare") boolean devoPranzare,
            @JsonProperty("orarioPranzo") String orarioPranzo,
            @JsonProperty("pausa") double pausa,
            @JsonProperty("tempoVisita") double tempoVisita) {
        this.orarioDiInizioVisita = orarioDiInizioVisita;
        this.devoPranzare = devoPranzare;
        this.orarioPranzo = orarioPranzo;
        this.pausa = pausa;
        this.tempoVisita = tempoVisita;
    }
    
	public boolean getDevoPranzare() { 
		return devoPranzare; 
	}
	
	public String getOrarioPranzo() { 
		return orarioPranzo; 
	}
	
    public String getOrarioDiInizioVisita() { 
    	return orarioDiInizioVisita; 
    }
    
    public double getPausa() { 
    	return pausa; 
    }
    
    public double getTempoVisita() { 
    	return tempoVisita; 
    }
    
}
