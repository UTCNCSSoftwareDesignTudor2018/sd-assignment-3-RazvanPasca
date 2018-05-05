package project.newsagency.client.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import project.newsagency.client.entities.SimpleClient;
import project.newsagency.client.view.SimpleClientView;
import project.newsagency.server.persistence.entities.Article;
import project.newsagency.server.persistence.entities.Author;
import project.newsagency.utils.commands.client.LoginCommand;
import project.newsagency.utils.commands.server.FetchArticlesCommand;

import java.io.IOException;
import java.util.Set;

public class SimpleClientController extends Thread {

    private SimpleClientView simpleClientView;
    private SimpleClient client;

    public SimpleClientController(SimpleClientView simpleClientView, SimpleClient client) throws JsonProcessingException {
        this.client = client;
        this.simpleClientView = simpleClientView;
        sendFetchArticlesCommand();
        addListeners();
    }

    public Set<Article> getArticles() {
        return client.getArticles();
    }

    public void setArticles(Set<Article> articles) {
        this.client.setArticles(articles);
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

    private void sendFetchArticlesCommand() throws JsonProcessingException {
        client.sendCommand(new FetchArticlesCommand());
    }

    @Override
    public void run() {
        while (true) {
            try {
                client.getCommands();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
