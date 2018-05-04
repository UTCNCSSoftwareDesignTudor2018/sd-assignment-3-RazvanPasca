package main;

import entities.Article;
import entities.Author;
import repositories.PersistenceManager;

import javax.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        Author author = new Author();
        author.setPassword("Author 1");
        author.setUserName("Author 1 pass");

        Article article = new Article("Titlu 1","Abstract 1","Body 1",author);
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        em.getTransaction()
                .begin();
        em.persist(author);
        em.persist(article);
        em.getTransaction()
                .commit();
        em.close();
        PersistenceManager.INSTANCE.close();

        Author author1 = new Author("Author 2","Author 2 pass", article);
        em.getTransaction()
                .begin();
        em.persist(author1);
        em.getTransaction()
                .commit();
        em.close();
        PersistenceManager.INSTANCE.close();
        return;
    }
}

