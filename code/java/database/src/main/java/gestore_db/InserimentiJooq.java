package gestore_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import interfacce.Inserimenti;
import jooq_db.jooq.generated.tables.Luogo;
import jooq_db.jooq.generated.tables.Mappa;
import jooq_db.jooq.generated.tables.Utente;
import jooq_db.jooq.generated.tables.records.LuogoRecord;
import jooq_db.jooq.generated.tables.records.MappaRecord;
import jooq_db.jooq.generated.tables.records.UtenteRecord;

/**
 * Classe contenente i metodi per gli inserimenti di tuple all'interno dei database utilizzando JOOQ
 * ogni metodo inserisce dati nella tabella omonima
 */
public class InserimentiJooq implements Inserimenti{
	
	//Pattern singleton
	private static InserimentiJooq istanza;
	
	private InserimentiJooq() {}
	
	public static synchronized InserimentiJooq getIstanza() {
		if (istanza == null) {
            istanza = new InserimentiJooq();
        }
		return istanza;
	}
	
	/**
	 * Inserisce nuovo utente
	 * @param username identificativo dell'utente
	 * @param password
	 * @return 1 se l'inserimento ha avuto successo, 0 altrimenti
	 */
	@Override
	public int utente(String username, String password) {
		int result = 0;
		try {
			Connection conn = DriverManager.getConnection(CreaDB.DB_URL);
			if (conn != null) {
				DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
				UtenteRecord utente = new UtenteRecord(username, password);
				result = create.insertInto(Utente.UTENTE).set(utente).execute();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
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
	public int luogo(String nome, double latitudine, double longitudine, String citta, 
			String indirizzo, String tipo, int tempoVisita, String immagine) {
		int result = 0;
		try {
			Connection conn = DriverManager.getConnection(CreaDB.DB_URL);
			if (conn != null) {
				DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
				LuogoRecord luogo = new LuogoRecord(nome, latitudine, longitudine, citta, indirizzo, tipo, tempoVisita, immagine);
				result = create.insertInto(Luogo.LUOGO).set(luogo).execute();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * Inserisce nuova mappa
	 * @param nomeMappa identifica la mappa
	 * @param nomeUtente che possiede la mappa
	 * @param giorno
	 * @param stringaNome è elenco dei nomi dei luoghi 
	 * @param stringaLatitudine è elenco delle latitudini dei luoghi 
	 * @param stringaLongitudine è elenco delle longitudini dei luoghi 
	 * @param stringaTempoVisita è elenco dei tempi di visita dei luoghi 
	 * @param stringaOraArrivo è elenco degli orari di arrivo dei luoghi 
	 * @return 1 se l'inserimento ha avuto successo, 0 altrimenti
	 */
	@Override
	public int mappa(String nomeMappa, String nomeUtente, int giorno, String stringaNome, String stringaLatitudine, 
			String stringaLongitudine, String stringaTempoVisita, String stringaOraArrivo) {
		int result = 0;
		try {
			Connection conn = DriverManager.getConnection(CreaDB.DB_URL);
			if (conn != null) {
				DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
				MappaRecord mappa = new MappaRecord(nomeMappa, nomeUtente, giorno, stringaNome, stringaLatitudine,
						stringaLongitudine, stringaTempoVisita, stringaOraArrivo);
				result = create.insertInto(Mappa.MAPPA).set(mappa).execute();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}

}

