package com.capgemini.cn.deemo.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author hasaker
 * @since 2019/9/9 12:19
 */
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MenuMapperTest.class)
@MapperScan("com.capgemini.cn.deemo.mapper")
public class MenuMapperTest {

    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void addMenu() {

        String[] menuUrl = new String[]{
                "/",
                "/",
                "/branch/**",
                "/department/**",
                "/fileInfo/**",
                "/loginLog/**",
                "/manualSign/**",
                "/message/**",
                "/messageType/**",
                "/operationLog/**",
                "/role/**",
                "/user/**"
        };
    }
}
