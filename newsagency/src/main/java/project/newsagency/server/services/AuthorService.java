package project.newsagency.server.services;

import project.newsagency.server.persistence.entities.Article;
import project.newsagency.server.persistence.entities.Author;

public interface AuthorService {

    Article editArticle(Article article, Author author);

    Article writeArticle(Article article, Author author);

    boolean login(Author author);
}
