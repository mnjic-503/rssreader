package com.ytu.reader.server.controller;

import com.ytu.reader.server.bean.ItemFavourite;
import com.ytu.reader.server.bean.ItemFavouriteKey;
import com.ytu.reader.server.service.ItemFavouriteService;
import com.ytu.reader.server.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

/**
 * @program: rssreader
 * @description: 收藏文章Controller
 * @author: LiuTeng
 * @create: 2020-05-18 19:14
 **/
@RestController
public class ItemFavouriteController {
    @Autowired
    ItemFavouriteService itemFavouriteService;

    /**
     * 查询用户的收藏文章
     * @param userId
     * @param pageNo
     * @return
     */
    @GetMapping("user/{userId}/favourites/{pageNo}")
    public Result getFavouriteItems(@PathVariable Integer userId, @PathVariable("pageNo") Integer pageNo){
        List<ItemFavourite> favourites = itemFavouriteService.findFavouritesByUserId(userId, pageNo);
        for (ItemFavourite favourite : favourites){
            favourite.getItem().setFavourite(1);
        }
        return Result.ok().put("data",favourites);
    }
    @PostMapping("/favourite")
    public Result save(@RequestBody ItemFavouriteKey itemFavouriteKey){
        ItemFavourite dbItemFavourite = itemFavouriteService.findOne(itemFavouriteKey);
        if (dbItemFavourite != null){
            return Result.error("保存失败");
        }else {
            itemFavouriteService.saveOne(new ItemFavourite(itemFavouriteKey));
            return Result.ok("保存成功");
        }
    }

    @DeleteMapping("/favourite")
    public Result delete(@RequestBody ItemFavouriteKey itemFavouriteKey){
        ItemFavourite dbItemFavourite = itemFavouriteService.findOne(itemFavouriteKey);
        if (dbItemFavourite != null){
            itemFavouriteService.deleteById(itemFavouriteKey);
            return Result.ok("删除成功");
        }else {
            return Result.error("删除失败");
        }
    }
}
