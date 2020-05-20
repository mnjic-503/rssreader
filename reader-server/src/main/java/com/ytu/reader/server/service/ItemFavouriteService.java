package com.ytu.reader.server.service;

import com.ytu.reader.server.base.BaseService;
import com.ytu.reader.server.base.BaseServiceImpl;
import com.ytu.reader.server.bean.ItemFavourite;
import com.ytu.reader.server.bean.ItemFavouriteKey;
import com.ytu.reader.server.repository.ItemFavouriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: rssreader
 * @description: 收藏文章Service接口
 * @author: LiuTeng
 * @create: 2020-05-18 19:09
 **/
@Service("itemFavouriteService")
public interface ItemFavouriteService extends BaseService<ItemFavourite, ItemFavouriteKey, ItemFavouriteRepository> {
    /**
     * 分页查询收藏文章
     * @param userId
     * @param pageNo
     * @return
     */
    List<ItemFavourite> findFavouritesByUserId(Integer userId,Integer pageNo);
}
