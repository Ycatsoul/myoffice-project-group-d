package com.capgemini.cn.deemo.utils;

import com.capgemini.cn.deemo.data.domain.Branch;
import com.capgemini.cn.deemo.data.domain.Department;
import com.capgemini.cn.deemo.data.domain.User;
import com.capgemini.cn.deemo.vo.request.BranchVo;
import com.capgemini.cn.deemo.vo.request.DepartmentVo;
import com.capgemini.cn.deemo.vo.request.UserVo;


/**
 * @ClassName: ConvertUtils
 * @Description: Vo与Entity之间的互相转化工具类
 * @Author: Brady
 * @Date: 5/30/2019 10:56 AM
 */
public class ConvertUtils {

    /**
     * @Title: convertEmployeeVoToEntity
     * @Description: 将EmployeeVo中各属性的值对应赋给Employee实体类
     * @Param: [employee]
     * @Return: com.capgemini.jtp.vo.employee.response.EmployeeVo
     * @Throws:
     * @Author: Brady
     * @Date: 5/30/2019 10:59 AM
     */
//    public static Department convertEmployeeVoToEntity(DepartmentEditVo departmentEditVo) {
//        Department department = new Department();
//        department.setId(departmentEditVo.getId());
//        employee.setName(employeeEditVo.getName());
//        employee.setGender(employeeEditVo.getGender());
//        employee.setBirthday(employeeEditVo.getBirthday());
//        employee.setIdcard(employeeEditVo.getIdCard());
//        employee.setEmail(employeeEditVo.getEmail());
//        employee.setAddress(employeeEditVo.getAddress());
//        employee.setPhone(employeeEditVo.getPhone());
//        employee.setWorkId(employeeEditVo.getWorkId());
//        employee.setDegree(employeeEditVo.getDegree());
//        employee.setSchool(employeeEditVo.getSchool());
//        employee.setMajor(employeeEditVo.getMajor());
//        employee.setBeginDate(employeeEditVo.getBeginDate());
//        employee.setCst(employeeEditVo.getCst());
//        employee.setSubDepartment(employeeEditVo.getSubDepartment());
//        employee.setDepartment(employeeEditVo.getDepartment());
//        employee.setLegalEntity(employeeEditVo.getLegalEntity());
//        employee.setTrainStartDate(employeeEditVo.getTrainStartDate());
//        employee.setGraduateDate(employeeEditVo.getGraduateDate());
//        employee.setSkillGroup(employeeEditVo.getSkillGroup());
//        employee.setSkillSets(employeeEditVo.getSkillSets());
//        employee.setLeaveDate(employeeEditVo.getLeaveDate());
//        employee.setTerType(employeeEditVo.getTerType());
//        employee.setTerReason(employeeEditVo.getTerReason());
//        employee.setExitInterviewComments(employeeEditVo.getExitInterviewComments());
//        employee.setLeaveAccount(employeeEditVo.getLeaveAccount());
//        employee.setEverEmpid(employeeEditVo.getEverEmpId());
//        employee.setGrade(employeeEditVo.getGrade());
//        employee.setId(employeeEditVo.getId());
//
//        return employee;
//    }


    /**
     * @Description: 将EmployeeVo中各属性的值对应赋给Employee实体类
     * @Classname : ConvertUtils
     * @author: Jason Jin
     * @date: 2019/6/8 10:20 AM
     */
//    public static EmployeePrize convertEmpPrizeVoToEntity(EmpPrizeEditVo empPrizeEditVo) {
//        EmployeePrize employeePrize = new EmployeePrize();
//        employeePrize.setId(empPrizeEditVo.getId());
//        employeePrize.setEmpId(empPrizeEditVo.getEmpId());
//        employeePrize.setPrizeDetail(empPrizeEditVo.getPrizeDetail());
//        employeePrize.setPrizeName(empPrizeEditVo.getPrizeName());
//        employeePrize.setPrizeGetTime(empPrizeEditVo.getPrizeGetTime());
//        return employeePrize;
//    }

    /**
     * @Description: 将EmployeePrize中各属性的值对应赋给EmpPrizeVo实体类
     * @Classname : ConvertUtils
     * @author: Jason Jin
     * @date: 2019/6/7 11:15 PM
     */
//    public static EmpPrizeVo convertEmpPrizeEntityToVo(EmployeePrize employeePrize) {
//        EmpPrizeVo empPrizeVo = new EmpPrizeVo();
//        empPrizeVo.setId(employeePrize.getId());
//        empPrizeVo.setPrizeName(employeePrize.getPrizeName());
//        empPrizeVo.setPrizeDetail(employeePrize.getPrizeDetail());
//        empPrizeVo.setEmpId(employeePrize.getEmpId());
//        empPrizeVo.setCreateTime(employeePrize.getCreateTime());
//        empPrizeVo.setCreateUser(employeePrize.getCreateUser());
//        empPrizeVo.setUpdateTime(employeePrize.getUpdateTime());
//        empPrizeVo.setUpdateUser(employeePrize.getCreateUser());
//        empPrizeVo.setPrizeGetTime(employeePrize.getPrizeGetTime());
//        return empPrizeVo;
//    }

    /**
     * @Title: convertEmployeeEntityToVo
     * @Description: 将Employee实体类中各属性的值对应赋给EmployeeVo
     * @Param: [employeeVo]
     * @Return: com.capgemini.jtp.entity.Employee
     * @Throws:
     * @Author: Brady
     * @Date: 5/30/2019 11:04 AM
     */
    public static DepartmentVo convertDepartmentToVo(Department department) {
        DepartmentVo departmentVo = new DepartmentVo();
//        departmentVo.setId(department.getId());
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
//        branchVo.setId(branch.getId());
//        branchVo.setBranchId(branch.getBranchId());
        branchVo.setBranchName(branch.getBranchName());
        branchVo.setBranchShortName(branch.getBranchShortName());

        return branchVo;
    }

    public static UserVo convertUserToVo(User user){
        UserVo userVo = new UserVo();
//        userVo.setId(user.getId());
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

    /**
     * @Description: 将Employee实体类中各属性的值对应赋给EmpListVo
     * @Classname : ConvertUtils
     * @author: Jason Jin
     * @date: 2019/6/23 12:01 PM
     */
//    public static EmpListVo convertEmployeeEntityToListVo(Employee employee) {
//        EmpListVo empListVo = new EmpListVo();
//        empListVo.setId(employee.getId());
//        empListVo.setName(employee.getName());
//        return empListVo;
//    }

}
