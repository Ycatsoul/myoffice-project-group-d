package com.capgemini.cn.deemo.vo.response;

import lombok.Data;

import java.sql.Date;

/**
 * @author hasaker
 * @since 2019/9/5 10:14
 */
@Data
public class MessageVo {

    private Long messageId;

    private Long messageTypeId;

    private String messageTypeName;

    private String messageTitle;

    private String messageContent;

    private Date beginTime;

    private Date endTime;

    private Long createUserId;

    private String createUserName;

    private Long branchId;

    private Long departmentId;

    private Long recipientId;

    private Boolean isPublished;

    private Date publishTime;
}
