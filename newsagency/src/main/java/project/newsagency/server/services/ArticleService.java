package project.newsagency.server.services;

import project.newsagency.server.persistence.entities.Article;

import java.util.Set;

public interface ArticleService {
    Article saveArticle(Article article);

    Article updateArticle(Article article);

    void deleteArticle(Article article);

    Set<Article> viewAllArticles();
}
