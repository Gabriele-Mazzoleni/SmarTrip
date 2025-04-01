package gestore_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import interfacce.UtenteDB;
import jooq_db.jooq.generated.tables.Utente;
import jooq_db.jooq.generated.tables.records.UtenteRecord;

/**
 * Classe contenente tutte le query di utente
 */
public class QueryUtente implements UtenteDB {
	
	/**
	 * Inserisce nuovo utente
	 * @param username identificativo dell'utente
	 * @param password
	 * @param dbPath
	 * @return 1 se l'inserimento ha avuto successo, 0 altrimenti
	 */
	@Override
	public synchronized int inserisciUtente(String username, String password) {
		int result = 0;
		try {
			Connection conn = DriverManager.getConnection(DatabaseManager.getIstanza().getUrl());
			if (conn != null) {
				DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
				UtenteRecord utente = new UtenteRecord(username, password);
				result = create.insertInto(Utente.UTENTE).set(utente).execute();
			}
		} catch (SQLException e) {
			System.out.println("Errore inserimento utente nel database");
		}
		
		return result;
	}
	
	/**
	 * Elimina utente
	 * @param username
	 * @param password
	 * @return 1 se la rimozione ha avuto successo, 0 altrimenti
	 */
	@Override
	public synchronized int eliminaUtente(String username) {
		int result = 0;
		try {
			Connection conn = DriverManager.getConnection(DatabaseManager.getIstanza().getUrl());
			if (conn != null) {
				DSLContext delete = DSL.using(conn, SQLDialect.SQLITE);
				result = delete.deleteFrom(Utente.UTENTE).where(Utente.UTENTE.USERNAME.eq(username)).execute();
			}
		} catch (SQLException e) {
			System.out.println("Errore eliminazione utente nel database");
		}
		return result;
	}
	
	/**
	 * Permette di cambiare password
	 * @param utente dell'utente a cui fare modifiche
	 * @param newPassword nuovo valore della password, unico attributo modificabile
	 * @return 1 se la modifica ha avuto successo, 0 altrimenti
	 */
	@Override
	public synchronized int cambiaPassword(String utente, String newPassword) {
		int result = 0;
		try {
			Connection conn = DriverManager.getConnection(DatabaseManager.getIstanza().getUrl());
			if (conn != null) {
				DSLContext cambiaPW = DSL.using(conn, SQLDialect.SQLITE);
				result = cambiaPW.update(Utente.UTENTE).set(Utente.UTENTE.PASSWORD, newPassword).
						where(Utente.UTENTE.USERNAME.eq(utente)).execute();
			}
		} catch (SQLException e) {
			System.out.println("Errore aggiornamento password nel database");
		}
		return result;
	}
	


}
