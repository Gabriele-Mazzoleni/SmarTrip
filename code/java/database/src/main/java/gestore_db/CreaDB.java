package gestore_db;

import java.io.File;
import java.io.IOException;
import java.sql.*;

/**
 * Classe per la creazione del database e delle tabelle
 */
public class CreaDB {
	
	/**
	 * Genera il database, se non è già presente
	 * @throws IOException
	 * @throws SQLException
	 */
	public void creaDB() throws IOException, SQLException {
		if (new File(DatabaseManager.getIstanza().getPath()).exists()) {
			System.out.println("DB gia' esistente");
		} else {
			DriverManager.getConnection(DatabaseManager.getIstanza().getUrl());
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
		Connection conn = DriverManager.getConnection(DatabaseManager.getIstanza().getUrl());
		if (conn != null) {
			Statement stmt = conn.createStatement();
			String tabellaUtente = "CREATE TABLE UTENTE "
					+ "(USERNAME TEXT, PASSWORD TEXT, PRIMARY KEY(USERNAME))";
			String tabellaLuogo = "CREATE TABLE LUOGO "
					+ "(NOME TEXT, LATITUDINE DOUBLE, LONGITUDINE DOUBLE, CITTA TEXT, INDIRIZZO TEXT, TIPO TEXT, "
					+ "TEMPOVISITA INT, IMMAGINE TEXT, PRIMARY KEY (NOME, LATITUDINE, LONGITUDINE))";
			String tabellaMappa = "CREATE TABLE MAPPA "
					+ "(NOMEMAPPA TEXT, NOMEUTENTE TEXT, GIORNO INT, LISTANOME TEXT, LISTALATITUDINE TEXT, "
					+ "LISTALONGITUDINE TEXT, LISTACITTA TEXT, LISTAINDIRIZZO TEXT, LISTATIPO TEXT, LISTATEMPOVISITA TEXT, "
					+ "LISTAIMMAGINE TEXT, LISTAORAARRIVO TEXT, PRIMARY KEY(NOMEMAPPA, NOMEUTENTE, GIORNO))";
			stmt.executeUpdate(tabellaUtente);
			stmt.executeUpdate(tabellaLuogo);
			stmt.executeUpdate(tabellaMappa);
			stmt.close();
			conn.close();
			System.out.println("Tabelle create");
		}
	}

	
	
}
