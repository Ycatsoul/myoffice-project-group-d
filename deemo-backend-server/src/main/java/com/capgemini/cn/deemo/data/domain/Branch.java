package com.capgemini.cn.deemo.data.domain;

import lombok.Data;

/**
 * @Description:机构管理实体类
 * @Classname :Branch
 * @author: Cola Guo
 * @date:2019-8-26 16:47
 */
@Data
public class Branch {

//    //id
//    private Integer id;
    //机构id
    private Long branchId;

    private String branchName;

    /**
     * 机构简称
     */
    private String branchShortName;

}
