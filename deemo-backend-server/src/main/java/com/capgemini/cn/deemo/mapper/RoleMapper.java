package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.Role;
import com.capgemini.cn.deemo.vo.request.RoleEditVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/8/29 14:13
 */
@Service
public interface RoleMapper {

    Role getRoleByRoleId(@Param("roleId") Long roleId);

    Role getRoleByRoleName(@Param("roleName") String roleName);

    List<Role> getRoles();

    Integer addRole(@Param("roleEditVo") RoleEditVo roleEditVo);

    Integer updateRole(@Param("roleEditVo") RoleEditVo roleEditVo);

    Integer deleteRole(@Param("roleId") Long roleId);
}
