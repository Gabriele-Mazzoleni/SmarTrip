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
		InserimentiJooq.getIstanza().utente("Daniele", "0000");
		InserimentiJooq.getIstanza().utente("Filippo", "1111");
		InserimentiJooq.getIstanza().utente("Gabriele", "1234");
		
		//Inserimento luoghi default
		InserimentiJooq.getIstanza().luogo("Piazza Vecchia Bergamo Alta", 45.704132898433684, 9.663110704162177, 
				"Bergamo", "Piazza Vecchia, 12", "Visitabile", 1800, 
				"https://lh5.googleusercontent.com/p/AF1QipMz7mB9xRRRqfZjhfSZz2P7lEYv7uTOtlNplL7z=w408-h306-k-no");
		InserimentiJooq.getIstanza().luogo("Via delle Mura Venete", 45.703574695665594, 9.659493483870945, 
				"Bergamo", "Viale delle Mura", "Visitabile", 3600,
				"https://lh5.googleusercontent.com/p/AF1QipMqJdExA2gaLIu315vBOaiTSRsFuGSobggEESX5=w408-h510-k-no");
		InserimentiJooq.getIstanza().luogo("Parco della Fara", 45.704937131924005, 9.670067450150196,
				"Bergamo", "Viale delle Mura, 46", "Visitabile", 1200,
				"https://lh5.googleusercontent.com/p/AF1QipOyCsvTCLjvnJbRq9AOdS70a8ghqTKvIM992TsV=w408-h724-k-no");
		InserimentiJooq.getIstanza().luogo("Museo Donizettiano", 45.703487888627826, 9.660738794097727,
				"Bergamo", "Via Arena, 9", "Visitabile", 7200,
				"https://lh5.googleusercontent.com/p/AF1QipNck8Sydjw0zYXSqctnVddbdizEwFrqQKldFbTn=w408-h306-k-no");
		InserimentiJooq.getIstanza().luogo("Museo Civico di Scienze Naturali", 45.70551457238456, 9.659585488376312,
				"Bergamo", "Piazza della Cittadella, 10", "Visitabile", 10800,
				"https://lh5.googleusercontent.com/p/AF1QipNDhmoG96RLjGRE2c4vbx-ajTU4VwLoltE6e4dF=w428-h240-k-no");
		InserimentiJooq.getIstanza().luogo("La Marianna", 45.706377641685286, 9.658217176604019,
				"Bergamo", "Largo Colle Aperto, 4", "Ristoro", 2700,
				"https://lh5.googleusercontent.com/p/AF1QipPbocYVM8Y9QBQQctaXIaq73cRA9SR5RzeVbbeO=w408-h724-k-no");
		InserimentiJooq.getIstanza().luogo("Circolino Città Alta", 45.70519839897728, 9.661652423418223,
				"Bergamo", "Vicolo Sant'Agata, 19", "Ristoro", 3600,
				"https://lh5.googleusercontent.com/p/AF1QipNtrBmP9qS9i0mw-UO6k1xPcqi1L70Gs6rffGcC=w408-h272-k-no");
		
		System.out.println("Dati inseriti");
		
	}
	
	public static void main(String[] args) throws Exception{	
		inserisciDati();
	}
	
}
