package com.capgemini.cn.deemo.vo.request;

import com.capgemini.cn.deemo.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author fuduwen
 * @version 1.0
 * @date 2019/8/24 14:48
 */
@Setter
@Getter
public class ScheduleListVo {

    @ApiModelProperty(value = "创建者Id")
    private Long createUserId;

    @ApiModelProperty(value = "日程开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;


}
