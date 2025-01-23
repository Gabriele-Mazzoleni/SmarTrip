package test_db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import jooq_db.jooq.generated.tables.Luogo;
import jooq_db.jooq.generated.tables.Utente;

/**
 * Classe principale per l'esecuzione dei test
 * L'esecuzione del test non compromette il database reale, viene creato un database di copia (test_db.db3).
 */
@Suite
@SelectClasses({
    TestInserimenti.class,
    TestAggiornamenti.class,
    TestRimozioni.class,
    TestCrea.class
})

public class DaTestare {

    /**
     * Metodo che verrà eseguito prima di tutti i test
     */
    @BeforeAll
    public static void setUpBeforeClass() {
        System.out.println("Setup prima dell'esecuzione di tutti i test.");
    }

    /**
     * Metodo che verrà eseguito dopo tutti i test
     */
    @AfterAll
    public static void tearDownAfterClass() {
        System.out.println("Pulizia dopo l'esecuzione di tutti i test.");

        try (Connection conn = DriverManager.getConnection(TestCrea.DB_TEST_URL)) {
            DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

            // Rimozione dei dati di test inseriti per ogni metodo
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
