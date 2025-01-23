package modelli;

/**
 * Classe modello dell'utente
 */
public class Utente {
	
	private String codice;
	private String username;
	private String password;
	
	public Utente(String c, String u, String s) {
		this.setCodice(c);
		this.setUsername(s);
		this.setPassword(s);
	}
	
	public String getCodice() {
		return codice;
	}
	
	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
