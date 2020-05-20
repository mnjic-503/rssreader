package com.ytu.reader.server.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: rssreader
 * @description: 用户订阅信息源信息表
 * @author: LiuTeng
 * @create: 2020-05-16 20:27
 **/
@Entity
@Table(name = "user_feed")
@IdClass(UserFeedKey.class)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","fieldHandler"})
public class UserFeed implements Serializable {
    public UserFeed() {
    }

    public UserFeed(Integer userId, Integer feedId, Date date) {
    }
    public UserFeed(Integer userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "user_id")
    private Integer userId;
    @Id
    @Column(name = "feed_id")
    private Integer feedId;
    @ManyToOne
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "feed_id",insertable = false,updatable = false)
    private Feed feed;

    private Date joinDate;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFeedId() {
        return feedId;
    }

    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        return "UserFeed{" +
                "userId=" + userId +
                ", feedId=" + feedId +
                ", user=" + user +
                ", feed=" + feed +
                ", joinDate=" + joinDate +
                '}';
    }
}
