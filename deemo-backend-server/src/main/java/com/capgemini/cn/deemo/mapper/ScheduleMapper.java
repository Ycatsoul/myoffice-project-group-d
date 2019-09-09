package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.Schedule;
import com.capgemini.cn.deemo.vo.request.ScheduleEditVo;
import com.capgemini.cn.deemo.vo.request.ScheduleListVo;
import com.capgemini.cn.deemo.vo.request.ScheduleSearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fuduwen
 * @version 1.0
 * @date 2019/8/23 14:20
 */
@Service
public interface ScheduleMapper {

    List<Schedule> listSchedules(@Param("scheduleSearchVo") ScheduleSearchVo scheduleSearchVo);

    List<Schedule> listSchedule(@Param("scheduleListVo") ScheduleListVo scheduleListVo);

    Integer insertSchedule(@Param("scheduleEditVo") ScheduleEditVo scheduleEditVo);

    Integer updateSchedule(@Param("scheduleEditVo") ScheduleEditVo scheduleEditVo);

    Integer deleteSchedule(@Param("scheduleId") Long scheduleId);
}
