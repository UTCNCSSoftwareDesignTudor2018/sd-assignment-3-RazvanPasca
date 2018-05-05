package project.newsagency.client.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import project.newsagency.client.entities.SimpleClient;
import project.newsagency.client.view.SimpleClientView;
import project.newsagency.server.persistence.entities.Article;
import project.newsagency.server.persistence.entities.Author;
import project.newsagency.utils.commands.client.LoginCommand;
import project.newsagency.utils.commands.server.FetchArticlesCommand;

import java.io.IOException;
import java.util.List;

public class SimpleClientController {

    private SimpleClientView simpleClientView;
    private SimpleClient client;
    private List<Article> articles;

    public SimpleClientController(SimpleClientView simpleClientView, SimpleClient client) throws JsonProcessingException {
        this.client = client;
        this.simpleClientView = simpleClientView;
        System.out.println("here");
        client.sendCommand(new FetchArticlesCommand());
        addListeners();
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    private void addListeners() {
        this.simpleClientView.addLoginListener(e -> {
            Author author = new Author(simpleClientView.getUserNameTextField(), simpleClientView.getPasswordField());
            try {
                client.sendCommand(new LoginCommand(author));
            } catch (JsonProcessingException e1) {
                e1.printStackTrace();
            }
        });
    }

    public void runClient() throws IOException {
        while (true) {
            client.getCommands();
        }
    }
}
