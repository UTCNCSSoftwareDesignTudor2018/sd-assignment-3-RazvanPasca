package project.newsagency.client.controller.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private ClientCommandFactory commandFactory = new ClientCommandFactory();
    private String jsonString;
    private ObjectMapper mapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).
            configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
    private Client client;

    public ClientCommandInterpreter() {
    }

    public ClientCommandInterpreter(Client client) {
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
            executeFailedLoginCommandResponse((FailedLoginCommandResponse) command);
        if (command instanceof SuccessfulLoginCommandResponse)
            executeSuccessfulLoginCommandResponse((SuccessfulLoginCommandResponse) command);
    }

    private void executeSuccessfulLoginCommandResponse(SuccessfulLoginCommandResponse command) {
        client.setLoginStatus(true);
    }

    private void executeFailedLoginCommandResponse(FailedLoginCommandResponse command) {
        client.setLoginStatus(false);
        System.out.println("Wrong login details, please retry");
    }

    private void executeFetchArticlesCommandResponse(FetchArticlesCommandResponse command) {
        List<Article> articles = new ArrayList<>(command.getArticles());
        client.setArticles(articles);
    }
}
