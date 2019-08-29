package com.capgemini.cn.deemo.service;

import com.capgemini.cn.deemo.data.domain.Department;
import com.capgemini.cn.deemo.data.dto.DepartmentDto;
import com.capgemini.cn.deemo.vo.request.DepartmentSearchVo;
import com.capgemini.cn.deemo.vo.response.DepartmentResponseVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
@Service
public interface DepartmentService {
    Department queryObject(Integer id);

    List<Department> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    boolean save(Department department);

    boolean update(Department department);

    boolean delete(Integer id);

    boolean deleteBatch(Integer[] ids);

    DepartmentResponseVo listDepart(DepartmentSearchVo departmentSearchVo);

    List<DepartmentDto> queryAll();

}
