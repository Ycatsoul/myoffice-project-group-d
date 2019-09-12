package com.capgemini.cn.deemo.service;

import com.capgemini.cn.deemo.data.domain.Role;
import com.capgemini.cn.deemo.vo.request.DeleteVo;
import com.capgemini.cn.deemo.vo.request.RoleEditVo;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/8 00:42
 */
public interface RoleService {

    Role getRoleByRoleId(Long roleId);

    Role getRoleByRoleName(String roleName);

    List<Role> getRoles();

    Integer addRole(RoleEditVo roleEditVo);

    Integer updateRole(RoleEditVo editVo);

    Integer deleteRole(DeleteVo deleteVo);
}
