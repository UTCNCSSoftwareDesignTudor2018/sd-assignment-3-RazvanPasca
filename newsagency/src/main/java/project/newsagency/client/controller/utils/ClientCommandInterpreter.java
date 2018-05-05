package project.newsagency.client.controller.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import project.newsagency.server.handlers.utils.BeanUtil;
import project.newsagency.utils.commands.Command;
import project.newsagency.utils.commands.client.FetchArticlesCommandResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class ClientCommandInterpreter {

    private ClientCommandFactory commandFactory = BeanUtil.getBean(ClientCommandFactory.class);
    private String jsonString;
    private ObjectMapper mapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).
            configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);

    public ClientCommandInterpreter() {
    }

    public void setJson(String input) {
        this.jsonString = input;
    }

    public void executeCommand(PrintWriter clientToServerOut) throws IOException {
        Command command = commandFactory.getCommand(this.jsonString);
        if (command instanceof FetchArticlesCommandResponse)
            executeFetchArticlesCommandResponse();
    }

    private void executeFetchArticlesCommandResponse() {

    }
}
