package com.capgemini.cn.deemo.data.domain;


import lombok.Data;

/**
 * @author Bowen Du
 * @since 2019-08-23 10:02
 */

@Data
public class MessageTrans {
    /**
     * ID
     */
    private Long messageTransId;

    /**
     * 信息ID
     */
    private Long messageId;

    /**
     * 接收用户ID
     */
    private Long recipientId;

    /**
     * 阅读状态
     */
    private Long isRead;
}
