package com.capgemini.cn.deemo.service.impl;

import com.capgemini.cn.deemo.data.domain.Department;
import com.capgemini.cn.deemo.data.dto.DepartmentDto;
import com.capgemini.cn.deemo.mapper.DepartmentMapper;
import com.capgemini.cn.deemo.service.DepartmentService;
import com.capgemini.cn.deemo.utils.ConvertUtils;
import com.capgemini.cn.deemo.vo.request.DepartmentSearchVo;
import com.capgemini.cn.deemo.vo.response.DepartmentVo;
import com.capgemini.cn.deemo.vo.response.DepartmentResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description:部门管理实现类
 * @Classname :DepartmentServiceImpl
 * @author: GuoBingjun
 * @date:2019-8-26 13:40
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public Department queryObject(Integer id) {
        return departmentMapper.queryObject(id);
    }

    @Override
    public List<Department> queryList(Map<String, Object> map) {
        return departmentMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return departmentMapper.queryTotal(map);
    }

    @Override
    public boolean save(Department department) {
        return departmentMapper.save(department) > 0;
    }

    @Override
    public boolean update(Department department) {
        return departmentMapper.update(department) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return departmentMapper.delete(id) > 0;
    }

    @Override
    public boolean deleteBatch(Integer[] ids) {
        return departmentMapper.deleteBatch(ids) > 0;
    }

    @Override
    public DepartmentResponseVo listDepart(DepartmentSearchVo departmentSearchVo) {
        DepartmentResponseVo departmentResponseVo = new DepartmentResponseVo();
        List<Department> departmentList = departmentMapper.listDepart(departmentSearchVo);
        List<DepartmentVo> departmentVos = new ArrayList<DepartmentVo>();
        for (Department department : departmentList) {
            DepartmentVo departmentVo = new DepartmentVo();
            departmentVos.add(ConvertUtils.convertDepartmentToVo(department));
        }
        departmentResponseVo.setDepartmentVos(departmentVos);
        departmentResponseVo.setListCount(departmentMapper.countDepart(departmentSearchVo));
        return departmentResponseVo;
    }

    @Override
    public List<DepartmentDto> queryAll() {
        return departmentMapper.queryAll();
    }


}
