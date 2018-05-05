package project.newsagency.server.handlers;

import project.newsagency.server.handlers.utils.BeanUtil;
import project.newsagency.server.handlers.utils.ServerCommandInterpreter;
import project.newsagency.server.persistence.entities.Author;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
    private ServerCommandInterpreter commandInterpreter = BeanUtil.getBean(ServerCommandInterpreter.class);
    private Socket socket;
    private int clientNumber;
    private BufferedReader in;
    private PrintWriter serverToClientOut;
    private Author loggedInAuthor;

    public ClientHandler(Socket socket, int clientNumber) {
        this.socket = socket;
        this.clientNumber = clientNumber;
        commandInterpreter.setClientHandler(this);
        log("New connection with client #" + clientNumber + " at " + socket);
    }

    private ClientHandler() {
    }

    boolean isAuthorLoggedIn() {
        return loggedInAuthor != null;
    }

    public Author getLoggedInAuthor() {
        return loggedInAuthor;
    }

    public void setLoggedInAuthor(Author loggedInAuthor) {
        this.loggedInAuthor = loggedInAuthor;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            serverToClientOut = new PrintWriter(socket.getOutputStream(), true);
            sendClientWelcomeMessage(serverToClientOut);

            while (true) {
                String input = in.readLine();
                if (input == null || input.equalsIgnoreCase("close")) {
                    break;
                }
                commandInterpreter.setJson(input);
                commandInterpreter.executeCommand(serverToClientOut);
            }
        } catch (IOException e) {
            log("Error handling client #" + clientNumber + ": " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                log("Couldn't close a socket, what's going on?");
            }
            log("Connection with client #" + clientNumber + " closed");
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


