package project.newsagency.server.services;

import project.newsagency.server.handlers.utils.BeanUtil;
import project.newsagency.server.persistence.entities.Article;
import project.newsagency.server.persistence.repositories.ArticleRepository;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

public class ArticleServiceImpl extends Observable implements ArticleService {
    private static Object lock = new Object();
    private static ArticleServiceImpl instance;

    ArticleRepository articleRepository = BeanUtil.getBean(ArticleRepository.class);

    private ArticleServiceImpl() {

    }

    ;

    public static ArticleServiceImpl getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new ArticleServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public Article saveArticle(Article article) {
        Article article1 = articleRepository.save(article);
        notifyEveryone();
        return article1;
    }

    @Override
    public void deleteArticle(Article article) {
        articleRepository.delete(article);
        notifyEveryone();
    }

    @Override
    public Set<Article> viewAllArticles() {
        return new HashSet<Article>(articleRepository.findAll());
    }

    private synchronized void notifyEveryone() {
        setChanged();
        notifyObservers();
        clearChanged();
    }


}
