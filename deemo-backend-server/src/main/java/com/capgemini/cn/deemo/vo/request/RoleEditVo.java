package com.capgemini.cn.deemo.vo.request;

import lombok.Data;

/**
 * @author hasaker
 * @since 2019/9/8 19:33
 */
@Data
public class RoleEditVo {

    private Long roleId;

    private String roleName;

    private String roleDesc;
}
