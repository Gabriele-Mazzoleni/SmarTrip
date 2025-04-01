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
		
	private DatabaseManager() {
		creaDB = new CreaDB();
		generaJooq = new GeneraJooq();
		popolaDB = new PopolaDB();
		queryUtente = new QueryUtente();
		queryLuogo = new QueryLuogo();
		queryMappa = new QueryMappa();
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
	
}
