package com.capgemini.cn.deemo.vo.request;

import lombok.Data;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
@Data
public class BranchSearchVo {
//    //主键Id
//    private Integer id;
    //部门Id
    private Long branchId;
    //部门名称
    private String branchName;
    //联系电话
    private String branchShortName;

    private int page;
    //一页几条数据
    private int limit;
}
