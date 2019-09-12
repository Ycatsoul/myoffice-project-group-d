package com.capgemini.cn.deemo.vo.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;


/**
 * @author fuduwen
 * @version 1.0
 * @date 2019/8/24 14:47
 */
@Data
@ApiModel(value = "Schedule更新请求Vo")
public class ScheduleEditVo {

    @ApiModelProperty(value = "日程ID")
    private Long scheduleId;

    @ApiModelProperty(value = "日程标题")
    private String scheduleTitle;

    @ApiModelProperty(value = "会议地址")
    private String scheduleAddress;

    @ApiModelProperty(value = "日程内容")
    private String scheduleContent;

    @ApiModelProperty(value = "日程开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date beginTime;

    @ApiModelProperty(value = "日程结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date endTime;

    @ApiModelProperty(value = "会议类型Id")
    private Long meetingTypeId;

    @ApiModelProperty(value = "创建时间",hidden = true)
    private Date createTime;

    @ApiModelProperty(value = "创建者Id",hidden = true)
    private Long createUserId;

    @ApiModelProperty(value = "预约人Id")
    private List<Long> userIds;

    @ApiModelProperty(value = "是否私有")
    private boolean isPrivate;
}
