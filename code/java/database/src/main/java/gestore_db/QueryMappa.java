package gestore_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Record12;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import databaseIF.MappaDB;
import jooq_db.jooq.generated.tables.Mappa;
import jooq_db.jooq.generated.tables.records.MappaRecord;

/**
 * Classe contenente tutte le query di mappa
 */
public class QueryMappa implements MappaDB {
	
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
	 * Restituisce solo i nomi delle mappe associate a un utente
	 * @param nomeUtente
	 * @return lista dei nomi delle mappe, se vuota nessuna mappa trovata
	 */
	public List<String> ritornaNomiMappeUtente(String nomeUtente) {
	    List<String> nomiMappe = new ArrayList<>();
	    try {
	        Connection conn = DriverManager.getConnection(DatabaseManager.getIstanza().getUrl());
	        if (conn != null) {
	            DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
	            Result<Record1<String>> result = create
	                .select(Mappa.MAPPA.NOMEMAPPA)
	                .from(Mappa.MAPPA)
	                .where(Mappa.MAPPA.NOMEUTENTE.eq(nomeUtente))
	                .fetch();

	            for (Record1<String> record : result) {
	                nomiMappe.add(record.value1());
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Errore durante il recupero dei nomi delle mappe dal database");
	    }

	    return nomiMappe;
	}

	
	/**
	 * Restituisce tutte le mappe associate a un utente
	 * @param nomeMappa
	 * @param nomeUtente
	 * @return lista di mappe con attributi, se vuota nessuna mappa trovata
	 */
	public List<Map<String, Object>> ritornaMappeUtente(String nomeMappa, String nomeUtente) {
	    List<Map<String, Object>> mappe = new ArrayList<>();
	    try {
	        Connection conn = DriverManager.getConnection(DatabaseManager.getIstanza().getUrl());
	        if (conn != null) {
	            DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
	            Result<Record12<String, String, Integer, String, String, String, String, String, String, String, String, String>> result = create
	                .select(
	                    Mappa.MAPPA.NOMEMAPPA,
	                    Mappa.MAPPA.NOMEUTENTE,
	                    Mappa.MAPPA.GIORNO,
	                    Mappa.MAPPA.LISTANOME,
	                    Mappa.MAPPA.LISTALATITUDINE,
	                    Mappa.MAPPA.LISTALONGITUDINE,
	                    Mappa.MAPPA.LISTACITTA,
	                    Mappa.MAPPA.LISTAINDIRIZZO,
	                    Mappa.MAPPA.LISTATIPO,
	                    Mappa.MAPPA.LISTATEMPOVISITA,
	                    Mappa.MAPPA.LISTAIMMAGINE,
	                    Mappa.MAPPA.LISTAORAARRIVO
	                )
	                .from(Mappa.MAPPA)
	                .where(Mappa.MAPPA.NOMEMAPPA.eq(nomeMappa)
	                		.and(Mappa.MAPPA.NOMEUTENTE.eq(nomeUtente)))
	                .fetch();
	            
	            for (Record12<String, String, Integer, String, String, String, String, String, String, String, String, String> record : result) {
	                Map<String, Object> mappa = new HashMap<>();
	                mappa.put("nomeMappa", record.value1());
	                mappa.put("nomeUtente", record.value2());
	                mappa.put("giorno", record.value3());
	                mappa.put("nomi", record.value4());
	                mappa.put("latitudini", record.value5());
	                mappa.put("longitudini", record.value6());
	                mappa.put("citta", record.value7());
	                mappa.put("indirizzi", record.value8());
	                mappa.put("tipi", record.value9());
	                mappa.put("tempiDiVisita", record.value10());
	                mappa.put("immagini", record.value11());
	                mappa.put("orariDiArrivo", record.value12());
	                mappe.add(mappa);
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Errore durante il recupero delle mappe dal database");
	    }
	    
	    return mappe;
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
