package modelli;

public class LuogoConDistanza {
    
	private Luogo luogo;
    private int distanza;

    public LuogoConDistanza(Luogo luogo, int distanza) {
        this.luogo = luogo;
        this.distanza = distanza;
    }

    public Luogo getLuogo() {
        return luogo;
    }

    public double getDistanza() {
        return distanza;
    }
    
}