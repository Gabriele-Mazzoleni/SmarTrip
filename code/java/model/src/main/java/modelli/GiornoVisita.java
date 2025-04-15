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
    private int tempoPranzo;
    private int pausa;
    private int tempoVisita;

    @JsonCreator
    public GiornoVisita(
            @JsonProperty("orarioDiInizioVisita") String orarioDiInizioVisita,
            @JsonProperty("devoPranzare") boolean devoPranzare,
            @JsonProperty("orarioPranzo") String orarioPranzo,
            @JsonProperty("tempoPranzo") int tempoPranzo,
            @JsonProperty("pausa") int pausa,
            @JsonProperty("tempoVisita") int tempoVisita) {
        this.orarioDiInizioVisita = orarioDiInizioVisita;
        this.devoPranzare = devoPranzare;
        this.orarioPranzo = orarioPranzo;
        this.tempoPranzo = tempoPranzo;
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
    
    public int getTempoPranzo() { 
    	return tempoPranzo; 
    }
    
    public int getPausa() { 
    	return pausa; 
    }
    
    public int getTempoVisita() { 
    	return tempoVisita; 
    }
    
}
