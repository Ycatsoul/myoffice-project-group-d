package com.capgemini.cn.deemo.controller;

import com.capgemini.cn.core.commons.BaseController;
import com.capgemini.cn.deemo.data.domain.Branch;
import com.capgemini.cn.deemo.service.BranchService;
import com.capgemini.cn.deemo.utils.CovertBeanToMapUtils;
import com.capgemini.cn.deemo.utils.IdWorker;
import com.capgemini.cn.deemo.utils.PageUtils;
import com.capgemini.cn.deemo.utils.Query;
import com.capgemini.cn.deemo.vo.base.RespBean;
import com.capgemini.cn.deemo.vo.request.BranchSearchVo;
import com.capgemini.cn.deemo.vo.request.BranchVo;
import com.capgemini.cn.deemo.vo.response.BranchResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
@Api
@RestController
@RequestMapping("/user/branch")
public class BranchController extends BaseController {

    @Autowired
    private BranchService branchService;

    @ApiOperation("显示页面字段信息")
    @PostMapping("list")
    public RespBean list(@RequestBody BranchSearchVo branchSearchVo){
        BranchResponseVo branchResponseVo = branchService.listBranch(branchSearchVo);
        if (branchResponseVo != null) {
            return RespBean.ok("查询成功",branchResponseVo);
        }
        return RespBean.error("查询失败！");

    }

    @ApiOperation("根据id查询机构信息")
    @PostMapping("/info/{id}")
    public RespBean info(@PathVariable("id") Integer id){

        if(StringUtils.isBlank(id.toString())){
            return RespBean.error("id不能为空");
        }

        Branch branch = branchService.queryObject(id);

        return RespBean.ok("成功",branch);
    }

    @ApiOperation("添加机构信息")
    @PostMapping("/save")
    public RespBean save(@RequestBody Branch branch, HttpServletRequest request){

//        //设置部门名称
//        branchService.queryObject(Integer.parseInt(branch.getId().toString()));
//        branch.setBranchName(branch.getBranchName());

        branch.setBranchId(IdWorker.get().nextId());

        boolean result = branchService.save(branch);


        //判断是否成功
        if(result){
            return RespBean.ok("添加成功",branch);
        }
        else {
            return RespBean.error("添加失败");
        }
    }


    /**
     * 修改
     */
    @ApiOperation("修改机构信息")
    @PostMapping("/update")
    public RespBean update(@RequestBody Branch branch,HttpServletRequest request){

        boolean result = branchService.update(branch);

        //判断是否成功
        if(result){
            return RespBean.ok("修改成功",branch);
        }
        else {
            return RespBean.error("修改失败");
        }
    }


    /**
     * 删除
     */
    @ApiOperation("删除机构信息")
    @PostMapping(value = "/delete/{id}")
    public RespBean delete (@PathVariable Integer id, HttpServletRequest request){

        if (StringUtils.isBlank(id.toString())) {
            return RespBean.error("删除失败");
        }

        boolean result = branchService.delete(id);

        //判断是否成功
        if(result){
            return RespBean.ok("删除成功");
        }
        else {
            return RespBean.error("删除失败");
        }

    }
}
