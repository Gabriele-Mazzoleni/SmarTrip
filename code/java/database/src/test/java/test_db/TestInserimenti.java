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
    public void test01InserimentoUtente() {
        int result = inserimentoJooq.utente("t0", "Tester", "tttt");
        assertEquals(1, result, "Il metodo inserimento utente dovrebbe restituire 1.");
    }

    @Test
    public void test02InserimentoLuogo() {
        int result = inserimentoJooq.luogo("Lougo di Test", 0.0, 0.0,
                "Città Test", "Indirizzo Test", "Test", 1000);
        assertEquals(1, result, "Il metodo inserimento luogo dovrebbe restituire 1.");
    }
}
