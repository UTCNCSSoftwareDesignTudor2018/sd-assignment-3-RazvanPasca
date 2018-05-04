package project.newsagency.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import project.newsagency.server.persistence.entities.Article;
import project.newsagency.server.persistence.entities.Author;
import project.newsagency.server.persistence.repositories.AuthorRepository;

public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    ArticleService articleService;

    @Override
    public Article editArticle(Article article, Author author) {
        return null;
    }

    @Override
    public Article writeArticle(Article article, Author author) {
        return null;
    }

    @Override
    public boolean login(Author author) {
        Author author1 = authorRepository.findByUserName(author.getUserName());
        return author.password.equals(author1.getPassword());
    }
}
