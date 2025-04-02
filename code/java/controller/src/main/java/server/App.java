package server;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Classe che avvia il server con Spring Boot
 */
@SpringBootApplication
@ComponentScan(basePackages = {"api", "server"})
public class App implements CommandLineRunner {

    // Valore di default porta
    @Value("${server.port}") 
    private int serverPort;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        trovaIp();
    }

    public void trovaIp() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();

                // Esclude interfacce non attive o loopback
                if (!networkInterface.isUp() || networkInterface.isLoopback()) {
                    continue;
                }

                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress inetAddress = addresses.nextElement();
                    String ip = inetAddress.getHostAddress();

                    // Controlla se è IPv4 e inizia con 172
                    if (inetAddress.getAddress().length == 4 && ip.startsWith("172.")) {
                        String[] parts = ip.split("\\.");
                        int secondOctet = Integer.parseInt(parts[1]);

                        // Controlla se il secondo ottetto è tra 16 e 31 (range privato)
                        if (secondOctet >= 16 && secondOctet <= 31) {
                            System.out.println("Server avviato correttamente");
                            System.out.println("Inserisci su app questo indirizzo: ");
                            System.out.println(ip + ":" + getServerPort());
                            return; // Esce dopo aver trovato il primo indirizzo valido
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Errore nel recupero dell'IP: " + e.getMessage());
        }
    }

    public int getServerPort() {
        return serverPort;
    }
}
