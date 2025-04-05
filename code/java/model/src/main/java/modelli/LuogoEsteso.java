package modelli;

/**
 * Classe modello del luogo 
 */
public class LuogoEsteso {
    private Luogo luogo;
    private String orarioDiArrivo;

    public LuogoEsteso(Luogo luogo, String orarioDiArrivo) {
        this.luogo = luogo;
        this.orarioDiArrivo = orarioDiArrivo;
    }

    public Luogo getLuogo() { 
    	return luogo; 
    }
    
    public String getOrarioDiArrivo() { 
    	return orarioDiArrivo; 
    }
    
}
