package gestore_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import interfacce.LuogoDB;
import jooq_db.jooq.generated.tables.Luogo;
import jooq_db.jooq.generated.tables.records.LuogoRecord;

/**
 * Classe contenente tutte le query di luogo
 */
public class QueryLuogo implements LuogoDB{
	
	/**
	 * Inserisce nuovo luogo
	 * @param nome del luogo
	 * @param latitudine
	 * @param longitudine
	 * @param citta
	 * @param indirizzo
	 * @param tipo 
	 * @param tempoVisita media tempo di visita in secondi
	 * @param immagine url dell'immagine del luogo
	 * @return 1 se l'inserimento ha avuto successo, 0 altrimenti
	 */
	@Override
	public synchronized int inserisciLuogo(String nome, double latitudine, double longitudine, String citta, 
			String indirizzo, String tipo, int tempoVisita, String immagine) {
		int result = 0;
		try {
			Connection conn = DriverManager.getConnection(DatabaseManager.getIstanza().getUrl());
	        if (conn != null) {
	            DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
	            // Controllo se il luogo esiste già per lo stesso nome, latitudine e longitudine
	            int count = create.selectCount()
	                              .from(Luogo.LUOGO)
	                              .where(Luogo.LUOGO.NOME.eq(nome)
	                              .and(Luogo.LUOGO.LATITUDINE.eq(latitudine))
	                              .and(Luogo.LUOGO.LONGITUDINE.eq(longitudine)))
	                              .fetchOne(0, int.class);
	            if (count == 0) { // Se il luogo non esiste, procedi con l'inserimento
	                LuogoRecord luogo = new LuogoRecord(nome, latitudine, longitudine, citta, indirizzo, tipo, tempoVisita, immagine);
	                result = create.insertInto(Luogo.LUOGO).set(luogo).execute();
	            }
	        }
		} catch (SQLException e) {
			System.out.println("Errore inserimento luogo nel database");
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
	public synchronized int eliminaLuogo(String nome, double latitudine, double longitudine) {
		int result = 0;
		try {
			Connection conn = DriverManager.getConnection(DatabaseManager.getIstanza().getUrl());
			if (conn != null) {
				DSLContext delete = DSL.using(conn, SQLDialect.SQLITE);
				result = delete.deleteFrom(Luogo.LUOGO).where(Luogo.LUOGO.NOME.eq(nome).
						and(Luogo.LUOGO.LATITUDINE.eq(latitudine)).
						and(Luogo.LUOGO.LONGITUDINE.eq(longitudine))).execute();
			}
		} catch (SQLException e) {
			System.out.println("Errore eliminazione luogo nel database");
		}
		return result;
	}

}
