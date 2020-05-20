package com.ytu.reader.server.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @program: rssreader
 * @description: 信息源信息表
 * @author: LiuTeng
 * @create: 2020-05-16 20:08
 **/
@Entity
@Table(name = "feed")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","fieldHandler"})
public class Feed implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String url;
    private String description;
    private String icon;
    private Integer follower;
    private Integer articles;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private FeedCategory feedCategory;
    private Integer status;

    @Transient
    private Integer subscribe;

    public Feed(String title, String link, String description, FeedCategory feedCategory) {
    }

    public Feed() {

    }

    public void init() {
    }

    public Integer getId() {
        return id;
    }

    public Feed setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getFollower() {
        return follower;
    }

    public void setFollower(Integer follower) {
        this.follower = follower;
    }

    public Integer getArticles() {
        return articles;
    }

    public void setArticles(Integer articles) {
        this.articles = articles;
    }

    public FeedCategory getFeedCategory() {
        return feedCategory;
    }

    public void setFeedCategory(FeedCategory feedCategory) {
        this.feedCategory = feedCategory;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                ", follower=" + follower +
                ", articles=" + articles +
                ", feedCategory=" + feedCategory +
                ", status=" + status +
                ", subscribe=" + subscribe +
                '}';
    }
}
