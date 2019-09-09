package com.capgemini.cn.deemo.vo.request;

import com.sun.tools.javac.util.List;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.sql.Date;

/**
 * @author hasaker
 * @since 2019/9/5 10:09
 */
@Data
@ApiModel
public class MessageEditVo {

    private Long messageId;

    private Long messageTypeId;

    private String messageTitle;

    private String messageContent;

    private Date beginTime;

    private Date endTime;

    private Long createUserId;

    private List<Long> recipientIds;
}
