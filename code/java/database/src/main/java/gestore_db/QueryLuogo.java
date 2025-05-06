package gestore_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import databaseIF.LuogoDB;
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
	 * Restituisce tutte le città
	 * @return lista di città, se vuota errore
	 */
	@Override
	public List<String> ritornaCitta() {
	    List<String> citta = new ArrayList<>();
	    try {
	        Connection conn = DriverManager.getConnection(DatabaseManager.getIstanza().getUrl());
	        if (conn != null) {
	            DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
	            Result<Record1<String>> result = create
	                .selectDistinct(Luogo.LUOGO.CITTA)
	                .from(Luogo.LUOGO)
	                .fetch();
	            for (Record1<String> record : result) {
	                citta.add(record.value1());
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Errore durante il recupero delle citta' dal database");
	    }
	    return citta;
	}

	/**
	 * Restituisce tutti i luoghi in una città
	 * @param citta
	 * @return lista di luoghi con attributi, se vuota errore
	 */
	public List<Map<String, Object>> ritornaLuoghiCitta(String cittaInput) {
	    List<Map<String, Object>> luoghi = new ArrayList<>();
	    try {
	        Connection conn = DriverManager.getConnection(DatabaseManager.getIstanza().getUrl());
	        if (conn != null) {
	            DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
	            Result<Record8<String, Double, Double, String, String, String, Integer, String>> result = create
	                .select(
	                	Luogo.LUOGO.NOME,
	                	Luogo.LUOGO.LATITUDINE,
	                	Luogo.LUOGO.LONGITUDINE,
	                	Luogo.LUOGO.CITTA,
	                	Luogo.LUOGO.INDIRIZZO,
	                	Luogo.LUOGO.TIPO,
	                    Luogo.LUOGO.TEMPOVISITA,
	                    Luogo.LUOGO.IMMAGINE
	                )
	                .from(Luogo.LUOGO)
	                .where(Luogo.LUOGO.CITTA.eq(cittaInput))
	                .and(Luogo.LUOGO.TIPO.eq("Visitabile"))
	                .fetch();
	            for (Record8<String, Double, Double, String, String, String, Integer, String> record : result) {
	                Map<String, Object> luogo = new HashMap<>();
	                luogo.put("nome", record.value1());
	                luogo.put("latitudine", record.value2());
	                luogo.put("longitudine", record.value3());
	                luogo.put("citta", record.value4());
	                luogo.put("indirizzo", record.value5());
	                luogo.put("tipo", record.value6());
	                luogo.put("tempoDiVisita", record.value7());
	                luogo.put("immagine", record.value8());
	                luoghi.add(luogo);
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Errore durante il recupero dei luoghi dal database");
	    }
	    
	    return luoghi;
	}
	
	/**
	 * Ritorna la lista dei n ristoranti più vicini date le coordinate
	 * @param latitudine, longitudine, n
	 * @return lista dei n ristoranti più vicini se presenti, altrimenti errore
	 */
	public List<Map<String, Object>> ritornaRistorantiVicini(double longitudine, double latitudine, int n) {
	    List<Map<String, Object>> luoghi = new ArrayList<>();
	    try {
	        Connection conn = DriverManager.getConnection(DatabaseManager.getIstanza().getUrl());
	        if (conn != null) {
	            DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
	            Result<Record8<String, Double, Double, String, String, String, Integer, String>> result = create
	                .select(
	                    Luogo.LUOGO.NOME,
	                    Luogo.LUOGO.LATITUDINE,
	                    Luogo.LUOGO.LONGITUDINE,
	                    Luogo.LUOGO.CITTA,
	                    Luogo.LUOGO.INDIRIZZO,
	                    Luogo.LUOGO.TIPO,
	                    Luogo.LUOGO.TEMPOVISITA,
	                    Luogo.LUOGO.IMMAGINE
	                )
	                .from(Luogo.LUOGO)
	                .where(Luogo.LUOGO.TIPO.eq("Ristoro"))
	                .fetch();

	            for (Record8<String, Double, Double, String, String, String, Integer, String> record : result) {
	                double latRistorante = record.value2();
	                double lonRistorante = record.value3();

	                // Calcolo le distanze usando il metodo di Haversine (è usato di solito)
	                double R = 6371e3; // raggio della terra in metri
	                double phi1 = Math.toRadians(latitudine);
	                double phi2 = Math.toRadians(latRistorante);
	                double deltaPhi = Math.toRadians(latRistorante - latitudine);
	                double deltaLambda = Math.toRadians(lonRistorante - longitudine);

	                double a = Math.sin(deltaPhi / 2) * Math.sin(deltaPhi / 2) +
	                           Math.cos(phi1) * Math.cos(phi2) *
	                           Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);
	                double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	                double distanza = R * c;

	                Map<String, Object> luogo = new HashMap<>();
	                luogo.put("nome", record.value1());
	                luogo.put("latitudine", latRistorante);
	                luogo.put("longitudine", lonRistorante);
	                luogo.put("citta", record.value4());
	                luogo.put("indirizzo", record.value5());
	                luogo.put("tipo", record.value6());
	                luogo.put("tempoDiVisita", record.value7());
	                luogo.put("immagine", record.value8());
	                luogo.put("distanza", distanza);

	                luoghi.add(luogo);
	            }
	            
	            // Ordino per vicinanza
	            luoghi.sort(Comparator.comparingDouble(l -> (double) l.get("distanza")));

	            // limito i primi 
	            if (luoghi.size() > n) {
	                luoghi = luoghi.subList(0, n);
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Errore durante il recupero dei luoghi dal database");
	    }

	    return luoghi;
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
