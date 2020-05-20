package com.ytu.reader.server.service;

import com.ytu.reader.server.base.BaseService;
import com.ytu.reader.server.bean.Feed;
import com.ytu.reader.server.repository.FeedRepository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @program: rssreader
 * @description: 信息源Service接口
 * @author: LiuTeng
 * @create: 2020-05-17 12:43
 **/
public interface FeedService extends BaseService<Feed, Integer, FeedRepository> {
    /**
     * 判断是否已经订阅
     * @param userId
     * @param feedId
     * @return
     */
    boolean ifSubscribe(Integer userId,Integer feedId);

    /**
     * 根据Url查询Feed
     * @param url
     * @return
     */
    Feed findFeedByUrl(String url);

    /**
     * 保存信息源标志
     * @param fileName
     * @param file
     * @return
     */
    boolean uploadIcon(String fileName, MultipartFile file);

    /**
     * 获取信息源标题
     * @return
     */
    List<String> getFeedTitles();

    /**
     * 根据关键字查询信息源
     * @param keyword
     * @param pageNo
     * @return
     */
    List<Feed> findFeedByKeyword(String keyword,Integer pageNo);

}
