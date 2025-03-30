package test_db;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gestore_db.CreaDB;

/**
 * Classe che testa metodi della classe "CreaDB"
 */
public class TestCrea {

    private static final String DB_TEST_FILE = "db/test_db.db3";
    public static final String DB_TEST_URL = "jdbc:sqlite:" + DB_TEST_FILE;
    private CreaDB creaDB;

    public TestCrea() {}

    /**
     * Metodo eseguito prima di ogni test
     */
    @BeforeEach
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
     * Metodo eseguito dopo ogni test
     */
    @AfterEach
    public void tearDown() {
        File dbFile = new File(DB_TEST_FILE);
        if (dbFile.exists()) {
            dbFile.delete();
        }
    }

    @Test
    public void test001DatabaseCreation() throws IOException, SQLException {
        creaDB.creaDB();
        File dbFile = new File(DB_TEST_FILE);
        assertTrue(dbFile.exists(), "Il database non è stato creato correttamente.");
    }

    @Test
    public void test002TableCreation() throws IOException, SQLException {

        creaDB.creaDB();

        try (Connection conn = DriverManager.getConnection(DB_TEST_URL)) {
            assertTrue(isTableExists(conn, "UTENTE"), "La tabella UTENTE non è stata creata.");
            assertTrue(isTableExists(conn, "LUOGO"), "La tabella LUOGO non è stata creata.");
            assertTrue(isTableExists(conn, "MAPPA"), "La tabella LUOGO non è stata creata.");
        }
    }

    private boolean isTableExists(Connection conn, String tableName) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            var resultSet = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='" + tableName + "'");
            return resultSet.next();
        }
    }
}
