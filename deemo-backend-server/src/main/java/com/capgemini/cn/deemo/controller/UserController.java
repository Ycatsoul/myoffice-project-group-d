package com.capgemini.cn.deemo.controller;

import com.capgemini.cn.core.commons.BaseController;
import com.capgemini.cn.deemo.service.UserService;
import com.capgemini.cn.deemo.vo.base.RespBean;
import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.UserEditVo;
import com.capgemini.cn.deemo.vo.request.UserSearchVo;
import com.capgemini.cn.deemo.vo.response.BraDepUserVo;
import com.capgemini.cn.deemo.vo.response.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hasaker
 * @create_date 2019-08-19 22:32
 * @description
 */
@Slf4j
@Api
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("查询用户")
    @GetMapping("/{userId}")
    public RespBean getUser(@PathVariable Long userId) {
        RespVos<UserVo> respVos = userService.getUser(userId);

        if (respVos != null && respVos.getSize() == 1) {
            return RespBean.ok("查询成功!", respVos);
        }

        return RespBean.error("查询失败!");
    }

    @ApiOperation("获取User列表")
    @PostMapping("/list")
    public RespBean listUsers(@RequestBody UserSearchVo userSearchVo){
        RespVos<UserVo> respVos = userService.listUsers(userSearchVo);

        if (respVos != null && respVos.getSize() > 0) {
            return RespBean.ok("查询成功!", respVos);
        }

        return RespBean.error("查询失败！");
    }

    @ApiOperation("获取机构-部门-用户树")
    @GetMapping("/braDepUserTree")
    public RespBean getBraDepUserTree() {
        RespVos<BraDepUserVo> respVos = userService.getBraDepUserTree();

        if (respVos != null && respVos.getSize() > 0) {
            return RespBean.ok("查询成功!", respVos);
        }

        return RespBean.error("查询失败！");
    }

    @ApiOperation("添加User")
    @PostMapping("/add")
    public RespBean addUser(@RequestBody UserEditVo userEditVo) {
        Integer res = userService.addUser(userEditVo);

        return res > 0 ? RespBean.ok("添加成功!") : RespBean.error("添加失败!");
    }

    @ApiOperation("更新User信息")
    @PutMapping("/update")
    public RespBean updateUser(@RequestBody UserEditVo userEditVo) {
        Integer res = userService.updateUser(userEditVo);

        return res > 0 ? RespBean.ok("更新成功!") : RespBean.error("更新失败!");
    }

    @ApiOperation("屏蔽User")
    @PutMapping("/block")
    public RespBean blockUsers(@RequestBody List<Long> userIds) {
        Integer res = userService.blockUsers(userIds);

        return res > 0 ? RespBean.ok("成功屏蔽" + res + "个用户!") : RespBean.error("屏蔽失败!");
    }

    @ApiOperation("删除User")
    @PostMapping("/delete")
    public RespBean deleteUsers(@RequestBody List<Long> userIds) {
        Integer res = userService.deleteUsers(userIds);

        return res > 0 ? RespBean.ok("成功删除" + res + "个用户!") : RespBean.error("删除失败!");
    }
}
