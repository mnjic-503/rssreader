package com.ytu.reader.server.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.beans.IntrospectionException;
import java.io.Serializable;

/**
 * @program: rssreader
 * @description: 信息源分类信息表
 * @author: LiuTeng
 * @create: 2020-05-16 20:10
 **/
@Entity
@Table(name = "feed_category")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","fieldHandler"})
public class FeedCategory implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private String categoryIcon;
    private String categoryName;
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FeedCategory{" +
                "id=" + id +
                ", categoryIcon='" + categoryIcon + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", status=" + status +
                '}';
    }
}
