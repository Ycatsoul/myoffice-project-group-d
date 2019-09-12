package com.capgemini.cn.deemo.vo.response;

import lombok.Data;
import java.sql.Date;
import org.springframework.context.annotation.Configuration;

/**
 * @author fuduwen
 * @version 1.0
 * @date 2019/9/10 11:34
 */
@Data
@Configuration
public class ScheduleVo {

    /**
     * 遍历schedule表信息的实体类
     */
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
