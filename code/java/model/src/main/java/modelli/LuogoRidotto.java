package modelli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe modello del luogo, contiene solo gli attributi necessari all'API
 */
public class LuogoRidotto {

	private String nome;
    private double latitudine;
    private double longitudine;
    private double tempoDiVisita;
    
    @JsonCreator
	public LuogoRidotto(@JsonProperty("nome") String n, 
			@JsonProperty("latitudine") double la, 
			@JsonProperty("longitudine") double lo, 
			@JsonProperty("tempoDiVisita") double t) {
    	this.setNome(n);
    	this.setLatitudine(la);
    	this.setLongitudine(lo);
    	this.setTempoDiVisita(t);
	}
    
    public void setNome(String n) {
    	this.nome = n;
    }
    
    public void setLatitudine(double lat) {
    	this.latitudine = lat;
    }
    
    public void setLongitudine(double lon) {
    	this.longitudine = lon;
    }
    
    public void setTempoDiVisita(double tv) {
    	this.tempoDiVisita = tv;
    }
    
    public String getNome() {
        return nome;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public double getTempoDiVisita() {
        return tempoDiVisita;
    }
    
}
