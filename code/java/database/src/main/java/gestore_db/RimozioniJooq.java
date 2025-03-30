package gestore_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import interfacce.Rimozioni;
import jooq_db.jooq.generated.tables.Luogo;
import jooq_db.jooq.generated.tables.Mappa;
import jooq_db.jooq.generated.tables.Utente;

/**
 * Classe contenente i metodi per gli inserimenti di tuple all'interno dei database utilizzando JOOQ
 * ogni metodo rimuove dati dalla tabella omonima
 */
public class RimozioniJooq implements Rimozioni {
	
	//Pattern singleton
	private static RimozioniJooq istanza;
	
	private RimozioniJooq() {}
	
	public static synchronized RimozioniJooq getIstanza() {
		if (istanza == null) {
            istanza = new RimozioniJooq();
        }
		return istanza;
	}
	
	/**
	 * Elimina utente
	 * @param username
	 * @param password
	 * @return 1 se la rimozione ha avuto successo, 0 altrimenti
	 */
	@Override
	public int utente(String username) {
		int result = 0;
		try {
			Connection conn = DriverManager.getConnection(CreaDB.DB_URL);
			if (conn != null) {
				DSLContext delete = DSL.using(conn, SQLDialect.SQLITE);
				result = delete.deleteFrom(Utente.UTENTE).where(Utente.UTENTE.USERNAME.eq(username)).execute();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	/**
	 * Elimina luogo
	 * @param nome del luogo
	 * @param latitudine
	 * @param longitudine
	 * @return 1 se la rimozione ha avuto successo, 0 altrimenti
	 */
	@Override
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

	/**
	 * Elimina mappa
	 * @param nomeMappa identifica la mappa
	 * @param nomeUtente che possiede la mappa
	 * @return 1 se la rimozione ha avuto successo, 0 altrimenti
	 */
	@Override
	public int mappa(String nomeMappa, String nomeUtente) {
		int result = 0;
		try {
			Connection conn = DriverManager.getConnection(CreaDB.DB_URL);
			if (conn != null) {
				DSLContext delete = DSL.using(conn, SQLDialect.SQLITE);
				result = delete.deleteFrom(Mappa.MAPPA).where(Mappa.MAPPA.NOMEMAPPA.eq(nomeMappa).
						and(Mappa.MAPPA.NOMEUTENTE.eq(nomeUtente))).execute();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
}

