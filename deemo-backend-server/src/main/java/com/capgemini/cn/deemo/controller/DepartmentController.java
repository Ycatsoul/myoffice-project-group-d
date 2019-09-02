package com.capgemini.cn.deemo.controller;

import com.capgemini.cn.core.commons.BaseController;
import com.capgemini.cn.deemo.data.domain.Department;
import com.capgemini.cn.deemo.service.DepartmentService;
import com.capgemini.cn.deemo.utils.IdWorker;
import com.capgemini.cn.deemo.vo.base.RespBean;
import com.capgemini.cn.deemo.vo.request.DepartmentSearchVo;
import com.capgemini.cn.deemo.vo.response.DepartmentResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:部门管理controller
 * @Classname :DepartmentController
 * @author: GuoBingjun
 * @date:2019-8-26 14:10
 */
@Api
@RestController
@RequestMapping("/user/department")
public class DepartmentController extends BaseController {

    @Autowired
    private DepartmentService departmentService;

    @ApiOperation("显示页面字段信息")
    @ResponseBody
    @PostMapping("list")
    public RespBean list(@RequestBody DepartmentSearchVo departmentSearchVo){

        DepartmentResponseVo departmentResponseVo = departmentService.listDepart(departmentSearchVo);
        if (departmentResponseVo != null) {
            return RespBean.ok("查询成功",departmentResponseVo);
        }
        return RespBean.error("查询失败！");
    }
//        //将bean转化成map,然后用map去查询
//        Map<String,Object> params = CovertBeanToMapUtils.convertBeanToMap(departmentVo);
//
//        //查询列表数据
//        Query query = new Query(params);
//
//        List<Department> departments  = departmentService.queryList(query);
//        int total = departmentService.queryTotal(query);
//
//        PageUtils pageUtil = new PageUtils(departments, total, query.getLimit(), query.getPage());

//        return RespBean.ok(pageUtil);



    @ApiOperation("根据id查询部门信息")
    @GetMapping("/info/{id}")
    public RespBean info(@PathVariable("id") Integer id){

        if(StringUtils.isBlank(id.toString())){
            return RespBean.error("id不能为空");
        }

        Department department = departmentService.queryObject(id);

        return RespBean.ok("查询成功",department);
    }

    @ApiOperation("添加部门信息")
    @PutMapping("/save")
    public RespBean save(@RequestBody Department department, HttpServletRequest request){

        //设置部门名称
//        departmentService.queryObject(Integer.parseInt(department.getId().toString()));
//        department.setDepartmentName(department.getDepartmentName());
        department.setDepartmentId(IdWorker.get().nextId());

        boolean result = departmentService.save(department);

        //判断是否成功
        if(result){
            return RespBean.ok("添加成功",department);
        }
        else {
            return RespBean.error("添加失败");
        }
    }


    /**
     * 修改
     */
    @ApiOperation("修改部门信息")
    @PostMapping("/update")
    public RespBean update(@RequestBody Department department,HttpServletRequest request){

        boolean result = departmentService.update(department);

        //判断是否成功
        if(result){
            return RespBean.ok("修改成功",department);
        }
        else {
            return RespBean.error("修改失败");
        }
    }


    /**
     * 删除
     */
    @ApiOperation("删除员工信息")
    @DeleteMapping(value = "/delete/{id}")
    public RespBean delete (@PathVariable Integer id, HttpServletRequest request){

        if (StringUtils.isBlank(id.toString())) {
            return RespBean.error("删除失败");
        }

        boolean result = departmentService.delete(id);

        //判断是否成功
        if(result){
            return RespBean.ok("删除成功");
        }
        else {
            return RespBean.error("删除失败");
        }

    }
}
