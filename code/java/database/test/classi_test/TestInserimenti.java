package classi_test;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import gestore_db.InserimentiJooq;
import org.junit.Test;

/**
 * Classe che testa metodi della classe "InserimentiJooq"
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestInserimenti {
	
	public TestInserimenti() {};

    private InserimentiJooq inserimentoJooq;

    /**
	 * Metodo che verrà prima dell'esecuzione di tutti i test
	 */
    @Before
    public void setUp() throws IOException {
        inserimentoJooq = InserimentiJooq.getIstanza();
    }
    
    @Test
    public void test01InserimentoUtente() {
        int result = inserimentoJooq.utente("t0", "Tester", "tttt");
        assertEquals(1, result);
    }
    
    @Test
    public void test02InserimentoLuogo() {
        int result = inserimentoJooq.luogo("Lougo di Test", 0.0, 0.0,
				"Città Test", "Indirizzo Test", "Test");
        assertEquals(1, result);
    } 
    
    
}

