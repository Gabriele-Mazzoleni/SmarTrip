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
 * Classe che testa metodi della classe "QueryMappa"
 */
@TestMethodOrder(MethodOrderer.MethodName.class) 
public class TestMappa {

    @BeforeAll
    public static void setUp() {
        DatabaseManager.getIstanza().setTestMode(true);
    }

    @AfterAll
    public static void tearDown() throws IOException, SQLException {
        DatabaseManager.getIstanza().setTestMode(false);
    }
	
    @Test
    public void test301InserisciMappa() {
        int result = DatabaseManager.getIstanza().getQueryMappa().inserisciMappa("Mappa di Test", "Tester", 1,
                "Nomi", "Latitudini", "Longitudini", "Tempi di visita", "Orari di arrivo");
        assertEquals(1, result);
    }
    
    @Test
    public void test302EliminaMappa() {
        int result = DatabaseManager.getIstanza().getQueryMappa().eliminaMappa("Mappa di Test", "Tester");
        assertEquals(1, result);
    }
	
}
