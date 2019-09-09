package com.capgemini.cn.deemo.data.domain;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;

/**
 * @author fuduwen
 * @version 1.0
 * @date 2019/8/21 15:59
 */
@Data
@Configuration
public class Schedule {
    private Long scheduleId;
    private String scheduleTitle;
    private String scheduleAddress;
    private String scheduleContent;
    private Date beginTime;
    private Date endTime;
    private Long meetingTypeId;
    private Date createTime;
    private Long createUserId;
    private boolean isPrivate;
}
