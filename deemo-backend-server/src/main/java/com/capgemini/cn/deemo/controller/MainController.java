package com.capgemini.cn.deemo.controller;

import com.capgemini.cn.core.commons.BaseController;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hasaker
 * @create_date 2019-08-19 17:52
 * @description
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("index")
public class MainController extends BaseController {

    @GetMapping("/")
    public String index() {
        return "Index";
    }
}
