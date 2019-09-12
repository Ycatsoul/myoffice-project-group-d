package com.capgemini.cn.deemo.vo.response;

import lombok.Data;

/**
 * @author hasaker
 * @since 2019/9/5 21:23
 */
@Data
public class MessageTypeVo {

    /**
     * ID
     */
    private Long messageTypeId;

    /**
     * 文件类型名字
     */
    private String messageTypeName;

    /**
     * 文件类型描述
     */
    private String messageTypeDesc;
}
