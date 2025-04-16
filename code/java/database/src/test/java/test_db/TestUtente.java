package test_db;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import gestore_db.DatabaseManager;

/**
 * Classe che testa metodi della classe "QueryUtente"
 */
@TestMethodOrder(MethodOrderer.MethodName.class) 
public class TestUtente {

    @BeforeAll
    public static void setUp() {
        DatabaseManager.getIstanza().setTestMode(true);
    }

    @AfterAll
    public static void tearDown() throws IOException, SQLException {
        DatabaseManager.getIstanza().setTestMode(false);
    }
	
    @Test
    public void test101InserisciUtente() {
        int result = DatabaseManager.getIstanza().getQueryUtente().inserisciUtente("Tester", "tttt");
        assertEquals(1, result);
    }
    
    @Test
    public void test102ControllaPassword() {
        int result = DatabaseManager.getIstanza().getQueryUtente().controllaPassword("Tester", "tttt");
        assertEquals(1, result);
    }
    
    @Test
    public void test103RimuoviUtente() {
        int result = DatabaseManager.getIstanza().getQueryUtente().eliminaUtente("Tester");
        assertEquals(1, result);
    }

}
