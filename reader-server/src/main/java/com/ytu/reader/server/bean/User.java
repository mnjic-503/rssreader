package com.ytu.reader.server.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: rssreader
 * @description: 用户信息表
 * @author: LiuTeng
 * @create: 2020-05-16 19:54
 **/
@Entity
@Table(name = "user")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","fieldHandler"})
public class User implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String icon;
    private String sign;
    private Integer feedNumber;
    private Date createDate;
    private Integer status;

    public void init(){
        this.email = "";
        this.sign = "";
        this.feedNumber = 0;
        this.createDate = new Date();
        this.status = 0;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Integer getFeedNumber() {
        return feedNumber;
    }

    public void setFeedNumber(Integer feedNumber) {
        this.feedNumber = feedNumber;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", icon='" + icon + '\'' +
                ", sign='" + sign + '\'' +
                ", feedNumber=" + feedNumber +
                ", createDate=" + createDate +
                ", status=" + status +
                '}';
    }
}
