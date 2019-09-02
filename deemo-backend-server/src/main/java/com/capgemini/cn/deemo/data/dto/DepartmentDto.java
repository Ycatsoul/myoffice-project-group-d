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
public class DepartmentDto {

    /*
    * 用户id
    * */
    private Long userId;

    /*
    * 部门id
    * */
    private Long departmentId;

    /*
    * 部门名称
    * */
    private String departmentName;

    /*
    * 机构编号
    * */
    private Integer branchId;

    /*
    * 用户列表
    * */
    private List<UserDto> userDtoList;

}