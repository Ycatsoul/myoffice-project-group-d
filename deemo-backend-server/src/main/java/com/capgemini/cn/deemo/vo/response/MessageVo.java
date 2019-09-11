package com.capgemini.cn.deemo.vo.response;

import lombok.Data;

import java.sql.Date;
import java.util.List;

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

    private List<UserVo> recipients;

    private Long createUserId;

    private String createUserName;

    private Boolean isPublished;

    private Date publishTime;
}
