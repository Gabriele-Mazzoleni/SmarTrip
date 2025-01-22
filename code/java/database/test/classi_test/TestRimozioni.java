package classi_test;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import gestore_db.RimozioniJooq;
import org.junit.Test;

/**
 * Classe che testa metodi della classe "RimozioniJooq"
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRimozioni {

    private RimozioniJooq rimozioniJooq;
    
    public TestRimozioni() {};

    /**
	 * Metodo che verrà prima dell'esecuzione di tutti i test
	 */
    @Before
    public void setUp() {
        rimozioniJooq = RimozioniJooq.getIstanza();
    }
    
    @Test
    public void test04RimozioneUtente() {
        int result = rimozioniJooq.utente("t0");
        assertEquals(1, result);
    }
    
    @Test
    public void test05RimozioneLuogo() {
        int result = rimozioniJooq.luogo("Lougo di Test", 0.0, 0.0);
        assertEquals(1, result);
    }
    
}


