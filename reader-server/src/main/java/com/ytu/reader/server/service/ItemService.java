package com.ytu.reader.server.service;

import com.ytu.reader.server.base.BaseService;
import com.ytu.reader.server.bean.Item;
import com.ytu.reader.server.repository.ItemRepository;

import java.util.List;

/**
 * @program: rssreader
 * @description: 文章Service接口
 * @author: LiuTeng
 * @create: 2020-05-18 09:41
 **/
public interface ItemService extends BaseService<Item, String, ItemRepository> {

    /**
     * 分页查询信息源的文章
     * @param feedId
     * @param pageNo
     * @return
     */
    List<Item> findItemsByFeedId(Integer feedId,Integer pageNo);

    List<Item> findItemsByKeyword(String keyword,Integer pageNo);

    /**
     * 判断文章是否收藏
     * @param userId
     * @param feedId
     * @param itemId
     * @return
     */
    boolean ifFavourite(Integer userId,Integer feedId,String itemId);
}
