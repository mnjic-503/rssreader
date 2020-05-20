package com.ytu.reader.server.service;

import com.ytu.reader.server.base.BaseService;
import com.ytu.reader.server.bean.UserFeed;
import com.ytu.reader.server.bean.UserFeedKey;
import com.ytu.reader.server.repository.UserFeedRepository;

/**
 * @program: rssreader
 * @description:
 * @author: LiuTeng
 * @create: 2020-05-18 10:12
 **/
public interface UserFeedService extends BaseService<UserFeed, UserFeedKey, UserFeedRepository> {
}
