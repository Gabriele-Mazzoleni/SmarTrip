package gestore_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import jooq_db.jooq.generated.tables.Luogo;
import jooq_db.jooq.generated.tables.Utente;
import jooq_db.jooq.generated.tables.records.LuogoRecord;
import jooq_db.jooq.generated.tables.records.UtenteRecord;

/**
 * Classe contenente i metodi per gli inserimenti di tuple all'interno dei database utilizzando JOOQ
 * ogni metodo inserisce dati nella tabella omonima
 */
public class InserimentiJooq{
	
	//Pattern singleton
	private static InserimentiJooq istanza = new InserimentiJooq();
	
	private InserimentiJooq() {}
	
	public static InserimentiJooq getIstanza() {
		return istanza;
	}
	
	/**
	 * @param codice identificativo dell'utente
	 * @param username
	 * @param password
	 * @return 1 se l'inserimento ha avuto successo, 0 altrimenti
	 */
	public int utente(String codice, String username, String password) {
		int result = 0;
		try {
			Connection conn = DriverManager.getConnection(CreaDB.DB_URL);
			if (conn != null) {
				DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
				UtenteRecord utente = new UtenteRecord(codice, username, password);
				result = create.insertInto(Utente.UTENTE).set(utente).execute();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * @param nome dell'edificio
	 * @param citta
	 * @param indirizzo
	 * @param latitudine
	 * @param longitudine
	 * @param tipo 
	 * @return 1 se l'inserimento ha avuto successo, 0 altrimenti
	 */
	public int luogo(String nome, double latitudine, double longitudine, String citta, 
			String indirizzo, String tipo) {
		int result = 0;
		try {
			Connection conn = DriverManager.getConnection(CreaDB.DB_URL);
			if (conn != null) {
				DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
				LuogoRecord luogo = new LuogoRecord(nome, latitudine, longitudine, citta, indirizzo, tipo);
				result = create.insertInto(Luogo.LUOGO).set(luogo).execute();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}

}

