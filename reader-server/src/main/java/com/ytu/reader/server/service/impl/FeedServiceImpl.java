package com.ytu.reader.server.service.impl;

import com.ytu.reader.server.base.BaseServiceImpl;
import com.ytu.reader.server.bean.Feed;
import com.ytu.reader.server.bean.UserFeed;
import com.ytu.reader.server.bean.UserFeedKey;
import com.ytu.reader.server.repository.FeedRepository;
import com.ytu.reader.server.service.FeedService;
import com.ytu.reader.server.service.UserFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @program: rssreader
 * @description: 信息源Service实现类
 * @author: LiuTeng
 * @create: 2020-05-18 09:30
 **/
@Service("feedService")
public class FeedServiceImpl extends BaseServiceImpl<Feed, Integer, FeedRepository> implements FeedService {
    @Autowired
    UserFeedService userFeedService;

    @Override
    public boolean ifSubscribe(Integer userId, Integer feedId) {
        UserFeedKey userFeedKey = new UserFeedKey(userId,feedId);
        UserFeed dbUserFeed = userFeedService.findOne(userFeedKey);
        return dbUserFeed != null;
    }

    @Override
    public Feed findFeedByUrl(String url) {
        //TODO 判断url是否重复
        return getRepository().findByUrl(url);
    }

    @Override
    public boolean uploadIcon(String fileName, MultipartFile file) {
        //TODO 保存信息源标志
        return false;
    }

    @Override
    public List<String> getFeedTitles() {
        return getRepository().findFeedTitle();
    }

    @Override
    public List<Feed> findFeedByKeyword(String keyword, Integer pageNo) {
        Page<Feed> page = getRepository().findByKeyWord(keyword, PageRequest.of(pageNo, 10));
        return page.getContent();
    }
}
