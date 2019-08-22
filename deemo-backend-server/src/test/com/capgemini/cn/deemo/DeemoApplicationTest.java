package com.capgemini.cn.deemo;

import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author hasaker
 * @since 2019-08-22 10:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.capgemini.cn.deemo.mapper")
public class DeemoApplicationTest {


}
