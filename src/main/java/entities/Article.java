package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue
    private Integer articleId;
    private String title;
    private String abs;
    private String body;

    @ManyToMany(fetch = FetchType.EAGER,
    mappedBy = "articles")
    private Set<Author> authors = new HashSet<>();

    public Article(String title, String abs,String body, Author author) {
        this.title = title;
        this.abs = abs;
        author.addArticle(this);
        this.body = body;
    }

    public Article() {
    }

    public void addAuthor(Author author){
        author.addArticle(this);
    }

    public void removeAuthor(Author author){
        author.removeArticle(this);
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer id) {
        this.articleId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbs() {
        return abs;
    }

    public void setAbs(String abs) {
        this.abs = abs;
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return Objects.equals(articleId, article.articleId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(articleId);
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
}

