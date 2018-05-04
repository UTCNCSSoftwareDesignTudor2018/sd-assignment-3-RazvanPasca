package project.newsagency.client.commands;

import project.newsagency.server.persistence.entities.Author;

public class LoginCommand implements Command {
    private Author author;

    public LoginCommand(Author author) {
        this.author = author;
    }

    @Override
    public void execute() {

    }
}
