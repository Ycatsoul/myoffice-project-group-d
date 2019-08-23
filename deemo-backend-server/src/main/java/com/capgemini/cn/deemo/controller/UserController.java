package com.capgemini.cn.deemo.controller;

import com.capgemini.cn.core.commons.BaseController;
import com.capgemini.cn.deemo.data.domain.User;
import com.capgemini.cn.deemo.service.UserService;
import com.capgemini.cn.deemo.utils.CovertBeanToMapUtils;
import com.capgemini.cn.deemo.utils.PageUtils;
import com.capgemini.cn.deemo.utils.Query;
import com.capgemini.cn.deemo.vo.base.RespBean;
import com.capgemini.cn.deemo.vo.request.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author hasaker
 * @create_date 2019-08-19 22:32
 * @description
 */
@Api
@RestController
@RequestMapping("/user/employee")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ApiOperation("普通搜索以及高级搜索")
    @PostMapping("/list")
    public RespBean list(@RequestBody UserVo userVo){

        //将bean转化成map,然后用map去查询
        Map<String,Object> params = CovertBeanToMapUtils.convertBeanToMap(userVo);

        //查询列表数据
        Query query = new Query(params);

        List<User> userList = userService.queryList(query);
        int total = userService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());

        return RespBean.ok(pageUtil);
    }

    /**
     * 信息
     */
    @ApiOperation("根据id查询员工信息")
    @PostMapping("/info/{id}")
    public RespBean info(@PathVariable("id") Integer id){

        if(StringUtils.isBlank(id.toString())){
            return RespBean.error("id不能为空");
        }

        User user = userService.queryObject(id);

        return RespBean.ok(user);
    }

    /**
     * 保存
     */
    @ApiOperation("添加员工信息")
    @PostMapping("/save")
    public RespBean save(@RequestBody User user, HttpServletRequest request){

        //设置员工姓名
        this.userService.queryObject(Integer.parseInt(user.getId().toString()));
        user.setUserName(user.getUserName());


        boolean result = userService.save(user);

        //判断是否成功
        if(result){
            return RespBean.ok("添加成功");
        }
        else {
            return RespBean.error("添加失败");
        }
    }

    /**
     * 修改
     */
    @ApiOperation("修改员工信息")
    @PostMapping("/update")
    public RespBean update(@RequestBody User user,HttpServletRequest request){

        boolean result = userService.update(user);

        //判断是否成功
        if(result){
            return RespBean.ok("修改成功");
        }
        else {
            return RespBean.error("修改失败");
        }
    }

//    /**
//     * 批量删除
//     */
//    @ApiOperation("批量删除员工信息")
//    @PostMapping("/deleteBatch/{ids}")
//    public RespBean deleteBatch(@PathVariable Long[] ids,HttpServletRequest request) {
//
//        if (ids.length == 0) {
//            return RespBean.error("修改失败").put("msg", "id不能为空");
//        }
//
//        Long hrId = getHrId(request);
//        //如果id为空，则返回错误信息
//        if (StringUtils.isBlank(hrId.toString())) {
//            return Result.error().put("msg", "操作失败");
//        }
//        Hr hr = hrService.getHrById(hrId);
//        boolean result = false;
//        //循环删除
//        for (Long l : ids) {
//            TProjectEntity tProject = new TProjectEntity();
//
//            tProject.setId(l);
//
//            //设置更新时间和操作人员
//            tProject.setUpdateUser(hr.getName());
//            tProject.setUpdateTime(DateUtils.getNowDate());
//            //假删除
//            tProject.setIsDeleted("1");
//
//            result = empProjectService.update(tProject);
//        }
//
//        //判断是否成功
//        if (result) {
//            return Result.ok();
//        } else {
//            return Result.error();
//        }
//    }
    /**
     * 删除
     */
    @ApiOperation("删除员工信息")
    @PostMapping(value = "/delete/{id}")
    public RespBean delete (@PathVariable Integer id, HttpServletRequest request){

        if (StringUtils.isBlank(id.toString())) {
            return RespBean.error("删除失败");
        }

        User user = new User();

        //要删除的empId
        user.setId(id);
        //假删除
        userService.delete(id);

        return RespBean.ok("删除成功");
    }
}
