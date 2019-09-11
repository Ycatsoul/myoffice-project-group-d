package com.capgemini.cn.deemo.controller;

import com.capgemini.cn.deemo.annotation.ControllerLog;
import com.capgemini.cn.deemo.service.LoginLogService;
import com.capgemini.cn.deemo.vo.base.RespBean;
import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.DeleteVo;
import com.capgemini.cn.deemo.vo.request.LoginLogSearchVo;
import com.capgemini.cn.deemo.vo.response.LoginLogVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author hasaker
 * @since 2019/9/4 22:38
 */
@Api
@RestController
@RequestMapping("/loginLog")
public class LoginLogController {
    private final LoginLogService loginLogService;

    public LoginLogController(LoginLogService loginLogService) {
        this.loginLogService = loginLogService;
    }

    @ApiOperation("列出LoginLog")
    @PostMapping("/")
    public RespBean listLoginLogs(@RequestBody LoginLogSearchVo loginLogSearchVo) {
        RespVos<LoginLogVo> respVos = loginLogService.listLoginLogs(loginLogSearchVo);

        if (respVos.getSize() > 0) {
            return RespBean.ok(respVos);
        }

        return RespBean.error("没有查询到相关信息!");
    }

    @ControllerLog(name = "删除登录日志")
    @ApiOperation("删除LoginLog")
    @PostMapping("/delete")
    public RespBean deleteLoginLogs(@RequestBody DeleteVo deleteVo) {
        Integer res = loginLogService.deleteLoginLogs(deleteVo);

        return res > 0 ? RespBean.ok("成功删除" + res + "条登录日志!") : RespBean.error("删除失败!");
    }
}
