package com.capgemini.cn.deemo.controller;

import com.capgemini.cn.deemo.annotation.ControllerLog;
import com.capgemini.cn.deemo.service.ManualSignService;
import com.capgemini.cn.deemo.vo.base.RespBean;
import com.capgemini.cn.deemo.vo.request.ManualSignEditVo;
import com.capgemini.cn.deemo.vo.response.ManualSignVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hasaker
 * @since 2019/9/12 00:14
 */
@Api
@RestController
@RequestMapping("/manualSignUser")
public class ManualSignUserController {

    private final ManualSignService manualSignService;

    public ManualSignUserController(ManualSignService manualSignService) {
        this.manualSignService = manualSignService;
    }

    @ControllerLog(name = "签到")
    @ApiOperation("添加一个签到")
    @PostMapping("/")
    public RespBean addManualSign(@RequestBody ManualSignEditVo manualSignEditVo) {
        ManualSignVo manualSignVo = manualSignService.addManualSign(manualSignEditVo);

        return manualSignEditVo != null ? RespBean.ok("添加成功!", manualSignVo) : RespBean.error("添加失败! 未找到相关信息!");
    }
}
