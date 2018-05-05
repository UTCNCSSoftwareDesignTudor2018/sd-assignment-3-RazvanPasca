package project.newsagency.server.handlers.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import project.newsagency.server.handlers.ClientHandler;
import project.newsagency.server.persistence.entities.Article;
import project.newsagency.server.persistence.entities.Author;
import project.newsagency.server.services.ArticleServiceImpl;
import project.newsagency.server.services.AuthorServiceImpl;
import project.newsagency.utils.commands.Command;
import project.newsagency.utils.commands.client.CreateArticleCommand;
import project.newsagency.utils.commands.client.DeleteArticleCommand;
import project.newsagency.utils.commands.client.FetchArticlesCommand;
import project.newsagency.utils.commands.client.LoginCommand;
import project.newsagency.utils.commands.server.FailedLoginCommandResponse;
import project.newsagency.utils.commands.server.FetchArticlesCommandResponse;
import project.newsagency.utils.commands.server.SuccessfulLoginCommandResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Set;

@Component("executor")
public class ServerCommandInterpreter extends Observable {

    private ArticleServiceImpl articleService = BeanUtil.getBean(ArticleServiceImpl.class);
    private AuthorServiceImpl authorService = BeanUtil.getBean(AuthorServiceImpl.class);
    private ServerCommandFactory commandFactory = BeanUtil.getBean(ServerCommandFactory.class);
    private ObjectMapper mapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).
            configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
    private String jsonString;
    private ClientHandler clientHandler;
    private PrintWriter serverToClientOut;

    public ServerCommandInterpreter() {
    }

    public void executeCommand() throws IOException {
        Command command = commandFactory.getCommand(this.jsonString);
        if (command instanceof FetchArticlesCommand)
            executeFetchArticlesCommand();
        if (command instanceof LoginCommand)
            executeLoginCommand((LoginCommand) command);
        if (command instanceof CreateArticleCommand)
            executeCreateArticleCommand((CreateArticleCommand) command);
        if (command instanceof DeleteArticleCommand)
            executeDeleteArticleCommand((DeleteArticleCommand) command);
    }


    private synchronized void executeDeleteArticleCommand(DeleteArticleCommand command) {
        Article article = command.getArticle();
        articleService.deleteArticle(article);
        setChanged();
        notifyObservers();
        clearChanged();
        System.out.println("Deleted " + article);
    }

    private synchronized void executeCreateArticleCommand(CreateArticleCommand command) {
        Article article = command.getArticle();
        article.addAuthor(clientHandler.getLoggedInAuthor());
        authorService.save(clientHandler.getLoggedInAuthor());
        articleService.saveArticle(article);
        setChanged();
        notifyObservers();
        clearChanged();
        System.out.println("Created/Updated " + article);
    }

    public void executeFetchArticlesCommand() throws JsonProcessingException {
        Set<Article> articles = articleService.viewAllArticles();
        System.out.println(articles);
        FetchArticlesCommandResponse fetchArticlesCommandResponse = new FetchArticlesCommandResponse(articles);
        sendClientResponse(fetchArticlesCommandResponse);
    }

    private void executeLoginCommand(LoginCommand command) throws JsonProcessingException {
        Author author = authorService.login(command.getAuthor());
        if (author == null) {
            FailedLoginCommandResponse failedLoginCommandResponse = new FailedLoginCommandResponse();
            sendClientResponse(failedLoginCommandResponse);
        } else {
            clientHandler.setLoggedInAuthor(author);
            SuccessfulLoginCommandResponse successfulLoginCommandResponse = new SuccessfulLoginCommandResponse();
            sendClientResponse(successfulLoginCommandResponse);
            System.out.println("Logged in author is " + author);
        }
    }

    private void sendClientResponse(Object object) throws JsonProcessingException {
        String jsonString = mapper.writeValueAsString(object);
        System.out.println("Send response " + jsonString);
        serverToClientOut.println(jsonString);
    }

    public void setJson(String input) {
        this.jsonString = input;
    }

    public void setClientHandler(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public void setPrintWriter(PrintWriter printWriter) {
        this.serverToClientOut = printWriter;
    }
}