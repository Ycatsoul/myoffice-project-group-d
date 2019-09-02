package com.capgemini.cn.deemo.data.domain;

import lombok.Data;

/**
 * @Description:部门管理实体类
 * @Classname :Department
 * @author: GuoBingjun
 * @date:2019-8-26 11:35
 */
@Data
public class Department {
//    //主键Id
//    private Integer id;
    //部门Id
    private Long departmentId;
    //部门名称

    private String departmentName;
    //联系电话
    private String connectPhone;
    //联系电话（座机）
    private String connectTelephone;
    //法人id
    private Long principalUserId;
    //分部门id
    private Long branchId;
}
