package server;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import modelli.Utente;

/**
 * Classe che avvia il server con Spring Boot
 */
@SpringBootApplication
public class App {
	
	public static void main(String[] args) {
		 SpringApplication.run(App.class, args);
	}
	
	@Bean
	 RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	 }
	
	@Bean
	 CommandLineRunner run(RestTemplate restTemplate) {
		return args -> {
			try {
			    ArrayList<Utente> list = restTemplate.exchange(
			        "http://localhost:8000/restaurants",
			        HttpMethod.GET,
			        null,
			        new ParameterizedTypeReference<ArrayList<Utente>>() {}
			    ).getBody();
			    System.out.println(list);
			} catch (RestClientException e) {
			    System.err.println("Errore nella chiamata REST: " + e.getMessage());
			}
		};
	 }
	 
}


