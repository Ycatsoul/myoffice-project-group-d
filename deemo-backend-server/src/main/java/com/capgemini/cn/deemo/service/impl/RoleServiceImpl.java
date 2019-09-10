package com.capgemini.cn.deemo.service.impl;

import com.capgemini.cn.deemo.data.domain.Role;
import com.capgemini.cn.deemo.mapper.RoleMapper;
import com.capgemini.cn.deemo.service.RoleService;
import com.capgemini.cn.deemo.vo.request.RoleEditVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/8 00:42
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public Role getRoleByRoleId(Long roleId) {
        return roleMapper.getRoleByRoleId(roleId);
    }

    @Override
    public Role getRoleByRoleName(String roleName) {
        return roleMapper.getRoleByRoleName(roleName);
    }

    @Override
    public List<Role> getRoles() {
        return roleMapper.getRoles();
    }

    @Override
    public Integer addRole(RoleEditVo roleEditVo) {
        return roleMapper.addRole(roleEditVo);
    }

    @Override
    public Integer updateRole(RoleEditVo roleEditVo) {
        return roleMapper.updateRole(roleEditVo);
    }

    @Override
    public Integer deleteRole(Long roleId) {
        return roleMapper.deleteRole(roleId);
    }
}
