package project.newsagency.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.newsagency.server.persistence.entities.Article;
import project.newsagency.server.persistence.repositories.ArticleRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public Article saveArticle(Article article) {
        Article article1 = articleRepository.save(article);
        return article1;
    }


    @Override
    public void deleteArticle(Article article) {
        articleRepository.delete(article);
    }

    @Override
    public Set<Article> viewAllArticles() {
        return new HashSet<Article>(articleRepository.findAll());
    }


}
