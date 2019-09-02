package com.capgemini.cn.deemo.vo.request;

import lombok.Data;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
@Data
public class UserVo {
//    //员工编号
//    private Integer id;
    //用户id
    private Long userId;
    //用户姓名
    private String username;
    //用户密码
    private String password;
    //用户性别
    private String gender;
    //部门编号
    private Long departmentId;

    //用户头像
    private String avatar;
    //姓名
    private String name;
    //是否被屏蔽
    private String isBlocked;
}
