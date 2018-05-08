package project.newsagency.server.persistence.entities;

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

    @ManyToMany(
            cascade = {CascadeType.MERGE, CascadeType.DETACH},
            fetch = FetchType.EAGER
    )
    @JoinTable(name = "author_to_article",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors = new HashSet<>();

    public Article(String title, String abs, String body, Author author) {
        this.title = title;
        this.abs = abs;
        author.addArticle(this);
        this.body = body;
    }

    public Article(String title, String abs, String body) {
        this.title = title;
        this.abs = abs;
        this.body = body;
    }

    public Article() {
    }

    public void addAuthor(Author author) {
        author.addArticle(this);
    }

    public void removeAuthor(Author author) {
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
        return Objects.equals(articleId, article.articleId) &&
                Objects.equals(title, article.title) &&
                Objects.equals(abs, article.abs) &&
                Objects.equals(body, article.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleId, title, abs, body);
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                '}';
    }
}

