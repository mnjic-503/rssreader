package com.ytu.reader.server.controller;

import com.ytu.reader.server.bean.Feed;
import com.ytu.reader.server.bean.User;
import com.ytu.reader.server.bean.UserFeed;
import com.ytu.reader.server.bean.UserFeedKey;
import com.ytu.reader.server.service.FeedService;
import com.ytu.reader.server.service.UserFeedService;
import com.ytu.reader.server.service.UserService;
import com.ytu.reader.server.utils.CommonUtils;
import com.ytu.reader.server.utils.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;


/**
 * @program: rssreader
 * @description: 信息源Controller类
 * @author: LiuTeng
 * @create: 2020-05-18 10:10
 **/
@RestController
public class FeedController {
    @Autowired
    FeedService feedService;
    @Autowired
    UserService userService;
    @Autowired
    UserFeedService userFeedService;

    /**
     * 查询信息源
     * @param id
     * @return
     */
    @GetMapping("/feed/{id}")
    public Result get(@PathVariable("id")Integer id){
        Feed feed = feedService.findOne(id);
        if (feed == null){
            return Result.error("未找到该信息源");
        }else {
            return Result.ok().put("data",feed);
        }
    }

    /**
     * 添加信息源
     * @param feed
     * @return
     */
    @PostMapping("/feed")
    public Result save(@RequestBody Feed feed){
        //判断链接是否存在
        Feed feedByUrl = feedService.findFeedByUrl(feed.getUrl());
        if (feedByUrl != null){
            return Result.error("该链接已存在");
        }
        //信息源标志地址
        String fileName = feed.getUrl()+".jpg";
        feed.setIcon("/file/upload/feedlogo/"+fileName);
        feed.init();
        feedService.saveOne(feed);
        return Result.ok().put("data",feed);
    }

    /**
     * 上传信息源标志
     * @param file
     * @param url
     * @return
     */
    @PostMapping("/feed/icon")
    public Result uploadIcon(@RequestParam("file")MultipartFile file,
                             @RequestParam("url")String url){
        String fileName = url+".jpg";
        if (feedService.uploadIcon(fileName,file)){
            return Result.ok("标志保存成功");
        }else {
            return Result.error("标志保存失败");
        }
    }

    /**
     * 修改信息源
     * @param id
     * @param feed
     * @return
     */
    @PutMapping("/feed/{id}")
    public Result update(@PathVariable("id")Integer id,
                         @RequestBody Feed feed){
        Feed dbFeed = feedService.findOne(id);
        if (dbFeed == null){
            return Result.error("数据出错");
        }
        BeanUtils.copyProperties(dbFeed,feed, CommonUtils.getNullPropertyNames(feed));
        //TODO 判断信息源链接是否重复
        return Result.ok().put("data",feed);
    }

    @DeleteMapping("/feed/{id}")
    public Result delete(@PathVariable("id")Integer id){
        userService.deleteById(id);
        return Result.ok("删除成功");
    }

    /**
     * 查询用户的订阅的所有信息源
     * @param userId
     * @return
     */
    @GetMapping("/user/{userId}/feeds")
    public Result getUserFeeds(@PathVariable Integer userId){
        User dbUser = userService.findOne(userId);
        if (dbUser == null){
            return Result.error("用户不存在");
        }
        //查询该用户的所有信息源
        UserFeed probe = new UserFeed(userId);
        Example<UserFeed> example = Example.of(probe);
        List<UserFeed> userFeeds = userFeedService.findAll(example);
        for (UserFeed userFeed : userFeeds){
            userFeed.getFeed().setSubscribe(1);
        }
        return Result.ok().put("data",userFeeds);
    }

    /**
     * 用户订阅信息源
     * @param feedId
     * @param userId
     * @return
     */
    @PostMapping("/user/feed/{feedId}")
    public Result addUserFeed(@PathVariable Integer feedId,@RequestParam("userId")Integer userId){
        // 用户订阅信息源
        UserFeed dbUserFeed = userFeedService.findOne(new UserFeedKey(userId,feedId));
        if (dbUserFeed != null){
            return Result.error("已经订阅过该数据源");
        }
        UserFeed userFeed = new UserFeed(userId, feedId, new Date());
        userFeedService.saveOne(userFeed);
        User dbUser = userService.findOne(userId);
        dbUser.setFeedNumber(dbUser.getFeedNumber()+1);
        userService.saveOne(dbUser);
        return Result.ok("已订阅");
    }

    /**
     * 删除用户订阅信息源
     * @param feedId
     * @param userId
     * @return
     */
    @DeleteMapping("/user/feed/{feedId}")
    public Result deleteUserFeed(@PathVariable Integer feedId,@RequestParam("userId")Integer userId){
        UserFeedKey userFeedKey = new UserFeedKey(userId, feedId);
        UserFeed dbUserFeed = userFeedService.findOne(userFeedKey);
        if (dbUserFeed == null){
            return Result.error("删除失败");
        }
        userFeedService.deleteById(userFeedKey);
        User dbUser = userService.findOne(userId);
        dbUser.setFeedNumber(dbUser.getFeedNumber()-1);
        userService.saveOne(dbUser);
        return Result.ok("已删除");
    }

    @GetMapping("/feed/titles")
    public Result getFeedTitles(){
        List<String> feedTitles = feedService.getFeedTitles();
        return Result.ok().put("data",feedTitles);
    }

    @GetMapping("/feed/{keyword}/{pageNo}")
    public Result findFeedByKeyword(@PathVariable String keyword,@PathVariable Integer pageNo,
                                    @RequestParam("userId")Integer userId){
        List<Feed> feeds;
        if ("all".equals(keyword)){
            feeds = feedService.findAll();
        }else {
            feeds = feedService.findFeedByKeyword(keyword,pageNo);
        }
        User dbUser = userService.findOne(userId);
        if(dbUser == null){
            return Result.error("数据错误");
        }
        //查询该用户的所有信息源
        UserFeed probe = new UserFeed(userId);
        Example<UserFeed> example = Example.of(probe);
        List<UserFeed> userFeeds = userFeedService.findAll(example);
        if (feeds != null){
            feeds = isSubscribe(feeds,userFeeds);
            //重新排序
            feeds.sort((o1, o2) -> {
                Integer f1 = o1.getId();
                Integer f2 = o2.getId();
                return f1.compareTo(f2);
            });
        }
        return Result.ok().put("data",feeds);
    }




    /**
     * 判断是否订阅信息源
     * @param feeds
     * @param userFeeds
     * @return
     */
    private List<Feed> isSubscribe(List<Feed> feeds,List<UserFeed> userFeeds){
        List<Feed> result = new ArrayList<>();
        Integer isSubscribe = 1;
        Integer notSubscribe = 0;
        Map<Feed,Integer> map = new HashMap<Feed, Integer>(feeds.size()+userFeeds.size());
        for (Feed feed : feeds){
            map.put(feed,notSubscribe);
        }
        for (UserFeed userFeed : userFeeds){
            Integer count = map.get(userFeed.getFeed());
            if (count != null){
                map.put(userFeed.getFeed(),++count);
            }
        }
        for (Map.Entry<Feed,Integer> entry : map.entrySet()){
            Feed feed = entry.getKey();
            if (!entry.getValue().equals(notSubscribe)){
                feed.setSubscribe(isSubscribe);
            }else {
                feed.setSubscribe(notSubscribe);
            }result.add(feed);
        }
        return result;
    }

}
