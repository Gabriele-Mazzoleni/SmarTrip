package gestore_db;

/**
 * Gestisce le query usando pattern singleton, thread safe
 */
public class DatabaseManager {

	//Pattern singleton
	private static DatabaseManager istanza;
	
	private final CreaDB creaDB;
	private final GeneraJooq generaJooq;
	private final PopolaDB popolaDB;
	private final QueryUtente queryUtente;
    private final QueryLuogo queryLuogo;
    private final QueryMappa queryMappa;
    
    private String dbPath;
    private String dbUrl;
    
    private boolean testMode = false;
    
    //Percorso database
	private static String DB_PATH = "../database/db/db.db3";
	private static String DB_URL = "jdbc:sqlite:" + DB_PATH;
	
    //Percorso database test
	private static String DB_PATH_TEST = "../database/db/test_db.db3";
	private static String DB_URL_TEST = "jdbc:sqlite:" + DB_PATH_TEST;
	

	private DatabaseManager() {
		creaDB = new CreaDB();
		generaJooq = new GeneraJooq();
		popolaDB = new PopolaDB();
		queryUtente = new QueryUtente();
		queryLuogo = new QueryLuogo();
		queryMappa = new QueryMappa();
		setTestMode(testMode);
	}
	
		
	public static DatabaseManager getIstanza() {
		if (istanza == null) {
			istanza = new DatabaseManager();
	    }
		return istanza;
	}

	public CreaDB getCreaDB() {
		return creaDB;
	}

	public GeneraJooq getGeneraJooq() {
		return generaJooq;
	}

	public PopolaDB getPopolaDB() {
		return popolaDB;
	}

	public QueryUtente getQueryUtente() {
		return queryUtente;
	}

	public QueryLuogo getQueryLuogo() {
		return queryLuogo;
	}

	public QueryMappa getQueryMappa() {
		return queryMappa;
	}

	public String getPath() {
		return dbPath;
	}

	public String getUrl() {
		return dbUrl;
	}

	public boolean getTestMode() {
		return testMode;
	}

	public void setTestMode(boolean testMode) {
		if(testMode) {
			this.dbPath = DB_PATH_TEST;
			this.dbUrl = DB_URL_TEST;
		}
		else {
			this.dbPath = DB_PATH;
			this.dbUrl = DB_URL;
		}
		this.testMode = testMode;
	}
	
	public static void main(String[] args) throws Exception {
		DatabaseManager.getIstanza().getCreaDB().creaDB();
		DatabaseManager.getIstanza().getGeneraJooq().generaJooq();
	}
	
}
