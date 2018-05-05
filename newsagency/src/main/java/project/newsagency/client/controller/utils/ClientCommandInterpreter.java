package project.newsagency.client.controller.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import project.newsagency.client.entities.SimpleClient;
import project.newsagency.server.persistence.entities.Article;
import project.newsagency.utils.commands.Command;
import project.newsagency.utils.commands.client.FetchArticlesCommandResponse;
import project.newsagency.utils.commands.server.FailedLoginCommandResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class ClientCommandInterpreter {

    private ClientCommandFactory commandFactory = new ClientCommandFactory();
    private String jsonString;
    private ObjectMapper mapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).
            configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
    private SimpleClient client;

    public ClientCommandInterpreter() {
    }

    public ClientCommandInterpreter(SimpleClient client) {
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
    }

    private void executeFailedLoginCommandResponse(FailedLoginCommandResponse command) {
        System.out.println("Wrong login details, please retry");
    }

    private void executeFetchArticlesCommandResponse(FetchArticlesCommandResponse command) {
        Set<Article> articles = command.getArticles();
        client.setArticles(articles);
    }
}
