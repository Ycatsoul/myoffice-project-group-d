package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/8 00:47
 */
@Service
public interface UserRoleMapper {

    List<Role> getRolesByUserId(@Param("userId") Long userId);

    Integer addUserRole(@Param("userRoleId") Long userRoleId, @Param("userId") Long userId, @Param("roleId") Long roleId);

    Integer deleteUserRoleByUserRoleId(@Param("userRoleId") Long userRoleId);

    Integer deleteUserRoleByUserIdAndRoleId(@Param("userId") Long userId, @Param("roleId") Long roleId);
}
