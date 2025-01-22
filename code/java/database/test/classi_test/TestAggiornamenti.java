package classi_test;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import gestore_db.AggiornamentiJooq;

/**
 * Classe che testa metodi della classe "AggiornamentiJooq"
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAggiornamenti {
	
	public TestAggiornamenti() {};

    private AggiornamentiJooq aggiornamentiJooq;

    /**
	 * Metodo che verrà prima dell'esecuzione di tutti i test
	 */
    @Before
    public void setUp() {
        aggiornamentiJooq = AggiornamentiJooq.getIstanza();
    }
    
    @Test
    public void test03cambiaPassword() {
        int result = aggiornamentiJooq.cambiaPassword("t0", "TTTT");
        assertEquals(1, result);
    }
    
}
    

