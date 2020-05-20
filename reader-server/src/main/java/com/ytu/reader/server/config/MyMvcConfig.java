package com.ytu.reader.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: rssreader
 * @description: Mvc配置类
 * @author: LiuTeng
 * @create: 2020-05-19 12:38
 **/
@Configuration
public class MyMvcConfig {
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/file/**")
                        .addResourceLocations("file:file/");
            }
        };
        return webMvcConfigurer;
    }
}
