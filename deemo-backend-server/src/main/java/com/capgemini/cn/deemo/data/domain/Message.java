package com.capgemini.cn.deemo.data.domain;


import com.sun.tools.javac.util.List;
import lombok.Data;

import java.sql.Date;


/**
 * @author Bowen Du
 * @since 2019-08-21 15:57
 */

@Data
public class Message {
    /**
     * 消息ID
     */
    private Long messageId;

    /**
     * 消息类型ID
     */
    private Long messageTypeId;

    /**
     * 消息标题
     */
    private String messageTitle;

    /**
     * 消息内容
     */
    private String messageContent;

    /**
     * 开始时间
     */
    private Date beginTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 发送用户ID
     */
    private Long createUserId;

    /**
     * 接收用户ID
     */
    private List<Long> recipientIds;

    /**
     * 发布状态
     */
    private Boolean isPublished;

    /**
     * 发布时间
     */
    private Date publishTime;
}
