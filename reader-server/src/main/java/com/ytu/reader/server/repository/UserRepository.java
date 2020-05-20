package com.ytu.reader.server.repository;

import com.ytu.reader.server.base.BaseRepository;
import com.ytu.reader.server.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @program: rssreader
 * @description: 用户表操作接口
 * @author: LiuTeng
 * @create: 2020-05-16 21:06
 **/
public interface UserRepository extends BaseRepository<User,Integer> {
    /**
     * 根据用户名查询用户
     * @param username
     * @param status
     * @return
     */
    @Query(value = "select user from User user where user.username = ?1 and user.status = ?2")
    User findUserByUserName(String username,Integer status);
}
