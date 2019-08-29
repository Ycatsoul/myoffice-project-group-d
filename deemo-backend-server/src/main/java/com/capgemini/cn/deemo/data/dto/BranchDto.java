package com.capgemini.cn.deemo.data.dto;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
@Data
public class BranchDto {

    //机构id
    private Long branchId;
    //机构名称
    private String branchName;

    private String branchShortName;

    private List<DepartmentDto> departmentDtoList;
}
