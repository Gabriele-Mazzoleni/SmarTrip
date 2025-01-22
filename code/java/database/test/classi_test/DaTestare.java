package classi_test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import jooq_db.jooq.generated.tables.Luogo;
import jooq_db.jooq.generated.tables.Utente;

/**
 * Specifica le classi di test nell'ordine da eseguire
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    TestInserimenti.class,  
    TestAggiornamenti.class,
    TestRimozioni.class,
    TestCrea.class
})

/**
 * Classe principale per l'esecuzione dei test
 * L'esecuzione del test non compromette il database reale, viene creato un database di copia (test_db.db3)
 */
public class DaTestare {

	/**
	 * Metodo statico che verrà eseguito prima dell'esecuzione di tutti i test
	 */
    @org.junit.BeforeClass
    public static void setUpBeforeClass() {
    	
    }

    /**
	 * Metodo statico che verrà eseguito dopo l'esecuzione di tutti i test
	 */
    @org.junit.AfterClass
    public static void tearDownAfterClass() {
    	 try (Connection conn = DriverManager.getConnection(TestCrea.DB_TEST_URL)) {
             DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
             
             //Rimozione dei dati di test inseriti per ogni metodo
             create.deleteFrom(Utente.UTENTE).execute();
             create.deleteFrom(Luogo.LUOGO).execute();
         } catch (SQLException e) {
             System.err.println("Errore durante la rimozione dei dati di test: " + e.getMessage());
         }
    	 
    	 File dbFile = new File("db/test_db.db3");
         if (dbFile.exists()) {
             dbFile.delete();
         }
    }
   
}
