package server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
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
    	duplicaOutputConsole("log_server.txt");
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

                if (!networkInterface.isUp() || networkInterface.isLoopback()) {
                    continue;
                }

                // Nome tecnico (es. wlan0) o nome descrittivo (es. Wi-Fi)
                String name = networkInterface.getName().toLowerCase();
                String displayName = networkInterface.getDisplayName().toLowerCase();

                // Aggiunto supporto a interfacce Windows come "Wi-Fi" e "Ethernet"
                if (!(name.startsWith("wlan") || name.startsWith("wl") || 
                      displayName.contains("wi-fi") || displayName.contains("ethernet"))) {
                    continue;
                }

                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    if (address instanceof Inet4Address && !address.isLoopbackAddress()) {
                        System.out.println("Server avviato correttamente");
                        System.out.println("Inserisci su app questo indirizzo: ");
                        System.out.println(address.getHostAddress() + ":" + getServerPort());
                        return;
                    }
                }
            }

            System.out.println("Nessun indirizzo IPv4 trovato per l'interfaccia Wi-Fi o Ethernet.");
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    
    public static void duplicaOutputConsole(String nomeFile) {
        try {
            // Crea un file log
            FileOutputStream fos = new FileOutputStream(nomeFile);
            @SuppressWarnings("resource")
			PrintStream fileOut = new PrintStream(fos, true);
            PrintStream console = System.out;

            // Scrivi sia su console che su file
            PrintStream dualOut = new PrintStream(new OutputStream() {
                @Override
                public void write(int b) throws IOException {
                    console.write(b);
                    fileOut.write(b);
                }
            }, true);

            System.setOut(dualOut);
            System.setErr(dualOut);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getServerPort() {
        return serverPort;
    }
}
