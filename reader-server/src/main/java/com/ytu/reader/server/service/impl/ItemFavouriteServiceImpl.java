package com.ytu.reader.server.service.impl;

import com.ytu.reader.server.base.BaseServiceImpl;
import com.ytu.reader.server.bean.ItemFavourite;
import com.ytu.reader.server.bean.ItemFavouriteKey;
import com.ytu.reader.server.repository.ItemFavouriteRepository;
import com.ytu.reader.server.service.ItemFavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: rssreader
 * @description: 收藏文章Service实现类
 * @author: LiuTeng
 * @create: 2020-05-18 19:12
 **/
@Service("itemFavouriteServiceImpl")
public class ItemFavouriteServiceImpl extends BaseServiceImpl<ItemFavourite, ItemFavouriteKey, ItemFavouriteRepository> implements ItemFavouriteService {
    @Autowired
    ItemFavouriteRepository itemFavouriteRepository;
    @Override
    public List<ItemFavourite> findFavouritesByUserId(Integer userId, Integer pageNo) {
        Sort.Order joinDateOrder = new Sort.Order(Sort.Direction.DESC,"joinDate");
        Example<ItemFavourite> example = Example.of(new ItemFavourite().setUserId(userId));
        Page<ItemFavourite> page = itemFavouriteRepository.findAll(example, PageRequest.of(pageNo, 10, Sort.by(joinDateOrder)));
        return page.getContent();
    }
}
