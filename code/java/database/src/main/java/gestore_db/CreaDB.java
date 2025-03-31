package gestore_db;

import java.io.File;
import java.io.IOException;
import java.sql.*;

/**
 * Classe per la creazione del database e delle tabelle
 */
public class CreaDB {
	
	//Pattern singleton
	private static CreaDB istanza = new CreaDB();
	public static String DB_PATH = "../database/db/db.db3";
	public static String DB_URL = "jdbc:sqlite:" + DB_PATH;
	
	private CreaDB() {}
	
	public synchronized static CreaDB getIstanza() {
		return istanza;
	}

	/**
	 * Genera il database, se non è già presente
	 * @throws IOException
	 * @throws SQLException
	 */
	public void creaDB() throws IOException, SQLException {
		if (new File(DB_PATH).exists()) {
			System.out.println("DB gia' esistente");
		} else {
			DriverManager.getConnection(DB_URL);
			creaTabella();
			System.out.println("DB creato");

		}
	}

	/**
	 * Crea le tabelle nel database
	 * @throws IOException
	 * @throws SQLException
	 */
	public void creaTabella() throws IOException, SQLException {
		Connection conn = DriverManager.getConnection(DB_URL);
		if (conn != null) {
			Statement stmt = conn.createStatement();
			String tabellaUtente = "CREATE TABLE UTENTE "
					+ "(USERNAME TEXT, PASSWORD TEXT, PRIMARY KEY(USERNAME))";
			String tabellaLuogo = "CREATE TABLE LUOGO "
					+ "(NOME TEXT, LATITUDINE DOUBLE, LONGITUDINE DOUBLE, CITTA TEXT, INDIRIZZO TEXT, TIPO TEXT, "
					+ "TEMPOVISITA INT, IMMAGINE STRING, PRIMARY KEY (NOME, LATITUDINE, LONGITUDINE))";
			String tabellaMappa = "CREATE TABLE MAPPA "
					+ "(NOMEMAPPA TEXT, NOMEUTENTE TEXT, GIORNO INT, LISTANOME TEXT, LISTALATITUDINE TEXT, "
					+ "LISTALONGITUDINE TEXT, LISTATEMPOVISITA TEXT, LISTAORAARRIVO TEXT, PRIMARY KEY(NOMEMAPPA, NOMEUTENTE, GIORNO))";
			stmt.executeUpdate(tabellaUtente);
			stmt.executeUpdate(tabellaLuogo);
			stmt.executeUpdate(tabellaMappa);
			stmt.close();
			conn.close();
			System.out.println("Tabelle create");
		}
	}

	public static void main(String[] args) throws IOException, SQLException {
		getIstanza().creaDB();
	}
	
}
