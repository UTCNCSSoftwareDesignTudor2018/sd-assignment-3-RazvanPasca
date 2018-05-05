package project.newsagency.utils.commands.client;

import project.newsagency.server.persistence.entities.Article;
import project.newsagency.utils.commands.Command;

public class DeleteArticleCommand extends Command {
    private Article article;

    public DeleteArticleCommand(Article article) {
        super("deleteArticle");
        this.article = article;
    }

    public DeleteArticleCommand() {
        super("deleteArticle");

    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
