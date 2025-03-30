package gestore_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import interfacce.Aggiornamenti;
import jooq_db.jooq.generated.tables.Utente;

/**
 * Classe contenente i metodi per le modifiche dei dati nelle tuple all'interno dei database utilizzando JOOQ
 * ogni metodo apporta modifiche alla tabella omonima
 * non tutte le tabelle ammettono modifiche aaaaa
 */
public class AggiornamentiJooq implements Aggiornamenti{
	
	//Pattern singleton
	private static AggiornamentiJooq istanza;
	
	private AggiornamentiJooq() {};
	
	public static synchronized AggiornamentiJooq getIstanza() {
		if (istanza == null) {
            istanza = new AggiornamentiJooq();
        }
		return istanza;
	}

	/**
	 * Permette di cambiare password
	 * @param utente dell'utente a cui fare modifiche
	 * @param newPassword nuovo valore della password, unico attributo modificabile
	 * @return 1 se la modifica ha avuto successo, 0 altrimenti
	 */
	@Override
	public int cambiaPassword(String utente, String newPassword) {
		int result = 0;
		try {
			Connection conn = DriverManager.getConnection(CreaDB.DB_URL);
			if (conn != null) {
				DSLContext cambiaPW = DSL.using(conn, SQLDialect.SQLITE);
				result = cambiaPW.update(Utente.UTENTE).set(Utente.UTENTE.PASSWORD, newPassword).
						where(Utente.UTENTE.USERNAME.eq(utente)).execute();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

}
