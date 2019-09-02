package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.Role;
import org.apache.ibatis.annotations.Param;

/**
 * @author hasaker
 * @since 2019/8/29 14:13
 */
public interface RoleMapper {
    Integer addRole(@Param("role") Role role);

    Integer deleteRole(@Param("roleId") Long roleId);
}