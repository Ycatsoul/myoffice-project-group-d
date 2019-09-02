package com.capgemini.cn.deemo.data.domain;


import lombok.Data;

import java.io.Serializable;

/**
 * @Description:员工信息实体类
 * @Classname :User
 * @author: GuoBingjun
 * @date:2019-8-21 15:27
 */
@Data
public class User implements Serializable {
//    //员工编号
//    private Integer id;
    /*
    * 用户id
    */
    private Long userId;

    /*
    * 用户姓名
    * */
    private String username;

    /*
    * 用户密码
    * */
    private String password;

    /*
    * 用户性别
    * */
    private String gender;

    /*
    * 部门id
    * */
    private Long departmentId;

    /*
    * 姓名
    * */
    private String name;

    /*
    * 头像
    * */
    private String avatar;

    /*
    * 是否被屏蔽
    * */
    private String isBlocked;
}
