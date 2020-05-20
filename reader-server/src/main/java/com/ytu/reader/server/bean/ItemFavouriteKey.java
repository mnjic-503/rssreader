package com.ytu.reader.server.bean;

import java.io.Serializable;

/**
 * @program: rssreader
 * @description: 收藏文章主键类
 * @author: LiuTeng
 * @create: 2020-05-16 20:34
 **/
public class ItemFavouriteKey implements Serializable {
    private Integer userId;
    private Integer feedId;
    private String  itemId;

    public ItemFavouriteKey() {
    }

    public ItemFavouriteKey(Integer userId, Integer feedId, String itemId) {
        this.userId = userId;
        this.feedId = feedId;
        this.itemId = itemId;
    }

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

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
