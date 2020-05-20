package com.ytu.reader.server.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @program: rssreader
 * @description: 用户订阅信息源主键类
 * @author: LiuTeng
 * @create: 2020-05-16 20:28
 **/
public class UserFeedKey implements Serializable {
    private Integer userId;
    private Integer feedId;

    public UserFeedKey() {
    }

    public UserFeedKey(Integer userId, Integer feedId) {
        this.userId = userId;
        this.feedId = feedId;
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
}
