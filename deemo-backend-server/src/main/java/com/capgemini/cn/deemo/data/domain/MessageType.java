package com.capgemini.cn.deemo.data.domain;

import lombok.Data;

/**
 * @author Bowen Du
 * @since 2019-08-23 10:08
 */

@Data
public class MessageType {
    private Long messageTypeId;
    //消息类型名称
    private String messageTypeName;
    //消息类型描述
    private String messageTypeDesc;
}
