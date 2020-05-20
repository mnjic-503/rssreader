package com.ytu.reader.server.repository;

import com.ytu.reader.server.base.BaseRepository;
import com.ytu.reader.server.bean.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;

/**
 * @program: rssreader
 * @description: 文章操作接口
 * @author: LiuTeng
 * @create: 2020-05-18 09:37
 **/
public interface ItemRepository extends BaseRepository<Item, String> {
    /**
     * 根据关键字查询文章
     * @param keyword
     * @param pageRequest
     * @return
     */
    @Query("select item from Item item where item.title like %?1% order by item.pubDate desc ")
    Page<Item> findByKeyword(String keyword, PageRequest pageRequest);
}
