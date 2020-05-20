package com.ytu.reader.server.controller;

import com.ytu.reader.server.bean.User;
import com.ytu.reader.server.service.UserService;
import com.ytu.reader.server.utils.CommonUtils;
import com.ytu.reader.server.utils.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @program: rssreader
 * @description: 用户Controller类
 * @author: LiuTeng
 * @create: 2020-05-17 08:36
 **/
@RestController
public class UserController {
    @Autowired
    UserService userService;



    /**
     * 添加用户
     * @param user 用户信息
     * @return
     */
    @PostMapping("/user")
    public Result save(@RequestBody User user){
        //判断用户名是否存在
        User userByUserName = userService.findUserByUserName(user.getUsername());
        if (userByUserName != null){
            return Result.error("用户名已存在");
        }
        //用户头像地址
        String fileName = user.getUsername()+".jpg";
        user.setIcon("/file/upload/avatar/"+fileName);
        user.init();
        userService.saveOne(user);
        return Result.ok().put("data",user);
    }

    /**
     * 保存用户头像
     * @param file
     * @param username
     * @return
     */
    @PostMapping("/user/avatar")
    public Result uploadAvatar(@RequestParam("file")MultipartFile file,
                               @RequestParam("username")String username){
        String fileName = username+".jpg";
        if (userService.uploadAvatar(fileName,file)){
            return Result.ok("头像保存成功");
        }else {
            return Result.error("头像保存失败");
        }
    }

    /**
     * 修改用户信息
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/user/{id}")
    public Result update(@PathVariable("id")Integer id,@RequestBody User user){
        User dbUser = userService.findOne(id);
        if(dbUser == null){
            return Result.error("数据出错，请稍后再试");
        }
        BeanUtils.copyProperties(dbUser,user,CommonUtils.getNullPropertyNames(user));
        //TODO 判断用户名是否重复，并且修改信息后，头像文件名也要相应改变
        userService.updateOne(id, user);
        return Result.ok().put("data",user);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/user/{id}")
    public Result delete(@PathVariable("id")Integer id){
        userService.deleteById(id);
        return Result.ok("删除成功");
    }
}
