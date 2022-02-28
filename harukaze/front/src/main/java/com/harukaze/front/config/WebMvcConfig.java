package com.harukaze.front.config;

import com.harukaze.front.intercepter.LocalThreadIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @PackageName: com.harukaze.front.config
 * @ClassName: WebMvcConfig
 * @Description:
 * @Author: doki
 * @Date: 20/12/2021 12:42 PM
 */
//@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    LocalThreadIntercepter localThreadIntercepter;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("*")
                .maxAge(30 * 1000);
//                .allowCredentials(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localThreadIntercepter)
                .addPathPatterns("/admin/**");
    }
}
