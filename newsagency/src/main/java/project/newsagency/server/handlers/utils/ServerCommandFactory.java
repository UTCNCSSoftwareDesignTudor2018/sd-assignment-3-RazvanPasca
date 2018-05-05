package project.newsagency.server.handlers.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import project.newsagency.utils.commands.Command;
import project.newsagency.utils.commands.client.LoginCommand;
import project.newsagency.utils.commands.server.FetchArticlesCommand;

import java.io.IOException;

@Component
public class ServerCommandFactory {
    private static final String LOGIN_COMMAND = "LOGIN";
    private static final String FETCH_ARTICLES_COMMAND = "FETCHARTICLES";
    private ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public Command getCommand(String type) throws IOException {
        Command command = mapper.readValue(type, Command.class);

        if (command.getMessage().equalsIgnoreCase(LOGIN_COMMAND))
            return mapper.readValue(type, LoginCommand.class);
        if (command.getMessage().equalsIgnoreCase(FETCH_ARTICLES_COMMAND))
            return mapper.readValue(type, FetchArticlesCommand.class);
        return null;
    }
}
