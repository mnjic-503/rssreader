package com.ytu.reader.server.service.impl;

import com.ytu.reader.server.base.BaseServiceImpl;
import com.ytu.reader.server.bean.UserFeed;
import com.ytu.reader.server.bean.UserFeedKey;
import com.ytu.reader.server.repository.UserFeedRepository;
import com.ytu.reader.server.service.UserFeedService;
import org.springframework.stereotype.Service;

/**
 * @program: rssreader
 * @description: 用户订阅信息源实现类
 * @author: LiuTeng
 * @create: 2020-05-18 10:13
 **/
@Service("userFeedService")
public class UserFeedServiceImpl extends BaseServiceImpl<UserFeed, UserFeedKey, UserFeedRepository>
        implements UserFeedService {

}
