package com.harukaze.front.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @PackageName: com.harukaze.front.config
 * @ClassName: MybatisConfig
 * @Description:
 * @Author: doki
 * @Date: 27/11/2021 1:39 PM
 */
@MapperScan("com.harukaze.front.mapper")
@Configuration
public class MybatisConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
