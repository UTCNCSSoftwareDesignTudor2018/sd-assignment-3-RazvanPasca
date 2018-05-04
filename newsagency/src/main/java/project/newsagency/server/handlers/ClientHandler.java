package project.newsagency.server.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {

    private Socket socket;
    private int clientNumber;

    public ClientHandler(Socket socket, int clientNumber) {
        this.socket = socket;
        this.clientNumber = clientNumber;
        log("New connection with client# " + clientNumber + " at " + socket);
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter serverToClientOut = new PrintWriter(socket.getOutputStream(), true);
            sendClientWelcomeMessage(serverToClientOut);

            // Get messages from the client, line by line; return them capitalized
            while (true) {
                String input = in.readLine();
                if (input == null || input.equalsIgnoreCase("close")) {
                    break;
                }
                System.out.println("I have received " + input);
                serverToClientOut.println(input.toUpperCase());
            }
        } catch (IOException e) {
            log("Error handling client# " + clientNumber + ": " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                log("Couldn't close a socket, what's going on?");
            }
            log("Connection with client# " + clientNumber + " closed");
        }
    }

    private void sendClientWelcomeMessage(PrintWriter clientOut) {
        clientOut.println("Hello, you are client #" + clientNumber + ".");
        clientOut.println("Enter a line with close to quit\n");
    }

    private void log(String message) {
        System.out.println(message);
    }
}


