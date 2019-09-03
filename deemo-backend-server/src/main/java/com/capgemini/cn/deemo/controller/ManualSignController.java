package com.capgemini.cn.deemo.controller;

import com.capgemini.cn.deemo.service.ManualSignService;
import com.capgemini.cn.deemo.vo.base.RespBean;
import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.ManualSignEditVo;
import com.capgemini.cn.deemo.vo.request.ManualSignSearchVo;
import com.capgemini.cn.deemo.vo.response.ManualSignVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/3 16:19
 */
@Api
@RestController
@RequestMapping("/manualSign")
public class ManualSignController {

    private final ManualSignService manualSignService;

    public ManualSignController(ManualSignService manualSignService) {
        this.manualSignService = manualSignService;
    }

    @ApiOperation("获取签到详尽信息")
    @GetMapping("/{manualSignId}")
    public RespBean getManualSign(@PathVariable Long manualSignId){
        RespVos<ManualSignVo> respVos = manualSignService.getManualSign(manualSignId);

        if (respVos != null && respVos.getSize() > 0) {
            return RespBean.ok("查询成功",respVos);
        }

        return RespBean.error("查询失败! 未找到相关信息!");
    }

    @ApiOperation("获取签到列表")
    @PostMapping("/list")
    public RespBean listManualSigns(@RequestBody ManualSignSearchVo manualSignSearchVo){
        RespVos<ManualSignVo> respVos = manualSignService.listManualSigns(manualSignSearchVo);

        if (respVos != null && respVos.getSize() > 0) {
            return RespBean.ok("查询成功",respVos);
        }

        return RespBean.error("查询失败! 未找到相关信息!");
    }

    @ApiOperation("添加一个签到")
    @PostMapping("/add")
    public RespBean addManualSign(@RequestBody ManualSignEditVo manualSignEditVo) {
        Integer res = manualSignService.addManualSign(manualSignEditVo);

        return res > 0 ? RespBean.ok("添加成功!") : RespBean.error("添加失败! 未找到相关信息!");
    }

    @ApiOperation("修改签到信息")
    @PutMapping("/update")
    public RespBean updateManualSign(@RequestBody ManualSignEditVo manualSignEditVo) {
        Integer res = manualSignService.updateManualSign(manualSignEditVo);

        return res > 0 ? RespBean.ok("修改成功!") : RespBean.error("修改失败! 未找到相关信息!");
    }

    @ApiOperation("删除签到")
    @PostMapping("/delete")
    public RespBean deleteManualSigns(@RequestBody List<Long> manualSignIds) {
        Integer res = manualSignService.deleteManualSigns(manualSignIds);

        return res > 0 ? RespBean.ok("删除成功!") : RespBean.error("删除失败! 未找到相关信息!");
    }
}
