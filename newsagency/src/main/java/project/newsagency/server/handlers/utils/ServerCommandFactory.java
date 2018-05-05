package project.newsagency.server.handlers.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import project.newsagency.utils.commands.Command;
import project.newsagency.utils.commands.client.CreateArticleCommand;
import project.newsagency.utils.commands.client.DeleteArticleCommand;
import project.newsagency.utils.commands.client.FetchArticlesCommand;
import project.newsagency.utils.commands.client.LoginCommand;

import java.io.IOException;

@Component
public class ServerCommandFactory {
    private static final String LOGIN_COMMAND = "login";
    private static final String FETCH_ARTICLES_COMMAND = "fetchArticles";
    private static final String CREATE_ARTICLE_COMMAND = "createArticle";
    private static final String DELETE_ARTICLE_COMMAND = "deleteArticle";
    private ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public Command getCommand(String type) throws IOException {
        Command command = mapper.readValue(type, Command.class);

        if (command.getMessage().equalsIgnoreCase(LOGIN_COMMAND))
            return mapper.readValue(type, LoginCommand.class);
        if (command.getMessage().equalsIgnoreCase(FETCH_ARTICLES_COMMAND))
            return mapper.readValue(type, FetchArticlesCommand.class);
        if (command.getMessage().equalsIgnoreCase(CREATE_ARTICLE_COMMAND))
            return mapper.readValue(type, CreateArticleCommand.class);
        if (command.getMessage().equalsIgnoreCase(DELETE_ARTICLE_COMMAND))
            return mapper.readValue(type, DeleteArticleCommand.class);
        return null;
    }
}
