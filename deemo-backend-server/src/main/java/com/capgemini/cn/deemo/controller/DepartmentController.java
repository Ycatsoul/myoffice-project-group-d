package com.capgemini.cn.deemo.controller;

import com.capgemini.cn.core.commons.BaseController;
import com.capgemini.cn.deemo.service.DepartmentService;
import com.capgemini.cn.deemo.vo.base.RespBean;
import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.DeleteVo;
import com.capgemini.cn.deemo.vo.request.DepartmentEditVo;
import com.capgemini.cn.deemo.vo.request.DepartmentSearchVo;
import com.capgemini.cn.deemo.vo.response.DepartmentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:部门管理controller
 * @Classname :DepartmentController
 * @author: GuoBingjun
 * @date:2019-8-26 14:10
 */
@Api
@RestController
@RequestMapping("/department")
public class DepartmentController extends BaseController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @ApiOperation("获取部门详尽信息")
    @GetMapping("/{departmentId}")
    public RespBean getDepartment(@PathVariable Long departmentId){
        RespVos<DepartmentVo> respVos = departmentService.getDepartment(departmentId);

        if (respVos != null && respVos.getSize() > 0) {
            return RespBean.ok("查询成功",respVos);
        }

        return RespBean.error("查询失败！");
    }

    @ApiOperation("获取部门列表")
    @PostMapping("/list")
    public RespBean listDepartments(@RequestBody DepartmentSearchVo departmentSearchVo){
        RespVos<DepartmentVo> respVos = departmentService.listDepartments(departmentSearchVo);

        if (respVos != null && respVos.getSize() > 0) {
            return RespBean.ok("查询成功",respVos);
        }

        return RespBean.error("查询失败！");
    }

    @ApiOperation("添加一个部门")
    @PostMapping("/")
    public RespBean addDepartment(@RequestBody DepartmentEditVo departmentEditVo) {
        Integer res = departmentService.addDepartment(departmentEditVo);

        return res > 0 ? RespBean.ok("添加成功!") : RespBean.error("添加失败!");
    }

    @ApiOperation("修改部门信息")
    @PutMapping("/")
    public RespBean updateDepartment(@RequestBody DepartmentEditVo departmentEditVo) {
        Integer res = departmentService.updateDepartment(departmentEditVo);

        return res > 0 ? RespBean.ok("修改成功!") : RespBean.error("修改失败!");
    }

    @ApiOperation("删除部门")
    @PostMapping("/delete")
    public RespBean deleteDepartments(@RequestBody DeleteVo deleteVo) {
        Integer res = departmentService.deleteDepartments(deleteVo);

        return res > 0 ? RespBean.ok("删除成功!") : RespBean.error("删除失败!");
    }
}
