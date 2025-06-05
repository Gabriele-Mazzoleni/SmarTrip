package gestore_db;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Classe utilizzata per popolare con più rapidità il database
 */
public class PopolaDB {
	
	/**
	 * Funzione per l'inserimento di tutti i dati iniziali di default
	 */
	public void inserisciDati() {
		
		//Inserimento utenti default
		DatabaseManager.getIstanza().getQueryUtente().inserisciUtente("Daniele", "0000");
		
		//Luoghi visitabili Bergamo
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Piazza Vecchia Bergamo Alta", 45.704132898433684, 9.663110704162177, 
				"Bergamo", "Piazza Vecchia, 12", "Visitabile", 1800, 
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145122/piazza_vecchia_hpbsb6.jpg");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Via delle Mura Venete", 45.703574695665594, 9.659493483870945, 
				"Bergamo", "Viale delle Mura", "Visitabile", 3600,
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145117/mura_ybdlzf.jpg");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Parco della Fara", 45.704937131924005, 9.670067450150196,
				"Bergamo", "Viale delle Mura, 46", "Visitabile", 1800,
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145110/fara_ww3uam.jpg");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Museo Donizettiano", 45.703487888627826, 9.660738794097727,
				"Bergamo", "Via Arena, 9", "Visitabile", 5400,
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145118/museo_donizetti_suwkbp.jpg");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Museo Civico di Scienze Naturali", 45.70551457238456, 9.659585488376312,
				"Bergamo", "Piazza della Cittadella, 10", "Visitabile", 10800,
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145120/museo_scienze_bergamo_mki4fs.jpg");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Basilica di Santa Maria Maggiore", 45.70359115967612, 9.662869239959228,
				"Bergamo", "Piazza Duomo, 1", "Visitabile", 1800,
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145105/basilica_bergamo_te1jhy.jpg");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Teatro Gaetano Donizetti", 45.69558221810594, 9.670779325685851,
				"Bergamo", "Piazza Camillo Benso Conte di Cavour, 15", "Visitabile", 3600,
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145131/teatro_donizetti_urexzw.jpg");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Porta Nuova", 45.69446136594647, 9.670513419618413,
				"Bergamo", "Largo Porta Nuova", "Visitabile", 900,
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145125/porta_nuova_hxfhfy.jpg");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Accademia Carrara", 45.70486623469592, 9.675723629697885,
				"Bergamo", "Piazza Giacomo Carrara, 82", "Visitabile", 7200,
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145105/accademia_carrara_thgwhm.jpg");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Gewiss Stadium", 45.70937374688233, 9.680880886056734,
				"Bergamo", "Viale Giulio Cesare, 18", "Visitabile", 5400,
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145114/gewiss_bstels.jpg");
		
		//Luoghi ristoro Bergamo
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("La Marianna", 45.706377641685286, 9.658217176604019,
				"Bergamo", "Largo Colle Aperto, 4", "Ristoro", 2700,
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145115/marianna_oupfns.jpg");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Circolino Citta' Alta", 45.70519839897728, 9.661652423418223,
				"Bergamo", "Vicolo Sant'Agata, 19", "Ristoro", 3600,
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145106/circolino_m15ear.jpg");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Burger King", 45.70950352529601, 9.681960125726125,
				"Bergamo", "Viale Giulio Cesare, 18", "Ristoro", 1200,
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145105/burgher_bergamo_vrutde.jpg");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Trattoria Da Adriano", 45.695326492445226, 9.666744652435572,
				"Bergamo", "Via Daniele Piccinini, 2", "Ristoro", 2700,
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145109/da_adriano_kws7gt.jpg");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("McDonald's", 45.69151841063481, 9.674482143528639,
				"Bergamo", "Piazzale Guglielmo Marconi, 1", "Ristoro", 1200,
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145115/mc_bergamo_kgvq5e.jpg");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Fratelli Coppola", 45.68861233875936, 9.662067346309597,
				"Bergamo", "Via Andrea Previtali, 29", "Ristoro", 3600,
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145111/fratelli_coppola_bnvvot.jpg");
		
		//Luoghi visitabili Milano
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Duomo di Milano", 45.46433804396952, 9.191850088198587, 
				"Milano", "Piazza del Duomo", "Visitabile", 7200, 
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145110/duomo_deiky7.jpg");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Galleria Vittorio Emanuele II", 45.4661740413808, 9.190047383201142, 
				"Milano", "Piazza del Duomo, 19-21", "Visitabile", 3600, 
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145113/galleria_m2vcvj.jpg");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Teatro alla Scala", 45.467573984036086, 9.189527960746275, 
				"Milano", "Via Filodrammatici, 2", "Visitabile", 5400, 
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145132/teatro_scala_f8jvn2.jpg");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Basilica di Sant'Ambrogio", 45.46266001042729, 9.175676107300392, 
				"Milano", "Piazza Sant'Ambrogio, 15", "Visitabile", 3600, 
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145128/sant_ambrogio_kkmngm.jpg");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Castello Sforzesco", 45.47086722606827, 9.179267585174403, 
				"Milano", "Piazza Castello", "Visitabile", 7200, 
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145106/castello_sforzesco_rsotfa.jpg");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Pinacoteca di Brera", 45.47217635738333, 9.187807738667559, 
				"Milano", "Via Brera, 28", "Visitabile", 10800, 
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145123/pinacoteca_brera_tkr886.jpg");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Museo Nazionale Scienza e Tecnologia Leonardo da Vinci", 45.462981843749915, 9.170682354554879, 
				"Milano", "Via San Vittore, 21", "Visitabile", 14400, 
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145119/museo_leonardo_b0uee9.jpg");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("CityLife", 45.47825687493349, 9.155450476142926, 
				"Milano", "Piazza Tre Torri", "Visitabile", 5400, 
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145107/citylife_fuyooi.jpg");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Parco Sempione", 45.472414292493575, 9.178109013862665, 
				"Milano", "Parco, Piazza Sempione", "Visitabile", 3600, 
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145122/parco_sempione_ydvz3n.jpg");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Piazza Gae Aulenti", 45.48379262501218, 9.189453560929175, 
				"Milano", "Piazza Gae Aulenti", "Visitabile", 2700, 
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145112/gae_aulenti_c5phau.jpg");

		//Luoghi ristoro Milano
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("McDonald's", 45.464666308141524, 9.18837531290623,
				"Milano", "Passaggio Duomo, 2", "Ristoro", 5400,
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145117/mc_duomo_rwydpg.jpg");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Ristorante Cracco", 45.46587132371149, 9.18995859853004,
				"Milano", "Galleria Vittorio Emanuele II", "Ristoro", 5400,
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145108/cracco_kknfrt.jpg");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Crazy Pizza", 45.47910830427721, 9.18438374159358,
				"Milano", "Via Varese, 1", "Ristoro", 3600,
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145108/crazy_pizza_llyo97.jpg");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Roadhouse", 45.47841876503319, 9.154284819958383,
				"Milano", "Shopping District, Piazza Tre Torri", "Ristoro", 3600,
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145127/roadhouse_m0bva8.jpg");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Poke House", 45.468744525407224, 9.185303457176191,
				"Milano", "Via Broletto, 41", "Ristoro", 2700,
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145124/pokehouse_atqfog.jpg");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Starbucks", 45.48055050450316, 9.186775375229852,
				"Milano", "Corso Garibaldi, 118", "Ristoro", 1800,
				"https://res.cloudinary.com/dp8gi1i2e/image/upload/v1749145129/sturbucks_nrvq4i.jpg");

		System.out.println("Dati inseriti");
		
	}
	
	public static void main(String[] args) throws IOException, SQLException {
		DatabaseManager.getIstanza().getPopolaDB().inserisciDati();
	}
	
}
