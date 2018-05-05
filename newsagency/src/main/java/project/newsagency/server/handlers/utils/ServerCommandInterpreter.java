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
import project.newsagency.utils.commands.client.FetchArticlesCommand;
import project.newsagency.utils.commands.client.LoginCommand;
import project.newsagency.utils.commands.server.FailedLoginCommandResponse;
import project.newsagency.utils.commands.server.FetchArticlesCommandResponse;
import project.newsagency.utils.commands.server.SuccessfulLoginCommandResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@Component("executor")
public class ServerCommandInterpreter {

    private ArticleServiceImpl articleService = BeanUtil.getBean(ArticleServiceImpl.class);
    private AuthorServiceImpl authorService = BeanUtil.getBean(AuthorServiceImpl.class);
    private ServerCommandFactory commandFactory = BeanUtil.getBean(ServerCommandFactory.class);
    private ObjectMapper mapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).
            configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
    private String jsonString;
    private ClientHandler clientHandler;

    public ServerCommandInterpreter() {
    }

    public void executeCommand(PrintWriter writer) throws IOException {
        Command command = commandFactory.getCommand(this.jsonString);
        if (command instanceof FetchArticlesCommand)
            executeFetchArticlesCommand(writer);
        if (command instanceof LoginCommand)
            executeLoginCommand((LoginCommand) command, writer);
    }

    private void executeFetchArticlesCommand(PrintWriter serverToClientOut) throws JsonProcessingException {
        Set<Article> articles = articleService.viewAllArticles();
        System.out.println(articles);
        FetchArticlesCommandResponse fetchArticlesCommandResponse = new FetchArticlesCommandResponse(articles);
        sendClientResponse(fetchArticlesCommandResponse, serverToClientOut);
    }

    private void executeLoginCommand(LoginCommand command, PrintWriter serverToClientOut) throws JsonProcessingException {
        Author author = authorService.login(command.getAuthor());
        if (author == null) {
            FailedLoginCommandResponse failedLoginCommandResponse = new FailedLoginCommandResponse();
            sendClientResponse(failedLoginCommandResponse, serverToClientOut);
        } else {
            clientHandler.setLoggedInAuthor(author);
            SuccessfulLoginCommandResponse successfulLoginCommandResponse = new SuccessfulLoginCommandResponse();
            sendClientResponse(successfulLoginCommandResponse, serverToClientOut);
            System.out.println("Logged in author is " + author);
        }
    }

    private void sendClientResponse(Object object, PrintWriter clientOut) throws JsonProcessingException {
        String jsonString = mapper.writeValueAsString(object);
        clientOut.println(jsonString);
    }

    public void setJson(String input) {
        this.jsonString = input;
    }

    public void setClientHandler(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }
}