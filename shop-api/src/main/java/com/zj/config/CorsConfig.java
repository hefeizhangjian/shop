package com.zj.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author 张建 119855181@qq.com
 * @version V1.0
 * @Package com.zj.config
 * @date 2020/4/20 23:35
 * @Copyright © 安徽华泓信息技术有限公司
 * @description: 跨域配置文件
 */
@Configuration
public class CorsConfig {
    public CorsConfig() {
    }
    @Bean
    public CorsFilter corsFilter(){
        //1.添加cors配置信息
        CorsConfiguration configuration=new CorsConfiguration();
        //设置准入的url
        configuration.addAllowedOrigin("http://localhost:8080");
        //设置是否发送cookie
        configuration.setAllowCredentials(true);
        //设置允许请求的方式
        configuration.addAllowedMethod("*");
        //设置允许的header
        configuration.addAllowedHeader("*");
        //2.为URL添加映射路径
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        //3.返回重新定义好的corsSource
        return new CorsFilter(source);
    }
}
