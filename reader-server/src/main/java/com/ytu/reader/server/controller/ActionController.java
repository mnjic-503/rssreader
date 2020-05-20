package com.ytu.reader.server.controller;

import com.ytu.reader.server.bean.User;
import com.ytu.reader.server.service.UserService;
import com.ytu.reader.server.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @program: rssreader
 * @description: 普通操作的Controller
 * @author: LiuTeng
 * @create: 2020-05-17 09:20
 **/
@RestController
public class ActionController {
    @Autowired
    UserService userService;

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @param session 登录成功后保存到session中
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session){
        User user = userService.findUserByUserName(username);
        if (user == null){
            return Result.error("用户名错误");
        }
        if (!user.getPassword().equals(password)){
            return Result.error("密码错误");
        }
        session.setAttribute("user",user);
        return Result.ok().put("data",user);
    }

    /**
     * 检查用户是否登录
     * @return
     */
    @GetMapping("/check")
    public Result checkLogin(){
        return Result.error("没有操作权限，请先登录");
    }

    /**
     * 获取当前用户
     * @param session
     * @return
     */
    @GetMapping("/getcurrent")
    public Result getCurrentUser(HttpSession session){
        User user = (User) session.getAttribute("user");
        return Result.ok().put("data",user);
    }
}
