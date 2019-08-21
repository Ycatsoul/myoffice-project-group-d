package com.capgemini.cn.deemo;

import com.capgemini.cn.core.commons.BaseBootApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;

/**
 * @author hasaker
 * @since 2019-08-19 16:22
 *
 */
@Slf4j
public class DeemoApplication extends BaseBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeemoApplication.class, args);
    }
}
