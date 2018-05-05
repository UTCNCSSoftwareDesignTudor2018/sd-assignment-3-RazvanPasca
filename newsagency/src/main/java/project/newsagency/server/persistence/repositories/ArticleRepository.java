package project.newsagency.server.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.newsagency.server.persistence.entities.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Integer>{
}
