package com.kd.springcloud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author conghuhu
 * @create 2021-10-08 11:07
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {



    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 跨域配置
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "http://124.222.60.243/","http://localhost:3001","http://124.222.224.173/","http://124.222.66.170/","http://139.224.190.128/")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }


}
