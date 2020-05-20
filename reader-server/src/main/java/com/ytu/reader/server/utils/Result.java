package com.ytu.reader.server.utils;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: rssreader
 * @description: 返回数据
 * @author: LiuTeng
 * @create: 2020-05-16 20:48
 **/
public class Result extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public Result(){
        put("code",0);
        put("msg","success");
    }

    public static Result error(){
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(),"未知异常请联系管理员");
    }
    public static Result error(String msg){
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }
    public static Result error(int code, String msg){
        Result result = new Result();
        result.put("code",code);
        result.put("msg",msg);
        return result;
    }

    public static Result ok(String msg){
        Result result = new Result();
        result.put("msg", msg);
        return result;
    }

    public static Result ok(Map<String, Object> map){
        Result result = new Result();
        result.putAll(map);
        return result;
    }
    public static Result ok(){
        return new Result();
    }

    @Override
    public Result put(String key, Object value){
        super.put(key,value);
        return this;
    }
}
