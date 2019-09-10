package com.capgemini.cn.deemo.vo.response;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author hasaker
 * @since 2019/9/4 15:18
 */
@Data
public class LoginLogVo {

    private Long loginLogId;

    private Boolean isSuccess;

    private Long loginUserId;

    private String loginUserName;

    private String loginIp;

    private String loginDesc;

    private Timestamp loginTime;
}
