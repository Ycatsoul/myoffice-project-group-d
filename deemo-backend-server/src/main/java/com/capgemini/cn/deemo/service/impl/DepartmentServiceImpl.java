package com.capgemini.cn.deemo.service.impl;

import com.capgemini.cn.deemo.data.domain.Branch;
import com.capgemini.cn.deemo.data.domain.Department;
import com.capgemini.cn.deemo.data.domain.User;
import com.capgemini.cn.deemo.mapper.BranchMapper;
import com.capgemini.cn.deemo.mapper.DepartmentMapper;
import com.capgemini.cn.deemo.mapper.UserMapper;
import com.capgemini.cn.deemo.service.DepartmentService;
import com.capgemini.cn.deemo.utils.IdWorker;
import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.DeleteVo;
import com.capgemini.cn.deemo.vo.request.DepartmentEditVo;
import com.capgemini.cn.deemo.vo.request.DepartmentSearchVo;
import com.capgemini.cn.deemo.vo.response.DepartmentVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:部门管理实现类
 * @Classname :DepartmentServiceImpl
 * @author: GuoBingjun
 * @date:2019-8-26 13:40
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentMapper departmentMapper;
    private final BranchMapper branchMapper;
    private final UserMapper userMapper;

    public DepartmentServiceImpl(DepartmentMapper departmentMapper, BranchMapper branchMapper, UserMapper userMapper) {
        this.departmentMapper = departmentMapper;
        this.branchMapper = branchMapper;
        this.userMapper = userMapper;
    }

    @Override
    public RespVos<DepartmentVo> getDepartment(Long departmentId) {
        Department department = departmentMapper.getDepartment(departmentId);

        if (department != null) {
            RespVos<DepartmentVo> respVos = new RespVos<>();
            respVos.setSize(1);
            respVos.setVos(new ArrayList<DepartmentVo>(){{
                add(convertToVo(department));
            }});

            return respVos;
        }

        return null;
    }

    @Override
    public RespVos<DepartmentVo> listDepartments(DepartmentSearchVo departmentSearchVo) {
        List<Department> departments = departmentMapper.listDepartments(departmentSearchVo);

        if (departments != null && departments.size() > 0) {
            RespVos<DepartmentVo> respVos = new RespVos<>();
            respVos.setSize(departmentMapper.countDepartments(departmentSearchVo));
            respVos.setVos(departments.stream().map(this::convertToVo).collect(Collectors.toList()));

            return respVos;
        }

        return null;
    }

    @Override
    public Integer addDepartment(DepartmentEditVo departmentEditVo) {
        departmentEditVo.setDepartmentId(IdWorker.get().nextId());

        return departmentMapper.insertDepartment(departmentEditVo);
    }

    @Override
    public Integer updateDepartment(DepartmentEditVo departmentEditVo) {
        return departmentMapper.updateDepartment(departmentEditVo);
    }

    @Override
    public Integer deleteDepartments(DeleteVo deleteVo) {
        return departmentMapper.deleteDepartments(deleteVo.getIds());
    }

    /**
     * 将Department装换为DepartmentVo
     * @param department entity
     * @return departmentVo
     */
    private DepartmentVo convertToVo(Department department) {
        DepartmentVo departmentVo = new DepartmentVo();

        Branch branch = branchMapper.getBranch(department.getBranchId());
        User user = userMapper.getUser(department.getPrincipalUserId());

        departmentVo.setDepartmentId(department.getDepartmentId());
        departmentVo.setDepartmentName(department.getDepartmentName());
        departmentVo.setConnectPhone(department.getConnectPhone());
        departmentVo.setConnectTelephone(department.getConnectTelephone());
        departmentVo.setPrincipalUserId(department.getPrincipalUserId());
        departmentVo.setPrincipalUserName(user == null ? null : user.getName());
        departmentVo.setBranchId(department.getBranchId());
        departmentVo.setBranchName(branch== null ? null : branch.getBranchName());
        departmentVo.setBranchShortName(branch== null ? null : branch.getBranchShortName());

        return departmentVo;
    }
}
