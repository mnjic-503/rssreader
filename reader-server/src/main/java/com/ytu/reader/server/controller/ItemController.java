package com.ytu.reader.server.controller;

import com.ytu.reader.server.bean.Feed;
import com.ytu.reader.server.bean.Item;
import com.ytu.reader.server.service.ItemService;
import com.ytu.reader.server.utils.Result;
import com.ytu.reader.server.utils.SingleEntriesFeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @program: rssreader
 * @description: 文章Controller类
 * @author: LiuTeng
 * @create: 2020-05-18 12:17
 **/
@Controller
public class ItemController {
    @Autowired
    ItemService itemService;

    /**
     * 获取自定义信息源的文章
     * @param feed
     * @param pageNo
     * @return
     */
    @PostMapping("/customize/feed/items/{pageNo}")
    @ResponseBody
    public Result getItemsByUrl(@RequestBody Feed feed,
                                @PathVariable("pageNo")Integer pageNo,
                                HttpSession session) throws IOException {
        List<Item> items;
        //自定义信息源查询文章
        if (pageNo != 0){
            items = (List<Item>) session.getAttribute("customize_feed_items");
        }else {
            SingleEntriesFeed singleEntriesFeed = new SingleEntriesFeed(feed.getUrl());
            items = singleEntriesFeed.createFeeds();
            if (items != null){
                session.setAttribute("customize_feed_items",items);
            }
        }
        if (items == null){
            return Result.error("数据错误");
        }
        if (pageNo*10 > items.size()){
            return Result.ok();
        }
        if (pageNo*10+10 > items.size()){
            return Result.ok().put("data",items.subList(pageNo*10,items.size()));
        }else {
            return Result.ok().put("data",items.subList(pageNo*10,pageNo*10+10));
        }
    }

    /**
     * 获取自定义信息源的文章
     * @param id
     * @param model
     * @param session
     * @return
     */
    @GetMapping(value = "/customize/item/{id}")
    public String getCustomizeItemById(@PathVariable("id")Integer id,Model model,HttpSession session){
        List<Item> items = (List<Item>) session.getAttribute("customize_feed_items");
        Item item = new Item();
        if (items != null && id < items.size()){
            item = items.get(id);
        }
        if (item == null) {
            model.addAttribute("msg", "连接访问出错");
            return "item/item";
        }
        model.addAttribute("msg", "访问成功");
        model.addAttribute("item", item);
        return "item/item";
    }

    /**
     * 根据ID查询文章
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/item/{id}")
    public String getItemById(@PathVariable("id")String id, Model model){
        Item dbItem = itemService.findOne(id);
        if (dbItem == null){
            model.addAttribute("msg","链接访问出错");
            return "/item/item";
        }
        model.addAttribute("msg","访问成功");
        model.addAttribute("item",dbItem);
        return "/item/item";
    }

    /**
     *分页查询信息源的文章
     * @param feedId
     * @param pageNo
     * @return
     */
    @GetMapping("/feed/{feedId}/items/{pageNo}")
    @ResponseBody
    public Result getItemsByFeedId(@PathVariable Integer feedId, @PathVariable Integer pageNo,
                                   @RequestParam("userId")Integer userId){
        List<Item> items = itemService.findItemsByFeedId(feedId, pageNo);
        for (Item item : items){
            if (itemService.ifFavourite(userId,feedId,item.getId())){
                item.setFavourite(1);
            }else {
                item.setFavourite(0);
            }
        }
        return Result.ok().put("data",items);
    }

    @GetMapping("/item/{keyword}/{pageNo}")
    @ResponseBody
    public Result getItemsByKeyword(@PathVariable String keyword, @PathVariable Integer pageNo,
                                    @RequestParam("userId")Integer userId){
        List<Item> items = itemService.findItemsByKeyword(keyword, pageNo);
        for (Item item : items){
            if (itemService.ifFavourite(userId,item.getFeed().getId(),item.getId())){
                item.setFavourite(1);
            }else {
                item.setFavourite(0);
            }
        }
        return Result.ok().put("data",items);
    }

}
