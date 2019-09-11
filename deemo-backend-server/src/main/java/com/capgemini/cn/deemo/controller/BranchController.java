package com.capgemini.cn.deemo.controller;

import com.capgemini.cn.core.commons.BaseController;
import com.capgemini.cn.deemo.annotation.ControllerLog;
import com.capgemini.cn.deemo.service.BranchService;
import com.capgemini.cn.deemo.vo.base.RespBean;
import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.BranchEditVo;
import com.capgemini.cn.deemo.vo.request.BranchSearchVo;
import com.capgemini.cn.deemo.vo.request.DeleteVo;
import com.capgemini.cn.deemo.vo.response.BranchVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
@Api
@RestController
@RequestMapping("/branch")
public class BranchController extends BaseController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @ApiOperation("根据id查询机构信息")
    @GetMapping("/{branchId}")
    public RespBean getBranch(@PathVariable("branchId") Long branchId){
        RespVos<BranchVo> respVos = branchService.getBranch(branchId);

        if (respVos != null) {
            return RespBean.ok("成功!", respVos);
        }

        return RespBean.error("失败!");
    }

    @ApiOperation("显示Branch列表")
    @PostMapping("/list")
    public RespBean listBranches(@RequestBody BranchSearchVo branchSearchVo){
        RespVos<BranchVo> respVos = branchService.listBranches(branchSearchVo);

        if (respVos != null) {
            return RespBean.ok("查询成功!", respVos);
        }

        return RespBean.error("查询失败！");
    }

    @ControllerLog(name = "添加Branch")
    @ApiOperation("添加Branch信息")
    @PostMapping("/")
    public RespBean addBranch(@RequestBody BranchEditVo branchEditVo){

        return branchService.addBranch(branchEditVo) > 0 ? RespBean.ok("添加成功!") : RespBean.error("添加失败!");
    }

    @ControllerLog(name = "修改Branch")
    @ApiOperation("修改Branch信息")
    @PutMapping("/")
    public RespBean updateBranch(@RequestBody BranchEditVo branchEditVo){

        return branchService.updateBranch(branchEditVo) > 0 ? RespBean.ok("编辑成功!") : RespBean.error("编辑失败!");
    }

    @ControllerLog(name = "删除Branch")
    @ApiOperation("删除Branch信息")
    @PostMapping("/delete")
    public RespBean deleteBranches(@RequestBody DeleteVo deleteVo){

        return branchService.deleteBranches(deleteVo) > 0 ? RespBean.ok("删除成功!") : RespBean.error("删除失败!");
    }
}
