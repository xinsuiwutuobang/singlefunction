package com.yangfei.singlefunction.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *  mybatis-plus 配置类
 * </p>
 *
 * @author yangfei
 * @since 2019/5/20
 */
@Configuration
@MapperScan("com.yangfei.singlefunction.mapper")
public class MybatisPlusConfig {
    /**
     * 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
