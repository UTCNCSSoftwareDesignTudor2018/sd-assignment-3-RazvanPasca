package project.newsagency.server.persistence.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "authors")
@PersistenceUnit(unitName = "news-agency-jpa")
public class Author {
    @Id
    @GeneratedValue
    public Integer authorId;
    public String userName;
    public String password;

    @ManyToMany(
            cascade = {CascadeType.MERGE, CascadeType.DETACH},
            fetch = FetchType.EAGER
    )
    @JoinTable(name = "author_to_article",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id"))
    @JsonIgnore
    private Set<Article> articles = new HashSet<>();

    public void addArticle(Article article) {
        articles.add(article);
        article.getAuthors().add(this);
    }

    public void removeArticle(Article article) {
        articles.remove(article);
        article.getAuthors().remove(this);
    }

    public Author(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Author(String userName, String password, Article article) {
        this.addArticle(article);
        this.userName = userName;
        this.password = password;
    }

    public Author() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return Objects.equals(authorId, author.authorId) &&
                Objects.equals(userName, author.userName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(authorId, userName);
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", userName='" + userName + "}";

    }
}
