package com.ytu.reader.server.service.impl;

import com.ytu.reader.server.base.BaseServiceImpl;
import com.ytu.reader.server.bean.User;
import com.ytu.reader.server.repository.UserRepository;
import com.ytu.reader.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @program: rssreader
 * @description: 用户Service实现类
 * @author: LiuTeng
 * @create: 2020-05-17 08:34
 **/
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User, Integer, UserRepository> implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User findUserByUserName(String username) {
        User user = userRepository.findUserByUserName(username, 0);
        return user;
    }

    @Override
    public boolean uploadAvatar(String fileName, MultipartFile file) {
        if(file.isEmpty()){
            return false;
        }
        try{
            //上传到/static/upload/avatar文件中，打包成jar文件会有错误
            //参照https://blog.csdn.net/wanping321/article/details/83246818
            //uploadPath = ResourceUtils.getURL("classpath:").getPath()+"/static/upload/avatar/";
            String uploadPath = "file/upload/avatar";
            File dir = new File(uploadPath);
            if (!dir.exists()){
                dir.mkdirs();
            }
            File avatar = new File(dir.getAbsolutePath()+"/"+fileName);
            file.transferTo(avatar);
            return true;
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }
}
