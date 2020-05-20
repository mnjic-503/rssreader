package com.ytu.reader.server.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: rssreader
 * @description: 获取文章封面工具类
 * @author: LiuTeng
 * @create: 2020-05-19 21:41
 **/
public class GetCoverUtils {
    /**
     * 得到网页中图片的地址
     */
    public static List<String> getImgStr(String htmlStr) {
        List<String> pics = new ArrayList<>();
        String img = "";
        Pattern patternImage;
        Matcher matcherImage;
        //     String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
        String imageLabel = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        patternImage = Pattern.compile
                (imageLabel, Pattern.CASE_INSENSITIVE);
        matcherImage = patternImage.matcher(htmlStr);
        while (matcherImage.find()) {
            // 得到<img />数据
            img = matcherImage.group();
            // 匹配<img>中的src数据
            String pattern = "src\\s*=\\s*\"?(.*?)(\"|>|\\s+)";
            Matcher m = Pattern.compile(pattern).matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }
}
