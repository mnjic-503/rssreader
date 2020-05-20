package com.ytu.reader.server.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @program: rssreader
 * @description: repository接口需要实现的父接口
 * @author: LiuTeng
 * @create: 2020-05-16 21:24
 **/
@NoRepositoryBean
public interface BaseRepository<T,ID extends Serializable> extends JpaRepository<T,ID> {

}
