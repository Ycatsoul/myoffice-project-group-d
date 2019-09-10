package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/8 10:49
 */
@Service
public interface MenuRoleMapper {

    List<Role> getRolesByMenuId(@Param("menuId") Long menuId);

    Integer addMenuRole(@Param("menuRoleId") Long menuRoleId,
                        @Param("menuId") Long menuId,
                        @Param("roleId") Long roleId);
}