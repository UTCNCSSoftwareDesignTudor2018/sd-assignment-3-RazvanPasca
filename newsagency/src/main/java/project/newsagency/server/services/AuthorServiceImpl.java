package project.newsagency.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.newsagency.server.persistence.entities.Article;
import project.newsagency.server.persistence.entities.Author;
import project.newsagency.server.persistence.repositories.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    ArticleService articleService;

    @Override
    public Article editArticle(Article article, Author author) {
        if (!article.getAuthors().contains(author))
            article.addAuthor(author);
        Article article1 = articleService.saveArticle(article);
        authorRepository.save(author);
        return article1;
    }

    @Override
    public Article writeArticle(Article article, Author author) {
        article.addAuthor(author);
        Article article1 = articleService.saveArticle(article);
        authorRepository.save(author);
        return article1;
    }

    @Override
    public Author login(Author author) {
        Author author1 = authorRepository.findByUserName(author.getUserName());
        if (author1 == null)
            return null;
        else if (author.password.equals(author1.getPassword()))
            return author1;
        else
            return null;
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }
}
