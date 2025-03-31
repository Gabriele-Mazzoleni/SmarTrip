package modelli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe modello dell'utente
 */
public class Utente {
	
	private String username;
	private String password;
	
    @JsonCreator
	public Utente(@JsonProperty("username") String u, @JsonProperty("password") String s) {
		this.setUsername(u);
		this.setPassword(s);
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
