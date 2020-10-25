package com.dangdang.bookmall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author zengyuzhi
 * @date 2020/10/19 下午7:02
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration();

        //配置跨域
        //允许那种请求头跨域
        corsConfiguration.addAllowedHeader("*");
        //允许哪种类型方法跨域 get post delete put
        corsConfiguration.addAllowedMethod("*");
        // 允许哪些请求源跨域
        corsConfiguration.addAllowedOrigin("*");
        // 是否携带cookie跨域
        corsConfiguration.setAllowCredentials(true);
        //允许跨域的路径
        source.registerCorsConfiguration("/**",corsConfiguration);

        return new CorsWebFilter(source);
    }
}
