package com.capgemini.cn.deemo.vo.response;

import lombok.Data;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
@Data
public class DepartmentVo {

    /**
     * 部门Id
     */
    private Long departmentId;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 联系电话
     */
    private String connectPhone;

    /**
     * 联系电话（座机）
     */
    private String connectTelephone;

    /**
     * 法人id
     */
    private Long principalUserId;

    /**
     * 分部门id
     */
    private Long branchId;
}
