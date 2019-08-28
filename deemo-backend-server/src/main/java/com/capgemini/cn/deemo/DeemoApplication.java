package com.capgemini.cn.deemo;

import com.capgemini.cn.core.commons.BaseBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

/**
 * @author hasaker
 * @since 2019-08-19 16:22
 *
 */
@MapperScan("com.capgemini.cn.deemo.mapper")
public class DeemoApplication extends BaseBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeemoApplication.class, args);
    }
}
