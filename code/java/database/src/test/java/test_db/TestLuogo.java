package test_db;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
 * Classe che testa metodi della classe "QueryLuogo"
 */
@TestMethodOrder(MethodOrderer.MethodName.class) 
public class TestLuogo {
	
    @BeforeAll
    public static void setUp() {
        DatabaseManager.getIstanza().setTestMode(true);
    }

    @AfterAll
    public static void tearDown() throws IOException, SQLException {
        DatabaseManager.getIstanza().setTestMode(false);
    }

    @Test
    public void test201InserisciLuogo() {
        int result = DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Luogo di Test", 0.0, 0.0,
                "Città Test", "Indirizzo Test", "Visitabile", 1000, "url");
        assertEquals(1, result);
    }
    
    @Test
    public void test202RitornaCitta() {
    	List<String> citta = DatabaseManager.getIstanza().getQueryLuogo().ritornaCitta();
    	assertTrue(citta.contains("Città Test"));
    }
    
    @Test
    public void test203RitornaLuoghiCitta() {
        List<Map<String, Object>> luoghi = DatabaseManager.getIstanza().getQueryLuogo().ritornaLuoghiCitta("Città Test");
        // Verifica la lista contenga almeno un luogo con nome
        boolean trovatoLuogo = luoghi.stream()
            .anyMatch(luogo -> luogo.containsKey("nome") && luogo.get("nome").equals("Luogo di Test"));
        assertTrue(trovatoLuogo);
    }
    
    @Test
    public void test204RitornaRistorantiVicini() {
    	List<Map<String, Object>> ristoranti = DatabaseManager.getIstanza().getQueryLuogo().ritornaRistorantiVicini(45.4642, 9.19, 1);
        // Verifica che la lista contenga almeno un ristorante con tipo "Ristoro"
        boolean trovatoRistoro = ristoranti.stream()
            .anyMatch(r -> r.containsKey("tipo") && "Ristoro".equals(r.get("tipo")));
        assertTrue(trovatoRistoro);
    }
    
    @Test
    public void test205EliminaLuogo() {
        int result = DatabaseManager.getIstanza().getQueryLuogo().eliminaLuogo("Luogo di Test", 0.0, 0.0);
        assertEquals(1, result);
    }
	
}
