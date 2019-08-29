package com.capgemini.cn.deemo.vo.request;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Bowen Du
 * @since 2019-08-22 17:55
 */

@Data
@ApiModel(value = "添加一条Message")
public class MessageAddVo {
    @ApiModelProperty(value = "消息ID")
    private Long messageId;
    @ApiModelProperty(value = "消息标题")
    private String messageTitle;
    @ApiModelProperty(value = "消息内容")
    private String messageContent;
    @ApiModelProperty(value = "开始时间")
    private Date beginTime;
    @ApiModelProperty(value = "结束时间")
    private Date endTime;
    @ApiModelProperty(value = "发送人ID")
    private Long sendUserId;
    @ApiModelProperty(value = "发布状态")
    private Long isPublished;
    @ApiModelProperty(value = "发布时间")
    private Date publishTime;
    @ApiModelProperty(value = "消息变更ID")
    private Long messageTransId;
    @ApiModelProperty(value = "接受者ID")
    private Long recipientId;
    @ApiModelProperty(value = "消息类型ID")
    private Long messageTypeId;
    @ApiModelProperty(value = "消息类型名称")
    private String messagetTypeName;
    @ApiModelProperty(value = "消息类型描述")
    private String messageTypeDesc;
}
