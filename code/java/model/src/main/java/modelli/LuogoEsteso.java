package modelli;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe modello del luogo, contiene attributi aggiuntivi per API
 */
public class LuogoEsteso extends LuogoRidotto{
	
    private LuogoRidotto luogo;
    private String orarioDiArrivo;

    public LuogoEsteso(@JsonProperty("luogo") LuogoRidotto luogo, @JsonProperty("luogo") String orarioDiArrivo) {
    	super(luogo.getNome(), luogo.getLatitudine(), luogo.getLongitudine(), luogo.getTempoDiVisita());
        this.luogo = luogo;
        this.orarioDiArrivo = orarioDiArrivo;
    }

	public LuogoRidotto getLuogo() { 
    	return luogo; 
    }
    
    public String getOrarioDiArrivo() { 
    	return orarioDiArrivo; 
    }
    
}
