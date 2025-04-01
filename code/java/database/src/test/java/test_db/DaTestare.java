package test_db;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;


/**
 * Classe che permette di eseguire tutti i test delle classi di test in ordine
 */
@Suite
@SelectClasses({
	TestCrea.class, 
	TestUtente.class, 
	TestLuogo.class, 
	TestMappa.class
})
public class DaTestare {

}
