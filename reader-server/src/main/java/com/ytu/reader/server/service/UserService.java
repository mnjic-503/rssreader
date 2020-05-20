package com.ytu.reader.server.service;

import com.ytu.reader.server.base.BaseService;
import com.ytu.reader.server.bean.User;
import com.ytu.reader.server.repository.UserRepository;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: rssreader
 * @description: 用户Service接口
 * @author: LiuTeng
 * @create: 2020-05-16 21:15
 **/
public interface UserService extends BaseService<User, Integer, UserRepository> {
    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User findUserByUserName(String username);

    /**
     * 上传用户头像
     * @param fileName 文件名
     * @param file 用户头像
     * @return
     */
    boolean uploadAvatar(String fileName,MultipartFile file);
}
