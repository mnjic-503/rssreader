package com.ytu.reader.server.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: rssreader
 * @description: 收藏文章信息表
 * @author: LiuTeng
 * @create: 2020-05-16 20:35
 **/
@Entity
@Table(name = "item_favourite")
@IdClass(ItemFavouriteKey.class)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","fieldHandler"})
public class ItemFavourite implements Serializable {
    @Id
    @Column(name = "user_id")
    private Integer userId;
    @Id
    @Column(name = "feed_id")
    private Integer feedId;
    @Id
    @Column(name = "item_id")
    private String itemId;
    @ManyToOne
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "feed_id",insertable = false,updatable = false)
    private Feed feed;
    @ManyToOne
    @JoinColumn(name = "item_id",insertable = false,updatable = false)
    private Item item;
    private Date joinDate;

    public ItemFavourite(ItemFavouriteKey itemFavouriteKey) {
        this.feedId = itemFavouriteKey.getFeedId();
        this.userId = itemFavouriteKey.getUserId();
        this.itemId = itemFavouriteKey.getItemId();
        this.joinDate = new Date();
    }

    public ItemFavourite(){

    }

    public Integer getUserId() {
        return userId;
    }

    public ItemFavourite setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Integer getFeedId() {
        return feedId;
    }

    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        return "ItemFavourite{" +
                "userId=" + userId +
                ", feedId=" + feedId +
                ", itemId='" + itemId + '\'' +
                ", user=" + user +
                ", feed=" + feed +
                ", item=" + item +
                ", joinDate=" + joinDate +
                '}';
    }
}
