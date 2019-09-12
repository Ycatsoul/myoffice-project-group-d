package com.capgemini.cn.deemo.service;

import com.capgemini.cn.deemo.data.domain.Schedule;
import com.capgemini.cn.deemo.vo.request.ScheduleEditVo;
import com.capgemini.cn.deemo.vo.request.ScheduleListVo;
import com.capgemini.cn.deemo.vo.request.ScheduleSearchVo;
import com.capgemini.cn.deemo.vo.response.ScheduleVo;

import java.util.List;

/**
 * @author fuduwen
 * @version 1.0
 * @date 2019/8/23 20:09
 */
public interface ScheduleService {

    public List<Schedule> listSchedule(Long createUserId);

    public List<Schedule> listSchedules(ScheduleSearchVo scheduleSearchVo);

    public Integer updateSchedule(ScheduleEditVo scheduleEditVo);

    public boolean insertSchedule(ScheduleEditVo scheduleEditVo);

    public boolean deleteSchedule(Long scheduleId);
}
