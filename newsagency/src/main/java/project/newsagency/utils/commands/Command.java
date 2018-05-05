package project.newsagency.utils.commands;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Command {
    @JsonProperty("message")
    protected String message;

    public Command(String message) {
        this.message = message;
    }

    public Command() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

