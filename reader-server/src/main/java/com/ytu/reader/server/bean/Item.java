package com.ytu.reader.server.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: rssreader
 * @description: 文章信息表
 * @author: LiuTeng
 * @create: 2020-05-16 20:21
 **/
@Entity
@Table(name = "item")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","fieldHandler"})
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Item implements Serializable {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    private String title;
    private String author;
    private String category;
    private String cover;
    private String description;
    private String link;
    private Date pubDate;
    @ManyToOne
    @JoinColumn(name = "feed_id")
    private Feed feed;

    @Transient
    private Integer favourite;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public Integer getFavourite() {
        return favourite;
    }

    public void setFavourite(Integer favourite) {
        this.favourite = favourite;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", cover='" + cover + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", pubDate=" + pubDate +
                ", feed=" + feed +
                '}';
    }
}
