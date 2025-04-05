package test_db;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import gestore_db.DatabaseManager;

/**
 * Classe che testa metodi della classe "CreaDB"
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestCrea {

    @BeforeAll
    public static void setUp() {
        DatabaseManager.getIstanza().setTestMode(true);
    }

    @AfterAll
    public static void tearDown() throws IOException, SQLException {
        DatabaseManager.getIstanza().setTestMode(false);
    }	
	
    @Test
    public void test001CreazioneDB() throws IOException, SQLException {
    	//Rimuovo database se già esistente
        File dbFile = new File(DatabaseManager.getIstanza().getPath());
    	if (dbFile.exists()) {
            dbFile.delete();
        }
    	DatabaseManager.getIstanza().getCreaDB().creaDB();
        File newDbFile = new File(DatabaseManager.getIstanza().getPath());
        assertTrue(newDbFile.exists());
    }

    @Test
    public void test002CreazioneTabelle() throws IOException, SQLException {
        try (Connection conn = DriverManager.getConnection(DatabaseManager.getIstanza().getUrl())) {
            assertTrue(isTableExists(conn, "UTENTE"));
            assertTrue(isTableExists(conn, "LUOGO"));
            assertTrue(isTableExists(conn, "MAPPA"));
        }
    }
    
    @Test
    public void test003PopolaTabelle() throws IOException, SQLException {
    	DatabaseManager.getIstanza().getPopolaDB().inserisciDati();
        Connection conn = DriverManager.getConnection(DatabaseManager.getIstanza().getUrl());
        Statement stmt = conn.createStatement();
        
        //Controlla se gli utenti sono stati inseriti
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM utente");
        rs.next();
        int userCount = rs.getInt(1);
        assertEquals(3, userCount);

        //Controlla se i luoghi sono stati inseriti
        rs = stmt.executeQuery("SELECT COUNT(*) FROM luogo");
        rs.next();
        int luogoCount = rs.getInt(1);
        assertTrue(luogoCount > 0, "I luoghi non sono stati inseriti correttamente");

        conn.close();
    }

    private boolean isTableExists(Connection conn, String tableName) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            var resultSet = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='" + tableName + "'");
            return resultSet.next();
        }
    }
}
