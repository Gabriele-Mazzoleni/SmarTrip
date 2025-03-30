package test_db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

import gestore_db.InserimentiJooq;

/**
 * Classe che testa metodi della classe "InserimentiJooq"
 */
@TestMethodOrder(MethodOrderer.MethodName.class) // Ordina i test in base ai nomi dei metodi
public class TestInserimenti {

    private InserimentiJooq inserimentoJooq;

    public TestInserimenti() {}

    /**
     * Metodo eseguito prima di ogni test
     */
    @BeforeEach
    public void setUp() throws IOException {
        inserimentoJooq = InserimentiJooq.getIstanza();
    }

    @Test
    public void test101InserimentoUtente() {
        int result = inserimentoJooq.utente("Tester", "tttt");
        assertEquals(1, result);
    }
    
    @Test
    public void test101InserimentoUtenteDuplicato() {
        int result = inserimentoJooq.utente("Tester", "tttt");
        assertEquals(0, result);
    }

    @Test
    public void test102InserimentoLuogo() {
        int result = inserimentoJooq.luogo("Luogo di Test", 0.0, 0.0,
                "Città Test", "Indirizzo Test", "Test", 1000, "url");
        assertEquals(1, result);
    }
    
    @Test
    public void test102InserimentoDuplicato() {
        int result = inserimentoJooq.luogo("Luogo di Test", 0.0, 0.0,
                "Città Test", "Indirizzo Test", "Test", 1000, "url");
        assertEquals(0, result);
    }
    
    @Test
    public void test103InserimentoMappa() {
        int result = inserimentoJooq.mappa("Mappa di Test", "Tester", 1,
                "Nomi", "Latitudini", "Longitudini", "Tempi di visita", "Orari di arrivo");
        assertEquals(1, result);
    }
    
    @Test
    public void test103InserimentoMappaDuplicato() {
        int result = inserimentoJooq.mappa("Mappa di Test", "Tester", 1,
                "Nomi", "Latitudini", "Longitudini", "Tempi di visita", "Orari di arrivo");
        assertEquals(0, result);
    }
}
