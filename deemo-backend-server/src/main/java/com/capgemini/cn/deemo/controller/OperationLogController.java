package com.capgemini.cn.deemo.controller;

import com.capgemini.cn.deemo.service.OperationLogService;
import com.capgemini.cn.deemo.vo.base.RespBean;
import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.DeleteVo;
import com.capgemini.cn.deemo.vo.request.OperationLogSearchVo;
import com.capgemini.cn.deemo.vo.response.OperationLogVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author hasaker
 * @since 2019/9/4 22:44
 */
@Api
@RestController
@RequestMapping("/operationLog")
public class OperationLogController {

    private final OperationLogService operationLogService;

    public OperationLogController(OperationLogService operationLogService) {
        this.operationLogService = operationLogService;
    }

    @ApiOperation("列出OperationLog")
    @PostMapping("/")
    public RespBean listOperationLogs(@RequestBody OperationLogSearchVo operationLogSearchVo) {
        RespVos<OperationLogVo> respVos = operationLogService.listOperationLogs(operationLogSearchVo);

        if (respVos.getSize() > 0) {
            return RespBean.ok(respVos);
        }

        return RespBean.error("没有查询到相关信息!");
    }

    @ApiOperation("删除OperationLog")
    @PostMapping("/delete")
    public RespBean deleteOperationLogs(@RequestBody DeleteVo deleteVo) {
        Integer res = operationLogService.deleteOperationLogs(deleteVo);

        return res > 0 ? RespBean.ok("成功删除" + res + "条操作日志!") : RespBean.error("删除失败!");
    }
}
