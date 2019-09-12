package com.capgemini.cn.deemo.controller;

import com.capgemini.cn.deemo.service.MenuService;
import com.capgemini.cn.deemo.service.UserRoleService;
import com.capgemini.cn.deemo.vo.base.RespBean;
import com.capgemini.cn.deemo.vo.request.UserRoleEditVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author hasaker
 * @since 2019/9/10 01:36
 */
@Api
@RestController
@RequestMapping("/system")
public class SystemController {

    private final MenuService menuService;
    private final UserRoleService userRoleService;

    public SystemController(MenuService menuService, UserRoleService userRoleService) {
        this.menuService = menuService;
        this.userRoleService = userRoleService;
    }

    @ApiOperation("获取目录树")
    @GetMapping("/menuTree")
    public RespBean getMenuTree() {

        return RespBean.ok(menuService.getMenuTree());
    }

    @ApiOperation("为用户添加权限")
    @PostMapping("/addRoleToUser")
    public RespBean addRoleToUser(UserRoleEditVo userRoleEditVo) {
        Integer res = userRoleService.addUserRole(userRoleEditVo);

        return res > 0 ? RespBean.ok("添加成功!") : RespBean.error("添加失败!");
    }

    @ApiOperation("删除用户权限")
    @DeleteMapping("/userRole/{userId}/{roleId}")
    public RespBean deleteRoleFromUser(@PathVariable Long userId, @PathVariable Long roleId) {
        Integer res = userRoleService.deleteUserRoleByUserIdAndRoleId(userId, roleId);

        return res > 0 ? RespBean.ok("删除成功!") : RespBean.error("删除失败!");
    }
}
