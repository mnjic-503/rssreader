package com.ytu.reader.server.utils;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.ytu.reader.server.bean.Feed;
import com.ytu.reader.server.bean.Item;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: rssreader
 * @description: 信息源解析工具类
 * @author: LiuTeng
 * @create: 2020-05-19 21:41
 **/
public class SingleEntriesFeed {
    String rssUrl;
    String title;
    URL url;
    List<Item> items;
    SyndFeedInput input;
    XmlReader xmlReader;

    public SingleEntriesFeed(String rssUrl){
        this.rssUrl = rssUrl;
    }
    public List<Item> createFeeds() throws IOException {
        System.out.println("Single Entries Feed process");
        System.out.println("The Url is "+rssUrl);
        //从网络读取RSS数据
        url = new URL(rssUrl);
        input = new SyndFeedInput();
        xmlReader = new XmlReader(url);
        try {
            SyndFeed syndFeed = input.build(xmlReader);
            items = new ArrayList<>();
            title = syndFeed.getTitle();
            setTheTitles(syndFeed);
            System.out.println("Single Entries Feed success");
        } catch (FeedException e) {
            e.printStackTrace();
            System.out.println("Single Entries Feed error");
        }
        return items;
    }

    private void setTheTitles(SyndFeed syndFeed) {
        for (int i = 0;i<syndFeed.getEntries().size();i++){
            SyndEntry entry = syndFeed.getEntries().get(i);
            Item item = new Item();
            item.setId(i+"");
            item.setTitle(entry.getTitle());
            item.setPubDate(entry.getPublishedDate()==null?new Date():new Date(entry.getPublishedDate().toString()));
            item.setDescription(entry.getDescription().getValue());
            item.setFeed(new Feed(syndFeed.getTitle(),syndFeed.getLink(),syndFeed.getDescription(),null));
            //自定义信息源无法添加收藏
            item.setFavourite(2);
            List<String> covers = GetCoverUtils.getImgStr(item.getDescription());
            item.setCover(covers.size() > 0?covers.get(0):"");
            items.add(item);
        }
    }

    public String getTitle() {
        return title;
    }

}
