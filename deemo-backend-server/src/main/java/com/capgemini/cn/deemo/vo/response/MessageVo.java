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

    /**
     * ID
     */
    private Long messageId;

    /**
     * 消息类型ID
     */
    private Long messageTypeId;

    /**
     * 消息类型
     */
    private String messageTypeName;

    /**
     * 标题
     */
    private String messageTitle;

    /**
     * 内容
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
     * 发送对象集合
     */
    private List<UserVo> recipients;

    /**
     * 消息创建用户ID
     */
    private Long createUserId;

    /**
     * 消息创建用户姓名
     */
    private String createUserName;

    /**
     * 消息是否已发布
     */
    private Boolean isPublished;

    /**
     * 消息发布时间
     */
    private Date publishTime;
}
