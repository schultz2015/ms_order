package com.example.springboot.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 跨域请求配置
 */
@Configuration
public class CorsConfiguration {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")//addMapping 添加可跨域的请求地址

                        //是否开启cookie跨域
                        .allowCredentials(false)

                        //规定能够跨域访问的方法类型
                        .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                        //设置跨域 域名权限 规定由某一个指定的域名+端口能访问跨域项目
                        .allowedOrigins("*");
            }
        };
    }

}