package project.newsagency.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import project.newsagency.server.handlers.ClientHandler;

import java.net.ServerSocket;

import static project.newsagency.config.ConfigSetup.readConfig;
import static project.newsagency.config.Constants.FILE_LOCATION;

@SpringBootApplication
public class NewsAgencyApplication implements CommandLineRunner {

    private int port;

    public static void main(String[] args) {
        SpringApplication.run(NewsAgencyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        this.port = readConfig(FILE_LOCATION);
        ServerSocket serverSocket = new ServerSocket(port);
        int clientNumber = 0;
        try {
            while (true) {
                System.out.println("waiting client connection..");
                //returns a new client thread or will block if no client connects
                new ClientHandler(serverSocket.accept(), clientNumber++).start();
            }
        } finally {
            serverSocket.close();
        }
    }
}
