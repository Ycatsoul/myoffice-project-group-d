package com.capgemini.cn.deemo.data.domain;


import lombok.Data;

/**
 * @Description:员工信息实体类
 * @Classname :User
 * @author: GuoBingjun
 * @date:2019-8-21 15:27
 */
@Data
public class User {
    /**
    * 用户id
    */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 所属部门id
     */
    private Long departmentId;

    /**
     * 是否被屏蔽
     */
    private Boolean isBlocked;
}
