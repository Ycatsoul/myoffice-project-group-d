package com.capgemini.cn.deemo.vo.response;

import lombok.Data;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
@Data
public class BranchVo {

    /**
     * 机构id
     */
    private Long branchId;

    /**
     * 机构名称
     */
    private String branchName;

    /**
     * 机构简称
     */
    private String branchShortName;

}
