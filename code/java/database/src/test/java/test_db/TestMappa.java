package test_db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
                "Nomi", "Latitudini", "Longitudini", "Città", "Indirizzi", "Tipi", "Tempi di visita", 
                "Immagini", "Orari di arrivo");
        assertEquals(1, result);
    }
    
    @Test
    public void test302RitornaMappeUtente() {
        String nomeUtente = "Tester"; 
        List<Map<String, Object>> mappe = DatabaseManager.getIstanza().getQueryMappa().ritornaMappeUtente(nomeUtente);
        // Verifica che ci sia almeno una mappa con nomeMappa uguale a "Mappa di Test"
        boolean trovataMappa = mappe.stream()
            .anyMatch(mappa -> mappa.containsKey("nomeMappa") && mappa.get("nomeMappa").equals("Mappa di Test"));
        assertTrue(trovataMappa);
    }

    
    @Test
    public void test303EliminaMappa() {
        int result = DatabaseManager.getIstanza().getQueryMappa().eliminaMappa("Mappa di Test", "Tester");
        assertEquals(1, result);
    }
	
}
