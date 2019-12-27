package com.atguigu.guli.service.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lzc
 * @create 2019-12-26 16:08
 */
@SpringBootApplication
@ComponentScan({"com.atguigu.guli"})    //为什么使用中括号？
public class ServiceEduApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceEduApplication.class, args);
    }
}
