package com.capgemini.cn.deemo.controller;

import com.capgemini.cn.deemo.service.MenuService;
import com.capgemini.cn.deemo.vo.base.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hasaker
 * @since 2019/9/10 01:36
 */
@Api
@RestController
@RequestMapping("/system")
public class SystemController {

    private final MenuService menuService;

    public SystemController(MenuService menuService) {
        this.menuService = menuService;
    }

    @ApiOperation("获取目录树")
    @GetMapping("/menuTree")
    public RespBean getMenuTree() {

        return RespBean.ok(menuService.getMenuTree());
    }
}
