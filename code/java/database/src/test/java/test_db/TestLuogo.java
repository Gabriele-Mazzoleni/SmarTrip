package test_db;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import gestore_db.DatabaseManager;

/**
 * Classe che testa metodi della classe "QueryLuogo"
 */
@TestMethodOrder(MethodOrderer.MethodName.class) 
public class TestLuogo {

    @Test
    public void test201InserimentoLuogo() {
        int result = DatabaseManager.getIstanza().getQueryLuogo().inserisciLuogo("Luogo di Test", 0.0, 0.0,
                "Città Test", "Indirizzo Test", "Test", 1000, "url");
        assertEquals(1, result);
    }
    
    @Test
    public void test202RimozioneUtente() {
        int result = DatabaseManager.getIstanza().getQueryLuogo().eliminaLuogo("Luogo di Test", 0.0, 0.0);
        assertEquals(1, result);
    }
	
}
