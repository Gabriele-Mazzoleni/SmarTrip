package test_db;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import gestore_db.DatabaseManager;

/**
 * Classe che testa metodi della classe "QueryMappa"
 */
@TestMethodOrder(MethodOrderer.MethodName.class) 
public class TestMappa {

    @Test
    public void test301InserimentoLuogo() {
        int result = DatabaseManager.getIstanza().getQueryMappa().inserisciMappa("Mappa di Test", "Tester", 1,
                "Nomi", "Latitudini", "Longitudini", "Tempi di visita", "Orari di arrivo");
        assertEquals(1, result);
    }
    
    @Test
    public void test302RimozioneUtente() {
        int result = DatabaseManager.getIstanza().getQueryMappa().eliminaMappa("Mappa di Test", "Tester");
        assertEquals(1, result);
    }
	
}
