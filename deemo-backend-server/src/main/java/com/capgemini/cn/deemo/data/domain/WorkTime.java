package com.capgemini.cn.deemo.data.domain;

import lombok.Data;

import java.sql.Date;

/**
 * @author hasaker
 * @since 2019/9/4 15:02
 */
@Data
public class WorkTime {

    private Long workTimeId;

    private Date onDutyTime;

    private Date offDutyTime;
}
