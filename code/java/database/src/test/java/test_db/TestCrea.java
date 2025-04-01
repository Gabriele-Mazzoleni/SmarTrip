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

import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.Configuration;
import org.jooq.meta.jaxb.Database;
import org.jooq.meta.jaxb.Generator;
import org.jooq.meta.jaxb.Jdbc;
import org.jooq.meta.jaxb.Target;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import gestore_db.CreaDB;
import gestore_db.DatabaseManager;
import gestore_db.PopolaDB;

/**
 * Classe che testa metodi della classe "CreaDB"
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestCrea {

    private static final String DB_TEST_FILE = "../database/db/test_db.db3";
    public static final String DB_TEST_URL = "jdbc:sqlite:" + DB_TEST_FILE;

    @Test
    public void test001CreazioneDB() throws IOException, SQLException {
    	DatabaseManager.getIstanza().getCreaDB().creaDB();
        File dbFile = new File(DB_TEST_URL);
        assertTrue(dbFile.exists());
    }

    @Test
    public void test002CreazioneTabelle() throws IOException, SQLException {
    	DatabaseManager.getIstanza().getCreaDB().creaDB();
        try (Connection conn = DriverManager.getConnection(DB_TEST_URL)) {
            assertTrue(isTableExists(conn, "UTENTE"));
            assertTrue(isTableExists(conn, "LUOGO"));
            assertTrue(isTableExists(conn, "MAPPA"));
        }
    }
    
    @Test
    public void test003PopolaTabelle() throws IOException, SQLException {
    	DatabaseManager.getIstanza().getPopolaDB().inserisciDati();
        Connection conn = DriverManager.getConnection(DB_TEST_URL);
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
