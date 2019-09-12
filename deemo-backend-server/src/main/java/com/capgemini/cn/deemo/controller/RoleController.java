package com.capgemini.cn.deemo.controller;

import com.capgemini.cn.deemo.annotation.ControllerLog;
import com.capgemini.cn.deemo.data.domain.Role;
import com.capgemini.cn.deemo.service.RoleService;
import com.capgemini.cn.deemo.vo.base.RespBean;
import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.DeleteVo;
import com.capgemini.cn.deemo.vo.request.RoleEditVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/8 19:42
 */
@Api
@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @ApiOperation("根据roleId获取Role")
    @GetMapping("/{roleId}")
    public RespBean getRoleByRoleId(@PathVariable Long roleId) {
        Role role = roleService.getRoleByRoleId(roleId);

        if (role != null) {
            return RespBean.ok(role);
        }

        return RespBean.error("获取失败!");
    }

    @ApiOperation("获取Role列表")
    @GetMapping("/roles")
    public RespBean getRoles() {
        List<Role> roles = roleService.getRoles();
        RespVos<Role> respVos = new RespVos<>();
        respVos.setVos(roles);
        respVos.setSize(roles.size());

        return RespBean.ok(respVos);
    }

    @ControllerLog(name = "添加角色")
    @ApiOperation("添加角色")
    @PostMapping("/")
    public RespBean addRole(@RequestBody RoleEditVo roleEditVo) {
        Integer res = roleService.addRole(roleEditVo);

        return res == 1 ? RespBean.ok("添加成功!") : RespBean.error("添加失败!");
    }

    @ControllerLog(name = "编辑角色")
    @ApiOperation("编辑角色")
    @PutMapping("/")
    public RespBean updateRole(@RequestBody RoleEditVo roleEditVo) {
        Integer res = roleService.updateRole(roleEditVo);

        return res == 1 ? RespBean.ok("编辑成功!") : RespBean.error("编辑失败!");
    }

    @ControllerLog(name = "删除角色")
    @ApiOperation("删除角色")
    @PostMapping("/delete")
    public RespBean deleteRoles(@RequestBody DeleteVo deleteVo) {
        Integer res = roleService.deleteRole(deleteVo);

        return res > 0 ? RespBean.ok("删除成功!") : RespBean.error("删除失败!");
    }
}
