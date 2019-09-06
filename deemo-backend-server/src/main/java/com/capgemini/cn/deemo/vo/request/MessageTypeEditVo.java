package com.capgemini.cn.deemo.vo.request;

import lombok.Data;

/**
 * @author hasaker
 * @since 2019/9/5 14:33
 */
@Data
public class MessageTypeEditVo {

    private Long messageTypeId;

    private String messageTypeName;

    private String messageTypeDesc;
}
