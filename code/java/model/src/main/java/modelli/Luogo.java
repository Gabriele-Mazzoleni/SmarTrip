package modelli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe modello del luogo, con tutti gli attributi nel database 
 */
public class Luogo {
	
	private String nome;
    private double latitudine;
    private double longitudine;
	private String citta;
    private String indirizzo;
    private String tipo;
    private int tempoDiVisita;
    private String immagine;
    
    @JsonCreator
	public Luogo(@JsonProperty("nome") String n, 
			@JsonProperty("latitudine") double la, 
			@JsonProperty("longitudine") double lo,
			@JsonProperty("citta") String c,
			@JsonProperty("indirizzo") String i,
			@JsonProperty("tipo") String t,
			@JsonProperty("tempoDiVisita") int tv,
			@JsonProperty("immagine") String img) {
    	this.nome = n;
    	this.latitudine = la;
    	this.longitudine = lo;
    	this.citta = c;
    	this.indirizzo = i;
    	this.tipo = t;
    	this.tempoDiVisita = tv;
    	this.immagine = img;
	}

	public String getCitta() {
		return citta;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public String getTipo() {
		return tipo;
	}

	public String getImmagine() {
		return immagine;
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

    public int getTempoDiVisita() {
        return tempoDiVisita;
    }
    
}

