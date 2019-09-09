package com.capgemini.cn.deemo.controller;

import com.capgemini.cn.deemo.service.UserService;
import com.capgemini.cn.deemo.vo.base.RespBean;
import com.capgemini.cn.deemo.vo.request.UserEditVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hasaker
 * @since 2019/9/9 10:07
 */
@Api
@RestController
public class LoginRegController {

    private final UserService userService;

    public LoginRegController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("跳转登录页")
    @RequestMapping("/login_page")
    public RespBean login() {
        return RespBean.error("尚未登录, 请先登录!");
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public RespBean register(@RequestBody UserEditVo userEditVo) {
        Integer res = userService.addUser(userEditVo);

        if (res == -1) {
            return RespBean.error("用户名已存在!");
        }

        return res == 1 ? RespBean.ok("注册成功!") : RespBean.error("注册失败!");
    }
}
