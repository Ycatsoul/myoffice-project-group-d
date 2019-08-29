package com.capgemini.cn.deemo.data.domain;

import lombok.Data;

/**
 * @author Bowen Du
 * @since 2019-08-23 10:08
 */

@Data
public class MessageType {
    private Long id;
    private Long messageTypeId;
    private String messagetTypeName;  //消息类型名称
    private String messageTypeDesc;  //消息类型描述
}
