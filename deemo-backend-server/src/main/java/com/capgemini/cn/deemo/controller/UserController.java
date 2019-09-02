package com.capgemini.cn.deemo.controller;

import com.capgemini.cn.core.commons.BaseController;
import com.capgemini.cn.deemo.data.domain.User;
import com.capgemini.cn.deemo.data.dto.BranchDto;
import com.capgemini.cn.deemo.data.dto.DepartmentDto;
import com.capgemini.cn.deemo.data.dto.UserDto;
import com.capgemini.cn.deemo.service.BranchService;
import com.capgemini.cn.deemo.service.DepartmentService;
import com.capgemini.cn.deemo.service.UserService;
import com.capgemini.cn.deemo.utils.IdWorker;
import com.capgemini.cn.deemo.vo.base.RespBean;
import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.UserSearchVo;
import com.capgemini.cn.deemo.vo.response.UserResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hasaker
 * @create_date 2019-08-19 22:32
 * @description
 */
@Slf4j
@Api
@RestController
@RequestMapping("/user/employee")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private BranchService branchService;

    @ApiOperation("显示页面字段信息")
    @ResponseBody
    @PostMapping("list")
    public RespBean list(@RequestBody UserSearchVo userSearchVo){

        UserResponseVo userResponseVo = userService.listUser(userSearchVo);
        if (userResponseVo != null) {
            return RespBean.ok("查询成功",userResponseVo);
        }
        return RespBean.error("查询失败！");
    }

    //查询人员菜单栏
    @ApiOperation("查询人员菜单")
    @ResponseBody
    @GetMapping("/peopleTree")
    public RespBean peopleTree(){

        //1.查询所有的员工user
        List<UserDto> userDtos = userService.queryAll();

        //2.查询所有部门
        List<DepartmentDto> departmentDtos = departmentService.queryAll();

        //3.查询所有机构
        List<BranchDto> branchDtos = branchService.queryAllBranch();

        //4.讲员工放到部门中
        for (DepartmentDto departmentDto: departmentDtos){
            List<UserDto> list = new ArrayList<>();
            for (UserDto userDto:userDtos)
            {
                if (userDto.getDepartmentId() == departmentDto.getUserId())
                {
                    list.add(userDto);
                }
            }
            departmentDto.setUserDtoList(list);
        }
        //5.讲部门放到机构中
        for (BranchDto branchDto : branchDtos){
            List<DepartmentDto> list1 = new ArrayList<>();
            for (DepartmentDto departmentDto : departmentDtos)
            {
                if (departmentDto.getBranchId() == departmentDto.getBranchId())
                {
                    list1.add(departmentDto);
                }
            }
            branchDto.setDepartmentDtoList(list1);
        }
        return RespBean.ok("success",branchDtos);
    }

//    @ApiOperation("普通搜索以及高级搜索")
//    @PostMapping("/list")
//    public RespBean list(@RequestBody UserVo userVo){
//
//        //将bean转化成map,然后用map去查询
//        Map<String,Object> params = CovertBeanToMapUtils.convertBeanToMap(userVo);
//
//        //查询列表数据
//        Query query = new Query(params);
//
//        List<User> userList = userService.queryList(query);
//        int total = userService.queryTotal(query);
//
//        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());
//
//        return RespBean.ok(pageUtil);
//    }

    /**
     * 信息
     */
    @ApiOperation("根据id查询员工信息")
    @GetMapping("/info/{id}")
    public RespBean info(@PathVariable("id") Integer id){

        if(StringUtils.isBlank(id.toString())){
            return RespBean.error("id不能为空");
        }

        User user = userService.queryObject(id);

        return RespBean.ok("查询成功",user);
    }

    /**
     * 保存
     */
    @ApiOperation("添加员工信息")
    @PutMapping("/save")
    public RespBean save(@RequestBody User user, HttpServletRequest request){

//        //设置员工姓名
//        this.userService.queryObject(Integer.parseInt(user.getId().toString()));
//        user.setUsername(user.getUsername());

        user.setUserId(IdWorker.get().nextId());
        boolean result = userService.save(user);

        //判断是否成功
        if(result){
            return RespBean.ok("添加成功",user);
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
            return RespBean.ok("修改成功",user);
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
    @DeleteMapping(value = "/delete/{id}")
    public RespBean delete (@PathVariable Integer id, HttpServletRequest request){

        if (StringUtils.isBlank(id.toString())) {
            return RespBean.error("删除失败");
        }

        //假删除
        boolean result = userService.delete(id);

        //判断是否成功
        if(result){
            return RespBean.ok("删除成功");
        }
        else {
            return RespBean.error("删除失败");
        }
    }
}
