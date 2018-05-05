package project.newsagency.client.controller.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import project.newsagency.utils.commands.Command;
import project.newsagency.utils.commands.client.FetchArticlesCommandResponse;
import project.newsagency.utils.commands.server.FailedLoginCommandResponse;

import java.io.IOException;

@Component
public class ClientCommandFactory {
    private static final String FETCH_ARTICLES_COMMAND_RESPONSE = "fetchArticlesResponse";
    private static final String FAILED_LOGIN_COMMAND_RESPONSE = "failedLoginResponse";

    private ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public Command getCommand(String type) throws IOException {
        Command command = mapper.readValue(type, Command.class);
        if (command.getMessage().equalsIgnoreCase(FETCH_ARTICLES_COMMAND_RESPONSE))
            return mapper.readValue(type, FetchArticlesCommandResponse.class);
        if (command.getMessage().equalsIgnoreCase(FAILED_LOGIN_COMMAND_RESPONSE))
            return mapper.readValue(type, FailedLoginCommandResponse.class);
        return null;
    }
}
