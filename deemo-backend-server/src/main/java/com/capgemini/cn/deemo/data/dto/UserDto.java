package com.capgemini.cn.deemo.data.dto;

import lombok.Data;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
@Data
public class UserDto {

    //用户id
    private Long userId;
    //用户姓名
    private String username;
    //部门id
    private Long departmentId;

}
