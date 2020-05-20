package com.ytu.reader.server.base;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * @program: rssreader
 * @description: service接口需要实现的父接口，封装了一些基本的service操作
 * @author: LiuTeng
 * @create: 2020-05-16 21:22
 **/
public interface BaseService<T,ID extends Serializable,R extends BaseRepository<T,ID>> {
    /**
     * 获取Repository
     * @return R
     */
    R getRepository();

    /**
     * 更新一条数据
     * @param id
     * @param entity
     * @return
     */
    T updateOne(ID id, T entity);

    /**
     * 更新一条数据
     * @param baseRepository
     * @param id
     * @param entity
     * @param <TT>
     * @param <TID>
     * @return
     */
    <TT,TID extends Serializable> TT updateOne(BaseRepository<TT, TID> baseRepository, TID id, TT entity);

    /**
     * 保存一条数据
     * @param entity
     * @return
     */
    T saveOne(T entity);

    /**
     * 保存一条数据
     * @param baseRepository
     * @param entity
     * @param <TT>
     * @param <TID>
     * @return
     */
    <TT,TID extends Serializable> TT saveOne(BaseRepository<TT, TID> baseRepository, TT entity);

    /**
     * 查找一条数据
     * @param id
     * @return
     */
    T findOne(ID id);

    /**
     * 删除一条数据
     * @param id
     */
    void deleteById(ID id);

    /**
     * 判断是否存在
     * @param id
     * @return
     */
    boolean exists(ID id);

    /**
     * 查询全部
     * @return
     */
    List<T> findAll();

    /**
     * 按照条件查询
     * @param example
     * @return
     */
    List<T> findAll(Example<T> example);

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    Page<T> findAll(Pageable pageable);

    T saveOrUpdate(ID id,T t);
}
