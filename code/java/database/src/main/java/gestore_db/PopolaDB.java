package gestore_db;

/**
 * Classe utilizzata per popolare con più rapidità il database
 */
public class PopolaDB {
	
	/**
	 * Funzione per l'inserimento di tutti i dati iniziali di default
	 */
	public static void inserisciDati() {
		
		//Inserimento utenti default
		InserimentiJooq.getIstanza().utente("u0", "Daniele", "0000");
		InserimentiJooq.getIstanza().utente("u1", "Filippo", "1111");
		InserimentiJooq.getIstanza().utente("u2", "Gabriele", "1234");
		
		//Inserimento luoghi default
		InserimentiJooq.getIstanza().luogo("Piazza Vecchia Bergamo Alta", 45.704132898433684, 9.663110704162177, 
				"Bergamo", "Piazza Vecchia, 12", "Esterno", 1800);
		InserimentiJooq.getIstanza().luogo("Via delle Mura Venete", 45.703574695665594, 9.659493483870945, 
				"Bergamo", "Viale delle Mura", "Esterno", 3600);
		InserimentiJooq.getIstanza().luogo("Parco della Fara", 45.704937131924005, 9.670067450150196,
				"Bergamo", "Viale delle Mura, 46", "Esterno", 1200);
		InserimentiJooq.getIstanza().luogo("Museo Donizettiano", 45.703487888627826, 9.660738794097727,
				"Bergamo", "Via Arena, 9", "Museo", 7200);
		InserimentiJooq.getIstanza().luogo("Museo Civico di Scienze Naturali", 45.70551457238456, 9.659585488376312,
				"Bergamo", "Piazza della Cittadella, 10", "Museo", 10800);
		InserimentiJooq.getIstanza().luogo("La Marianna", 45.706377641685286, 9.658217176604019,
				"Bergamo", "Largo Colle Aperto, 4", "Ristoro", 2700);
		InserimentiJooq.getIstanza().luogo("Circolino Città Alta", 45.70519839897728, 9.661652423418223,
				"Bergamo", "Vicolo Sant'Agata, 19", "Ristoro", 3600);
		
		System.out.println("Dati inseriti");
		
	}
	
	public static void main(String[] args) throws Exception{	
		inserisciDati();
	}
	
}
