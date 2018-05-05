package project.newsagency.utils.commands.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import project.newsagency.server.persistence.entities.Author;
import project.newsagency.utils.commands.Command;

public class LoginCommand extends Command {

    @JsonProperty("author")
    private Author author;

    public LoginCommand(Author author) {
        super("login");
        this.author = author;
    }

    public LoginCommand() {
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
