package modelli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe modello del luogo, con tutti gli attributi nel database 
 */
public class Luogo extends LuogoRidotto{
	
    private String citta;
    private String indirizzo;
    private String tipo;
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
    	super(n, la, lo, tv);
    	this.citta = c;
    	this.indirizzo = i;
    	this.tipo = t;
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
    
}

