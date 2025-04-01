package test_db;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import gestore_db.DatabaseManager;

/**
 * Classe che testa metodi della classe "QueryUtente"
 */
@TestMethodOrder(MethodOrderer.MethodName.class) 
public class TestUtente {

    @Test
    public void test101InserimentoUtente() {
        int result = DatabaseManager.getIstanza().getQueryUtente().inserisciUtente("Tester", "tttt");
        assertEquals(1, result);
    }
    
    @Test
    public void test102CambiaPassword() {
        int result = DatabaseManager.getIstanza().getQueryUtente().cambiaPassword("Tester", "TTTT");
        assertEquals(1, result);
    }
    
    @Test
    public void test103RimozioneUtente() {
        int result = DatabaseManager.getIstanza().getQueryUtente().eliminaUtente("Tester");
        assertEquals(1, result);
    }
	

}
