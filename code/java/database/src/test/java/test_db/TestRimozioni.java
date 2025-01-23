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
    public void test04RimozioneUtente() {
        int result = rimozioniJooq.utente("t0");
        assertEquals(1, result, "La rimozione dell'utente dovrebbe restituire 1.");
    }

    @Test
    public void test05RimozioneLuogo() {
        int result = rimozioniJooq.luogo("Lougo di Test", 0.0, 0.0);
        assertEquals(1, result, "La rimozione del luogo dovrebbe restituire 1.");
    }
}
