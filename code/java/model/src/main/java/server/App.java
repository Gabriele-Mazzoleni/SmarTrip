package server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import modelli.Utente;

/**
 * Classe che avvia il server con Spring Boot
 */
@SpringBootApplication
@ComponentScan(basePackages = {"modelli", "server"})
public class App {
	
	private String port = "8083";
	
	public static void main(String[] args) {
		 SpringApplication.run(App.class, args);
	}
	
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

    
    //DA METTERE IN CLIENT
    /*
	@Bean
	 RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	 }
	
	@Bean
	 CommandLineRunner run(RestTemplate restTemplate) {
		return args -> {
			try {
				@SuppressWarnings("unchecked")
				ArrayList<Utente> list = restTemplate.getForObject(
					"http://localhost:8083/utenti", ArrayList.class
				);
				System.out.println(list);
			} catch (RestClientException e) {
			    System.err.println("Errore nella chiamata REST: " + e.getMessage());
			}
		};
	 }
	
	 
	 */
}


