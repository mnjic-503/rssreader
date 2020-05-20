package com.ytu.reader.server;

import com.ytu.reader.server.bean.*;
import com.ytu.reader.server.repository.FeedRepository;
import com.ytu.reader.server.repository.ItemFavouriteRepository;
import com.ytu.reader.server.repository.UserFeedRepository;
import com.ytu.reader.server.service.ItemService;
import com.ytu.reader.server.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class ReaderServerApplicationTests {
    @Autowired
    UserService userService;
    @Autowired
    FeedRepository feedRepository;
    @Autowired
    ItemFavouriteRepository itemFavouriteRepository;
    @Autowired
    ItemService itemService;
    @Autowired
    UserFeedRepository userFeedRepository;

    @Transactional
    @Test
    void contextLoads() {
        User user = userService.findOne(26);
        System.out.println(user);
    }

    @Test
    void getFeedTitle(){
        List<String> feedTitle = feedRepository.findFeedTitle();
        System.out.println(feedTitle);
    }

    @Test
    void getItemsByFeedId(){

        List<Item> items = itemService.findItemsByFeedId(1, 0);
        for (Item item:items){
            System.out.println(item.getTitle());
        }
    }

    @Test
    public void changeFeedIcon(){
        List<Feed> feeds = feedRepository.findAll();
        for (Feed feed : feeds){
            String fileName = feed.getUrl()+".jpg";
            feed.setIcon("/file/upload/feedlogo/"+fileName);
            feedRepository.save(feed);
        }
    }

    @Test
    public void getUserFeed(){
        Optional<UserFeed> byId = userFeedRepository.findById(new UserFeedKey(26, 1));
        System.out.println(byId.get());
    }

}
