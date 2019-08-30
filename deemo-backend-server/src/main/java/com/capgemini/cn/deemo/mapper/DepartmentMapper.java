package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.Department;
import com.capgemini.cn.deemo.data.dto.DepartmentDto;
import com.capgemini.cn.deemo.vo.request.DepartmentSearchVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:部门管理mapper类
 * @Classname :DepartmentMapper
 * @author: GuoBingjun
 * @date:2019-8-26 13:30
 */
public interface DepartmentMapper extends BaseMapper<Department> {
    List<Department> listDepart(@Param("departmentSearch") DepartmentSearchVo departmentSearchVo);
    Long countDepart(@Param("departmentSearch") DepartmentSearchVo departmentSearchVo);
    List<DepartmentDto> queryAll();
}
