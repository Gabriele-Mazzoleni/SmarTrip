package classi_test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gestore_db.CreaDB;

/**
 * Classe che testa metodi della classe "CreaDB"
 */
public class TestCrea {
	
	public TestCrea() {};
    
    private static final String DB_TEST_FILE = "db/test_db.db3";
    public static final String DB_TEST_URL = "jdbc:sqlite:" + DB_TEST_FILE;
    private CreaDB creaDB;

    /**
	 * Metodo che verrà prima dell'esecuzione di tutti i test
	 */
    @Before
    public void setUp() throws IOException, SQLException {
        
        creaDB = CreaDB.getIstanza();
        CreaDB.DB_PATH = DB_TEST_FILE;
        CreaDB.DB_URL = DB_TEST_URL;

        File dbFile = new File(DB_TEST_FILE);
        if (dbFile.exists()) {
            dbFile.delete();
        }
    }

    /**
	 * Metodo che verrà dopo dell'esecuzione di tutti i test
	 */
    @After
    public void tearDown() {
        File dbFile = new File(DB_TEST_FILE);
        if (dbFile.exists()) {
            dbFile.delete();
        }
    }

    @Test
    public void testDatabaseCreation() throws IOException, SQLException {
        creaDB.creaDB();
        File dbFile = new File(DB_TEST_FILE);
        assertTrue(dbFile.exists());
    }
    
	@Test
    public void testTableCreation() throws IOException, SQLException {
        
        creaDB.creaDB();

        try (Connection conn = DriverManager.getConnection(DB_TEST_URL)) {
            assertTrue(isTableExists(conn, "UTENTE"));
            assertTrue(isTableExists(conn, "LUOGO"));
        }
    }

    private boolean isTableExists(Connection conn, String tableName) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            var resultSet = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='" + tableName + "'");
            return resultSet.next();
        }
    }
}

