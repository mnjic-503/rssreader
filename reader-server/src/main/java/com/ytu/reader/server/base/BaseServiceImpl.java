package com.ytu.reader.server.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @program: rssreader
 * @description: service实现类的父类
 * @author: LiuTeng
 * @create: 2020-05-16 21:48
 **/
public abstract class BaseServiceImpl<T, ID extends Serializable, R extends BaseRepository<T, ID>> implements BaseService<T,ID,R>{
    @PersistenceContext
    protected EntityManager em;
    @Autowired
    protected R baseRepository;

    @Override
    public R getRepository() {
        return baseRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public T updateOne(ID id, T entity) {
        return updateOne(baseRepository,id,entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public <TT, TID extends Serializable> TT updateOne(BaseRepository<TT, TID> baseRepository, TID id, TT entity) {
        return baseRepository.save(entity);
    }

    @Override
    public T saveOne(T entity) {
        return saveOne(baseRepository,entity);
    }

    @Override
    public <TT, TID extends Serializable> TT saveOne(BaseRepository<TT, TID> baseRepository, TT entity) {
        return baseRepository.save(entity);
    }

    @Override
    public T findOne(ID id) {
        Optional<T> byId = baseRepository.findById(id);
        return byId.orElse(null);
    }

    @SuppressWarnings("all")
    @Override
    public void deleteById(ID id) {
        baseRepository.deleteById(id);
    }

    @Override
    public boolean exists(ID id) {
        return baseRepository.existsById(id);
    }

    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public List<T> findAll(Example<T> example) {
        return baseRepository.findAll(example);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return baseRepository.findAll(pageable);
    }

    @Override
    public T saveOrUpdate(ID id, T t) {
        if(id!=null){
            T db=findOne(id);
            if(db!=null){
                return updateOne(id, t);
            }
        }
        return saveOne(t);
    }
}
