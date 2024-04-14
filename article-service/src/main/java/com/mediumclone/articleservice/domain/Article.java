package com.mediumclone.articleservice.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Article {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @Column(columnDefinition = "LONGTEXT")
    private String content;
    private Long author;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;
    @ManyToMany
//    @Cascade(CascadeType.ALL)
    private List<Tag> tags = new ArrayList<>();
    public Article() {
    }


    @PrePersist
    protected void onCreate() {
        created_at = new Date();
        updated_at = new Date();
    }

    public Article(String title, String content, Long author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTag(Tag tag) {
        this.tags.add(tag);
    }
    public void resetTags(){
        this.tags.clear();
    }
    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author=" + author +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", tags=" + tags +
                '}';
    }
}
