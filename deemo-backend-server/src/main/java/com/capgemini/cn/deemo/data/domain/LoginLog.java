package com.capgemini.cn.deemo.data.domain;

import lombok.Data;

import java.sql.Date;

/**
 * @author hasaker
 * @since 2019/9/4 14:56
 */
@Data
public class LoginLog {

    private Long loginId;

    private Boolean isSuccess;

    private Long loginUserId;

    private Date loginTime;

    private String loginIp;

    private String loginDesc;
}
