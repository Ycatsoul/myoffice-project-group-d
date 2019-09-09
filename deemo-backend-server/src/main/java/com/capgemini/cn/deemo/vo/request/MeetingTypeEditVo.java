package com.capgemini.cn.deemo.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author fuduwen
 * @version 1.0
 * @date 2019/8/24 15:07
 */
@Data
@ApiModel(value = "MeetingType更新请求Vo")
public class MeetingTypeEditVo {
    @ApiModelProperty(value = "会议类型ID")
    private Long meetingTypeId;

    @ApiModelProperty(value = "会议类型名称")
    private String meetingTypeName;
}
