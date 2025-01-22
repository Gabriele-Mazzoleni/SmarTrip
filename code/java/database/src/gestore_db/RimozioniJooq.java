package gestore_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import jooq_db.jooq.generated.tables.Luogo;
import jooq_db.jooq.generated.tables.Utente;

/**
 * Classe contenente i metodi per gli inserimenti di tuple all'interno dei database utilizzando JOOQ
 * ogni metodo rimuove dati dalla tabella omonima
 */
public class RimozioniJooq {
	
	//Pattern singleton
	private static RimozioniJooq istanza = new RimozioniJooq();
	
	private RimozioniJooq() {}
	
	public static RimozioniJooq getIstanza() {
		return istanza;
	}
	
	/**
	 * @param codice identificativo dell'utente
	 * @param username
	 * @param password
	 * @return 1 se la rimozione ha avuto successo, 0 altrimenti
	 */
	public int utente(String codice) {
		int result = 0;
		try {
			Connection conn = DriverManager.getConnection(CreaDB.DB_URL);
			if (conn != null) {
				DSLContext delete = DSL.using(conn, SQLDialect.SQLITE);
				result = delete.deleteFrom(Utente.UTENTE).where(Utente.UTENTE.CODICE.eq(codice)).execute();
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
	 * @return 1 se la rimozione ha avuto successo, 0 altrimenti
	 */
	public int luogo(String nome, double latitudine, double longitudine) {
		int result = 0;
		try {
			Connection conn = DriverManager.getConnection(CreaDB.DB_URL);
			if (conn != null) {
				DSLContext delete = DSL.using(conn, SQLDialect.SQLITE);
				result = delete.deleteFrom(Luogo.LUOGO).where(Luogo.LUOGO.NOME.eq(nome).
						and(Luogo.LUOGO.LATITUDINE.eq(latitudine)).
						and(Luogo.LUOGO.LONGITUDINE.eq(longitudine))).execute();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
}

