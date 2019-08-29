package com.capgemini.cn.deemo.data.domain;


import lombok.Data;

/**
 * @author Bowen Du
 * @since 2019-08-23 10:02
 */

@Data
public class MessageTrans {
    private Long id;
    private Long messageTransId;
    private Long recipientId; //接收人
    private Long isRead;  //阅读状态
    private Message message;
}
