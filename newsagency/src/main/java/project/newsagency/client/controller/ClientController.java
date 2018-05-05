package project.newsagency.client.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import project.newsagency.client.entities.Client;
import project.newsagency.client.view.ArticleView;
import project.newsagency.client.view.ClientView;
import project.newsagency.server.persistence.entities.Article;
import project.newsagency.server.persistence.entities.Author;
import project.newsagency.utils.commands.client.CreateArticleCommand;
import project.newsagency.utils.commands.client.FetchArticlesCommand;
import project.newsagency.utils.commands.client.LoginCommand;

import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ClientController implements Observer {

    private ClientView clientView;
    private Client client;

    public ClientController(ClientView clientView, Client client) throws IOException {
        this.client = client;
        this.clientView = clientView;
        client.addObserver(this);
        client.connectToServer();
        sendFetchArticlesCommand();
        addListeners();
    }

    private void setTableModel(List<Article> articles) {
        DefaultTableModel tableModel = new DefaultTableModel() {

            private static final long serialVersionUID = 5245348397698764465L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.addColumn("Title");
        tableModel.addColumn("Abstract");
        tableModel.addColumn("Author");
        for (Article article : articles)
            tableModel.addRow(new String[]{article.getTitle(), article.getAbs(), article.getAuthors().toString()});
        clientView.setTableModel(tableModel);
    }

    private void addListeners() {
        addLoginListener();
        addTableMouseListener();
    }

    private void addLoginListener() {
        this.clientView.addLoginListener(e -> {
            Author author = new Author(clientView.getUserNameTextField(), clientView.getPasswordField());
            try {
                client.sendCommand(new LoginCommand(author));
            } catch (JsonProcessingException e1) {
                e1.printStackTrace();
            }
        });
    }

    private void addTableMouseListener() {
        this.clientView.addTableMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    try {
                        showArticleView(clientView.getTableRow(mouseEvent));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        showArticleView(-1);
                    }
                }
            }
        });

    }

    private void showArticleView(int row) {
        ArticleView articleView = new ArticleView(client.getLoginStatus());
        if (row > -1)
            articleView.setArticleContent(client.getArticles().get(row));
        articleView.setVisible(true);
        addArticleViewListeners(articleView);
    }

    private void addArticleViewListeners(ArticleView articleView) {
        addArticleViewCreateListener(articleView);
        addArticleViewDeleteListener(articleView);
    }

    private void addArticleViewDeleteListener(ArticleView articleView) {
        articleView.addCreateArticleButtonListener(e -> {
            sendCreateArticleCommand();
        });
    }

    private void addArticleViewCreateListener(ArticleView articleView) {
    }

    private void sendFetchArticlesCommand() throws JsonProcessingException {
        client.sendCommand(new FetchArticlesCommand());
    }

    private void sendCreateArticleCommand() {
        client.sendCommand(new CreateArticleCommand());
    }

    @Override
    public void update(Observable o, Object arg) {
        setTableModel((List<Article>) arg);
    }
}
