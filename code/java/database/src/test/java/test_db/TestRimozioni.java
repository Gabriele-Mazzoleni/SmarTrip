package test_db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

import gestore_db.RimozioniJooq;

/**
 * Classe che testa metodi della classe "RimozioniJooq"
 */
@TestMethodOrder(MethodOrderer.MethodName.class) // Ordina i test in base ai nomi dei metodi
public class TestRimozioni {

    private RimozioniJooq rimozioniJooq;

    public TestRimozioni() {}

    /**
     * Metodo eseguito prima di ogni test
     */
    @BeforeEach
    public void setUp() {
        rimozioniJooq = RimozioniJooq.getIstanza();
    }
    
    @Test
    public void test201RimozioneMappa() {
        int result = rimozioniJooq.mappa("Mappa di Test", "Tester");
        assertEquals(1, result);
    }

    @Test
    public void test202RimozioneUtente() {
        int result = rimozioniJooq.utente("Tester");
        assertEquals(1, result);
    }

    @Test
    public void test203RimozioneLuogo() {
        int result = rimozioniJooq.luogo("Luogo di Test", 0.0, 0.0);
        assertEquals(1, result);
    }
}
