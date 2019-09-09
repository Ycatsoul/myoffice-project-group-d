package com.capgemini.cn.deemo.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Date;

/**
 * @author fuduwen
 * @version 1.0
 * @date 2019/8/24 14:48
 */
@Data
@ApiModel
public class ScheduleSearchVo {

    @ApiModelProperty(value = "departmentId")
    private Long departmentId;

    @ApiModelProperty(value = "日程开始时间")
    private Date beginTime;


}
