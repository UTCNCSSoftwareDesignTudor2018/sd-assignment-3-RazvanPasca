package project.newsagency.client.controller.utils;

import project.newsagency.client.entities.Client;
import project.newsagency.server.persistence.entities.Article;
import project.newsagency.utils.commands.Command;
import project.newsagency.utils.commands.server.FailedLoginCommandResponse;
import project.newsagency.utils.commands.server.FetchArticlesCommandResponse;
import project.newsagency.utils.commands.server.SuccessfulLoginCommandResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ClientCommandInterpreter {

    private PrintWriter clientToServerOut;
    private ClientCommandFactory commandFactory = new ClientCommandFactory();
    private String jsonString;
    private Client client;

    public ClientCommandInterpreter() {
    }

    public ClientCommandInterpreter(Client client, PrintWriter clientToServerOut) {
        this.clientToServerOut = clientToServerOut;
        this.client = client;
    }

    public void setJson(String input) {
        this.jsonString = input;
    }

    public void executeCommand(PrintWriter clientToServerOut) throws IOException {
        Command command = commandFactory.getCommand(this.jsonString);
        if (command instanceof FetchArticlesCommandResponse)
            executeFetchArticlesCommandResponse((FetchArticlesCommandResponse) command);
        if (command instanceof FailedLoginCommandResponse)
            executeFailedLoginCommandResponse();
        if (command instanceof SuccessfulLoginCommandResponse)
            executeSuccessfulLoginCommandResponse();
    }

    private void executeSuccessfulLoginCommandResponse() {
        client.setLoginStatus(true);
    }

    private void executeFailedLoginCommandResponse() {
        client.setLoginStatus(false);
        System.out.println("Wrong login details, please retry");
    }

    private void executeFetchArticlesCommandResponse(FetchArticlesCommandResponse command) {
        List<Article> articles = new ArrayList<>(command.getArticles());
        this.client.setArticles(articles);
    }
}
