package gestore_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import interfacce.MappaDB;
import jooq_db.jooq.generated.tables.Mappa;
import jooq_db.jooq.generated.tables.records.MappaRecord;

public class QueryMappa implements MappaDB{
	
	/**
	 * Inserisce nuova mappa
	 * @param nomeMappa identifica la mappa
	 * @param nomeUtente che possiede la mappa
	 * @param giorno
	 * @param stringaNome è elenco dei nomi dei luoghi 
	 * @param stringaLatitudine è elenco delle latitudini dei luoghi 
	 * @param stringaLongitudine è elenco delle longitudini dei luoghi 
	 * @param stringaCitta è elenco delle città dove si trovano i luoghi
	 * @param stringaIndirizzo è indirizzo dei luoghi
	 * @param stringaTipo indica se il luogo è visitabile o ristoro
	 * @param stringaTempoVisita è elenco dei tempi di visita dei luoghi
	 * @param stringaImmagine è elenco dei link delle immagini 
	 * @param stringaOraArrivo è elenco degli orari di arrivo dei luoghi 
	 * @return 1 se l'inserimento ha avuto successo, 0 altrimenti
	 */
	@Override
	public synchronized int inserisciMappa(String nomeMappa, String nomeUtente, int giorno, String stringaNome, String stringaLatitudine, 
			String stringaLongitudine, String stringaCitta, String stringaIndirizzo, String stringaTipo, String stringaTempoVisita, 
			String stringaImmagine, String stringaOraArrivo) {
		int result = 0;
		try {
			Connection conn = DriverManager.getConnection(DatabaseManager.getIstanza().getUrl());
		    if (conn != null) {
		    	DSLContext create = DSL.using(conn, SQLDialect.SQLITE);    
		        // Controllo se la mappa esiste già per lo stesso nome, utente e giorno
		        int count = create.selectCount()
		                          .from(Mappa.MAPPA)
		                          .where(Mappa.MAPPA.NOMEMAPPA.eq(nomeMappa)
		                          .and(Mappa.MAPPA.NOMEUTENTE.eq(nomeUtente))
		                          .and(Mappa.MAPPA.GIORNO.eq(giorno)))
		                          .fetchOne(0, int.class);    
		         if (count == 0) { // Se la mappa non esiste, procedi con l'inserimento
		        	 MappaRecord mappa = new MappaRecord(nomeMappa, nomeUtente, giorno, stringaNome, 
		                                                    stringaLatitudine, stringaLongitudine, stringaCitta, 
		                                                    stringaIndirizzo, stringaTipo, stringaTempoVisita, 
		                                                    stringaImmagine, stringaOraArrivo);
		             result = create.insertInto(Mappa.MAPPA).set(mappa).execute();
		         }
		     }
		} catch (SQLException e) {
			System.out.println("Errore inserimento mappa nel database");
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
	public synchronized int eliminaMappa(String nomeMappa, String nomeUtente) {
		int result = 0;
		try {
			Connection conn = DriverManager.getConnection(DatabaseManager.getIstanza().getUrl());
			if (conn != null) {
				DSLContext delete = DSL.using(conn, SQLDialect.SQLITE);
				result = delete.deleteFrom(Mappa.MAPPA).where(Mappa.MAPPA.NOMEMAPPA.eq(nomeMappa).
						and(Mappa.MAPPA.NOMEUTENTE.eq(nomeUtente))).execute();
			}
		} catch (SQLException e) {
			System.out.println("Errore eliminazione mappa nel database");
		}
		return result;
	}

}
