package com.ytu.reader.server.repository;

import com.ytu.reader.server.base.BaseRepository;
import com.ytu.reader.server.bean.Feed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @program: rssreader
 * @description: 信息源表操作接口
 * @author: LiuTeng
 * @create: 2020-05-17 12:45
 **/
public interface FeedRepository extends BaseRepository<Feed,Integer> {

    /**
     * 根据关键字分页查询信息源
     * @param keyWord
     * @param pageRequest
     * @return
     */
    @Query("select feed from Feed feed where feed.title like %?1% and feed.status = 0 order by feed.follower desc ")
    Page<Feed> findByKeyWord(String keyWord, PageRequest pageRequest);

    /**
     * 获取可用信息源标题
     * @return
     */
    @Query("select feed.title from Feed feed where feed.status = 0 group by feed.title")
    List<String> findFeedTitle();

    /**
     * 获取不可用信息源标题
     * @return
     */
    @Query("select feed.title from Feed feed where feed.status = 1 group by feed.title")
    List<String> findErrorFeedTitle();

    /**
     * 通过Url查询信息源
     * @param url
     * @return
     */
    Feed findByUrl(String url);
}
