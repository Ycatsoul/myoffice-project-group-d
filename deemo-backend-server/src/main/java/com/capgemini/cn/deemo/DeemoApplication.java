package com.capgemini.cn.deemo;


import com.capgemini.cn.core.commons.BaseController;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author hasaker
 * @create_date 2019-08-19 16:22
 * @description
 */

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.capgemini.cn.deemo.mapper")
public class DeemoApplication extends BaseController {
    public static void main(String[] args) {
        SpringApplication.run(DeemoApplication.class, args);
    }
}
