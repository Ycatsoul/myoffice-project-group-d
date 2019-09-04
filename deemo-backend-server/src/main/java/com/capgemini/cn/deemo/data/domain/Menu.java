package com.capgemini.cn.deemo.data.domain;

import lombok.Data;

/**
 * @author hasaker
 * @since 2019/9/4 17:02
 */
@Data
public class Menu {

    private Long menuId;

    private String menuUrl;

    private String menuPath;

    private String menuComponent;

    private String menuName;

    private String menuIcon;

    private Integer menuOrder;

    private Long parentMenuId;

    private Boolean requireAuth;

    private Boolean disabled;
}
