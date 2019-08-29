package com.capgemini.cn.deemo.data.domain;


import lombok.Data;

import java.util.Date;

/**
 * @author Bowen Du
 * @since 2019-08-21 15:57
 */

@Data
public class Message {
    private Long id;
    private Long messageId;  //消息ID
    private String messageTitle;  //消息标题
    private String messageContent;  //消息内容
    private Date beginTime;  //开始时间
    private Date endTime;  //结束时间
    private String sendUserId;  //发送人
    private Long isPublished;  //发布状态
    private Date pulishTime;  //发布时间
}
