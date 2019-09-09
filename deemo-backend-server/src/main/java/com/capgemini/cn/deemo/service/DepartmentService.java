package com.capgemini.cn.deemo.service;

import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.DeleteVo;
import com.capgemini.cn.deemo.vo.request.DepartmentEditVo;
import com.capgemini.cn.deemo.vo.request.DepartmentSearchVo;
import com.capgemini.cn.deemo.vo.response.DepartmentVo;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
public interface DepartmentService {
    
    RespVos<DepartmentVo> getDepartment(Long departmentId);

    RespVos<DepartmentVo> listDepartments(DepartmentSearchVo departmentSearchVo);

    Integer addDepartment(DepartmentEditVo departmentEditVo);

    Integer updateDepartment(DepartmentEditVo departmentEditVo);

    Integer deleteDepartments(DeleteVo deleteVo);

}
