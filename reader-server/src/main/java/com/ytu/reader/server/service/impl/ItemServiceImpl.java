package com.ytu.reader.server.service.impl;

import com.ytu.reader.server.base.BaseServiceImpl;
import com.ytu.reader.server.bean.Feed;
import com.ytu.reader.server.bean.Item;
import com.ytu.reader.server.bean.ItemFavourite;
import com.ytu.reader.server.bean.ItemFavouriteKey;
import com.ytu.reader.server.repository.ItemFavouriteRepository;
import com.ytu.reader.server.repository.ItemRepository;
import com.ytu.reader.server.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @program: rssreader
 * @description: 文章Service实现类
 * @author: LiuTeng
 * @create: 2020-05-18 11:21
 **/
@Service("itemService")
public class ItemServiceImpl extends BaseServiceImpl<Item, String, ItemRepository> implements ItemService {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemFavouriteRepository itemFavouriteRepository;


    @Override
    public List<Item> findItemsByFeedId(Integer feedId, Integer pageNo) {
        Item probe = new Item();
        probe.setFeed(new Feed().setId(feedId));
        Example<Item> example = Example.of(probe);
        Sort.Order pubDateOrder = new Sort.Order(Sort.Direction.DESC,"pubDate");
        Page<Item> page = itemRepository.findAll(example, PageRequest.of(pageNo, 10,Sort.by(pubDateOrder)));
        return page.getContent();
    }

    @Override
    public List<Item> findItemsByKeyword(String keyword, Integer pageNo) {
        Sort.Order pubDateOrder = new Sort.Order(Sort.Direction.DESC,"pubDate");
        Page<Item> page = itemRepository.findByKeyword(keyword, PageRequest.of(pageNo, 10, Sort.by(pubDateOrder)));
        return page.getContent();
    }

    @Override
    public boolean ifFavourite(Integer userId, Integer feedId, String itemId) {
        Optional<ItemFavourite> itemFavourite = itemFavouriteRepository.findById(new ItemFavouriteKey(userId, feedId, itemId));
        return itemFavourite.isPresent();
    }
}
