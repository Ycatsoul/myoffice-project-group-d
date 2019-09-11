package com.capgemini.cn.deemo.controller;

import com.capgemini.cn.deemo.data.domain.Role;
import com.capgemini.cn.deemo.service.RoleService;
import com.capgemini.cn.deemo.vo.base.RespBean;
import com.capgemini.cn.deemo.vo.base.RespVos;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
