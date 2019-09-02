package com.capgemini.cn.deemo.utils;

import com.capgemini.cn.deemo.data.domain.Branch;
import com.capgemini.cn.deemo.data.domain.Department;
import com.capgemini.cn.deemo.data.domain.User;
import com.capgemini.cn.deemo.vo.response.BranchVo;
import com.capgemini.cn.deemo.vo.response.DepartmentVo;
import com.capgemini.cn.deemo.vo.response.UserVo;


/**
 * @ClassName: ConvertUtils
 * @Description: Vo与Entity之间的互相转化工具类
 * @Author: Brady
 * @Date: 5/30/2019 10:56 AM
 */
public class ConvertUtils {

    public static DepartmentVo convertDepartmentToVo(Department department) {
        DepartmentVo departmentVo = new DepartmentVo();

        departmentVo.setDepartmentId(department.getDepartmentId());
        departmentVo.setDepartmentName(department.getDepartmentName());
        departmentVo.setConnectPhone(department.getConnectPhone());
        departmentVo.setConnectTelephone(department.getConnectTelephone());
        departmentVo.setPrincipalUserId(department.getPrincipalUserId());
        departmentVo.setBranchId(department.getBranchId());

        return departmentVo;
    }

    public static BranchVo convertBranchToVo(Branch branch) {
        BranchVo branchVo = new BranchVo();

        branchVo.setBranchId(branch.getBranchId());
        branchVo.setBranchName(branch.getBranchName());
        branchVo.setBranchShortName(branch.getBranchShortName());

        return branchVo;
    }

    public static UserVo convertUserToVo(User user){
        UserVo userVo = new UserVo();

        userVo.setUserId(user.getUserId());
        userVo.setUsername(user.getUsername());
        userVo.setPassword(user.getPassword());
        userVo.setDepartmentId(user.getDepartmentId());
        userVo.setGender(user.getGender());
        userVo.setIsBlocked(user.getIsBlocked());
        userVo.setName(user.getName());
        userVo.setAvatar(user.getAvatar());

        return userVo;
    }
}
