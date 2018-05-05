package project.newsagency.client.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import project.newsagency.client.controller.utils.ClientCommandInterpreter;
import project.newsagency.server.persistence.entities.Article;
import project.newsagency.utils.commands.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Observable;

import static project.newsagency.config.ConfigSetup.readConfig;
import static project.newsagency.config.Constants.FILE_LOCATION;

public class Client extends Observable implements Runnable {
    private ClientCommandInterpreter commandInterpreter;
    private BufferedReader in;
    private PrintWriter clientToServerOut;
    private Socket socket;
    private List<Article> articles;
    private boolean loginStatus;

    public Client() throws IOException {
        connectToServer();
        commandInterpreter = new ClientCommandInterpreter(this, clientToServerOut);
    }

    public void connectToServer() throws IOException {
        int port = readConfig(FILE_LOCATION);
        socket = new Socket("127.0.0.1", port);
        in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        clientToServerOut = new PrintWriter(socket.getOutputStream(), true);

        // Consume the initial welcoming messages from the server
        for (int i = 0; i < 3; i++) {
            System.out.println(in.readLine() + "\n");
        }
        new Thread(this).start();
    }

    public void sendCommand(Command command) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).
                configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        String jsonString = objectMapper.writeValueAsString(command);
        clientToServerOut.println(jsonString);
        System.out.println("Command sent " + jsonString + " command");
    }

    @Override
    public void run() {
        while (true) {
            String input = null;
            try {
                input = in.readLine();
                commandInterpreter.setJson(input);
                commandInterpreter.executeCommand(clientToServerOut);
                System.out.println("Command executed " + input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        setChanged();
        this.articles = articles;
        notifyObservers(articles);
        clearChanged();
    }

    public boolean getLoginStatus() {
        return this.loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }
}
