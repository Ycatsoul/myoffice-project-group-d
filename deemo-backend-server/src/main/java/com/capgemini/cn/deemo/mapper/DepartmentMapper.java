package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.Department;
import com.capgemini.cn.deemo.vo.request.DepartmentEditVo;
import com.capgemini.cn.deemo.vo.request.DepartmentSearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:部门管理mapper类
 * @Classname :DepartmentMapper
 * @author: GuoBingjun
 * @date:2019-8-26 13:30
 */
@Service
public interface DepartmentMapper {
    Department getDepartment(@Param("departmentId") Long departmentId);

    List<Department> listDepartments(@Param("departmentSearchVo") DepartmentSearchVo departmentSearchVo);

    Integer countDepartments(@Param("departmentSearchVo") DepartmentSearchVo departmentSearchVo);

    Integer insertDepartment(@Param("departmentEditVo") DepartmentEditVo departmentEditVo);

    Integer updateDepartment(@Param("departmentEditVo") DepartmentEditVo departmentEditVo);

    Integer deleteDepartments(@Param("departmentIds") List<Long> departmentIds);
}
