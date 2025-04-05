package server;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
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
@ComponentScan(basePackages = {"controller", "services", "server"})
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
            // Ottieni tutte le interfacce di rete
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();

                // Ignora le interfacce che non sono attive o sono di loopback
                if (!networkInterface.isUp() || networkInterface.isLoopback()) {
                    continue;
                }

                // Puoi filtrare ulteriormente per nome, ad esempio "wlan0", "wlp3s0", ecc.
                String interfaceName = networkInterface.getName();
                if (!interfaceName.startsWith("wlan") && !interfaceName.startsWith("wl")) {
                    continue;
                }

                // Ottieni gli indirizzi associati a questa interfaccia
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();

                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();

                    // Considera solo indirizzi IPv4 non di loopback
                    if (address instanceof Inet4Address && !address.isLoopbackAddress()) {
                        System.out.println("Server avviato correttamente");
                        System.out.println("Inserisci su app questo indirizzo: ");
                        System.out.println(address.getHostAddress() + ":" + getServerPort());
                        return; // trovato, esci
                    }
                }
            }

            System.out.println("Nessun indirizzo IPv4 trovato per l'interfaccia Wi-Fi.");
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public int getServerPort() {
        return serverPort;
    }
}
