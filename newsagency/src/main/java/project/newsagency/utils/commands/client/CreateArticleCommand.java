package project.newsagency.utils.commands.client;

import project.newsagency.server.persistence.entities.Article;
import project.newsagency.utils.commands.Command;

public class CreateArticleCommand extends Command {
    private Article article;

    public CreateArticleCommand() {
        super("createArticle");
    }

    public CreateArticleCommand(Article article) {
        super("createArticle");
        this.article = article;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
