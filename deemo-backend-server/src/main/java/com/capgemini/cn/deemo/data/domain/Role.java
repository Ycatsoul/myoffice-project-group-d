package com.capgemini.cn.deemo.data.domain;

import lombok.Data;

/**
 * @author hasaker
 * @since 2019/8/29 10:38
 * 角色类
 */
@Data
public class Role {

    /**
     * Id
     */
    private Long roleId;

    /**
     * 名称
     */
    private String roleName;

    /**
     * 描述
     */
    private String roleDesc;
}
