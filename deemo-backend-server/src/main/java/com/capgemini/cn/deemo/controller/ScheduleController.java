package com.capgemini.cn.deemo.controller;

import com.capgemini.cn.core.commons.BaseController;
import com.capgemini.cn.deemo.data.domain.Schedule;
import com.capgemini.cn.deemo.service.ScheduleService;
import com.capgemini.cn.deemo.vo.base.RespBean;
import com.capgemini.cn.deemo.vo.request.ScheduleEditVo;
import com.capgemini.cn.deemo.vo.request.ScheduleListVo;
import com.capgemini.cn.deemo.vo.request.ScheduleSearchVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author fuduwen
 * @version 1.0
 * @date 2019/8/23 12:32
 */
@Slf4j
@Api(value = "日程")
@RestController
@RequestMapping("/schedule")
public class ScheduleController extends BaseController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @ApiOperation(value = "获取schedule列表，将相应的信息遍历到日历上")
    @PostMapping("/list")
    public RespBean getScheduleList(@RequestBody ScheduleListVo scheduleListVo){

        List<Schedule> schedule = scheduleService.listSchedule(scheduleListVo);

        /**
         * 判断是否获取成功
         */
        if(schedule != null){
            return RespBean.ok("获取成功",schedule);
        }else{
            return RespBean.error("获取失败");
        }
    }

    @ApiOperation(value = "新建一条schedule")
    @PostMapping("/set")
    public RespBean postSchedule(@RequestBody ScheduleEditVo scheduleEditVo){

        boolean res = scheduleService.insertSchedule(scheduleEditVo);

        /**
         * 判断是否增加成功
         */
        if(res){
            return RespBean.ok("新增成功");
        }else{
            return RespBean.error("新增失败");
        }
    }

    @ApiOperation(value = "根据beginTime,createUserId获取相应的schedule信息")
    @PostMapping("/get")
    public RespBean getSchedule(@RequestBody ScheduleSearchVo scheduleSearchVo){

        List<Schedule> schedule = scheduleService.listSchedules(scheduleSearchVo);
        /**
         * 判断是否获取成功
         */
        if(schedule != null){
            return RespBean.ok("获取成功",schedule);
        }else{
            return RespBean.error("获取失败");
        }
    }

    @ApiOperation("更新Schedule")
    @PostMapping("/update")
    public RespBean updateSchedule(@RequestBody ScheduleEditVo schedule) {

        Integer res = scheduleService.updateSchedule(schedule);

        /**
         * 判断是否更新成功
         */
        if(res>0){
            return RespBean.ok("更新成功");
        }else{
            return RespBean.error("更新失败");
        }
    }

    @ApiOperation(value = "根据scheduleId删除一条Schedule")
    @DeleteMapping("/{scheduleId}")
    public RespBean deleteSchedule(@PathVariable Long scheduleId) {
        boolean res1 = scheduleService.deleteSchedule(scheduleId);

        /**
         * 判断是否删除成功
         */
        if(res1){
            return RespBean.ok("删除成功");
        }else{
            return RespBean.error("删除失败");
        }
    }
}
