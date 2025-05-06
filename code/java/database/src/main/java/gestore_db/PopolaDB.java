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
				"https://lh5.googleusercontent.com/p/AF1QipMz7mB9xRRRqfZjhfSZz2P7lEYv7uTOtlNplL7z=w408-h306-k-no");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Via delle Mura Venete", 45.703574695665594, 9.659493483870945, 
				"Bergamo", "Viale delle Mura", "Visitabile", 3600,
				"https://lh5.googleusercontent.com/p/AF1QipMqJdExA2gaLIu315vBOaiTSRsFuGSobggEESX5=w408-h510-k-no");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Parco della Fara", 45.704937131924005, 9.670067450150196,
				"Bergamo", "Viale delle Mura, 46", "Visitabile", 1800,
				"https://lh5.googleusercontent.com/p/AF1QipOyCsvTCLjvnJbRq9AOdS70a8ghqTKvIM992TsV=w408-h724-k-no");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Museo Donizettiano", 45.703487888627826, 9.660738794097727,
				"Bergamo", "Via Arena, 9", "Visitabile", 5400,
				"https://lh5.googleusercontent.com/p/AF1QipNck8Sydjw0zYXSqctnVddbdizEwFrqQKldFbTn=w408-h306-k-no");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Museo Civico di Scienze Naturali", 45.70551457238456, 9.659585488376312,
				"Bergamo", "Piazza della Cittadella, 10", "Visitabile", 10800,
				"https://lh5.googleusercontent.com/p/AF1QipNDhmoG96RLjGRE2c4vbx-ajTU4VwLoltE6e4dF=w428-h240-k-no");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Basilica di Santa Maria Maggiore", 45.70359115967612, 9.662869239959228,
				"Bergamo", "Piazza Duomo, 1", "Visitabile", 1800,
				"https://lh3.googleusercontent.com/p/AF1QipPjmEvn0ks8AfecHPS09AjqHwXveqHLyRLiZbOF=w408-h302-k-no");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Teatro Gaetano Donizetti", 45.69558221810594, 9.670779325685851,
				"Bergamo", "Piazza Camillo Benso Conte di Cavour, 15", "Visitabile", 3600,
				"https://lh3.googleusercontent.com/p/AF1QipMPM85w9FlzxHpXjkdBM0dRvVD0H9aPE-G0QtxJ=w408-h272-k-no");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Porta Nuova", 45.69446136594647, 9.670513419618413,
				"Bergamo", "Largo Porta Nuova", "Visitabile", 900,
				"https://lh3.googleusercontent.com/gps-cs-s/AB5caB-TMqsze284_r0eUOVAEavJrUe5NQ62ClwrK-9j-DDDS04XLM-Vh-STcfh_E5lAWV0YDrUxk8xt1b4-0Asqw5vOlB0Ce7sk_QD0b8vFOGf-eHlKu6P_lz-JR8xdsONP0ic--iu1cg=w408-h306-k-no");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Accademia Carrara", 45.70486623469592, 9.675723629697885,
				"Bergamo", "Piazza Giacomo Carrara, 82", "Visitabile", 7200,
				"https://lh3.googleusercontent.com/p/AF1QipOUYKN0BZEX9E4SxxP1dbbZpLlLVyjoOZvZvGtB=w408-h272-k-no");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Gewiss Stadium", 45.70937374688233, 9.680880886056734,
				"Bergamo", "Viale Giulio Cesare, 18", "Visitabile", 5400,
				"https://lh3.googleusercontent.com/gps-cs-s/AB5caB__I3SQ-0DoVyxor0SStrgOPJa1mU33XqHIeNRsnYdWDqqPM3EIQTcqyeAqDNHgdqf1RR6N8Zbbv6hq2ODy0qTkGo8XAtEM_p31RClDHfHjCeQAFd3fLEnkVQaD30CcPp_-TecB=w426-h240-k-no");
		
		//Luoghi ristoro Bergamo
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("La Marianna", 45.706377641685286, 9.658217176604019,
				"Bergamo", "Largo Colle Aperto, 4", "Ristoro", 2700,
				"https://lh5.googleusercontent.com/p/AF1QipPbocYVM8Y9QBQQctaXIaq73cRA9SR5RzeVbbeO=w408-h724-k-no");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Circolino Citta' Alta", 45.70519839897728, 9.661652423418223,
				"Bergamo", "Vicolo Sant'Agata, 19", "Ristoro", 3600,
				"https://lh5.googleusercontent.com/p/AF1QipNtrBmP9qS9i0mw-UO6k1xPcqi1L70Gs6rffGcC=w408-h272-k-no");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Burger King", 45.70950352529601, 9.681960125726125,
				"Bergamo", "Viale Giulio Cesare, 18", "Ristoro", 1200,
				"https://lh3.googleusercontent.com/gps-cs-s/AB5caB-EPZZYH0nYojbEo5KpRJHWa-AIxIikSVLfoiYTbeikgwj4kdm5f3bJCwR4sQIWPLgiZ5bUaUMbOFtTwHAim7C-e4B_1ItnuIQKagp4K9bbVHvGwbh2jmBL1GB6JVY2Zvjxa8oj=w408-h544-k-no");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Trattoria Da Adriano", 45.695326492445226, 9.666744652435572,
				"Bergamo", "Via Daniele Piccinini, 2", "Ristoro", 2700,
				"https://lh3.googleusercontent.com/gps-cs-s/AB5caB-9w9danoRBjoyRpqtMl8xDf4lk6HA0pGDOZx8RdY_GKYCFQzCZLnuEDkFtTZgyUW95V7WwFPmkAxQIqt5Y473UryMSpxPX4SzXDs00CB6_WTt3dtx-tMKitoUy_XjoYXkC4SQg7g=w408-h408-k-no");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("McDonald's", 45.69151841063481, 9.674482143528639,
				"Bergamo", "Piazzale Guglielmo Marconi, 1", "Ristoro", 1200,
				"https://lh3.googleusercontent.com/p/AF1QipNSSm_AU_2ASUK7B0_PTXK4Gv8Gb0TAVEbbxEvN=w408-h272-k-no");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Fratelli Coppola", 45.68861233875936, 9.662067346309597,
				"Bergamo", "Via Andrea Previtali, 29", "Ristoro", 3600,
				"https://lh3.googleusercontent.com/p/AF1QipPXSsKeQq1ZZsvVzzPRyijZew2iU00FFAFfLFLI=w408-h272-k-no");
		
		//Luoghi visitabili Milano
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Duomo di Milano", 45.46433804396952, 9.191850088198587, 
				"Milano", "Piazza del Duomo", "Visitabile", 7200, 
				"https://lh3.googleusercontent.com/gps-cs-s/AB5caB-T21y3z_FNCicvywCDqpqfum00CvP5P3q5S4JUc-MWhat-oNUcgyi_CmRp16AgcyfswSiFDpt9KIoAQqpSSEzMPxSD4EH2YGtoyD8GjhZDqb6CbxDMaWZEXlsRFceldApIpIXm=w408-h272-k-no");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Galleria Vittorio Emanuele II", 45.4661740413808, 9.190047383201142, 
				"Milano", "Piazza del Duomo, 19-21", "Visitabile", 3600, 
				"https://lh3.googleusercontent.com/gps-cs-s/AB5caB8rx74-37Jry7hl4o8RXvChlQo1B_ugG1wZ9p3meNRItzzjqByiTpZw4kElw2FmuV-cPq5tYQgK2azjgXx1vLJ9tPrd9ampVdoELYkB4jmyWp0o79bJMm50O8Mwd9x7Za7ZOIly=w408-h544-k-no");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Teatro alla Scala", 45.467573984036086, 9.189527960746275, 
				"Milano", "Via Filodrammatici, 2", "Visitabile", 5400, 
				"https://lh3.googleusercontent.com/gps-cs-s/AB5caB9ZRfCx3WP5jScqDMOSj2WES-JFWr2YLNdKs34JmKhTvtvpUKHRyv8yCXt9uBoRm_cgDX_7etU7UcPO1LRcXaGAw6vI7CSOaJvQOATP4EZCseSANLQT1L7Wg-zY_xeL8FBJSd2R=w408-h306-k-no");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Basilica di Sant'Ambrogio", 45.46266001042729, 9.175676107300392, 
				"Milano", "Piazza Sant'Ambrogio, 15", "Visitabile", 3600, 
				"https://lh3.googleusercontent.com/gps-cs-s/AB5caB8_M9UVJ06gXubiV39rh9T8Hcgr7MCw354M6cepBNhYKP8IA0RVal2jr-G0Bh1wUjLly02Ob6dyHplB7W4gYaH7zMG2Y6jl-JsoT3kV1FQyGyIA5eLWJeX7IJ-hZ5ABgg4-ZSJG=w408-h243-k-no");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Castello Sforzesco", 45.47086722606827, 9.179267585174403, 
				"Milano", "Piazza Castello", "Visitabile", 7200, 
				"https://lh3.googleusercontent.com/gps-cs-s/AB5caB_E2dCd4IbDJY_ySz1GZFKR0MwNYRBo6DMo9pJTH_388ve3knA9MQYgGhdxMtAxAQPQKYclNrLNnFaJeneNCntGMIbt7WQozKmX1i2uieV5xfZ3ccpCL5aY0uOeI4Dqx0B_Cr_u=w408-h306-k-no");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Pinacoteca di Brera", 45.47217635738333, 9.187807738667559, 
				"Milano", "Via Brera, 28", "Visitabile", 10800, 
				"https://lh3.googleusercontent.com/gps-cs-s/AB5caB_UjK_Z8urGfMweK9ViU3wMKS-79R0WSajd-DA850LnmoXKB8AwnmGRhJBn0X2xKqWGAuFN7XcERSv2msiZhJwAxmltEOvg4ji9hJsnullxetLoxkQBWJOyUWk1KxORYwVajJ93=w408-h305-k-no");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Museo Nazionale Scienza e Tecnologia Leonardo da Vinci", 45.462981843749915, 9.170682354554879, 
				"Milano", "Via San Vittore, 21", "Visitabile", 14400, 
				"https://lh3.googleusercontent.com/gps-cs-s/AB5caB9H3TVnupfDo01X_KqFQK3ayE70q6kNGRR2lPBD-bSoxNCVr98eflD_VYgGXhkpGZkKmGH3G7CNbGBJaqTJKhuIspG3ZzOGuhmG4HQgpxbsaUrLE4_vZh6SVw_gSv5dmQ8Gi9E=w408-h306-k-no");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("CityLife", 45.47825687493349, 9.155450476142926, 
				"Milano", "Piazza Tre Torri", "Visitabile", 5400, 
				"https://lh3.googleusercontent.com/gps-cs-s/AB5caB920F8haIsqx2KkXb5vQ6cgAiO_W1ucP9OthoRNug27SF4z7BXKOACDQ9woUuLvWvw81Ufnkh3SrkJ-Mcrxkvv0HVVsA2tkU_UPdXaLDyzYSKDwOMWfi913Qez_lpbpQRr48kY=w408-h544-k-no");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Parco Sempione", 45.472414292493575, 9.178109013862665, 
				"Milano", "Parco, Piazza Sempione", "Visitabile", 3600, 
				"https://lh3.googleusercontent.com/gps-cs-s/AB5caB9a82M-iLd93KVJTEDpXmqNgn7avqesWanyS0_ddB-goqJKq5MVaAlFY4_DtRXQ5sXJYQtjsR_6sZ3SboFvvlrV_e2XqonZA5eUhy1nLcxIw-A2wa5fUJs7AukV2YZ-Fx2rTR0e=w408-h544-k-no");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Piazza Gae Aulenti", 45.48379262501218, 9.189453560929175, 
				"Milano", "Piazza Gae Aulenti", "Visitabile", 2700, 
				"https://lh3.googleusercontent.com/gps-cs-s/AB5caB-Ap9kcrB9pgpgxkxzxRNsCcl5nKxTVKaH2uKTPEatwUIyQuIC_JMH2Uzk2RsqiwPcGKk0JxdqVdUUXMUm2Wpxwrhhq0M12KEXde_wW2n_f77py9M_fBFFYz1XoymN74tN4fPZx=w408-h306-k-no");

		//Luoghi ristoro Milano
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("McDonald's", 45.464666308141524, 9.18837531290623,
				"Milano", "Passaggio Duomo, 2", "Ristoro", 5400,
				"https://lh3.googleusercontent.com/p/AF1QipPyZ2YGY7oPxJOCBvYeEcmaKZjseapkTziolH8h=w408-h271-k-no");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Ristorante Cracco", 45.46587132371149, 9.18995859853004,
				"Milano", "Galleria Vittorio Emanuele II", "Ristoro", 5400,
				"https://lh3.googleusercontent.com/p/AF1QipPsepHcPVn4Ucf9I7_eEIBNQDQg4LR0f3YlrR7v=w408-h272-k-no");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Crazy Pizza", 45.47910830427721, 9.18438374159358,
				"Milano", "Via Varese, 1", "Ristoro", 3600,
				"https://lh3.googleusercontent.com/p/AF1QipM_ZppzxCUOj3F9RnaLP2-BPwluSNrL7dNXrLxe=w408-h507-k-no");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Roadhouse", 45.47841876503319, 9.154284819958383,
				"Milano", "Shopping District, Piazza Tre Torri", "Ristoro", 3600,
				"https://lh3.googleusercontent.com/gps-cs-s/AB5caB-xTxrwz53ydBp5DXqyB9nllY8GOsenk_iuhe5P81egwe4IupsC5fo-OednXi5bPWoj1PIavS6-SbWiS1WN8cI47vBYf-0UiC4H6dPwuCimQcf0F9ciXUz5k9HNnIJVyX7dmJw=w408-h306-k-no");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Poke House", 45.468744525407224, 9.185303457176191,
				"Milano", "Via Broletto, 41", "Ristoro", 2700,
				"https://lh3.googleusercontent.com/p/AF1QipOPYILQJHgLSYmPjAwJCBmtZfsxQ0hpLT5jp8Lc=w408-h272-k-no");
		DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Starbucks", 45.48055050450316, 9.186775375229852,
				"Milano", "Corso Garibaldi, 118", "Ristoro", 1800,
				"https://lh3.googleusercontent.com/p/AF1QipOTR-u4-BwFlrXNfNG8JEqQUM3A6QlksbFTA6xY=w426-h240-k-no");

		System.out.println("Dati inseriti");
		
	}
	
	public static void main(String[] args) throws IOException, SQLException {
		DatabaseManager.getIstanza().getPopolaDB().inserisciDati();
	}
	
}
