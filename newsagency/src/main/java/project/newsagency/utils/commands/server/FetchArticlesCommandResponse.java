package project.newsagency.utils.commands.server;

import com.fasterxml.jackson.annotation.JsonProperty;
import project.newsagency.server.persistence.entities.Article;
import project.newsagency.utils.commands.Command;

import java.util.Set;

public class FetchArticlesCommandResponse extends Command {

    @JsonProperty("articles")
    private Set<Article> articles;

    public FetchArticlesCommandResponse(Set<Article> articles) {
        super("FetchArticlesResponse");
        this.articles = articles;
    }

    public FetchArticlesCommandResponse() {
        super("FetchArticlesResponse");
    }

    public Set<Article> getArticles() {
        return articles;
    }


}
