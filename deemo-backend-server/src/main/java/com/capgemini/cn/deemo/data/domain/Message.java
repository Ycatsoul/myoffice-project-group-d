package com.capgemini.cn.deemo.data.domain;


import lombok.Data;

import java.util.Date;

/**
 * @author Bowen Du
 * @since 2019-08-21 15:57
 */

@Data
public class Message {
    //消息ID
    private Long messageId;
    //消息标题
    private String messageTitle;
    //消息内容
    private String messageContent;
    //开始时间
    private Date beginTime;
    //结束时间
    private Date endTime;
    //发送人
    private String sendUserId;
    //发布状态
    private Long isPublished;
    //发布时间
    private Date publishTime;
}
