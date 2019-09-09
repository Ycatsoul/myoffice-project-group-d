package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/8 10:43
 */
@Service
public interface MenuMapper {

    Menu getMenuByMenuId(@Param("menuId") Long menuId);

    Menu getMenuByMenuName(@Param("menuName") String menuName);

    List<Menu> getAllMenus();
}
