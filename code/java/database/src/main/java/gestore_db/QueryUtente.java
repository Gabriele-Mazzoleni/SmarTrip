package gestore_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import databaseIF.UtenteDB;
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
	            // Controllo se l'utente esiste già
	            int count = create.selectCount()
	                              .from(Utente.UTENTE)
	                              .where(Utente.UTENTE.USERNAME.eq(username))
	                              .fetchOne(0, int.class);       
	            if (count == 0) { // Se l'utente non esiste, procedi con l'inserimento
	                UtenteRecord utente = new UtenteRecord(username, password);
	                result = create.insertInto(Utente.UTENTE).set(utente).execute();
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Errore inserimento utente nel database");
	    }
	    
	    return result;
	}
	
	/**
	 * Controlla se la password inserita è corretta
	 * @param username 
	 * @param password valore da controllare
	 * @return 1 se corretta, 0 altrimenti
	 */
	@Override
	public int controllaPassword(String username, String password) {
		int result = 0;
	    try (Connection conn = DriverManager.getConnection(DatabaseManager.getIstanza().getUrl())) {
	        if (conn != null) {
	            DSLContext db = DSL.using(conn, SQLDialect.SQLITE);
	            String storedPassword = db.select(Utente.UTENTE.PASSWORD)
	                                      .from(Utente.UTENTE)
	                                      .where(Utente.UTENTE.USERNAME.eq(username))
	                                      .fetchOneInto(String.class);
	            if (storedPassword != null && storedPassword.equals(password)) {
	                result = 1;
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Errore controllo password nel database");
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
	
}
