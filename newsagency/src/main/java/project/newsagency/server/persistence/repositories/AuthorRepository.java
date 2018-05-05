package project.newsagency.server.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.newsagency.server.persistence.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {

    Author findByUserName (String userName);

}
