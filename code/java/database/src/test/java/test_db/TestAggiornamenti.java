package test_db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

import gestore_db.AggiornamentiJooq;

/**
 * Classe che testa i metodi della classe "AggiornamentiJooq"
 */
@TestMethodOrder(MethodOrderer.MethodName.class) // Ordina i test in base ai nomi dei metodi
public class TestAggiornamenti {

    private AggiornamentiJooq aggiornamentiJooq;

    public TestAggiornamenti() {}

    /**
     * Metodo eseguito prima di ogni test
     */
    @BeforeEach
    public void setUp() {
        aggiornamentiJooq = AggiornamentiJooq.getIstanza();
    }

    @Test
    public void test201cambiaPassword() {
        int result = aggiornamentiJooq.cambiaPassword("Tester", "TTTT");
        assertEquals(1, result);
    }
}
