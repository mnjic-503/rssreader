package com.ytu.reader.server.repository;

import com.ytu.reader.server.base.BaseRepository;
import com.ytu.reader.server.bean.UserFeed;
import com.ytu.reader.server.bean.UserFeedKey;

/**
 * @program: rssreader
 * @description: 用户订阅信息源操作接口
 * @author: LiuTeng
 * @create: 2020-05-18 09:39
 **/
public interface UserFeedRepository extends BaseRepository<UserFeed, UserFeedKey> {
}
